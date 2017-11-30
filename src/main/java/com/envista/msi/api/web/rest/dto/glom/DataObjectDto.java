package com.envista.msi.api.web.rest.dto.glom;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 11/29/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "DataObjectDto.getAll", procedureName = "SHP_GLM_GET_DATA_OBJS_INFO_PRO",
                resultClasses = DataObjectDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_DATA_OBJECT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IS_ACTIVE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_DATA_OBJ_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "DataObjectDto.getCount", procedureName = "SHP_GLM_GET_DATA_OBJS_INFO_PRO",
                resultSetMappings = "DataObjectDto.TotalCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_DATA_OBJECT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IS_ACTIVE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_DATA_OBJ_INFO", type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "DataObjectDto.TotalCount", classes = {
                @ConstructorResult(
                        targetClass = DataObjectDto.class,
                        columns = {
                                @ColumnResult(name = "TOTAL_COUNT", type = int.class)
                        })
        })
})
@Entity
public class DataObjectDto implements Serializable {

    @Id
    @Column(name = "DATA_OBJECT_ID")
    private long dataObjectId;

    @Column(name = "DATA_OBJECT_NAME")
    private String dataObjectName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_ACTIVE")
    private int isActive;

    @Column(name = "TOTAL_COUNT")
    private int totalCount;

    public DataObjectDto() {
    }

    public DataObjectDto(int totalCount) {
        this.totalCount = totalCount;
    }

    public long getDataObjectId() {
        return dataObjectId;
    }

    public void setDataObjectId(long dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    public String getDataObjectName() {
        return dataObjectName;
    }

    public void setDataObjectName(String dataObjectName) {
        this.dataObjectName = dataObjectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
