package co.com.crediya.sqs.sender.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class StateChangeMessage {
    private Integer idApplication;
    private Integer newState;
    private String emailUser;
}
