package com.envista.msi.api.web.rest.dto.glom;

/**
 * Created by Ramu Adepu on 29-12-2017.
 */
import javax.persistence.*;
import java.io.Serializable;

@NamedStoredProcedureQueries({

        @NamedStoredProcedureQuery(name = "RunScriptDto.insertRunScript", procedureName = "SHP_GLM_INST_UPD_RUN_SCR_PRO",
                resultClasses = RunScriptDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_RUN_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_MODE_VALUE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_DATA_OBJECT_ID", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COMMENTS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_RESULT_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IS_ACTIVE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FROM_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TO_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_ID", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_RUN_INFO", type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "RunScriptDto.TotalCount", classes = {
                @ConstructorResult(
                        targetClass = RunScriptDto.class,
                        columns = {
                                @ColumnResult(name = "TOTAL_COUNT", type = int.class)
                        })
        })
})
@Entity
public class RunScriptDto implements Serializable {

    @Id
    @Column(name = "RUN_ID")
    private Long runId;

    @Column(name = "SCRIPT_ID")
    private Long scriptId;

    @Column(name = "DATA_OBJECT_ID")
    private int dataObjectId;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "RESULT_TYPE")
    private String resultType;

    @Column(name = "IS_ACTIVE")
    private int isActive;

    @Column(name = "MODE_VALUE")
    private int modeValue;

    @Column(name = "FROM_DATE")
    private String fromDate;

    @Column(name = "TO_DATE")
    private String toDate;

    @Column(name = "TOTAL_COUNT")
    private int totalCount;

    @Column(name = "ROW_NUM")
    private Long rowNumber;

    public Long getRunId() {
        return runId;
    }

    public void setRunId(Long runId) {
        this.runId = runId;
    }

    public Long getScriptId() {
        return scriptId;
    }

    public void setScriptId(Long scriptId) {
        this.scriptId = scriptId;
    }

    public int getDataObjectId() {
        return dataObjectId;
    }

    public void setDataObjectId(int dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getModeValue() {
        return modeValue;
    }

    public void setModeValue(int modeValue) {
        this.modeValue = modeValue;
    }
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Long getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public RunScriptDto() {
    }

    public RunScriptDto(int totalCount) {
        this.totalCount = totalCount;
    }


}
