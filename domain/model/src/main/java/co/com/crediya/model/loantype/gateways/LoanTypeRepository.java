package co.com.crediya.model.loantype.gateways;

import co.com.crediya.model.loantype.LoanType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LoanTypeRepository {
    Mono<LoanType> save(LoanType loanidTip);
    Mono<LoanType> findById(Integer loanTypeId);
    Flux<LoanType>findAll();
    Mono<Void> deleteById(String idTiploan);
}
