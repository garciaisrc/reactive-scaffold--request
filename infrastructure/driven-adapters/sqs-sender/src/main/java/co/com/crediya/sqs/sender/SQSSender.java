package co.com.crediya.sqs.sender;

import co.com.crediya.model.application.gateways.AlertSenderGateway;
import co.com.crediya.model.dto.CapacityRequestMessage;
import co.com.crediya.model.dto.StateChangeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import io.awspring.cloud.sqs.operations.SqsTemplate;



@Component
@RequiredArgsConstructor
public class SQSSender implements AlertSenderGateway {

    private final SqsTemplate sqsTemplate;

    @Override
    public Mono<Void> sendStateChange(StateChangeMessage message) {
        return Mono.fromRunnable(() ->
                sqsTemplate.send(sqsMessage -> sqsMessage
                        .queue("loan-application-state-change")
                        .payload(message))
        );
    }
    @Override
    public Mono<Void> sendCapacityRequest(CapacityRequestMessage message) {
        return Mono.fromRunnable(() ->
                sqsTemplate.send(sqsMessage -> sqsMessage
                        .queue("loan-application-capacity-request")
                        .payload(message))
        );
    }
}