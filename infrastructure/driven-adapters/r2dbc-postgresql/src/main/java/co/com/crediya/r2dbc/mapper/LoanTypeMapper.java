package co.com.crediya.r2dbc.mapper;

import co.com.crediya.model.loantype.LoanType;
import co.com.crediya.r2dbc.entity.LoanTypeEntity;

public class LoanTypeMapper {

    public static LoanType toDomain(LoanTypeEntity entity) {
        if (entity == null) return null;
        return LoanType.builder()
                .idTip(entity.getIdTip())
                .nameLoan(entity.getNameLoan())
                .miniAmount(entity.getMiniAmount())
                .maxAmount(entity.getMaxAmount())
                .rateInterest(entity.getRateInterest())
                .autoValidation(entity.getAutoValidation())
                .build();
    }

    public static LoanTypeEntity toEntity(LoanType domain) {
        if (domain == null) return null;
        return LoanTypeEntity.builder()
                .idTip(domain.getIdTip())
                .nameLoan(domain.getNameLoan())
                .miniAmount(domain.getMiniAmount())
                .maxAmount(domain.getMaxAmount())
                .rateInterest(domain.getRateInterest())
                .autoValidation(domain.getAutoValidation())
                .build();
    }
}
