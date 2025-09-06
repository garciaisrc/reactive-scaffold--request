package co.com.crediya.model.stateapplication;
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
public class StateApplication {
    private Integer idState;
    private String nameState;
    private String descriptionSt;

}
