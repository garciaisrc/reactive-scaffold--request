package co.com.crediya.r2dbc.repository;

import co.com.crediya.r2dbc.entity.LoanTypeEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LoanTypeReactiveRepository extends ReactiveCrudRepository<LoanTypeEntity, Integer> {
}
