package co.com.crediya.r2dbc.adapter;

import co.com.crediya.model.application.Application;
import co.com.crediya.model.application.gateways.ApplicationRepository;
import co.com.crediya.r2dbc.repository.ApplicationReactiveRepository;
import co.com.crediya.r2dbc.mapper.ApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class ApplicationRepositoryAdapter implements ApplicationRepository {

    private final ApplicationReactiveRepository repository;

    @Override
    public Mono<Application> save(Application application) {
        return repository.save(ApplicationMapper.toEntity(application))
                .map(ApplicationMapper::toModel);
    }

    @Override
    public Flux<Application> findAll() {
        return repository.findAll().map(ApplicationMapper::toModel);
    }

    @Override
    public Mono<Application> findById(Integer id) {
        return repository.findById(id).map(ApplicationMapper::toModel);
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return repository.deleteById(id);
    }
}