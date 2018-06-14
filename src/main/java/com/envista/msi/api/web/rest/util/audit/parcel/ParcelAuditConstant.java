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
        FSC,
        RES,
        RSC
    }

    /**
     * Rating statuses.
     */
    public enum RTRStatus{
        READY_FOR_RATE("ReadyForRate"),
        CONTESTED("Contested"),
        CLOSED("Closed"),
        RATING_EXCEPTION("RatingException"),
        NO_PRICE_SHEET("NoPriceSheet"),
        OVER_CHARGED("OverCharged"),
        UNDER_CHARGED("UnderCharged");

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

    public static final String COMMERCIAL_ADJUSTMENT_CHARGE_TYPE = "COMMERCIAL ADJUSTMENT";
    public static final String RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE = "RESIDENTIAL ADJUSTMENT";
    public static final String RESIDENTIAL_COMMERCIAL_ADJUSTMENT_CHARGE_TYPE = "RESIDENTIAL/COMMERCIAL ADJ";


    public static final String MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME = "Parcel_Rating_MSI_AR_Code_Mapping";
    public static final String MSI_AR_DAS_CHARGE_CODE_NAME = "DeliveryAreaSurcharge";
    public static final String MSI_AR_LPS_CHARGE_CODE_NAME = "LargePackageSurcharge";
    public static final String MSI_AR_DECLARED_VALUE_CHARGE_CODE_NAME = "DeclaredValue";

    public static final String RATE_REQUEST_EVENT_DATE_FORMAT = "MM/dd/yyyy hh:mm";

    public static final String AR_RATE_REQUEST_LICENSE_KEY = "FRT23A9DB63C39VM2A0C";
    public static final String AR_RATE_REQUEST_PROTOCOL = "https";
    public static final String AR_RATE_REQUEST_HOST_NAME = "rtr02.envistacorp.com";
    public static final String AR_RATE_REQUEST_URI_PATH = "ws/freight/rate";

    public static final String RATE_JOB_ID_SEQUENCE_NAME = "SHP_RATING_JOB_S";
    public static final String RATE_JOB_ID_SEQUENCE_VALUE = "SHP_RATING_JOB_S.NEXTVAL";

    public enum ParcelRatingQueueRateStatus {
        READY_FOR_RATE(0),
        DONE(1),
        RATING_EXCEPTION(2),
        EMPTY_PRICE_SHEET(3);

        public final int value;

        ParcelRatingQueueRateStatus(int value) {
            this.value = value;
        }
    }

    public enum ParcelRatingInputProcessStatus{
        NEW("New"),
        RUNNING("Running"),
        COMPLETED("Completed"),
        EXCEPTION("Exception");

        public final String value;

        ParcelRatingInputProcessStatus(String value) {
            this.value = value;
        }
    }
}
