package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ashish Kumar on 17/04/2017.
 */
@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(name = "UserRole.verifyuserRole", procedureName = "shp_rpt_verify_role_proc",
        resultSetMappings = "VerifyRole",
        parameters = {
                @StoredProcedureParameter(mode= ParameterMode.IN, name = "p_user_id",type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_role", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_verificationMsg",type = Void.class)
        })
        })

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "VerifyRole", classes = {
                @ConstructorResult(
                        targetClass = UserRoleDto.class,
                        columns = {
                                @ColumnResult(name = "p_verificationMsg", type = String.class)
                        }
                )
        })
})
@Entity
public class UserRoleDto implements Serializable{
    @Id
    private Long id;
    @Column(name = "p_verificationMsg")
    private String verificationMsg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVerificationMsg() {
        return verificationMsg;
    }

    public void setVerificationMsg(String verificationMsg) {
        this.verificationMsg = verificationMsg;
    }

    public UserRoleDto(String verificationMsg) {
        this.verificationMsg = verificationMsg;
    }

    public UserRoleDto() {
    }
}
