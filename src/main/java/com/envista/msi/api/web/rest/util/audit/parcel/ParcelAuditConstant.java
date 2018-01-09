package com.envista.msi.api.web.rest.util.audit.parcel;

/**
 * This class represents all the constants used in Parcel Audit.
 * Created by Sujit kumar on 10/07/2017.
 */
public class ParcelAuditConstant {

    /**
     * Charge classification codes used in parcel audit.
     */
    public enum ChargeClassificationCode{
        FRT,
        FSC, //Fuel Surcharges
        ACS, // Accessorial for FedEx
        ACC // Accessorial for UPS
    }

    /**
     * Charge description codes used in parcel audit.
     */
    public enum ChargeDescriptionCode{
        DSC, // Discount
        FSC
    }

    /**
     * Rating statuses.
     */
    public enum RTRStatus{
        READY_FOR_RATE("ReadyForRate"),
        CONTESTED("Contested"),
        CLOSED("Closed"),
        RATING_EXCEPTION("RatingException"),
        NO_PRICE_SHEET("NoPriceSheet");

        public final String value;

        RTRStatus(String value){
            this.value = value;
        }
    }

    /**
     * used to distinguish the rate type.
     */
    public enum RateTo{
        UPS,
        NON_UPS
    }

    /**
     * This name represents the last updated used name for parcel audit process.
     */
    public static final String PARCEL_RTR_RATING_USER_NAME = "ParcelRTRRating";

    /**
     * A constant to hold all non-ups carrier ids.
     * Multiple ids can be separated by comma.
     */
    public static final String NON_UPS_CARRIER_IDS = "22";

    public static final String EBILL_MANIFEST_TABLE_NAME = "SHP_EBILL_MANIFEST_TB";
    public static final String EBILL_GFF_TABLE_NAME = "SHP_EBILL_GFF_TB";
}
