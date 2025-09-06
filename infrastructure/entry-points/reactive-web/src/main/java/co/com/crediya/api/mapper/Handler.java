package co.com.crediya.api.mapper;

import co.com.crediya.api.dto.ApplicationRequestDTO;
import co.com.crediya.api.dto.ApplicationResponseDTO;
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
    public Mono<ServerResponse> createApplication(ServerRequest request) {
        return request.bodyToMono(ApplicationRequestDTO.class)
                .flatMap(dto -> {
                    // DTO -> Dominio (incluye el numDocumentUser que es clave)
                    Application application = Application.builder()
                            .numDocumentUser(dto.getNumDocumentUser()) // se usa para verificar existencia
                            .amountApp(dto.getAmountApp().doubleValue())
                            .termApp(dto.getTermApp())
                            .emailApp(dto.getEmailApp()) // opcional, será sobrescrito si viene del microservicio
                            .idTip(dto.getIdTip())
                            .build();

                    return loanApplicationUseCase.submitApplication(application);
                })
                .flatMap(app -> {
                    // Dominio -> ResponseDTO
                    ApplicationResponseDTO responseDTO = ApplicationResponseDTO.builder()
                            .idApplication(app.getIdApplication())
                            .numDocumentUser(app.getNumDocumentUser()) // ahora viene del microservicio
                            .emailApp(app.getEmailApp())              // también viene del microservicio
                            .amountApp(BigDecimal.valueOf(app.getAmountApp()))
                            .termApp(app.getTermApp())
                            .loanTypeName(app.getIdTip())
                            .state(app.getIdState())
                            .build();

                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(responseDTO);
                })
                .onErrorResume(e ->
                        ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue("Error: " + e.getMessage())
                );
    }
}