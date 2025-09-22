package co.com.crediya.usecase.application;
import co.com.crediya.model.application.Application;
import co.com.crediya.model.application.gateways.AlertSenderGateway;
import co.com.crediya.model.application.gateways.ApplicationRepository;
import co.com.crediya.model.application.gateways.IRestConsumerClient;
import co.com.crediya.model.dto.CapacityRequestMessage;
import co.com.crediya.model.loantype.gateways.LoanTypeRepository;
import co.com.crediya.model.dto.StateChangeMessage;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RequiredArgsConstructor
public class LoanApplicationUseCase {
    private final ApplicationRepository applicationRepository;
    private final LoanTypeRepository loanTypeRepository;
    private final IRestConsumerClient iRestConsumerUserClient;

    private final AlertSenderGateway alertSenderGateway;
    public Mono<Application> submitApplication(Application application) {
        return iRestConsumerUserClient.getUserByDocument(application.getNumDocumentUser())
                .flatMap(userResponse -> {
                    if (Boolean.TRUE.equals(userResponse.getExists())) {
                        // Traer valores del microservicio y setear en dominio
                        application.setNumDocumentUser(userResponse.getNumDocumentUser());
                        application.setEmailApp(userResponse.getEmailApp());

                        return internalManagement(application);
                    } else {
                        return Mono.error(new IllegalArgumentException(
                                "El usuario no existe, no es posible realizar la solicitud del préstamo"
                        ));
                    }
                });
    }

    private Mono<Application> internalManagement(Application application) {
        return validateLoanType(application.getIdTip(), application.getAmountApp())
                .then(applicationRepository.save(
                        application.toBuilder()
                                .idState(1) // Estado inicial: ID de "Pendiente de revisión"
                                .build()
                ));
    }

    private Mono<Void> validateLoanType(Integer id, Double amount) {
        return loanTypeRepository.findbyid(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El tipo de préstamo no existe")))
                .flatMap(loanType -> {
                    if (amount < loanType.getMiniAmount() || amount > loanType.getMaxAmount()) {
                        return Mono.error(new IllegalArgumentException(
                                String.format("El monto %.2f está fuera de los límites [%s - %s]",
                                        amount, loanType.getMiniAmount(), loanType.getMaxAmount())
                        ));
                    }
                    return Mono.empty();
                });
    }
    public Mono<Application> updateApplicationState(Integer idApplication, Integer idState) {
        return applicationRepository.findById(idApplication)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Solicitud no encontrada")))
                .flatMap(existing -> {
                    Application updated = existing.toBuilder()
                            .idState(idState)
                            .build();

                    return applicationRepository.save(updated)
                            .flatMap(saved -> {
                                StateChangeMessage message = StateChangeMessage.builder()
                                        .idApplication(saved.getIdApplication())
                                        .newState(saved.getIdState())
                                        .emailUser(saved.getEmailApp())
                                        .build();
                                return alertSenderGateway.sendStateChange(message)
                                        .thenReturn(saved);
                            });
                });
    }
    public Mono<List<String>> calculateCapacity(CapacityRequestMessage message) {
        return loanTypeRepository.findbyid(message.getIdTip())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El tipo de préstamo no existe")))
                .zipWith(iRestConsumerUserClient.validateUserExistence(message.getNumDocumentUser()))
                .flatMap(tuple -> {
                    var loanType = tuple.getT1();
                    var userInfo = tuple.getT2();

                    // Enriquecemos el mensaje con baseSalary y rateInterest
                    CapacityRequestMessage enrichedMessage = message.toBuilder()
                            .rateInterest(loanType.getRateInterest())
                            .baseSalary(userInfo.getBaseSalary().doubleValue())
                            .build();

                    if (enrichedMessage.getRateInterest() != null) {
                        return alertSenderGateway.sendCapacityRequest(enrichedMessage)
                                .then(Mono.just("Solicitud enviada a validacion automatica"));
                    } else {
                        return Mono.just("El tipo de préstamo requiere validacion manual");
                    }
                })
                .flux()
                .collectList();
    }
}