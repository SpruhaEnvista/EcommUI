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
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_verificationMsg",type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportsValidation.verifySavedSchedShippers", procedureName = "shp_rpt_svdsched_shipper_proc",
        resultSetMappings = "VerifySavedSchedShippers",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_saved_sched_rpt_id", type = Long.class),
                @StoredProcedureParameter(mode= ParameterMode.IN, name = "p_user_id",type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_verificationMsg",type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = "ReportsValidation.verifyAssignedReport", procedureName = "shp_rpt_verify_assiged_rpt",
                resultSetMappings = "VerifyAccounts",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode= ParameterMode.IN, name = "p_rpt_id",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_verificationMsg",type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportsValidation.verifySavedSchedShipperGroups", procedureName = "shp_rpt_svdsched_shipGrps_proc",
                resultSetMappings = "VerifySavedSchedShippers",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_saved_sched_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode= ParameterMode.IN, name = "p_user_id",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_verificationMsg",type = Void.class)
                }),

        @NamedStoredProcedureQuery(name = "ReportsValidation.verifyShipperGroups", procedureName = "shp_rpt_verify_shipGrps_proc",
                resultSetMappings = "VerifySavedSchedShippers",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_shipperGroupIds", type = String.class),
                        @StoredProcedureParameter(mode= ParameterMode.IN, name = "p_user_id",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_verificationMsg",type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportsValidation.verifyShippers", procedureName = "shp_rpt_verify_shippers_proc",
                resultSetMappings = "VerifySavedSchedShippers",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_shipperIds", type = String.class),
                        @StoredProcedureParameter(mode= ParameterMode.IN, name = "p_user_id",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_verificationMsg",type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportsValidation.verifyAssignCarrier", procedureName = "shp_rpt_verify_Assign_Crr_proc",
                resultSetMappings = "VerifySavedSchedShippers",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_carrierIds", type = String.class),
                        @StoredProcedureParameter(mode= ParameterMode.IN, name = "p_user_id",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_verificationMsg",type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportsValidation.verifyCarrier", procedureName = "shp_rpt_verifyCarrier_proc",
                resultSetMappings = "VerifySavedSchedShippers",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_verificationMsg",type = Void.class),
                        @StoredProcedureParameter(mode= ParameterMode.IN, name = "p_userId",type = Long.class),
                        @StoredProcedureParameter(mode= ParameterMode.IN,name = "p_rptId",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "p_savedschedRptId",type = Long.class)

                }),
        @NamedStoredProcedureQuery(name = "ReportsValidation.verifyAssignedAccounts", procedureName = "shp_rpt_assigned_accounts_proc",
                resultSetMappings = "VerifyAccounts",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customerIds", type = String.class),
                        @StoredProcedureParameter(mode= ParameterMode.IN, name = "p_user_id",type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_verificationMsg",type = Void.class)
                }),

        })
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "VerifyAccounts", classes = {
                @ConstructorResult(
                        targetClass = ReportsValidationDto.class,
                        columns = {
                                @ColumnResult(name = "p_verificationMsg", type = String.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "VerifySavedSchedShippers", classes = {
                @ConstructorResult(
                        targetClass = ReportsValidationDto.class,
                        columns = {
                                @ColumnResult(name = "p_verificationMsg", type = String.class)
                        }
                )
        }),

})

@Entity
public class ReportsValidationDto implements Serializable {

    @Id
    private long id;

    @Column(name = "p_verificationMsg")
    private String verificationMsg;


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

    public String getVerificationMsg() {
        return verificationMsg;
    }

    public void setVerificationMsg(String verificationMsg) {
        this.verificationMsg = verificationMsg;
    }
}


