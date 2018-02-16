package com.envista.msi.api.web.rest.util;

import com.envista.msi.api.dao.invoicing.DashBoardDao;
import com.envista.msi.api.web.rest.dto.invoicing.CreditResponseDto;
import com.envista.msi.api.web.rest.dto.invoicing.FileDefDto;
import com.envista.msi.api.web.rest.dto.invoicing.UvVoiceUpdateBean;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceDto;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.text.ParseException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.FileInputStream;

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
        object.put("name", "Add As Competitor [Omit]");
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
            String[] claimFlagArray = StringUtils.split(object.getString("claimFlags"), "~");
            String[] invCommentsArray = StringUtils.split(object.getString("invoiceComments"), "~");
            String userName = object.getString("userName");

            for (int arr = 0; arr < idsArray.length; arr++) {
                bean = new UvVoiceUpdateBean();
                bean.setEbillManifestId(new Long(idsArray[arr]));
                bean.setClaimFlag(claimFlagArray[arr]);
                bean.setInternalInvComments(invCommentsArray[arr]);
                bean.setActionName(object.getString("action"));
                bean.setVoiceName(object.getString("name"));
                bean.setUserName(userName);
                if (arr == 0)
                    bean.setVoiceComments(object.getString("voiceComments"));
                else
                    bean.setVoiceComments(null);

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
    public static Map<String,Object> processXLSXFile(File file,Long fileInfoId,Long fileTypeId,DashBoardDao dao) throws Exception{
        List<CreditResponseDto> dtos = new ArrayList<CreditResponseDto>();
        Map<String,Object> resObject=new HashMap<String,Object>();
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook w = new XSSFWorkbook(fis);
        int numberOfSheets=0;
        CreditResponseDto dto = null;
        XSSFSheet	sheet;

        try {
            int totalRowsProcessed = 0;
            int totalRowsUpdated = 0;
            LinkedHashMap<String, Integer> headerLocations;

            numberOfSheets = w.getNumberOfSheets();
            if(numberOfSheets>0) {
                FileDefDto fileDefDto = null;
                String line="";
                for (int i = 0; i < numberOfSheets; i++) {
                    sheet = w.getSheetAt(i);
                    if (sheet.getSheetName().equalsIgnoreCase("Voids")
                            || sheet.getSheetName().equalsIgnoreCase("Rate Errors")
                            || sheet.getSheetName().equalsIgnoreCase("Accessorial Errors")
                            || sheet.getSheetName().equalsIgnoreCase("Resi Comm Adjustment")
                            || sheet.getSheetName().equalsIgnoreCase("Dups")) {
                        headerLocations = returnHeaderLocations(sheet);
                        if(headerLocations != null && headerLocations.size()>0) {
                            Set<String> keys = headerLocations.keySet();
                            line="";
                            for (String k : keys) {
                                if(!"STARTFROM".equalsIgnoreCase(k)) {
                                    line = line.concat(k).concat("*");
                                 }
                            }
                        }
                        fileDefDto = dao.validateFileType(fileTypeId, line);
                        if (fileDefDto == null) {
                            resObject.put("error", "Please upload a valid file format.");
                            return resObject;
                        }

                        for (int row = headerLocations.get("STARTFROM") + 1; row < sheet.getLastRowNum(); row++) {

                            if (totalRowsProcessed % 100 == 0) {
                                 // System.out.println("Breakpoint " + totalRowsProcessed);
                            }
                            totalRowsProcessed++;
                            try {
                                dto = processEachRow(sheet, headerLocations, row, sheet.getSheetName(), fileInfoId);
                                if (dto != null)
                                    dtos.add(dto);
                                totalRowsUpdated++;
                            } catch (Exception e) {
                                if (e.getMessage() != null
                                        && "Tracking number for this row is null".equalsIgnoreCase(e.getMessage())) {
                                    totalRowsProcessed--;
                                    break;
                                }
                                e.printStackTrace();
                            }
                        }
                    }

                }
                if(fileDefDto != null) {
                    resObject.put("dtos", dtos);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resObject;
    }
    private static CreditResponseDto processEachRow(XSSFSheet sheet, HashMap<String, Integer> headerLocations, int row, String sheetName,Long fileInfoId) throws Exception {
        CreditResponseDto dto = null;
        String trackingNumber =  (String) getValueFromColumn(sheet, headerLocations, row, "Tracking Number", "String");
        /*String invoiceNumber = (String) getValueFromColumn(sheet, headerLocations, row, "Invoice #", "String");
        String invoiceDate =  (String) getValueFromColumn(sheet, headerLocations, row, "Invoice Date", "date");
        String shipperAccount = (String) getValueFromColumn(sheet, headerLocations, row, "Shipper #", "String");
        String netBilled = (String) getValueFromColumn(sheet, headerLocations, row, "Net Billed", "double");*/
        String reasonCredit = (String) getValueFromColumn(sheet, headerLocations, row, "Reason for Credit", "String");
        /*String creditRequestAmount = (String) getValueFromColumn(sheet, headerLocations, row, "Credit Request Amount", "double");
        String creditORdenial = (String) getValueFromColumn(sheet, headerLocations, row, "Credit / Denial", "String");
        String adjustmentAmount = (String) getValueFromColumn(sheet, headerLocations, row, "Adjustment Amount", "double");*/
        String adjustmentMessage = (String) getValueFromColumn(sheet, headerLocations, row, "Adjustment Message", "String");
        //String eBillManifestID = (String) getValueFromColumn(sheet, headerLocations, row, "E-Bill Manifest ID", "double");

        if(trackingNumber != null && trackingNumber.trim().length()>0 && reasonCredit != null && adjustmentMessage != null) {
            dto = new CreditResponseDto();
            dto.setFileInfoId(fileInfoId);
            dto.setTrackingNumber(trackingNumber);
            dto.setNotes(reasonCredit + ";" + adjustmentMessage);

            if(sheetName.equalsIgnoreCase("Voids"))
            dto.setCreditClass("Void");
            else if(sheetName.equalsIgnoreCase("Resi Comm Adjustment"))
                dto.setCreditClass("Resi");
            else if(sheetName.equalsIgnoreCase("Dups"))
                dto.setCreditClass("DUP");
           /* else if(sheetName.equalsIgnoreCase("Rate Errors"))
                dto.setCreditClass("Rate Errors");
            else if(sheetName.equalsIgnoreCase("Accessorial Errors"))
                dto.setCreditClass("Accessorial Errors");*/
        }
        return dto;

     }

    private static Object getValueFromColumn(XSSFSheet sheet, HashMap<String, Integer> headerLocations, int rowNum,
                                            String columnHeader, String type) throws  ParseException {
        if (type == null || type.isEmpty()) {
            return null;
        }

        int column = headerLocations.get(columnHeader);
        XSSFRow row = sheet.getRow(rowNum);
        String value=null;
        if (column > -1) {
            if ("String".equalsIgnoreCase(type)) {

                XSSFCell cell =  row.getCell(column) ;
               if(cell != null)
                  value = cell.getStringCellValue().replaceAll("[^\\p{Print}]", " ").replaceAll("\\s+", " ").trim();
                if(value != null)
                   return value;
                else
                    return null;
            } else if ("double".equalsIgnoreCase(type)) {
                XSSFCell cell =  row.getCell(column) ;
                DataFormatter fmt = new DataFormatter();


                  value= fmt.formatCellValue(cell);
                double netChargesCell = 0.0;
                try {
                    netChargesCell =  cell.getNumericCellValue();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
                 if(value != null)
                    return value;
                else
                    return null;
             }
			else if ("date".equalsIgnoreCase(type)) {
                XSSFCell cell =  row.getCell(column) ;
                Date date = cell.getDateCellValue();
                DataFormatter fmt = new DataFormatter();
                 String valueAsSeenInExcel = fmt.formatCellValue(cell);
                if(valueAsSeenInExcel != null)
                    return valueAsSeenInExcel;
                else
                    return null;
             } else {
                 return null;
            }
        }

        return null;
    }
        private static LinkedHashMap<String, Integer> returnHeaderLocations(XSSFSheet sheet) throws Exception {

            LinkedHashMap<String, Integer> headerLocations = new LinkedHashMap<String, Integer>();

           /* headerLocations.put("Tracking Number", -1);
            headerLocations.put("Invoice #", -1);
            headerLocations.put("Invoice Date", -1);
            headerLocations.put("Shipper #", -1);
            headerLocations.put("Net Billed", -1);
            headerLocations.put("Reason for Credit", -1);
            headerLocations.put("Credit Request Amount", -1);
            headerLocations.put("Credit / Denial", -1);
            headerLocations.put("Adjustment Amount", -1);
            headerLocations.put("Adjustment Message", -1);
            headerLocations.put("E-Bill Manifest ID", -1);*/

            int row = -1;// rowAndColumn[0];
            headerLocations.put("STARTFROM", row + 1);

            for (int i = 0; i < headerLocations.size(); i++) {
                XSSFCell headerCell = null;
                String headerContents;
                try {
                    XSSFRow row1 = sheet.getRow(row + 1);
                    headerCell = row1.getCell(i);
                    headerContents = headerCell.getStringCellValue().replaceAll("[^\\p{Print}]", " ").replaceAll("\\s+", " ")
                            .trim();
                } catch (Exception e) {
                    continue;
                }
                if (checkIfNullOrEmpty(headerContents)) {
                    continue;
                } else {
                    if (headerLocations.get(headerContents) != null) {
                        if (headerLocations.get(headerContents) == -1) {
                            headerLocations.put(headerContents, i);
                        } else {
                            headerLocations.put(headerContents + "-" + i, i);
                        }
                    } else {
                        headerLocations.put(headerContents, i);
                    }
                }
            }

            return headerLocations;
        }
    private static boolean checkIfNullOrEmpty(String contents) {
        if (contents == null || contents.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static void unZip(String zipFile, String OUTPUT_FOLDER) throws IOException{
        byte[] buffer = new byte[1024];
        try{

            File folder = new File(OUTPUT_FOLDER);
            if(!folder.exists()){
                folder.mkdir();
            }
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry ze = zis.getNextEntry();

            while(ze!=null){
                String fileName = ze.getName();
                File newFile = new File(OUTPUT_FOLDER + File.separator + fileName);
                 new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
         }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
