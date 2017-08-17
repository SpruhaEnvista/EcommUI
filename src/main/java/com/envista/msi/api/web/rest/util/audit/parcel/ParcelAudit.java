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
        String trackingNumbers = "";

        try{
            if(args[0] != null && !args[0].trim().equalsIgnoreCase("n")){
                fromDate = args[0].trim();
            }
        }catch (Exception e){}
        try{
            if(args[1] != null && !args[1].trim().equalsIgnoreCase("n")) {
                toDate = args[1].trim();
            }
        }catch (Exception e){}
        try{
            if(args[2] != null && !args[2].trim().equalsIgnoreCase("n")) {
                customerId = args[2].trim();
            }
        }catch (Exception e){}
        try{
            if(args[3] != null && !args[3].trim().equalsIgnoreCase("n")) {
                trackingNumbers = args[3].trim();
            }
        }catch (Exception e){}

        System.out.println("Start Parcel Auditing...");
        String queryString = "fromDate=" +  fromDate + "&toDate=" + toDate + "&customerId=" + customerId + "&trackingNumbers=" + trackingNumbers;
        try {
            String url = AUDIT_API_BASE_PATH_VALUE + "/auditParcel" + "?" + queryString;
            String resp = CommonUtil.hitUrl(url);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done Parcel Auditing...");
    }
}