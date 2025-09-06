package co.com.crediya.consumer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExistsUserResponse {
    private Boolean exists;
    private String numDocumentUser;
    private String emailApp;
}