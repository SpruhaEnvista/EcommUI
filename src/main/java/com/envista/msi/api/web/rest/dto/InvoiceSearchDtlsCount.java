package com.envista.msi.api.web.rest.dto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by user1 on 2/2/2017.
 */
@Entity
public class InvoiceSearchDtlsCount implements Serializable{

    @Id
    @Column(name="ROWCOUNT",unique = true)
    private Integer recordCount;

    public InvoiceSearchDtlsCount() {
    }

    public InvoiceSearchDtlsCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }
}
