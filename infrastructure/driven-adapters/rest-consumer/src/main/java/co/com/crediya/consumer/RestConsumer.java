package co.com.crediya.consumer;

import co.com.crediya.model.application.gateways.IRestConsumerClient;
import co.com.crediya.model.dto.UserVerificationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RestConsumer implements IRestConsumerClient {

    private final WebClient webClient;

    @Override
    public Mono<UserVerificationResult> getUserByDocument(String document) {
        return webClient.get()
                .uri("/users/document/{document}", document)
                .retrieve()
                .onStatus(
                        status -> status.isError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new RuntimeException("Error al consultar usuario: " + body)))
                )
                .bodyToMono(ExistsUserResponse.class)
                .map(resp -> UserVerificationResult.builder()
                        .exists(resp.getExists())
                        .numDocumentUser(resp.getNumDocumentUser())
                        .emailApp(resp.getEmailApp())
                        .build()
                );
    }
}