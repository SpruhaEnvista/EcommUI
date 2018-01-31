package com.envista.msi.api.web.rest.util;

import com.envista.msi.api.dao.invoicing.DashBoardDao;
import com.envista.msi.api.web.rest.dto.glom.RunReportDto;
import com.envista.msi.api.web.rest.dto.invoicing.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
@Component
public class FileOperations {

    private static Logger LOG = LoggerFactory.getLogger(FileOperations.class);

    @Value("${FILESERVER}")
    private String fileServer;
    private static final String MIME_TYPE = "application/text";

    @Autowired
    DashBoardDao dao;

    private String fileNameForXLSX = "";
    private String fileNameForCSV = "";
    private FileWriter writer = null;
    private final String COMMA_SEPARATOR = ",";

    public static void main(String[] args) throws IOException {

        FileOperations fileOperations = new FileOperations();

        fileOperations.customOmitFileUploadOperation(null, 0L,"Voids",1L);
    }

    private String getFileServerAbsolutePath(String physicalFileName) throws FileNotFoundException {

        String drive = physicalFileName.substring(physicalFileName.lastIndexOf('\\')+1, physicalFileName.length());
        physicalFileName = fileServer + drive;
        if (!(new File(physicalFileName)).exists()) {
            physicalFileName = physicalFileName.replace("$", "");
        }

        return physicalFileName;
    }

