package co.com.crediya.usecase.application;

import co.com.crediya.model.application.Application;
import co.com.crediya.model.application.gateways.AppRestConsumer;
import co.com.crediya.model.application.gateways.ApplicationRepository;
import co.com.crediya.model.dto.UserInfo;
import co.com.crediya.model.dto.UserListResult;
import co.com.crediya.model.loantype.LoanType;
import co.com.crediya.model.loantype.gateways.LoanTypeRepository;
import co.com.crediya.model.stateapplication.StateApplication;
import co.com.crediya.model.stateapplication.gateways.StateApplicationRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ListerAppUseCase {

    private final ApplicationRepository applicationRepository;
    private final LoanTypeRepository loanTypeRepository;
    private final StateApplicationRepository stateApplicationRepository;
    private final AppRestConsumer appRestConsumer;

    public Flux<UserListResult> findSolicitudesPendientes(int page, int size, String nameState) {
        return applicationRepository.findAll(page, size)
                .flatMap(this::enrichApplication)
                .filter(result -> result.getNameState().equalsIgnoreCase(nameState));
    }

    private Mono<UserListResult> enrichApplication(Application app) {
        Mono<UserInfo> userMono = appRestConsumer.validateUserExistence(app.getNumDocumentUser());
        Mono<LoanType> loanTypeMono = loanTypeRepository.findbyid(app.getIdTip());
        Mono<StateApplication> stateMono = stateApplicationRepository.findById(app.getIdState());

        return Mono.zip(userMono, loanTypeMono, stateMono)
                .map(tuple -> {
                    UserInfo user = tuple.getT1();
                    LoanType loanType = tuple.getT2();
                    StateApplication state = tuple.getT3();

                    return UserListResult.builder()
                            .idApplication(app.getIdApplication())
                            .numDocumentUser(app.getNumDocumentUser())
                            .firstName(user.getFirstName())
                            .baseSalary(user.getBaseSalary())
                            .emailApp(user.getEmailApp())
                            .amountApp(BigDecimal.valueOf(app.getAmountApp()))
                            .termApp(app.getTermApp())
                            .nameLoan(loanType.getNameLoan())
                            .rateInterest(loanType.getRateInterest())
                            .nameState(state.getNameState())
                            .montoMensual(BigDecimal.valueOf(app.getAmountApp() / app.getTermApp()))
                            .build();
                })
                .onErrorResume(e -> {
                    return Mono.empty();
                });
    }
}
