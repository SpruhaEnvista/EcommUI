package com.envista.msi.api.web.rest.dto.glom;

import com.envista.msi.api.web.rest.dto.invoicing.VoiceDto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 9/13/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ScriptDto.getAll", procedureName = "SHP_GLM_GET_SCRIPTS_PRO",
                resultClasses = ScriptDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = int.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = int.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_SCRIPT_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ScriptDto.getCount", procedureName = "SHP_GLM_GET_SCRIPTS_PRO",
                resultSetMappings = "totalCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = int.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = int.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_SCRIPT_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ScriptDto.insertOrUpdate", procedureName = "SHP_GLM_INST_UPD_SCRIPTS_PRO",
                resultClasses = ScriptDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_DESCRIPTION", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_EFFECTIVE_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_EXPIRY_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IS_ACTIVE", type = int.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IS_FAVORITE", type = int.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_ID", type = int.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_VOICE_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ScriptDto.findByScriptName", procedureName = "SHP_GLM_GET_BY_SCRIPT_NAME_PRO",
                resultClasses = VoiceDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PREV_SCRIPT_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_SCRIPT_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ScriptDto.delete", procedureName = "SHP_GLM_DEL_SCRIPT_PRO",
                resultClasses = VoiceDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_SCRIPT_INFO", type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "totalCount", classes = {
                @ConstructorResult(
                        targetClass = ScriptDto.class,
                        columns = {
                                @ColumnResult(name = "TOTAL_COUNT", type = int.class)
                        })
        })
})
@Entity
public class ScriptDto implements Serializable {

    @Id
    @Column(name = "SCRIPT_ID")
    private Long scriptId;

    @Column(name = "SCRIPT_NAME", nullable = false, unique = true)
    private String scriptName;

    @Column(name = "CUSTOMER_ID", nullable = true)
    private Long customerId;

    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    @Column(name = "EFFECTIVE_DATE", nullable = false, unique = true)
    private String effectiveDate;

    @Column(name = "EXPIRY_DATE", nullable = true)
    private String expiryDate;

    @Column(name = "IS_ACTIVE", nullable = true)
    private int isActive;

    @Column(name = "IS_Favorite", nullable = false, unique = true)
    private int isFavorite;

    @Column(name = "CUSTOMER_NAME", nullable = true)
    private String customerName;

    @Column(name = "TOTAL_COUNT")
    private int totalCount;

    @Column(name = "ROW_NUM")
    private Long rowNumber;


    public ScriptDto() {
    }

    public ScriptDto(int totalCount) {
        this.totalCount = totalCount;
    }

    public Long getScriptId() {
        return scriptId;
    }

    public void setScriptId(Long scriptId) {
        this.scriptId = scriptId;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
