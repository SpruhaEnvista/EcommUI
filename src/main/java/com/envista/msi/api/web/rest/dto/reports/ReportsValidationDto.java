package com.envista.msi.api.web.rest.dto.reports;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ashish Kumar on 07/04/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportsValidation.verifyAccounts", procedureName = "shp_rpt_verify_accounts_proc",
                resultSetMappings = "VerifyAccounts",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_saved_sched_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode= ParameterMode.IN, name = "p_user_id",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT ,name = "p_verificationMsg",type = String.class)
                })
        })
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "VerifyAccounts", classes = {
                @ConstructorResult(
                        targetClass = ReportsValidationDto.class,
                        columns = {
                                @ColumnResult(name = "p_verificationMsg", type = String.class)
                        }
                )
        })
})

@Entity
public class ReportsValidationDto implements Serializable {

     @Id
     private long id;

     @Column(name ="p_verificationMsg")
     private String  verificationMsg;

    public ReportsValidationDto() {
    }

    public ReportsValidationDto(String verificationMsg) {
        this.verificationMsg = verificationMsg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String isVerified() {
        return verificationMsg;
    }

    public void setVerified(String verified) {
        verificationMsg = verificationMsg;
    }
}

