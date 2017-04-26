package com.envista.msi.api.web.rest.dto;

import com.envista.msi.api.web.rest.dto.reports.SavedSchedReportsDto;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sreenivas on 4/17/2017.
 */

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "UserDetailsMapper", classes = {
                @ConstructorResult(
                        targetClass = UserDetailsDto.class,
                        columns = {
                                @ColumnResult(name = "is_email_to_be_sent",type = Boolean.class),
                                @ColumnResult(name = "user_id",type = Long.class),
                                @ColumnResult(name = "email",type = String.class),
                                @ColumnResult(name = "access_from",type = Integer.class),
                                @ColumnResult(name = "is_active",type = Boolean.class),
                                @ColumnResult(name = "from_email",type = String.class),
                                @ColumnResult(name = "user_name",type = String.class),
                                @ColumnResult(name = "land_on_reports_page",type = Boolean.class)
                        }
                )
        })
})
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "UserProfileTb.getUserDetailsById", procedureName = "shp_user_details_byuserid_proc",
                resultSetMappings = "UserDetailsMapper",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "UserDetails", type = void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class)
                }
        )
})

@Entity
public class UserDetailsDto {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "is_email_to_be_sent")
    private Boolean emailToBeSent;

    @Column(name = "email")
    private String email;

    @Column(name = "access_from")
    private Integer accessFrom;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name = "from_email")
    private String fromEmail;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "land_on_reports_page")
    private Boolean landOnReportsPage;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getEmailToBeSent() {
        return emailToBeSent;
    }

    public void setEmailToBeSent(Boolean emailToBeSent) {
        this.emailToBeSent = emailToBeSent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAccessFrom() {
        return accessFrom;
    }

    public void setAccessFrom(Integer accessFrom) {
        this.accessFrom = accessFrom;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getLandOnReportsPage() {
        return landOnReportsPage;
    }

    public void setLandOnReportsPage(Boolean landOnReportsPage) {
        this.landOnReportsPage = landOnReportsPage;
    }

    public  UserDetailsDto(){}

    public UserDetailsDto(Boolean emailToBeSent,Long userId,String email,Integer accessFrom,Boolean active,String fromEmail,String userName,Boolean landOnReportsPage){
        this.userId = userId;
        this.emailToBeSent = emailToBeSent;
        this.email = email;
        this.accessFrom = accessFrom;
        this.active = active;
        this.fromEmail = fromEmail;
        this.userName = userName;
        this.landOnReportsPage = landOnReportsPage;
    }
}
