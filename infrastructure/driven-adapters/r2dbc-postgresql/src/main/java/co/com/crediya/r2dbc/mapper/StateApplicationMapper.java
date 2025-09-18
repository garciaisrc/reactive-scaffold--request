package co.com.crediya.r2dbc.mapper;

import co.com.crediya.model.stateapplication.StateApplication;
import co.com.crediya.r2dbc.entity.StateApplicationEntity;

public class StateApplicationMapper {
    public static StateApplication toModel(StateApplicationEntity entity) {
        return StateApplication.builder()
                .idState(entity.getIdState())
                .nameState(entity.getNameState())
                .build();
    }
}
