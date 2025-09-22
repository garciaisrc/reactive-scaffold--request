package co.com.crediya.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateChangeMessage {
    private Integer idApplication;
    private Integer newState;
    private String emailUser;
}
