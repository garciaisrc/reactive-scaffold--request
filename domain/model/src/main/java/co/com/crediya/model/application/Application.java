package co.com.crediya.model.application;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Application {
    private Integer idApplication;
    private String numDocumentUser;
    private String emailApp;
    private Double amountApp;
    private Integer termApp;
    private Integer idState;
    private Integer idTip;
}
