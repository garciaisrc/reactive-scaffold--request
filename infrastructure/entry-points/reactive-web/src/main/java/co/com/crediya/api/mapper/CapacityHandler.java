package co.com.crediya.api.mapper;

import co.com.crediya.api.dto.ApplicationRequestDTO;
import co.com.crediya.api.dto.CapacityRequestDTO;
import co.com.crediya.model.application.gateways.ApplicationRepository;
import co.com.crediya.model.application.gateways.IRestConsumerClient;
import co.com.crediya.model.dto.CapacityRequestMessage;
import co.com.crediya.usecase.application.LoanApplicationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CapacityHandler {

    private final LoanApplicationUseCase loanApplicationUseCase;
    private final ApplicationRepository applicationRepository;
    private final IRestConsumerClient restConsumer;

    public Mono<ServerResponse> calculateCapacity(ServerRequest request) {
        return request.bodyToMono(CapacityRequestDTO.class)
                .flatMapMany(dto ->
                        restConsumer.validateUserExistence(dto.getNumDocumentUser())
                                .flatMapMany(userInfo -> {
                                    if (userInfo.getBaseSalary() == null) {
                                        return Flux.error(new IllegalArgumentException("El usuario no tiene salario base registrado"));
                                    }
                                    // Traer solicitudes por documento
                                    return applicationRepository.findByNumDocumentUser(dto.getNumDocumentUser())
                                            .switchIfEmpty(Mono.error(new IllegalArgumentException("Solicitud no encontrada")))
                                            .map(app -> CapacityRequestMessage.builder()
                                                    .idApplication(app.getIdApplication())
                                                    .idTip(app.getIdTip())
                                                    .amount(app.getAmountApp())
                                                    .term(app.getTermApp())
                                                    .idState(app.getIdState())
                                                    .baseSalary(userInfo.getBaseSalary().doubleValue())
                                                    .numDocumentUser(app.getNumDocumentUser())
                                                    .build()
                                            );
                                })
                )
                // Flux<CapacityRequestMessage>
                .flatMap(msg -> loanApplicationUseCase.calculateCapacity(msg))
                .collectList()
                .flatMap(results -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Collections.singletonMap("messages", results))
                );
    }
}