    public Map<String,Object> customOmitFileUploadOperation(MultipartFile file, Long fileInfoId,String fileType,Long fileTypeId) throws IOException {
        LOG.info("***customOmitFileUploadOperation method started***");

        List<CreditResponseDto> dtos = new ArrayList<CreditResponseDto>();
        Map<String,Object> resObject=new HashMap<String,Object>();
        FileOutputStream outputStream = null;
        String filePath = InvConstants.filePath;
        String fileName=file.getOriginalFilename();
        File dir = new File(filePath);
        int count = 0;
        if (!dir.exists())
            dir.mkdirs();
        try {
            String savedFilepath = dir + "/" + new SimpleDateFormat("yyyyMMddhhmm").format(InvoicingUtilities.getCurrentTimeStamp()) + "_" + file.getOriginalFilename();

            outputStream = new FileOutputStream(savedFilepath);
            outputStream.write(file.getBytes());
            outputStream.close();
            String line = "";
            //String cvsSplitBy = ",";
            BufferedReader br = null;
            try{
             if(fileName.endsWith(".csv")) {
                br = new BufferedReader(new FileReader(savedFilepath));
            }
            else if(fileName.endsWith(".zip")) {
                ZipFile zipFile = new ZipFile(savedFilepath);
                final Enumeration<? extends ZipEntry> entries = zipFile.entries();
                int numberOfFilesCount = 0;
                ZipEntry entry = null;
                while (entries.hasMoreElements()) {
                    if(numberOfFilesCount==0)
                     entry = (ZipEntry)entries.nextElement();
                    if (!entry.isDirectory()) {
                        ++numberOfFilesCount;
                    }
                    if(numberOfFilesCount >= 2 ){
                        break;
                    }
                }
                if(numberOfFilesCount >=2){
                    resObject.put("error","Please upload a single .csv file in archive.");
                    return resObject;
                }
                if(entry != null)
                    br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));
            }
                FileDefDto fileDefDto=null;
                while ((line = br.readLine()) != null) {
                    if(count == 0){
                        line=line.replaceAll("\"","");
                        if(fileType != null && fileType.equalsIgnoreCase("GSRs")){
                            line=line.replaceAll(",,,,,,,,,,,,,,,,","").replaceAll(",","*").concat("*");

                        }else{
                            line=line.replaceAll(",","*").concat("*");
                        }

                        fileDefDto= dao.validateFileType(fileTypeId,line);
                        if(fileDefDto == null){
                            resObject.put("error","Please upload a valid file format.");
                            break;
                        }
                    }else if (count != 0 && null != fileDefDto) {
                        /*if (StringUtils.containsIgnoreCase(line, "\"")) {
                            line = StringUtils.remove(line, "\"");
                        }*/
                        String[] lineArray = CommonUtil.parseDemilitedLineWithMutipleDoubleQuotesInBetween(line, ',');

                        if(lineArray != null && lineArray.length >0){
                            CreditResponseDto dto=null;
                            if(fileType != null && fileType.equalsIgnoreCase("Voids")){
                                if( lineArray[3] != null && !lineArray[3].trim().equals("") ){
                                    dto = new CreditResponseDto();
                                    dto.setFileInfoId(fileInfoId);
                                    dto.setCustomerCode(lineArray[1]);
                                    dto.setTrackingNumber(lineArray[3] != null?lineArray[3].replace("\'",""):"");
                                    dto.setNotes(lineArray[10] != null ?lineArray[10].replace("\'","").replaceAll("\"",""):"");
                                    dto.setStatus(lineArray[16]);
                                }


                            }else if(fileType != null && fileType.equalsIgnoreCase("GSRs")){
                                if( lineArray[0] != null  && !lineArray[0].trim().equals("")) {
                                    dto = new CreditResponseDto();
                                    dto.setFileInfoId(fileInfoId);
                                    dto.setTrackingNumber(lineArray[0] != null ? lineArray[0].replace("\'", "") : "");
                                    dto.setNotes(lineArray[6] != null ? lineArray[6].replaceAll("\"", "") : "");
                                    dto.setStatus("Approved");
                                }

                            }else if(fileType != null && fileType.equalsIgnoreCase("Address Corrections and Residentials")){
                                if( lineArray[0] != null  && !lineArray[0].trim().equals("")) {
                                    dto = new CreditResponseDto();
                                    dto.setFileInfoId(fileInfoId);
                                    dto.setTrackingNumber(lineArray[0] != null ? lineArray[0].replace("\'", "") : "");
                                    dto.setNotes(lineArray[6] != null ? lineArray[6].replaceAll("\"", "") : "");
                                    dto.setStatus("Approved");
                                }

                            }else if(fileType != null && fileType.equalsIgnoreCase("Hazmat")){
                                if( lineArray[0] != null  && !lineArray[0].trim().equals("")) {
                                    dto = new CreditResponseDto();
                                    dto.setFileInfoId(fileInfoId);
                                    dto.setTrackingNumber(lineArray[0] != null ? lineArray[0].replace("\'", "") : "");
                                    dto.setNotes(lineArray[4] != null ? lineArray[4].replaceAll("\"", "") : "");
                                    dto.setStatus("Approved");
                                }

                            }
                            if(dto != null){
                                dtos.add(dto);
                            }

                        }
                    }

                    count++;

                }
                if(fileDefDto != null) {
                    resObject.put("dtos", dtos);
                }

            } catch (IOException e) {
                System.out.println(count);
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println(count);
            LOG.error("***Exception Occurred in the Custom omits File upload ***");
            e.printStackTrace();
        }

