package co.com.crediya.model.application.gateways;

import co.com.crediya.model.dto.StateChangeMessage;
import reactor.core.publisher.Mono;

public interface AlertSenderGateway {
    Mono<Void> sendStateChange(StateChangeMessage message);
}