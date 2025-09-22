package co.com.crediya.r2dbc.adapter;

import co.com.crediya.model.stateapplication.StateApplication;
import co.com.crediya.model.stateapplication.gateways.StateApplicationRepository;
import co.com.crediya.r2dbc.mapper.StateApplicationMapper;
import co.com.crediya.r2dbc.repository.StateApplicationReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class StateApplicationRepositoryAdapter implements StateApplicationRepository {

    private final StateApplicationReactiveRepository repository;

    @Override
    public Mono<StateApplication> findById(Integer id) {
        return repository.findById(id)
                .map(StateApplicationMapper::toModel);
    }
}