package com.envista.msi.api.web.rest.dto.glom;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 9/19/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "RulesDto.getAll", procedureName = "SHP_GLM_GET_RULES_PRO",
                resultClasses = RulesDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_RULE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IS_ACTIVE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_RULES_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "RulesDto.getCount", procedureName = "SHP_GLM_GET_RULES_PRO",
                resultSetMappings = "RulesDto.TotalCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_RULE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IS_ACTIVE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_RULES_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "RulesDto.insertOrUpdate", procedureName = "SHP_GLM_INST_UPD_RULES_PRO",
                resultClasses = RulesDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_RULE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_RULE_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_DATA_OBJECT_ID", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SEQUENCE_ID", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCOPE_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IDENTITY_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COMMENTS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COLUMN_1", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COLUMN_2", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COLUMN_3", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COLUMN_4", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COLUMN_5", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COLUMN_6", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COLUMN_7", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COLUMN_8", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COLUMN_9", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COLUMN_10", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IS_ACTIVE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_ID", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INSERT_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_RULES_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "RulesDto.findByRuleName", procedureName = "SHP_GLM_GET_BY_RULE_NAME_PRO",
                resultClasses = RulesDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_RULE_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PREV_RULE_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_RULES_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "RulesDto.delete", procedureName = "SHP_GLM_DEL_RULES_PRO",
                resultClasses = RulesDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_RULE_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_RULES_INFO", type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "RulesDto.TotalCount", classes = {
                @ConstructorResult(
                        targetClass = RulesDto.class,
                        columns = {
                                @ColumnResult(name = "TOTAL_COUNT", type = int.class)
                        })
        })
})
@Entity
public class RulesDto implements Serializable {

    @Id
    @Column(name = "RULE_ID")
    private Long ruleId;

    @Column(name = "SCRIPT_ID")
    private Long scriptId;

    @Column(name = "RULE_NAME")
    private String ruleName;

    @Column(name = "DATA_OBJECT_ID")
    private int dataObjectId;

    @Column(name = "SEQUENCE_ID")
    private int sequence;

    @Column(name = "SCOPE_NAME")
    private String scope;

    @Column(name = "IDENTITY_NAME")
    private String identity;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "ACTION")
    private String action;

    @Column(name = "COLUMN_1")
    private String column1;

    @Column(name = "COLUMN_2")
    private String column2;

    @Column(name = "COLUMN_3")
    private String column3;

    @Column(name = "COLUMN_4")
    private String column4;

    @Column(name = "COLUMN_5")
    private String column5;

    @Column(name = "COLUMN_6")
    private String column6;

    @Column(name = "COLUMN_7")
    private String column7;

    @Column(name = "COLUMN_8")
    private String column8;

    @Column(name = "COLUMN_9")
    private String column9;

    @Column(name = "COLUMN_10")
    private String column10;

    @Column(name = "IS_ACTIVE")
    private int isActive;

    @Column(name = "TOTAL_COUNT")
    private int totalCount;

    @Column(name = "ROW_NUM")
    private Long rowNumber;


    public RulesDto() {
    }

    public RulesDto(int totalCount) {
        this.totalCount = totalCount;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getScriptId() {
        return scriptId;
    }

    public void setScriptId(Long scriptId) {
        this.scriptId = scriptId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public int getDataObjectId() {
        return dataObjectId;
    }

    public void setDataObjectId(int dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public String getColumn5() {
        return column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }

    public String getColumn6() {
        return column6;
    }

    public void setColumn6(String column6) {
        this.column6 = column6;
    }

    public String getColumn7() {
        return column7;
    }

    public void setColumn7(String column7) {
        this.column7 = column7;
    }

    public String getColumn8() {
        return column8;
    }

    public void setColumn8(String column8) {
        this.column8 = column8;
    }

    public String getColumn9() {
        return column9;
    }

    public void setColumn9(String column9) {
        this.column9 = column9;
    }

    public String getColumn10() {
        return column10;
    }

    public void setColumn10(String column10) {
        this.column10 = column10;
    }


    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
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
}
