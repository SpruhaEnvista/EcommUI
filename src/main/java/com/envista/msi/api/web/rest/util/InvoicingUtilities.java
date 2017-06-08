package com.envista.msi.api.web.rest.util;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by KRISHNAREDDYM on 5/1/2017.
 */
public final class InvoicingUtilities {

    private static Logger LOG = LoggerFactory.getLogger(InvoicingUtilities.class);


    public static String identifyFedexCreditClass(String reaon) {
        // TODO Auto-generated method stub
        String creditClass;
        if (StringUtils.containsIgnoreCase(reaon, "COMMERCIAL") || StringUtils.containsIgnoreCase(reaon, "RESI") || StringUtils.containsIgnoreCase(reaon, "BUS")
                || StringUtils.containsIgnoreCase(reaon, "DELIVERED"))
            creditClass = "Resi";
        else if (StringUtils.containsIgnoreCase(reaon, "DUPLI") || StringUtils.containsIgnoreCase(reaon, "DOUBLE"))
            creditClass = "Dup";
        else if (StringUtils.containsIgnoreCase(reaon, "DIM"))
            creditClass = "Dim";
        else if (StringUtils.containsIgnoreCase(reaon, "SERVICE") || StringUtils.containsIgnoreCase(reaon, "LATE") || StringUtils.containsIgnoreCase(reaon, "GSR")
                || StringUtils.containsIgnoreCase(reaon, "P.O") || StringUtils.containsIgnoreCase(reaon, "PROOF") || StringUtils.containsIgnoreCase(reaon, "POD"))
            creditClass = "GSR";
        else if (StringUtils.containsIgnoreCase(reaon, "AREA SUR") || StringUtils.containsIgnoreCase(reaon, "DEL SUR"))
            creditClass = "DAS";
        else if (StringUtils.containsIgnoreCase(reaon, "ADULT"))
            creditClass = "Adult Signature";
        else if (StringUtils.containsIgnoreCase(reaon, "DAMAGED"))
            creditClass = "DAMAGED";
        else if (StringUtils.containsIgnoreCase(reaon, "ADDRESS WAS COR"))
            creditClass = "ADDY";
        else if (StringUtils.containsIgnoreCase(reaon, "SAT"))
            creditClass = "Saturday";
        else if (StringUtils.containsIgnoreCase(reaon, "FUEL"))
            creditClass = "Fuel Surcharge";
        else if (StringUtils.containsIgnoreCase(reaon, "FRAUD"))
            creditClass = "Third Party";
        else if (StringUtils.containsIgnoreCase(reaon, "CLB"))
            creditClass = "Closed Loop Billing";
        else
            creditClass = "Other";

        return creditClass;
    }

    public static String identifyUpsCreditClass(String tranCode, String reason, String service) {
        String creditClass = "";
        if (StringUtils.containsIgnoreCase(tranCode, "VOD") || StringUtils.containsIgnoreCase(reason, "VOID"))
            creditClass = "Void";
        else if (StringUtils.containsIgnoreCase(reason, "COMMERCIAL ADDRESS") || StringUtils.containsIgnoreCase(reason, "RESIDENT"))
            creditClass = "Resi";
        else if (StringUtils.containsIgnoreCase(reason, "DUP") || StringUtils.containsIgnoreCase(reason, "NOT PREVIOUSLY BILLED") || StringUtils.containsIgnoreCase(reason, "NPB"))
            creditClass = "Dup";
        else if (StringUtils.containsIgnoreCase(reason, "DIM") || StringUtils.containsIgnoreCase(reason, "WEIGHT"))
            creditClass = "Dim";
        else if (StringUtils.containsIgnoreCase(reason, "REMBOURSEMENT DE SERVICE GARANTI") || StringUtils.containsIgnoreCase(tranCode, "GSR"))
            creditClass = "GSR";
        else if (StringUtils.containsIgnoreCase(reason, "ADDRESS") && StringUtils.containsNone(reason, "COMMERCIAL ADDRESS"))
            creditClass = "ADDY";
        else if (StringUtils.containsIgnoreCase(reason, "SAT"))
            creditClass = "Saturday";
        else if (StringUtils.containsIgnoreCase(reason, "ADDINTIAONAL HAND"))
            creditClass = "Addintiaonal Handling";
        else if (StringUtils.containsIgnoreCase(reason, "LARGE P"))
            creditClass = "Large Package";
        else if (StringUtils.containsIgnoreCase(reason, "FUEL"))
            creditClass = "Fuel Surcharge";
        else if (StringUtils.containsIgnoreCase(reason, "THIRD PARTY") || StringUtils.containsIgnoreCase(reason, "TP") || StringUtils.containsIgnoreCase(reason, "UNAUTHORIZED"))
            creditClass = "Third Party";
        else if (StringUtils.containsIgnoreCase(service, "GST") || StringUtils.containsIgnoreCase(service, "HST") || StringUtils.containsIgnoreCase(service, "VAT"))
            creditClass = "TAX";
        else if (StringUtils.containsIgnoreCase(service, "ZONE ADJUSTMENT"))
            creditClass = "Zone Adjustment";
        else if (StringUtils.containsIgnoreCase(service, "CORP REBATE"))
            creditClass = "Corporate Rebates";
        else if (StringUtils.containsIgnoreCase(reason, "BILLING ADJUSTMENT FOR W/E"))
            creditClass = "Billing Adjustment";
        else if (StringUtils.containsIgnoreCase(reason, "CLB"))
            creditClass = "Closed Loop Billing";
        else if (StringUtils.equals(reason, ",,,,") || StringUtils.containsIgnoreCase(reason, "DUP BILLING") || StringUtils.containsIgnoreCase(reason, "DUPLICATE SHIPMENTS")
                || StringUtils.containsIgnoreCase(reason, "SHIPMENT NOT PREVIOUSLY BILLED") || StringUtils.containsIgnoreCase(reason, "RESIDENTIAL SERVICE ADJUSTMENT"))
            creditClass = "Misc";
        else
            creditClass = "Other";

        return creditClass;
    }

    public static Timestamp getCurrentTimeStamp() {

        return new java.sql.Timestamp(new java.util.Date().getTime());

    }

    public static JSONArray getBuissnessPartnersList() throws JSONException {
        JSONArray jsonArray = new JSONArray();

        JSONObject object = new JSONObject();

        object.put("id", 0);
        object.put("name", "MSI");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", 1);
        object.put("name", "Lojistic");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", 2);
        object.put("name", "Trans Audit");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", 3);
        object.put("name", "PC Synergy");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", 4);
        object.put("name", "RAF Logistics");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", 5);
        object.put("name", "First One Management");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", 6);
        object.put("name", "Compete Consulting");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", 7);
        object.put("name", "iDrive");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", 8);
        object.put("name", "Kenco Logistic Services");
        jsonArray.put(object);


        return jsonArray;

    }

    public static JSONArray getFlagValueList() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("flag", "Y");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("flag", "N");
        jsonArray.put(object);


        return jsonArray;

    }

}
