package co.com.crediya.model.application.gateways;

import co.com.crediya.model.dto.UserInfo;
import reactor.core.publisher.Mono;

public interface AppRestConsumer {
    public Mono<UserInfo> validateUserExistence(String document);
}
