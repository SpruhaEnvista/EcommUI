package com.envista.msi.api.web.rest.dto.i18n;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;

/**
 * Created by Sujit kumar on 08/06/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = InternationalizationDto.Config.StoredProcedureQueryName.I18N_LABELS_BY_LOCALE,
                procedureName = InternationalizationDto.Config.StoredProcedureName.I18N_LABELS_BY_LOCALE,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_locale", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "result_data", type = Void.class)
                },
                resultClasses = {InternationalizationDto.class}
        )
})

@Entity
public class InternationalizationDto {
    @Id
    @Column(name = "KEY")
    private String key;

    @Column(name = "VALUE")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String label) {
        this.key = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public static class Config{
        static class StoredProcedureName{
            static final String I18N_LABELS_BY_LOCALE = "shp_avtr_get_I18n_values_proc";
        }

        public static class StoredProcedureQueryName{
            public static final String I18N_LABELS_BY_LOCALE = "InternationalizationDto.getI18nLabelsByLocale";
        }
    }
}
