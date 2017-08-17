package com.envista.msi.api.web.rest.util;

import com.envista.msi.api.web.rest.dto.invoicing.CreditResponseDto;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRDto;
import com.envista.msi.api.web.rest.dto.invoicing.CustomOmitsDto;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceDto;
import org.apache.commons.lang.StringUtils;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
@Component
public class FileOperations {

    private static Logger LOG = LoggerFactory.getLogger(FileOperations.class);

    @Value("${FILESERVER}")
    private String fileServer;
    private static final String MIME_TYPE = "application/text";

    String fileNameForXLSX="";

    public static void main(String[] args) throws IOException {

        FileOperations fileOperations = new FileOperations();

        fileOperations.customOmitFileUploadOperation(null, 0L);
    }

    public String getFileServerAbsolutePath(String physicalFileName) throws FileNotFoundException {

        String drive = physicalFileName.substring(physicalFileName.lastIndexOf('\\')+1, physicalFileName.length());
        physicalFileName = fileServer + drive;
        if (!(new File(physicalFileName)).exists()) {
            physicalFileName = physicalFileName.replace("$", "");
        }

        return physicalFileName;
    }

    public List<CreditResponseDto> customOmitFileUploadOperation(MultipartFile file, Long fileInfoId) throws IOException {
        LOG.info("***customOmitFileUploadOperation method started***");

        List<CreditResponseDto> dtos = new ArrayList<CreditResponseDto>();
        FileOutputStream outputStream = null;
        String filePath = InvConstants.filePath;
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
            String cvsSplitBy = ",";

            try (BufferedReader br = new BufferedReader(new FileReader(savedFilepath))) {
                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    if (count != 0) {
                        if (StringUtils.containsIgnoreCase(line, "\"")) {
                            line = StringUtils.remove(line, "\"");
                        }
                        String[] lineArray = line.split(cvsSplitBy);

                        CreditResponseDto dto = new CreditResponseDto();
                        dto.setFileInfoId(fileInfoId);
                        dto.setCustomerCode(lineArray[1]);
                        dto.setTrackingNumber(lineArray[3]);
                        dto.setNotes(lineArray[10]);
                        dto.setStatus(lineArray[16]);
                        dtos.add(dto);
                    }

                    count++;

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

        return dtos;
    }
    /*
        This method exports the voices data
     */
    public void exportVoices(String exportType, List<VoiceDto> voiceDtos,HttpServletResponse response) throws IOException {
        LOG.info("***exportVoices method started****");

        FileOutputStream out = null;
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
                        voiceDto.getComments() != null?voiceDto.getComments().toString():""
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
                    out = new FileOutputStream(new File(fileNameForXLSX),false);
                    LOG.info("***" +fileNameForXLSX+" File Created *** ");
                }
                workbook.write(out);
                fileDownloadFromServer(response,new File(fileNameForXLSX));
            }
            catch (Exception e)
            {
                LOG.error("***Exception Occurred in the VoicesExport EXCEL export ***");
                e.printStackTrace();
            }
            finally{
                out.close();

            }
        }

    }

    /*
        This method exports the CustomOmits data
     */
    public void exportCustomOmits(String exportType,List<CustomOmitsDto> customOmitsDtos,HttpServletResponse response) throws IOException {
        LOG.info("***exportCustomOmits method started****");

        FileOutputStream out = null;
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
                    out = new FileOutputStream(new File(fileNameForXLSX),false);
                    LOG.info("***" +fileNameForXLSX+" File Created *** ");
                }
                workbook.write(out);
                fileDownloadFromServer(response,new File(fileNameForXLSX));
            }
            catch (Exception e)
            {
                LOG.error("***Exception Occurred in the Custom omits EXCEL export ***");
                e.printStackTrace();
            }
            finally{
                out.close();
            }
        }

    }

    /*
        This method exports the PendingCredits data
     */
    public void exportPendingCredits(String exportType,List<CreditsPRDto> pendingCreditsDtos,HttpServletResponse response) throws IOException {
        LOG.info("***exportPendingCredits method started****");

        FileOutputStream out = null;
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
            data.put(1, new Object[] {"CUSTOMER CODE","CARRIER","TRACKING NUMBER","SHIPPER NUMBER","INVOICE NUMBER","INVOICE DATE","SHIP FROM","SHIP TO","REFERENCE NUMBER","REASON","WEEK END DATE","CREDIT AMOUNT","OMIT FLAG","REVIEW FLAG","COMMENTS"});
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
                        pendingCreditsDto.getOmitFlag() != null?pendingCreditsDto.getOmitFlag().toString():"",
                        pendingCreditsDto.getReviewFlag() != null?pendingCreditsDto.getReviewFlag().toString():"",
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
                    out = new FileOutputStream(new File(fileNameForXLSX),false);
                    LOG.info("***" +fileNameForXLSX+" File Created ***");
                }
                workbook.write(out);
                fileDownloadFromServer(response,new File(fileNameForXLSX));

            }
            catch (Exception e)
            {
                LOG.error("***Exception Occurred in the pending credits EXCEL export ***");
                e.printStackTrace();
            }
            finally{
                out.close();
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
    }

}
