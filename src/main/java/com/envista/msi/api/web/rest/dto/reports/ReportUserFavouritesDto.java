package com.envista.msi.api.web.rest.dto.reports;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;
import com.envista.msi.api.web.rest.dto.dashboard.filter.UserFilterUtilityDataDto;

import javax.persistence.*;

/**
 * Created by Bharath on 24/11/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = ReportUserFavouritesDto.Config.StoredProcedureQueryName.INSERT_USER_FAV_RPT,
                procedureName = ReportUserFavouritesDto.Config.StoredProcedureName.INSERT_USER_FAV_RPT,
                resultSetMappings = {ReportUserFavouritesDto.Config.ResultMappings.INSERT_USER_FAV_RPT_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "USER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rpt_id", type = Long.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = ReportUserFavouritesDto.Config.StoredProcedureQueryName.DELETE_USER_FAV_RPT,
                procedureName = ReportUserFavouritesDto.Config.StoredProcedureName.DELETE_USER_FAV_RPT,
                resultSetMappings = {ReportUserFavouritesDto.Config.ResultMappings.INSERT_USER_FAV_RPT_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "deletedRows", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "USER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rpt_id", type = Long.class)
                }
        )

})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = ReportUserFavouritesDto.Config.ResultMappings.INSERT_USER_FAV_RPT_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = ReportUserFavouritesDto.class,
                                columns = {
                                        @ColumnResult(name = "USER_ID", type = Long.class),
                                        @ColumnResult(name = "rpt_id", type = Long.class),
                                        @ColumnResult(name = "create_date", type = String.class)
                                }
                        )
                }
        )
})

@Entity
public class ReportUserFavouritesDto {



    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "rpt_id")
    private Long rptId;

    @Column(name = "create_date")
    private String createDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRptId() {
        return rptId;
    }

    public void setRptId(Long rptId) {
        this.rptId = rptId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


    public static class Config{
        static class ResultMappings{
            static final String INSERT_USER_FAV_RPT_MAPPING = "ReportUserFavouritesDto.SaveFavouriteReportMapping";
            static final String DELETE_USER_FAV_RPT_MAPPING = "ReportUserFavouritesDto.SaveFavouriteReportMapping";


        }

        static class StoredProcedureName{
            static final String INSERT_USER_FAV_RPT = "shp_rpt_ins_user_fav_proc";
            static final String DELETE_USER_FAV_RPT = "shp_rpt_del_user_fav_proc";


        }

        public static class StoredProcedureQueryName{
            public static final String INSERT_USER_FAV_RPT = "ReportUserFavouritesDto.savefavouritereport";
            public static final String DELETE_USER_FAV_RPT = "ReportUserFavouritesDto.deletefavouritereport";


        }
    }
}
