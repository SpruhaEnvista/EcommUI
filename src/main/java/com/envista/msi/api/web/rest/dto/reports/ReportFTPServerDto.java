package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Siddhant on 06/04/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportFTPServer.getFTPServer", procedureName = "shp_rpt_ftp_servers_proc",
                resultSetMappings = "FTPServer",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "customerIds", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "shipperGroupIds", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "shipperIds", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_ftp_server_info", type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = "ReportFTPServer.getSaveRptFTPServer", procedureName = "shp_rpt_saved_ftp_account_proc",
                resultSetMappings = "SaveRptFTPServer",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_saved_ftp_acc_info", type = Void.class)
        })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "FTPServer", classes = {
                @ConstructorResult(
                        targetClass = ReportFTPServerDto.class,
                        columns = {
                                @ColumnResult(name = "FTP_ACCOUNTS_ID", type = Long.class),
                                @ColumnResult(name = "FTP_ACCOUNT_NAME", type = String.class)
                        })
        }),
        @SqlResultSetMapping(name = "SaveRptFTPServer", classes = {
                @ConstructorResult(
                        targetClass = ReportFTPServerDto.class,
                        columns = {
                                @ColumnResult(name = "FTP_ACCOUNTS_ID", type = Long.class),
                                @ColumnResult(name = "FTP_ACCOUNT_NAME", type = String.class),
                                @ColumnResult(name = "IS_ACTIVE", type = Boolean.class)
                        })
        }),

})
@Entity
public class ReportFTPServerDto implements Serializable {

    @Id
    @Column(name="FTP_ACCOUNTS_ID")
    private Long ftpAccountId;

    @Column(name="FTP_ACCOUNT_NAME")
    private String ftpAccountName;

    @Column(name="IS_ACTIVE")
    private Boolean isActive;

    private String customerIds;

    private String shipperGroupIds;

    private String shipersIds;

    private Long rptId;

    public ReportFTPServerDto() { }

    public ReportFTPServerDto(Long ftpAccountId, String ftpAccountName) {
        this.ftpAccountId = ftpAccountId;
        this.ftpAccountName = ftpAccountName;
    }

    public ReportFTPServerDto(Long ftpAccountId, String ftpAccountName, Boolean isActive) {
        this.ftpAccountId = ftpAccountId;
        this.ftpAccountName = ftpAccountName;
        this.isActive = isActive;
    }

    public Long getFtpAccountId() {return ftpAccountId;}

    public void setFtpAccountId(Long ftpAccountId) { this.ftpAccountId = ftpAccountId; }

    public String getFtpAccountName() {return ftpAccountName; }

    public void setFtpAccountName(String ftpAccountName) { this.ftpAccountName = ftpAccountName;}

    public Boolean getIsActive() { return isActive; }

    public void setIsActive(Boolean active) { isActive = active; }

    public String getCustomerIds() { return customerIds;  }

    public void setCustomerIds(String customerIds) { this.customerIds = customerIds; }

    public String getShipperGroupIds() { return shipperGroupIds; }

    public void setShipperGroupIds(String shipperGroupIds) {  this.shipperGroupIds = shipperGroupIds; }

    public String getShipersIds() { return shipersIds; }

    public void setShipersIds(String shipersIds) { this.shipersIds = shipersIds; }

    public Long getRptId() { return rptId; }

    public void setRptId(Long rptId) { this.rptId = rptId; }
}
