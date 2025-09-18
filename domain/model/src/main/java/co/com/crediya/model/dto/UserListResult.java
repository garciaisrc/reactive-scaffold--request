package co.com.crediya.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserListResult {

    // Datos que vienen del microservicio AutCrediya
    private String firstName;
    private BigDecimal baseSalary;

    // Datos de la solicitud (LoaCrediya)
    private Integer idApplication;
    private String numDocumentUser;
    private String emailApp;
    private BigDecimal amountApp;
    private Integer termApp;

    // Datos de LoanType
    private String nameLoan;
    private Double rateInterest;

    // Datos del StateApplication
    private String nameState;

    // Campo calculado
    private BigDecimal montoMensual;
}
