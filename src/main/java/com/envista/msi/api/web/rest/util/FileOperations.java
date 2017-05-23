package com.envista.msi.api.web.rest.util;

import com.envista.msi.api.web.rest.dto.invoicing.CreditResponseDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
@Component
public class FileOperations {

    private static Logger LOG = LoggerFactory.getLogger(FileOperations.class);

    public static void main(String[] args) throws IOException {

        FileOperations fileOperations = new FileOperations();

        fileOperations.customOmitFileUploadOperation(null);
    }

    public List<CreditResponseDto> customOmitFileUploadOperation(MultipartFile file) throws IOException {
        LOG.info("***customOmitFileUploadOperation method started***");

        List<CreditResponseDto> dtos = new ArrayList<CreditResponseDto>();
        FileOutputStream outputStream = null;
        String filePath = "D:/Invoicing/Custom_Omit_uploads";
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
}
