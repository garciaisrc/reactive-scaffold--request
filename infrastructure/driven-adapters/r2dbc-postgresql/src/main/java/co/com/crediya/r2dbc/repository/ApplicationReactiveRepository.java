package co.com.crediya.r2dbc.repository;

import co.com.crediya.r2dbc.entity.ApplicationEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ApplicationReactiveRepository extends ReactiveCrudRepository<ApplicationEntity, Integer> {
}
