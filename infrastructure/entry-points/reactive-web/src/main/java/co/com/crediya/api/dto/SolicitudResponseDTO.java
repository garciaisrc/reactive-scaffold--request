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
public class SolicitudResponseDTO {
    private Integer idApplication;
    private String numDocumentUser;
    private String firstName;
    private BigDecimal baseSalary;
    private BigDecimal amountApp;
    private Integer termApp;
    private String emailApp;
    private String nameLoan;
    private Double rateInterest;
    private String nameState;
    private BigDecimal montoMensual;
}
