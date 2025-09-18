package co.com.crediya.consumer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExistsUserResponse {
    private Boolean exists;
    private String numDocumentUser;
    private String emailApp;
    private String firstName;
    private BigDecimal baseSalary;
}