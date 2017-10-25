package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "CreditResponseDto.insertOrUpdate", procedureName = "SHP_INV_INS_UPD_CRE_RESP_PRO",
                /*resultClasses = DashBoardDto.class,*/
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CREDIT_RESP_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_CODE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TRACKING_NUMBER", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_NOTES", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_STATUS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FILE_INFO_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FILE_TYPE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CREDIT_CLASS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class)
                        /*@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_CREDIT_RES_INFO", type = Void.class)*/
                })
})
@Entity
public class CreditResponseDto implements Serializable {

    @Id
    @Column(name = "CREDIT_RESP_ID")
    private Long id;

    @Column(name = "CUSTOMER_CODE")
    private String customerCode;

    @Column(name = "TRACKING_NUMBER")
    private String trackingNumber;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "INSERT_COUNT")
    private int insertCount;


    @Column(name = "FILE_INFO_ID")
    private Long fileInfoId;


    public CreditResponseDto() {
    }

    public CreditResponseDto(int insertCount) {
        this.insertCount = insertCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInsertCount() {
        return insertCount;
    }

    public void setInsertCount(int insertCount) {
        this.insertCount = insertCount;
    }

    public Long getFileInfoId() {
        return fileInfoId;
    }

    public void setFileInfoId(Long fileInfoId) {
        this.fileInfoId = fileInfoId;
    }

}
