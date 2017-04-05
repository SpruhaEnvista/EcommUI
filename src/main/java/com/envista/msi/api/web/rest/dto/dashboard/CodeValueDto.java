package com.envista.msi.api.web.rest.dto.dashboard;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 20/03/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = CodeValueDto.Config.StoredProcedureQueryName.CODE_VALUE_BY_CODE_GROUP_ID,
                procedureName = CodeValueDto.Config.StoredProcedureName.CODE_VALUE_BY_CODE_GROUP_ID,
                resultSetMappings = {CodeValueDto.Config.ResultMappings.CODE_VALUE_BY_CODE_GROUP_ID_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.CodeValueParam.CODE_GROUP_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.CodeValueParam.FCODE_VALUE_DATA_PARAM, type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = CodeValueDto.Config.ResultMappings.CODE_VALUE_BY_CODE_GROUP_ID_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = CodeValueDto.class,
                                columns = {
                                        @ColumnResult(name = "NSP_CODE_VALUE_ID", type = Long.class),
                                        @ColumnResult(name = "CODE_VALUE", type = String.class),
                                        @ColumnResult(name ="PROPERTY_1", type = String.class)
                                }
                        )
                }
        )
})

@Entity
public class CodeValueDto implements Serializable {

    @Column(name = "NSP_CODE_VALUE_ID")
    @Id
    private Long id;

    @Column(name = "CODE_VALUE")
    private String codeValue;

    @Column( name = "PROPERTY_1")
    private String propertyOne;

    public CodeValueDto(Long id, String codeValue , String propertyOne) {
        this.id = id;
        this.codeValue = codeValue;
        this.propertyOne = propertyOne;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getPropertyOne() {
        return propertyOne;
    }

    public void setPropertyOne(String propertyOne) {
        this.propertyOne = propertyOne;
    }

    public static class Config{
        static class ResultMappings{
            static final String CODE_VALUE_BY_CODE_GROUP_ID_MAPPING = "CodeValueDto.CodeValueByCodeGroupId";
        }

        static class StoredProcedureName{
            static final String CODE_VALUE_BY_CODE_GROUP_ID = "SHP_DB_CODE_VAL_BY_GRP_ID_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String CODE_VALUE_BY_CODE_GROUP_ID = "CodeValueDto.getCodeValueByCodeGroupId";
        }
    }
}
