package com.envista.msi.api.web.rest.util.audit.parcel;


import com.envista.msi.api.web.rest.util.CommonUtil;

/**
 * Created by Sujit kumar on 09/08/2017.
 */
public class ParcelAudit {
    //private static final String AUDIT_API_BASE_PATH_VALUE = "http://localhost:8086/api/parcel/rtr";
    private static final String AUDIT_API_BASE_PATH_VALUE = "https://avatar.myshipinfo.com/msiapi/api/parcel/rtr";

    public static void main(String[] args){
        String fromDate = "";
        String toDate = "";
        String customerId = "";

        try{fromDate = args[0].trim();}catch (Exception e){}
        try{toDate = args[1].trim();}catch (Exception e){}
        try{customerId = args[2].trim();}catch (Exception e){}

        if(fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()){
            System.out.println("Start Parcel Auditing...");
            String queryString = "fromDate=" +  fromDate + "&toDate=" + toDate + "&customerId=" + customerId;
            try {
                String url = AUDIT_API_BASE_PATH_VALUE + "/auditParcel" + "?" + queryString;
                String resp = CommonUtil.hitUrl(url);
                System.out.println(resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done Parcel Auditing...");
    }
}