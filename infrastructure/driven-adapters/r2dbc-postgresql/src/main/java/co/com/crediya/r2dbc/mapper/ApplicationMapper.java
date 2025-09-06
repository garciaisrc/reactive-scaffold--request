package co.com.crediya.r2dbc.mapper;

import co.com.crediya.model.application.Application;
import co.com.crediya.r2dbc.entity.ApplicationEntity;

public class ApplicationMapper {
    public static Application toModel(ApplicationEntity entity) {
        return Application.builder()
                .idApplication(entity.getIdApplication())
                .numDocumentUser(entity.getNumDocumentUser())
                .amountApp(entity.getAmountApp())
                .termApp(entity.getTermApp())
                .emailApp(entity.getEmailApp())
                .idState(entity.getIdState())
                .idTip(entity.getIdTip())
                .build();
    }

    public static ApplicationEntity toEntity(Application model) {
        return ApplicationEntity.builder()
                .idApplication(model.getIdApplication())
                .numDocumentUser(model.getNumDocumentUser())
                .amountApp(model.getAmountApp())
                .termApp(model.getTermApp())
                .emailApp(model.getEmailApp())
                .idState(model.getIdState())
                .idTip(model.getIdTip())
                .build();
    }
}
