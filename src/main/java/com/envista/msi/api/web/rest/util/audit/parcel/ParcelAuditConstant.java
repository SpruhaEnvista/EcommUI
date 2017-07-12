package com.envista.msi.api.web.rest.util.audit.parcel;

/**
 * Created by Sujit kumar on 10/07/2017.
 */
public class ParcelAuditConstant {
    public enum ChargeClassificationCode{
        FRT,
        FSC, //Fuel Surcharges
        ACS, // Accessorial for FedEx
        ACC // Accessorial for UPS
    }

    public enum ChargeDescriptionCode{
        DSC, // Discount
        FSC
    }
}
