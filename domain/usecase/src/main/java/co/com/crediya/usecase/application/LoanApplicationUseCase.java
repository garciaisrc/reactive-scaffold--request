package co.com.crediya.usecase.application;
import co.com.crediya.model.application.Application;
import co.com.crediya.model.application.gateways.ApplicationRepository;
import co.com.crediya.model.application.gateways.IRestConsumerClient;
import co.com.crediya.model.loantype.gateways.LoanTypeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class LoanApplicationUseCase {
    private final ApplicationRepository applicationRepository;
    private final LoanTypeRepository loanTypeRepository;
    private final IRestConsumerClient iRestConsumerUserClient;
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
        return loanTypeRepository.findById(id)
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
}