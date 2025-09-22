package co.com.crediya.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CapacityRequestMessage {
    private Integer idApplication;
    private Integer idTip;
    private Double amount;
    private Integer term;
    private Integer idState;
    private Double baseSalary;
    private Double rateInterest;
    private String numDocumentUser;
}
