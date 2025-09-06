package co.com.crediya.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class RestConsumerConfig {
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8081/api/v1") // URL de microservicio AUTENTICACION
                .build();
    }
}
