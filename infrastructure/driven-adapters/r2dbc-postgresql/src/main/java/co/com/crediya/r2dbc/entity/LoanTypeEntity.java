package co.com.crediya.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table("loan_type")
public class LoanTypeEntity {
    @Id
    private Integer idTip;
    @Column("name_loan")
    private String nameLoan;
    @Column("mini_amount")
    private Double miniAmount;
    @Column("max_amount")
    private Double maxAmount;
    @Column("rate_interest")
    private Double rateInterest;
    @Column("auto_validation")
    private Boolean autoValidation;
}
