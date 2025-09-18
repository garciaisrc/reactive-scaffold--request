package co.com.crediya.api.mapper;

import co.com.crediya.api.dto.ApplicationRequestDTO;
import co.com.crediya.api.dto.ApplicationResponseDTO;
import co.com.crediya.api.dto.UpdateStateRequestDTO;
import co.com.crediya.model.application.Application;
import co.com.crediya.usecase.application.LoanApplicationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;


@Component
@RequiredArgsConstructor
public class Handler {

    private final LoanApplicationUseCase loanApplicationUseCase;

    // Crear nueva solicitud (POST)
    public Mono<ServerResponse> createApplication(ServerRequest request) {
        return request.bodyToMono(ApplicationRequestDTO.class)
                .flatMap(dto -> {
                    Application application = Application.builder()
                            .numDocumentUser(dto.getNumDocumentUser())
                            .amountApp(dto.getAmountApp().doubleValue())
                            .termApp(dto.getTermApp())
                            .emailApp(dto.getEmailApp())
                            .idTip(dto.getIdTip())
                            .build();

                    return loanApplicationUseCase.submitApplication(application);
                })
                .flatMap(this::toResponse)
                .onErrorResume(this::errorResponse);
    }

    // Actualizar estado de una solicitud (PUT)
    public Mono<ServerResponse> updateApplication(ServerRequest request) {
        return request.bodyToMono(UpdateStateRequestDTO.class)
                .doOnNext(dto -> System.out.println("📥 Recibido DTO: " + dto))
                .onErrorResume(e -> {
                    System.out.println("Error deserializando: " + e.getMessage());
                    return Mono.error(new RuntimeException("El body no cumple con el DTO"));
                })
                .flatMap(dto -> loanApplicationUseCase.updateApplicationState(dto.getIdApplication(), dto.getIdState()))
                .flatMap(this::toResponse)
                .onErrorResume(this::errorResponse);
    }

    // Mapear dominio -> ResponseDTO
    private Mono<ServerResponse> toResponse(Application app) {
        ApplicationResponseDTO responseDTO = ApplicationResponseDTO.builder()
                .idApplication(app.getIdApplication())
                .numDocumentUser(app.getNumDocumentUser())
                .emailApp(app.getEmailApp())
                .amountApp(BigDecimal.valueOf(app.getAmountApp()))
                .termApp(app.getTermApp())
                .loanTypeName(app.getIdTip())
                .state(app.getIdState())
                .build();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(responseDTO);
    }

    // Manejo de errores
    private Mono<ServerResponse> errorResponse(Throwable e) {
        return ServerResponse.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("Error: " + e.getMessage());
    }
}