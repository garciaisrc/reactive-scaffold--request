package co.com.crediya.r2dbc.repository;

import co.com.crediya.r2dbc.entity.StateApplicationEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StateApplicationReactiveRepository extends ReactiveCrudRepository<StateApplicationEntity, Integer> {
}
