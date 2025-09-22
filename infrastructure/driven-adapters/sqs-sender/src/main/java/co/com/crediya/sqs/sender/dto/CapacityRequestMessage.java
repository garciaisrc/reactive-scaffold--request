package co.com.crediya.sqs.sender.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CapacityRequestMessage {
    private Integer idApplication;
    private Integer idTip;
    private Double amount;
    private Integer term;
    private Integer idState;
    private Double baseSalary;
    private Double rateInterest;
}