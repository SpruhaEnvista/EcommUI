package com.envista.msi.api.web.rest.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * This utility class is specially designed to keep all methods which are commonly used/to be used over different module in Avatar project.
 *
 * Created by Sujit kumar on 21/06/2017.
 */
public class CommonUtil {

    /**
     * Used to get response in the form of string by connecting to the server through given URL and request payload.
     * This is basically a POST call to the server, requestXML is the request payload in XML format.
     *
     * On exception, it returns an empty string.
     *
     * @param urlString
     * @param requestXML
     * @return
     * @throws Exception
     */
    public static String connectAndGetResponseAsString(String urlString, String requestXML) {

        StringBuffer respStr = new StringBuffer();
        BufferedReader in = null;
        OutputStreamWriter out = null;
        try {
            URL url = new URL(urlString);

            URLConnection connection;
            connection = url.openConnection();
            connection.setRequestProperty("Content-Type", "application/*+xml");
            connection.setDoOutput(true);
            out = new OutputStreamWriter(connection.getOutputStream(), Charset.forName("UTF-8"));
            out.write(requestXML.trim());
            out.close();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String resp = "";
            while ((resp = in.readLine()) != null) {
                respStr.append(resp);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respStr.toString();
    }

    public static String hitUrl(String urlString){
        BufferedReader in = null;
        StringBuffer respStr = new StringBuffer();
        try {
            URL url = new URL(urlString);

            URLConnection connection = null;
            connection = url.openConnection();
            connection.setRequestProperty("Content-Type", "text/html");
            connection.setDoOutput(true);

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String resp = "";
            while ((resp = in.readLine()) != null) {
                respStr.append(resp);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respStr.toString();
    }

    /**
     * Convert the given number into 2 decimal format.
     * This is a convenient way to obtain a DecimalFormat when internationalization is not the main concern.
     * @param value
     * @return
     */
    public static String toDecimalFormat(Double value){
        return toDecimalFormat(value, "0.00");
    }

    /**
     * Convert number into given decimal format.
     * This is a convenient way to obtain a DecimalFormat when internationalization is not the main concern.
     * @param value
     * @param pattern
     * @return
     */
    public static String toDecimalFormat(Double value, String pattern){
        if(null == value){
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);
    }

    public static String decimalNumberToCommaReadableFormat(Double value){
        return decimalNumberToCommaReadableFormat(value, "#,###.00");
    }

    public static String decimalNumberToCommaReadableFormat(Double value, String pattern){
        if(null == value){
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);
    }

    public static void generateCSVFromJson (JSONArray resultsArray ,PrintStream out) throws Exception
    {
        String lineSeprator = "\n" ;
        String strDelimeter = "," ;
        String optionalDelimeter = "\"" ;

        JSONArray firstRowData = resultsArray.getJSONArray(0);
        int noOfColumns = firstRowData.length();

        // Setting headers
        StringBuffer headerInfo = new StringBuffer();
        for(int i=0; i< noOfColumns; i++){
            JSONObject columnInfo = firstRowData.getJSONObject(i);
            boolean isColumnExist = false;

                headerInfo.append(columnInfo.getString("header"));
                if (i < noOfColumns-1)
                    headerInfo.append(strDelimeter);
                else
                    headerInfo.append(lineSeprator);

        }
        out.print(headerInfo.toString());

        int noOfRows = resultsArray.length();

        for( int i=0 ;i < noOfRows ; i++ ) {

            JSONArray eachRowData = resultsArray.getJSONArray(i);
            StringBuffer dataInfo = new StringBuffer();
            for (int j = 0; j < noOfColumns; j++) {
                Object object = null;
                JSONObject columnInfo = eachRowData.getJSONObject(j);
                if(columnInfo.has("value"))
                    object = columnInfo.get("value");
                else
                object = "";

                dataInfo.append(optionalDelimeter+"\t").append(object.toString().replaceAll("\"", "")).append(optionalDelimeter);
                if (j < noOfColumns-1)
                    dataInfo.append(strDelimeter);
                else
                    dataInfo.append(lineSeprator);


                //break;
            }
            out.print(dataInfo.toString());
            out.flush();
        }



    }

    public static Workbook generateXlsxFromJson (JSONArray resultsArray ) throws Exception {

        Workbook workbook = new SXSSFWorkbook(100);
        Row row = null;
        Cell cell = null;
        int rowCount = 0;
        String sheetName = "Sheet1";
        int count = 0;
        Sheet sheet = workbook.createSheet(sheetName);
        CreationHelper createHelper = workbook.getCreationHelper();

        try {
            sheet.setDefaultColumnWidth(15);
            sheet.setDefaultRowHeight((short) 240);

            row = sheet.createRow(rowCount++);
            JSONArray firstRowData = resultsArray.getJSONArray(0);
            int noOfColumns = firstRowData.length();

            Font dataFont = workbook.createFont();
            dataFont.setFontName("Tahoma");
            dataFont.setFontHeightInPoints((short) 8);

            Font headerFont = workbook.createFont();
            headerFont.setFontName("Tahoma");
            headerFont.setUnderline(Font.U_SINGLE);
            headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            headerFont.setFontHeightInPoints((short) 8);

            CellStyle headerStyle= workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);


            CellStyle cellStyles [] = new CellStyle[ noOfColumns];
            String dataTypes[] = new String[noOfColumns];

            // Setting headers
            for ( int i=0; i< noOfColumns; i++ ) {
                JSONObject columnInfo = firstRowData.getJSONObject(i);
                String dataType = columnInfo.getString("dataType");
                String format = columnInfo.getString("format");

                if ( "NUMBER".equalsIgnoreCase(dataType) ) {
                    headerStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                } else {
                    headerStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                }

                cell = row.createCell(i);
                cell.setCellValue(columnInfo.getString("header"));
                cell.setCellStyle(headerStyle);

                CellStyle cellStyle= workbook.createCellStyle();
                cellStyle.setFont(dataFont);


                if ( "NUMBER".equalsIgnoreCase(dataType) ) {
                    cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                } else {
                    cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                }

                if ( format != null && !"".equals(format.trim()) ) {
                    cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(format));
                }

                cellStyles[i] =  cellStyle;
                dataTypes[i] = dataType;

            }

            int noOfRows = resultsArray.length();

            for( int i=0 ;i < noOfRows ; i++ ) {

                row = sheet.createRow(rowCount++);

                JSONArray eachRowData = resultsArray.getJSONArray(i);
                for ( int j=0; j< noOfColumns; j++ ) {

                    JSONObject columnInfo = eachRowData.getJSONObject(j);
                    cell = row.createCell(j);
                    Object columnValue = "";
                    String dataType = dataTypes[j];

                    if (columnInfo.has("value")) {
                        columnValue = columnInfo.get("value");
                    }

                    if ("NUMBER".equalsIgnoreCase(dataType)) {
                        DecimalFormat decimalFormat = new DecimalFormat("#.00");
                        if( !"".equals(columnValue.toString().trim())) {
                            cell.setCellValue(Double.parseDouble(decimalFormat.format(Double.parseDouble(columnValue.toString()))));
                        } else {
                            cell.setCellValue("");
                        }
                    } else if ("DATE".equalsIgnoreCase(dataType)) {
                        if( !"".equals(columnValue.toString().trim())) {
                            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = (Date) formatter.parse(columnValue.toString());
                            cell.setCellValue(date);
                        } else {
                            cell.setCellValue("");
                        }
                    } else
                        cell.setCellValue((String) columnValue);

                    cell.setCellStyle(cellStyles[j]);
                }
            }


        }  catch ( Exception ex) {
            throw  ex;
        } finally {
            if ( workbook != null) {
               // workbook.close();
            }
        }
        return workbook;
    }


    public static Workbook generateXlsxFromJson(JSONArray dataJSONArray, Map<String,String> headersDtMap, Map<String,String> headersPropMap, String sheetname ) throws Exception {

        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        try {

            int rowCount = 0;

            sheet = workbook.createSheet(sheetname);
            CreationHelper createHelper = workbook.getCreationHelper();
            //CellStyle cellStyle[] = new CellStyle[headersDtMap.size()];

            // Excel row with headers with cell format
            row = sheet.createRow(rowCount++);
            Set<String> headersSet =  headersDtMap.keySet();
            String[] headerarray = new String[headersSet.size()];
            headersSet.toArray(headerarray);

            for (int i = 1; i <=headersDtMap.size(); i++) {
                cell = row.createCell(i - 1);
                CellStyle style = workbook.createCellStyle();//Create style
                Font font = workbook.createFont();//Create font
                font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
                style.setFont(font);//set it to bold
                if(headersDtMap != null )
                    cell.setCellValue(headerarray[i-1]);
                    cell.setCellStyle(style);

                    /*cellStyle[i - 1] = workbook.createCellStyle();
                    cellStyle[i - 1].setDataFormat(createHelper.createDataFormat().getFormat(headersDtMap.get(i)));*/

                sheet.setDefaultColumnWidth(15);
            }

            for (int j = 1; j <=dataJSONArray.length(); j++)  {

                JSONObject carrJson = (JSONObject)dataJSONArray.get(j-1);
                if (carrJson != null) {
                    row = sheet.createRow(rowCount++);
                    for (int i = 1; i <= headerarray.length; i++) {
                        String headerKey = headerarray[i-1];
                        String propertyKey = headersPropMap.get(headerKey);
                        String headerDataType = headersDtMap.get(headerKey);
                        cell = row.createCell(i - 1);


                        cell.setCellValue(carrJson.getString(propertyKey));

                        if(i>1)
                        {
                            CellStyle cellStyle = workbook.createCellStyle();
                            cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
                            cellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
                            cell.setCellStyle(cellStyle);

                            /*CellStyle cellStyle = cell.getCellStyle();
                            cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
                            cell.setCellStyle(cellStyle);*/
                        }


                        if("NUMBER".equalsIgnoreCase(headerDataType))
                        {
                            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        }
                        else{
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }

                    }//End of each Cell

                }//End of carrJson

            } // End Of ResultSet


            //workbook.dispose(); // we have to check whether it will not cause problem when streaming to browser.
        } catch (Exception e) {
            throw e;
        } finally {

        }

        return workbook;
    }

    public static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
