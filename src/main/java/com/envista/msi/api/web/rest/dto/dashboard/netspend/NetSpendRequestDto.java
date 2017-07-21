package com.envista.msi.api.web.rest.dto.dashboard.netspend;

import java.io.Serializable;

/**
 * Created by Sujit kumar on 03/02/2017.
 */
public class NetSpendRequestDto implements Serializable{
    private String carrierName;
    private String invoiceDate;
    private String carrierDetails;

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCarrierDetails() {
        return carrierDetails;
    }

    public void setCarrierDetails(String carrierDetails) {
        this.carrierDetails = carrierDetails;
    }
}
