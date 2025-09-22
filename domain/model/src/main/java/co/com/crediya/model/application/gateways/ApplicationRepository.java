package co.com.crediya.model.application.gateways;

import co.com.crediya.model.application.Application;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApplicationRepository {
    Mono<Application> save(Application application);
    Mono<Application> findById(Integer idApplication);
    Flux<Application> findAll(int page, int size);
    Mono<Void> deleteById(Integer idApplication);

    Flux<Application> findByNumDocumentUser(String numDocumentUser);
}
