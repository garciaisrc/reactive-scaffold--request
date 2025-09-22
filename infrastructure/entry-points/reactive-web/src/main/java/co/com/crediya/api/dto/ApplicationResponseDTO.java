package co.com.crediya.api.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
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

    @JsonGetter("state")
    public String getStateDescription() {
        return switch (state) {
            case 1 -> "Pendiente por revision";case 2 -> "Aprobado";case 3 -> "Rechazado";default -> "Desconocido";
        };
    }
    @JsonGetter("loanTypeName")
    public String getLoanTypeDescription() {
        return switch (loanTypeName) {
            case 1 -> "Personal";case 2 -> "Vehicular";case 3 -> "Hipotecario";default -> "Desconocido";
        };
    }

}