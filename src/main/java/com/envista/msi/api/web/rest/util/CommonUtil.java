package com.envista.msi.api.web.rest.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                cell = row.createCell(i);
                cell.setCellValue(columnInfo.getString("header"));
                cell.setCellStyle(headerStyle);

                CellStyle cellStyle= workbook.createCellStyle();
                cellStyle.setFont(dataFont);

                String dataType = columnInfo.getString("dataType");
                String format = columnInfo.getString("format");

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

}
