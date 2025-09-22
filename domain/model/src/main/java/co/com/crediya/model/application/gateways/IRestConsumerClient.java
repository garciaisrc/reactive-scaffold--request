package co.com.crediya.model.application.gateways;

import co.com.crediya.model.dto.UserInfo;
import co.com.crediya.model.dto.UserVerificationResult;
import reactor.core.publisher.Mono;


public interface IRestConsumerClient {
    Mono<UserVerificationResult> getUserByDocument(String document);
    Mono<UserInfo> validateUserExistence(String document);
}