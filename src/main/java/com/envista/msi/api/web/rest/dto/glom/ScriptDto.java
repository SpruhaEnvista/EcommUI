package com.envista.msi.api.web.rest.dto.glom;



import com.envista.msi.api.web.rest.dto.CustomerDto;

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
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_ID", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_SCRIPT_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ScriptDto.getCount", procedureName = "SHP_GLM_GET_SCRIPTS_PRO",
                resultSetMappings = "ScriptDtoTotalCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_ID", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_SCRIPT_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ScriptDto.insertOrUpdate", procedureName = "SHP_GLM_INST_UPD_SCRIPTS_PRO",
                resultClasses = ScriptDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_ID", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_DESCRIPTION", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_EFFECTIVE_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_EXPIRY_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IS_ACTIVE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IS_FAVORITE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_ID", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_SCRIPTS_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ScriptDto.findByScriptName", procedureName = "SHP_GLM_GET_BY_SCRIPT_NAME_PRO",
                resultClasses = ScriptDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PREV_SCRIPT_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_SCRIPT_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ScriptDto.delete", procedureName = "SHP_GLM_DEL_SCRIPT_PRO",
                resultClasses = ScriptDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SCRIPT_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_SCRIPT_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ScriptDto.getCustomers", procedureName = "SHP_GLM_GET_CUTOMERS_PRO",
                resultClasses = CustomerDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_ID", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_CUST_INFO", type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "ScriptDtoTotalCount", classes = {
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
    private long scriptId;

    @Column(name = "SCRIPT_NAME")
    private String scriptName;

    @Column(name = "CUSTOMER_ID")
    private int customerId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "EFFECTIVE_DATE")
    private String effectiveDate;

    @Column(name = "EXPIRY_DATE")
    private String expiryDate;

    @Column(name = "IS_ACTIVE")
    private int isActive;

    @Column(name = "IS_Favorite")
    private int isFavorite;

    @Column(name = "CUSTOMER_NAME")
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

    public long getScriptId() {
        return scriptId;
    }

    public void setScriptId(long scriptId) {
        this.scriptId = scriptId;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
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
