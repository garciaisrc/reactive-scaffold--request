package co.com.crediya.api.mapper;

import co.com.crediya.api.dto.SolicitudResponseDTO;
import co.com.crediya.model.dto.UserListResult;
import co.com.crediya.usecase.application.ListerAppUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.PageRequest;


@Slf4j
@Component
@RequiredArgsConstructor
public class ListarHandler {

    private final ListerAppUseCase listerAppUseCase;

    public Mono<ServerResponse> listarSolicitudes(ServerRequest request) {
        int page = Integer.parseInt(request.queryParam("page").orElse("0"));
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));
        String state = request.queryParam("state").orElse("").trim();

        log.info("Recibiendo petición listarSolicitudes con page={}, size={}",page,size);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        listerAppUseCase.findSolicitudesPendientes(page,size,state)
                                .map(this::toResponseDTO),
                        SolicitudResponseDTO.class
                )
                .onErrorResume(e -> {
                    log.error("Error al listar solicitudes: {}", e.getMessage(),e);
                    return ServerResponse.status(500)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue("Error al listar solicitudes: " + e.getMessage());
                });
    }

    private SolicitudResponseDTO toResponseDTO(UserListResult result) {
        return SolicitudResponseDTO.builder()
                .firstName(result.getFirstName())
                .baseSalary(result.getBaseSalary())
                .idApplication(result.getIdApplication())
                .numDocumentUser(result.getNumDocumentUser())
                .emailApp(result.getEmailApp())
                .amountApp(result.getAmountApp())
                .termApp(result.getTermApp())
                .nameLoan(result.getNameLoan())
                .rateInterest(result.getRateInterest())
                .nameState(result.getNameState())
                .montoMensual(result.getMontoMensual())
                .build();
    }
}
