package co.com.crediya.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVerificationResult {
    private Boolean exists;
    private String numDocumentUser;
    private String emailApp;
}