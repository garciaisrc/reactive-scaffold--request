package co.com.crediya.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ApplicationRequestDTO {
    private String numDocumentUser;
    private String emailApp;
    private BigDecimal amountApp;
    private Integer termApp;
    private Integer idTip;
}