        return resObject;
    }
    /*
        This method exports the voices data
     */
    public void exportVoices(String exportType, List<VoiceDto> voiceDtos,HttpServletResponse response) throws IOException {
        LOG.info("***exportVoices method started****");

        FileOutputStream fOut = null;
        BufferedOutputStream bOut=null;
        String filePath = getFileServerAbsolutePath("Invoicing");
        File dir=new File(filePath);
        if (!dir.exists())
            dir.mkdirs();

        if(exportType.equalsIgnoreCase("XLS") || exportType.equalsIgnoreCase("XLSX")){
            LOG.info("***EXCEL type export****");
            @SuppressWarnings("resource")
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Voice");

            Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
            data.put(1, new Object[] {"VOICE ID", "VOICE NAME","VOICE TYPE","PARENT VOICE NAME","COMMENTS"});
            int count = 1;
            for(VoiceDto voiceDto:voiceDtos){
                count++;
                data.put(count, new Object[] {
                        voiceDto.getVoiceId() != null?voiceDto.getVoiceId():"",
                        voiceDto.getVoiceName() != null?voiceDto.getVoiceName().toString():"",
                        voiceDto.getVoiceType() != null?voiceDto.getVoiceType().toString():"",
                        voiceDto.getParentVoiceName() != null?voiceDto.getParentVoiceName().toString():"",
                        voiceDto.getComments() != null? voiceDto.getComments().toString():""
                });
            }

            Set<Integer> keyset = data.keySet();
            int rownum = 0;
            //First row Font style
            XSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            XSSFCellStyle cellStyle= workbook.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            excelFileWriteAsPerColumnTypes(keyset,rownum,sheet,data,cellStyle);

            try{
                fileNameForXLSX=filePath+"/VoicesXLSX_"+new SimpleDateFormat("yyyyMMddhhmmss").format(InvoicingUtilities.getCurrentTimeStamp())+".xlsx";

                if(exportType.equalsIgnoreCase("XLSX")){
                    fOut=new FileOutputStream(new File(fileNameForXLSX));
                    bOut=new BufferedOutputStream(fOut);
                    LOG.info("***" +fileNameForXLSX+" File Created *** ");
                }
                workbook.write(bOut);
                fileDownloadFromServer(response,new File(fileNameForXLSX));
            }
            catch (Exception e)
            {
                LOG.error("***Exception Occurred in the VoicesExport EXCEL export ***");
                e.printStackTrace();
            }
            finally{
                fOut.close();
                bOut.close();

            }
        }else if(exportType.equalsIgnoreCase("CSV")){
            LOG.info("***Voices CSV type export****");
            try {
                fileNameForCSV=filePath+"/VoicesCSV_"+new SimpleDateFormat("yyyyMMddhhmmss").format(InvoicingUtilities.getCurrentTimeStamp())+".csv" ;
                writer = new FileWriter(fileNameForCSV,false);
                writer.append("VOICE ID");
                writer.append(COMMA_SEPARATOR);
                writer.append("VOICE NAME");
                writer.append(COMMA_SEPARATOR);
                writer.append("VOICE TYPE");
                writer.append(COMMA_SEPARATOR);
                writer.append("PARENT VOICE NAME");
                writer.append(COMMA_SEPARATOR);
                writer.append("COMMENTS");
                writer.append('\n');
                for(VoiceDto voiceDto:voiceDtos){
                    writer.append(voiceDto.getVoiceId() != null?voiceDto.getVoiceId().toString().trim():"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(voiceDto.getVoiceName() != null?StringEscapeUtils.escapeCsv(voiceDto.getVoiceName().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(voiceDto.getVoiceType() !=null?StringEscapeUtils.escapeCsv(voiceDto.getVoiceType().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(voiceDto.getParentVoiceName() != null?StringEscapeUtils.escapeCsv(voiceDto.getParentVoiceName().toString().trim()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append( voiceDto.getComments() != null? StringEscapeUtils.escapeCsv(voiceDto.getComments().toString().trim()):"");
                    writer.append('\n');
                }
            }catch(Exception e){
                LOG.error("***Exception Occurred in the exportVoices CSV export ***");
                e.printStackTrace();
            }finally{
                writer.flush();
                writer.close();
                fileDownloadFromServer(response,new File(fileNameForCSV));
            }

        }

    }

    /*
        This method exports the CustomOmits data
     */
    public void exportCustomOmits(String exportType,List<CustomOmitsDto> customOmitsDtos,HttpServletResponse response) throws IOException {
        LOG.info("***exportCustomOmits method started****");

        FileOutputStream fOut = null;
        BufferedOutputStream bOut=null;

        String filePath = getFileServerAbsolutePath("Invoicing");
        File dir=new File(filePath);
        if (!dir.exists())
            dir.mkdirs();

        if(exportType.equalsIgnoreCase("XLS") || exportType.equalsIgnoreCase("XLSX")){
            LOG.info("***EXCEL type export****");
            @SuppressWarnings("resource")
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Custom Omits");

            Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
            data.put(1, new Object[] {"CUSTOM OMIT ID", "TRACKING NUMBER","CUSTOMER","CREDIT TYPE","CARRIER","EXPIRY DATE","COMMENTS"});
            int count = 1;
            for(CustomOmitsDto customOmitsDto:customOmitsDtos){
                count++;
                data.put(count, new Object[] {
                        customOmitsDto.getCustomOmitsId() != null?customOmitsDto.getCustomOmitsId():"",
                        customOmitsDto.getTrackingNumber() != null?customOmitsDto.getTrackingNumber().toString():"",
                        customOmitsDto.getCustomerName() != null?customOmitsDto.getCustomerName().toString():"",
                        customOmitsDto.getCreditType() != null?customOmitsDto.getCreditType().toString():"",
                        customOmitsDto.getCarrierName() != null?customOmitsDto.getCarrierName().toString():"",
                        customOmitsDto.getExpiryDate() != null?customOmitsDto.getExpiryDate().toString():"",
                        customOmitsDto.getComments() != null?customOmitsDto.getComments().toString():""
                });
            }

            Set<Integer> keyset = data.keySet();
            int rownum = 0;
            //First row Font style
            XSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            XSSFCellStyle cellStyle= workbook.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            excelFileWriteAsPerColumnTypes(keyset,rownum,sheet,data,cellStyle);

            try{
                fileNameForXLSX=filePath+"/CustomOmitsXLSX_"+new SimpleDateFormat("yyyyMMddhhmmss").format(InvoicingUtilities.getCurrentTimeStamp())+".xlsx";

                if(exportType.equalsIgnoreCase("XLSX")){
                    fOut=new FileOutputStream(new File(fileNameForXLSX));
                    bOut=new BufferedOutputStream(fOut);
                    LOG.info("***" +fileNameForXLSX+" File Created *** ");
                }
                workbook.write(bOut);
                fileDownloadFromServer(response,new File(fileNameForXLSX));
            }
            catch (Exception e)
            {
                LOG.error("***Exception Occurred in the Custom omits EXCEL export ***");
                e.printStackTrace();
            }
            finally{
                fOut.close();
                bOut.close();
            }
        }else if(exportType.equalsIgnoreCase("CSV")){
            LOG.info("***CustomOmits CSV type export****");
            try {
                fileNameForCSV=filePath+"/CustomOmitsCSV_"+new SimpleDateFormat("yyyyMMddhhmmss").format(InvoicingUtilities.getCurrentTimeStamp())+".csv";
                writer = new FileWriter(fileNameForCSV,false);
                writer.append("CUSTOM OMIT ID");
                writer.append(COMMA_SEPARATOR);
                writer.append("TRACKING NUMBER");
                writer.append(COMMA_SEPARATOR);
                writer.append("CUSTOMER");
                writer.append(COMMA_SEPARATOR);
                writer.append("CREDIT TYPE");
                writer.append(COMMA_SEPARATOR);
                writer.append("CARRIER");
                writer.append(COMMA_SEPARATOR);
                writer.append("EXPIRY DATE");
                writer.append(COMMA_SEPARATOR);
                writer.append("COMMENTS");
                writer.append('\n');
                for(CustomOmitsDto customOmitsDto:customOmitsDtos){
                    writer.append(customOmitsDto.getCustomOmitsId() != null?customOmitsDto.getCustomOmitsId().toString():"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(customOmitsDto.getTrackingNumber() != null?StringEscapeUtils.escapeCsv(customOmitsDto.getTrackingNumber().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(customOmitsDto.getCustomerName() != null?StringEscapeUtils.escapeCsv(customOmitsDto.getCustomerName().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(customOmitsDto.getCreditType() != null?StringEscapeUtils.escapeCsv(customOmitsDto.getCreditType().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append( customOmitsDto.getCarrierName() != null?StringEscapeUtils.escapeCsv(customOmitsDto.getCarrierName().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(customOmitsDto.getExpiryDate() != null?StringEscapeUtils.escapeCsv(customOmitsDto.getExpiryDate().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(customOmitsDto.getComments() != null?StringEscapeUtils.escapeCsv(customOmitsDto.getComments().toString()):"");
                    writer.append('\n');
                }
            }catch(Exception e){
                LOG.error("***Exception Occurred in the exportCustomOmits CSV export ***");
                e.printStackTrace();
            }finally{
                writer.flush();
                writer.close();
                fileDownloadFromServer(response,new File(fileNameForCSV));
            }
        }
    }

    /*
        This method exports the PendingCredits data
     */
    public void exportPendingCredits(String exportType,List<CreditsPRDto> pendingCreditsDtos,HttpServletResponse response) throws IOException {
        LOG.info("***exportPendingCredits method started****");

        FileOutputStream fOut = null;
        BufferedOutputStream bOut=null;
        String filePath = getFileServerAbsolutePath("Invoicing");
        File dir=new File(filePath);
        if (!dir.exists())
            dir.mkdirs();

        if(exportType.equalsIgnoreCase("XLS") || exportType.equalsIgnoreCase("XLSX")){
            LOG.info("***EXCEL type export****");
            @SuppressWarnings("resource")
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Pending Credits");

            Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
            data.put(1, new Object[] {"CUSTOMER CODE","CARRIER","TRACKING NUMBER","SHIPPER NUMBER","INVOICE NUMBER","INVOICE DATE","SHIP FROM","SHIP TO","REFERENCE NUMBER","REASON","WEEK END DATE","CREDIT AMOUNT","CLAIM FLAG","REVIEW FLAG","CREDIT CLASS","COMMENTS"});
            int count = 1;
            for(CreditsPRDto pendingCreditsDto:pendingCreditsDtos){
                count++;
                data.put(count, new Object[] {
                        pendingCreditsDto.getCustomerCode() != null?pendingCreditsDto.getCustomerCode():"",
                        pendingCreditsDto.getCarrierName() != null?pendingCreditsDto.getCarrierName().toString():"",
                        pendingCreditsDto.getTrackingNumber() != null?pendingCreditsDto.getTrackingNumber().toString():"",
                        pendingCreditsDto.getShipperNumber() != null?pendingCreditsDto.getShipperNumber().toString():"",
                        pendingCreditsDto.getInvoiceNumber() != null?pendingCreditsDto.getInvoiceNumber().toString():"",
                        pendingCreditsDto.getInvoiceDate() != null?pendingCreditsDto.getInvoiceDate().toString():"",
                        pendingCreditsDto.getShipFrom() != null?pendingCreditsDto.getShipFrom().toString():"",
                        pendingCreditsDto.getShipTo() != null?pendingCreditsDto.getShipTo().toString():"",
                        pendingCreditsDto.getRefNumber() != null?pendingCreditsDto.getRefNumber().toString():"",
                        pendingCreditsDto.getReason() != null?pendingCreditsDto.getReason().toString():"",
                        pendingCreditsDto.getWeekEndDate() != null?pendingCreditsDto.getWeekEndDate().toString():"",
                        pendingCreditsDto.getCreditAmount() != null?pendingCreditsDto.getCreditAmount().toString():"",
                        pendingCreditsDto.getClaimFlag() != null ? pendingCreditsDto.getClaimFlag().toString() : "",
                        pendingCreditsDto.getReviewFlag() != null?pendingCreditsDto.getReviewFlag().toString():"",
                        pendingCreditsDto.getCreditClass() != null?pendingCreditsDto.getCreditClass().toString():"",
                        pendingCreditsDto.getComments() != null?pendingCreditsDto.getComments().toString():""
                });
            }

            Set<Integer> keyset = data.keySet();
            int rownum = 0;
            //First row Font style
            XSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            XSSFCellStyle cellStyle= workbook.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            excelFileWriteAsPerColumnTypes(keyset,rownum,sheet,data,cellStyle);

            try{
                fileNameForXLSX=filePath+"/PendingCreditsXLSX_"+new SimpleDateFormat("yyyyMMddhhmmss").format(InvoicingUtilities.getCurrentTimeStamp())+".xlsx";

                if(exportType.equalsIgnoreCase("XLSX")){
                    fOut=new FileOutputStream(new File(fileNameForXLSX));
                    bOut=new BufferedOutputStream(fOut);
                    LOG.info("***" +fileNameForXLSX+" File Created ***");
                }
                workbook.write(bOut);
                fileDownloadFromServer(response,new File(fileNameForXLSX));

            }
            catch (Exception e)
            {
                LOG.error("***Exception Occurred in the pending credits EXCEL export ***");
                e.printStackTrace();
            }
            finally{
                fOut.close();
                bOut.close();
            }
        }else if(exportType.equalsIgnoreCase("CSV")){
            LOG.info("***PendingCredits CSV type export****");
            try {
                fileNameForCSV=filePath+ "/PendingCreditsCSV_"+new SimpleDateFormat("yyyyMMddhhmmss").format(InvoicingUtilities.getCurrentTimeStamp())+".csv" ;
                writer = new FileWriter(fileNameForCSV,false);
                writer.append("CUSTOMER CODE");
                writer.append(COMMA_SEPARATOR);
                writer.append("CARRIER");
                writer.append(COMMA_SEPARATOR);
                writer.append("TRACKING NUMBER");
                writer.append(COMMA_SEPARATOR);
                writer.append("SHIPPER NUMBER");
                writer.append(COMMA_SEPARATOR);
                writer.append("INVOICE NUMBER");
                writer.append(COMMA_SEPARATOR);
                writer.append("INVOICE DATE");
                writer.append(COMMA_SEPARATOR);
                writer.append("SHIP FROM");
                writer.append(COMMA_SEPARATOR);
                writer.append("SHIP TO");
                writer.append(COMMA_SEPARATOR);
                writer.append("REFERENCE NUMBER");
                writer.append(COMMA_SEPARATOR);
                writer.append("REASON");
                writer.append(COMMA_SEPARATOR);
                writer.append("WEEK END DATE");
                writer.append(COMMA_SEPARATOR);
                writer.append("CREDIT AMOUNT");
                writer.append(COMMA_SEPARATOR);
                writer.append("CLAIM FLAG");
                writer.append(COMMA_SEPARATOR);
                writer.append("REVIEW FLAG");
                writer.append(COMMA_SEPARATOR);
                writer.append("CREDIT CLASS");
                writer.append(COMMA_SEPARATOR);
                writer.append("COMMENTS");
                writer.append('\n');
                for(CreditsPRDto pendingCreditsDto:pendingCreditsDtos){
                    writer.append(pendingCreditsDto.getCustomerCode() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getCustomerCode()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getCarrierName() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getCarrierName().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getTrackingNumber() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getTrackingNumber().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getShipperNumber() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getShipperNumber().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append( pendingCreditsDto.getInvoiceNumber() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getInvoiceNumber().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getInvoiceDate() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getInvoiceDate().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getShipFrom() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getShipFrom().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getShipTo() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getShipTo().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getRefNumber() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getRefNumber().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getReason() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getReason().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getWeekEndDate() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getWeekEndDate().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getCreditAmount() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getCreditAmount().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getClaimFlag() != null ? StringEscapeUtils.escapeCsv(pendingCreditsDto.getClaimFlag().toString()) : "");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getReviewFlag() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getReviewFlag()).toString():"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getCreditClass() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getCreditClass().toString()):"");
                    writer.append(COMMA_SEPARATOR);
                    writer.append(pendingCreditsDto.getComments() != null?StringEscapeUtils.escapeCsv(pendingCreditsDto.getComments().toString()):"");
                    writer.append('\n');
                }
            }catch(Exception e){
                LOG.error("***Exception Occurred in the exportPendingCredits CSV export ***");
                e.printStackTrace();
            }finally{
                writer.flush();
                writer.close();
                fileDownloadFromServer(response,new File(fileNameForCSV));
            }
        }

    }

    public void excelFileWriteAsPerColumnTypes(Set<Integer> keyset,int rownum,XSSFSheet sheet,Map<Integer, Object[]> data, XSSFCellStyle cellStyle){
        for (Integer key : keyset){
            Row row = sheet.createRow(rownum++) ;
            sheet.autoSizeColumn(key);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr){
                Cell cell = row.createCell(cellnum++);
                if(rownum ==1){
                    cell.setCellStyle(cellStyle);
                }
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj+"");
                else if(obj instanceof Date)
                    cell.setCellValue((Date)obj);
                else if(obj instanceof Date)
                    cell.setCellValue((Date)obj);
                else if(obj instanceof Long)
                    cell.setCellValue((Long)obj);
            }
        }

    }

    public void fileDownloadFromServer(HttpServletResponse response,File file) throws IOException{
        InputStream in = new FileInputStream(file);
        response.setContentType(MIME_TYPE);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
        in.close();
    }


    /*
    This method exports the GLOM report data
 */
    public void exportGlmReportData(List<RunReportDto> dtos) throws IOException {
        LOG.info("***exportGlmReportData method started****");

        FileOutputStream fOut = null;
        BufferedOutputStream bOut = null;
        String filePath = getFileServerAbsolutePath("GLOM");
        File dir = new File(filePath);
        if (!dir.exists())
            dir.mkdirs();


        LOG.info("***EXCEL type export****");
        @SuppressWarnings("resource")
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Voice");

        Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
        data.put(1, new Object[]{"INVOICE ID", "INVOICE NUMBER", "BOL NUMBER", "GL ACCOUNT CODE", "INVOICE MODE", "CUSTOMER NAME"
                , "CARRIER NAME", "CUSTOMER ID", "SERVICE CODE ID", "PRO NUMBER"});
        int count = 1;
        for (RunReportDto dto : dtos) {
            count++;
            data.put(count, new Object[]{
                    dto.getInvoiceId() != null ? dto.getInvoiceId() : "",
                    dto.getInvoiceNumber() != null ? dto.getInvoiceNumber().toString() : "",
                    dto.getBolNumber() != null ? dto.getBolNumber().toString() : "",
                    dto.getGlAccountCode() != null ? dto.getGlAccountCode().toString() : "",
                    dto.getInvoiceMode() != null ? dto.getInvoiceMode().toString() : "",
                    dto.getCustomerName() != null ? dto.getCustomerName().toString() : "",
                    dto.getCarrierName() != null ? dto.getCarrierName().toString() : "",
                    dto.getCustomerId() != null ? dto.getCustomerId().toString() : "",
                    dto.getServiceCodeId() != null ? dto.getServiceCodeId().toString() : "",
                    dto.getProNumber() != null ? dto.getProNumber().toString() : ""
            });
        }

        Set<Integer> keyset = data.keySet();
        int rownum = 0;
        //First row Font style
        XSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        excelFileWriteAsPerColumnTypes(keyset, rownum, sheet, data, cellStyle);

        try {
            fileNameForXLSX = filePath + "/GLOMSCRIPT_REPORT_" + new SimpleDateFormat("yyyyMMddhhmmss").format(InvoicingUtilities.getCurrentTimeStamp()) + ".xlsx";

                /*if(exportType.equalsIgnoreCase("XLSX")){*/
            fOut = new FileOutputStream(new File(fileNameForXLSX));
            bOut = new BufferedOutputStream(fOut);
            LOG.info("***" + fileNameForXLSX + " File Created *** ");
            //}
            workbook.write(bOut);
            //fileDownloadFromServer(response,new File(fileNameForXLSX));
        } catch (Exception e) {
            LOG.error("***Exception Occurred in the GLOMSCRIPT_REPORT_ EXCEL export ***" + fileNameForXLSX);
            e.printStackTrace();
        } finally {
            if (fOut != null)
                fOut.close();
            if (bOut != null)
                bOut.close();

        }


    }
}
