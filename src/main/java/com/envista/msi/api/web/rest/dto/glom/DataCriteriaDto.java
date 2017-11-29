package com.envista.msi.api.web.rest.dto.glom;

import javax.persistence.*;

/**
 * Created by KRISHNAREDDYM on 11/29/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "DataCriteriaDto.getByDataObjectId", procedureName = "SHP_GLM_GET_DATA_CRIT_INFO_PRO",
                resultClasses = DataCriteriaDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_DATA_OBJECT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_DATA_CRIT_INFO", type = Void.class)
                })
})

@Entity
public class DataCriteriaDto {

    @Id
    @Column(name = "DATA_CRITERIA_ID")
    private long dataCriteriaId;

    @Column(name = "DATA_OBJECT_ID")
    private long dataObjectId;

    @Column(name = "CODE_VALUE_ID")
    private long codeValueId;

    @Column(name = "COLUMN_NAME")
    private String columnName;

    @Column(name = "OPERATOR")
    private String operator;

    @Column(name = "AND_OR_COND")
    private String andOrCondition;

    @Column(name = "VALUE")
    private String value;


    public DataCriteriaDto() {
    }

    public long getDataCriteriaId() {
        return dataCriteriaId;
    }

    public void setDataCriteriaId(long dataCriteriaId) {
        this.dataCriteriaId = dataCriteriaId;
    }

    public long getDataObjectId() {
        return dataObjectId;
    }

    public void setDataObjectId(long dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    public long getCodeValueId() {
        return codeValueId;
    }

    public void setCodeValueId(long codeValueId) {
        this.codeValueId = codeValueId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAndOrCondition() {
        return andOrCondition;
    }

    public void setAndOrCondition(String andOrCondition) {
        this.andOrCondition = andOrCondition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
