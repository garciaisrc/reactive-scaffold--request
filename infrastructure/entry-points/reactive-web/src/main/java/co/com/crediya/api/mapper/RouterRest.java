package co.com.crediya.api.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RouterRest {

    private final Handler handler;         // Para crear solicitudes
    private final ListarHandler listarHandler; // Para listar solicitudes

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                // Crear nueva solicitud
                .POST("/api/v1/solicitud", handler::createApplication)
                // Listar solicitudes con paginación
                .GET("/api/v1/solicitud", listarHandler::listarSolicitudes)
                .build();
    }
}