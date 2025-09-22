package co.com.crediya.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserInfo {
    private Boolean exists;
    private String numDocumentUser;
    private String emailApp;
    private String firstName;
    private BigDecimal baseSalary;
}
