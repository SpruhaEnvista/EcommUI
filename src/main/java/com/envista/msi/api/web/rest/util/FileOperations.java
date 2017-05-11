package com.envista.msi.api.web.rest.util;

import com.envista.msi.api.web.rest.dto.invoicing.CreditResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
@Component
public class FileOperations {

    private static Logger LOG = LoggerFactory.getLogger(FileOperations.class);

    public List<CreditResponseDto> customOmitFileUploadOperation(List<MultipartFile> files) throws IOException {
        LOG.info("***customOmitFileUploadOperation method started***");

        List<CreditResponseDto> dtos = new ArrayList<CreditResponseDto>();
        FileOutputStream outputStream = null;
        String filePath = "D:/Invoicing/Custom_Omit_uploads";
        File dir = new File(filePath);
        if (!dir.exists())
            dir.mkdirs();
        try {
            CreditResponseDto dto = new CreditResponseDto();
            dto.setCustomerCode("DALI");
            dto.setTrackingNumber("Test123");
            dto.setNotes("test notes");
            dto.setStatus("approved");
            dtos.add(dto);

            dto = new CreditResponseDto();
            dto.setCustomerCode("DALI");
            dto.setTrackingNumber("Test1234");
            dto.setNotes("test notes1");
            dto.setStatus("approved1");
            dtos.add(dto);

            dto = new CreditResponseDto();
            dto.setCustomerCode("DALI");
            dto.setTrackingNumber("Test12345");
            dto.setNotes("test notes2");
            dto.setStatus("approved2");
            dtos.add(dto);


            for (MultipartFile file : files) {
                outputStream = new FileOutputStream(dir + "/" + new SimpleDateFormat("yyyyMMddhhmm").format(InvoicingUtilities.getCurrentTimeStamp()) + "_" + file.getOriginalFilename());
                String savedFilepath = dir + "/" + new SimpleDateFormat("yyyyMMddhhmm").format(InvoicingUtilities.getCurrentTimeStamp()) + "_" + file.getOriginalFilename();
                LOG.info("***File Path --> " + savedFilepath + " ***");
                outputStream.write(file.getBytes());
                //readCustomOmitFileOperation(savedFilepath);
                outputStream.close();
            }
        } catch (Exception e) {
            LOG.error("***Exception Occurred in the Custom omits File upload ***");
            e.printStackTrace();
        }

        return dtos;
    }
}
