package co.com.crediya.model.stateapplication.gateways;

import co.com.crediya.model.stateapplication.StateApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StateApplicationRepository {
    Mono<StateApplication> save(StateApplication stateApplication);
    Flux<StateApplication> findAll();
    Mono<StateApplication> findByName(Integer name);
}
