package com.envista.msi.api.web.rest.dto.rtr;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ViewLogDto.getViewLogs", procedureName = "get_view_logs",
                resultSetMappings = "ViewLogDto.getViewLogs",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "job_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "refcur_view_log",
                                type = Void.class)
                })})
@SqlResultSetMappings(
        @SqlResultSetMapping(
                name = "ViewLogDto.getViewLogs",
                classes = {
                        @ConstructorResult(
                                targetClass = ViewLogDto.class,
                                columns = {
                                        @ColumnResult(name = "CREATE_DATE", type = String.class),
                                        @ColumnResult(name = "LAST_UPDATE_DATE", type = String.class)
                                }
                        )
                }
        )
)

@Entity
//@Table(name = "SHP_PCL_RATE_REQ_RESP_LOG_TB")
public class ViewLogDto implements Serializable {

    @Id
    @Column(name = "PARCEL_RATE_REQ_RESP_ID")
    private String parcel_rate_req_resp_id;
    @Column(name = "CREATE_DATE")
    private String create_Date;
    @Column(name = "LAST_UPDATE_DATE")
    private String last_Update_Date;


    public ViewLogDto(String create_Date,String last_Update_Date ) {
        this.create_Date=create_Date;
        this.last_Update_Date=last_Update_Date;
    }

    public ViewLogDto() {
    }

    public String getCreate_Date() {
        return create_Date;
    }

    public void setCreate_Date(String create_Date) {
        this.create_Date = create_Date;
    }

    public String getLast_Update_Date() {
        return last_Update_Date;
    }

    public void setLast_Update_Date(String last_Update_Date) {
        this.last_Update_Date = last_Update_Date;
    }


}