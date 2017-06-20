package com.envista.msi.api.web.rest.util;

import com.envista.msi.api.web.rest.dto.invoicing.UvVoiceUpdateBean;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceDto;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    public static JSONArray getUVActions() throws JSONException {
        JSONArray jsonArray = new JSONArray();

        JSONObject object = new JSONObject();

        object.put("id", "IgnoreandOmit");
        object.put("name", "Ignore and Omit");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "IgnoreandClaim");
        object.put("name", "Ignore and Claim");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "AddAsLikelyNotOurVoiceOmitbutReview");
        object.put("name", "Add As Likely Not Our Voice (Omit but Review)");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "AddAsLikelyOurVoiceClaimbutReview");
        object.put("name", "Add As Likely Our Voice (Claim but Review)");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "AddAsNotOurVoiceOmit)");
        object.put("name", "Add As Not Our Voice (Omit)");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "AddAsCompetitorNotOurVoice");
        object.put("name", "Add As Competitor (Not Our Voice)");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "AddAsOurVoiceClaim");
        object.put("name", "Add As Our Voice (Claim)");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "-1");
        object.put("name", "-----------Select Voice-----------");
        jsonArray.put(object);


        return jsonArray;

    }

    public static JSONArray prepareVoiceArray(List<VoiceDto> dtos) throws JSONException {

        JSONArray jsonArray = new JSONArray();


        JSONObject object = new JSONObject();

        object.put("id", "IgnoreandOmit");
        object.put("name", "Ignore and Omit");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "IgnoreandClaim");
        object.put("name", "Ignore and Claim");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "AddAsLikelyNotOurVoiceOmitbutReview");
        object.put("name", "Add As Likely Not Our Voice [Omit but Review]");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "AddAsLikelyOurVoiceClaimbutReview");
        object.put("name", "Add As Likely Our Voice [Claim but Review]");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "AddAsNotOurVoiceOmit");
        object.put("name", "Add As Not Our Voice [Omit]");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "AddAsCompetitorNotOurVoice");
        object.put("name", "Add As Competitor [Not Our Voice]");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "AddAsOurVoiceClaim");
        object.put("name", "Add As Our Voice [Claim]");
        jsonArray.put(object);

        object = new JSONObject();
        object.put("id", "-1");
        object.put("name", "-----------Select Voice-----------");
        jsonArray.put(object);


        for (VoiceDto dto : dtos) {
            object = new JSONObject();

            object.put("id", dto.getVoiceId());
            object.put("name", dto.getVoiceName());
            jsonArray.put(object);
        }

        return jsonArray;
    }

    public static List<UvVoiceUpdateBean> prepareUvUpdateBean(JSONObject json) throws JSONException {
        List<UvVoiceUpdateBean> beans = new ArrayList<UvVoiceUpdateBean>();
        JSONArray jsonArray = json.getJSONArray("myJSON");
        for (int i = 0; i < jsonArray.length(); i++) {
            UvVoiceUpdateBean bean = new UvVoiceUpdateBean();
            JSONObject object = jsonArray.getJSONObject(i);


            String[] idsArray = StringUtils.split(object.getString("ids"), "~");
            String[] omitFlagArray = StringUtils.split(object.getString("omitFlags"), "~");
            String[] invCommentsArray = StringUtils.split(object.getString("invoiceComments"), "~");

            for (int arr = 0; arr < idsArray.length; arr++) {
                bean = new UvVoiceUpdateBean();
                bean.setEbillManifestId(new Long(idsArray[arr]));
                bean.setOmitFlag(omitFlagArray[arr]);
                bean.setInternalInvComments(invCommentsArray[arr]);
                bean.setActionName(object.getString("action"));
                bean.setVoiceName(object.getString("name"));
                bean.setVoiceComments(object.getString("voiceComments"));

                if (NumberUtils.isNumber(bean.getActionName())) {
                    bean.setVoiceId(Long.valueOf(bean.getActionName()));
                    bean.setActionName(null);
                } else
                    bean.setVoiceId(0L);

                beans.add(bean);

            }
        }

        return beans;
    }

}
