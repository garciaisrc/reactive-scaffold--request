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
public class ApplicationResponseDTO {
    private Integer idApplication;
    private String numDocumentUser;
    private BigDecimal amountApp;
    private Integer termApp;
    private String emailApp;
    private Integer loanTypeName;
    private Integer state;
}