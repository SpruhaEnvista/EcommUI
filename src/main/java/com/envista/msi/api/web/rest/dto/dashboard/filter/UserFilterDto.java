package com.envista.msi.api.web.rest.dto.dashboard.filter;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 13/03/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = UserFilterDto.Config.StoredProcedureQueryName.USER_FILTER_BY_FILTER_ID,
                procedureName = UserFilterDto.Config.StoredProcedureName.USER_FILTER_BY_FILTER_ID,
                resultSetMappings = {UserFilterDto.Config.ResultMappings.USER_FILTER_BY_FILTER_ID_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterParam.FILTER_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.UserFilterParam.FILTER_DATA_PARAM, type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = UserFilterDto.Config.StoredProcedureQueryName.USER_FILTER_BY_USER_ID,
                procedureName = UserFilterDto.Config.StoredProcedureName.USER_FILTER_BY_USER_ID,
                resultSetMappings = {UserFilterDto.Config.ResultMappings.USER_FILTER_BY_USER_ID_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterParam.USER_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.UserFilterParam.FILTER_DATA_PARAM, type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = UserFilterDto.Config.ResultMappings.USER_FILTER_BY_FILTER_ID_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = UserFilterDto.class,
                                columns = {
                                        @ColumnResult(name = "FILTER_ID", type = Long.class),
                                        @ColumnResult(name = "FILTER_NAME", type = String.class),
                                        @ColumnResult(name = "FILTER_DETAILS", type = String.class)
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name = UserFilterDto.Config.ResultMappings.USER_FILTER_BY_USER_ID_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = UserFilterDto.class,
                                columns = {
                                        @ColumnResult(name = "FILTER_ID", type = Long.class),
                                        @ColumnResult(name = "FILTER_NAME", type = String.class),
                                        @ColumnResult(name = "DEFAULT_FILTER", type = Integer.class)
                                }
                        )
                }
        )
})

@Entity
public class UserFilterDto implements Serializable {
    @Id
    @Column(name = "FILTER_ID")
    private Long id;

    @Column(name = "FILTER_NAME")
    private String name;

    @Column(name = "FILTER_DETAILS")
    private String filterDetails;

    @Column(name = "DEFAULT_FILTER")
    private Integer defaultFilter;

    public UserFilterDto(Long id, String name, String filterDetails) {
        this.id = id;
        this.name = name;
        this.filterDetails = filterDetails;
    }

    public UserFilterDto(Long id, String name, Integer defaultFilter) {
        this.id = id;
        this.name = name;
        this.defaultFilter = defaultFilter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilterDetails() {
        return filterDetails;
    }

    public void setFilterDetails(String filterDetails) {
        this.filterDetails = filterDetails;
    }

    public Integer getDefaultFilter() {
        return defaultFilter;
    }

    public void setDefaultFilter(Integer defaultFilter) {
        this.defaultFilter = defaultFilter;
    }

    public static class Config{
        static class ResultMappings{
            static final String USER_FILTER_BY_FILTER_ID_MAPPING = "UserFilterDto.UserFilterDetails";
            static final String USER_FILTER_BY_USER_ID_MAPPING = "UserFilterDto.UserFilterDetailsByUser";
        }

        static class StoredProcedureName{
            static final String USER_FILTER_BY_FILTER_ID = "SHP_DB_FILTER_INFO_PROC";
            static final String USER_FILTER_BY_USER_ID = "SHP_DB_FILTER_BY_USER_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String USER_FILTER_BY_FILTER_ID = "UserFilterDto.getUserFilterDetails";
            public static final String USER_FILTER_BY_USER_ID = "UserFilterDto.getUserFilterDetailsByUser";
        }
    }
}
