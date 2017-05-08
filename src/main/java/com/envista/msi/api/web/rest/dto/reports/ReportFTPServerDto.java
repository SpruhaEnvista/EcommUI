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
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_ftp_server_info", type = Void.class)
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

})
@Entity
public class ReportFTPServerDto implements Serializable {

    @Id
    @Column(name="FTP_ACCOUNTS_ID")
    private Long ftpAccountId;

    @Column(name="FTP_ACCOUNT_NAME")
    private String ftpAccountName;

    private String customerIds;

    private String shipperGroupIds;

    private String shipersIds;

    private Long userId;

    public ReportFTPServerDto() { }

    public ReportFTPServerDto(Long ftpAccountId, String ftpAccountName) {
        this.ftpAccountId = ftpAccountId;
        this.ftpAccountName = ftpAccountName;
    }

    public Long getFtpAccountId() {return ftpAccountId;}

    public void setFtpAccountId(Long ftpAccountId) { this.ftpAccountId = ftpAccountId; }

    public String getFtpAccountName() {return ftpAccountName; }

    public void setFtpAccountName(String ftpAccountName) { this.ftpAccountName = ftpAccountName;}

    public String getCustomerIds() { return customerIds;  }

    public void setCustomerIds(String customerIds) { this.customerIds = customerIds; }

    public String getShipperGroupIds() { return shipperGroupIds; }

    public void setShipperGroupIds(String shipperGroupIds) {  this.shipperGroupIds = shipperGroupIds; }

    public String getShipersIds() { return shipersIds; }

    public void setShipersIds(String shipersIds) { this.shipersIds = shipersIds; }

    public Long getUserId() { return userId;  }

    public void setUserId(Long userId) {  this.userId = userId; }
}
