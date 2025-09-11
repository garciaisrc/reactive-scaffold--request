package co.com.crediya.r2dbc.adapter;

import co.com.crediya.model.loantype.LoanType;
import co.com.crediya.model.loantype.gateways.LoanTypeRepository;
import co.com.crediya.r2dbc.entity.LoanTypeEntity;
import co.com.crediya.r2dbc.mapper.LoanTypeMapper;
import co.com.crediya.r2dbc.repository.LoanTypeReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class LoanTypeRepositoryAdapter implements LoanTypeRepository {

    private final LoanTypeReactiveRepository repository;

    @Override
    public Mono<LoanType> save(LoanType loanType) {
        LoanTypeEntity entity = LoanTypeMapper.toEntity(loanType);
        return repository.save(entity)
                .map(LoanTypeMapper::toDomain);
    }

    @Override
    public Mono<LoanType> findbyid(Integer loanTypeId) {
        return repository.findById(Integer.valueOf(loanTypeId))
                .map(LoanTypeMapper::toDomain);
    }

    @Override
    public Flux<LoanType> findAll() {
        return repository.findAll()
                .map(LoanTypeMapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String idTiploan) {
        return repository.deleteById(Integer.valueOf(idTiploan));
    }
}