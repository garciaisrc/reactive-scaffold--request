package co.com.crediya.r2dbc.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("application")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ApplicationEntity {
    @Id
    private Integer idApplication;
    @Column("num_document_user")
    private String numDocumentUser;
    @Column("amount_app")
    private Double amountApp;
    @Column("term_app")
    private Integer termApp;
    @Column("email_app")
    private String emailApp;
    @Column("id_state")
    private Integer idState;
    @Column("id_tip")
    private Integer idTip;
}
