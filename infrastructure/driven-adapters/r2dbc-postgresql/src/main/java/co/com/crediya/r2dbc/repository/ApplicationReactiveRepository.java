package co.com.crediya.r2dbc.repository;

import co.com.crediya.r2dbc.entity.ApplicationEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface ApplicationReactiveRepository extends ReactiveCrudRepository<ApplicationEntity, Integer> {
    @Query("SELECT * FROM application ORDER BY id_application LIMIT :size OFFSET :offset")
    Flux<ApplicationEntity> findAllPaged(int size, int offset);

    @Query("SELECT * FROM application WHERE num_document_user = :numDocumentUser")
    Flux<ApplicationEntity> findByNumDocumentUser(String numDocumentUser);
}
