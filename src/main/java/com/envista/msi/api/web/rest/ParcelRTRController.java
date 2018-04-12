package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.rtr.ParcelRTRService;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.response.CommonResponse;
import com.envista.msi.api.web.rest.response.ErrorResponse;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sujit kumar on 08/06/2017.
 */

@RestController
@RequestMapping(value = "/api/parcel/rtr")
public class ParcelRTRController {

    @Inject
    private ParcelRTRService parcelRTRService;

    /*@RequestMapping(value = "/auditParcel", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommonResponse> auditParcelInvoice(@RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate,
                                                             @RequestParam(required = false) String customerId, @RequestParam(required = false) String trackingNumbers) {
        CommonResponse response = new CommonResponse();
        try{
            parcelRTRService.parcelRTRRating(customerId, fromDate, toDate, trackingNumbers);
        }catch (InvalidDataAccessResourceUsageException e){
            //Need to check this case later. This is causing because of transaction management.
        }
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Parcel Audit completed successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value = "/auditParcel", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE})
    public ResponseEntity<CommonResponse> auditParcelInvoice(@RequestBody String trackingNumbers) {
        CommonResponse response = new CommonResponse();
        try{
            parcelRTRService.parcelRTRRating(null, null, null, trackingNumbers);
        }catch (InvalidDataAccessResourceUsageException e){
            //Need to check this case later. This is causing because of transaction management.
        }
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Parcel Audit completed successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }*/

    @RequestMapping(value = "/auditParcelInv", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommonResponse> auditParcelInvoice(@RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate,
                                                             @RequestParam(required = false) String fromShipDate, @RequestParam(required = false) String toShipDate,
                                                             @RequestParam(required = false) String customerId, @RequestParam(required = false) String invoiceIds,
                                                             @RequestParam(required = false) String trackingNumbers, @RequestParam(required = false, defaultValue = "0") Integer limit,
                                                             @RequestParam(required = false) String rateTo) {
        String message = "Parcel Audit completed successfully";
        List<ParcelAuditDetailsDto> invoiceList = parcelRTRService.loadInvoiceIds(fromShipDate, toShipDate, customerId, invoiceIds, limit, rateTo);
        Map<String, Object> respMap = new HashMap<>();
        if(invoiceList != null && !invoiceList.isEmpty()){
            respMap.put("invoiceIds", invoiceList);
            parcelRTRService.doParcelAuditingInvoiceNumberWise(invoiceList, trackingNumbers, rateTo, fromShipDate, toShipDate, customerId);
        }else{
            message = "No Invoice found!";
        }
        CommonResponse response = new CommonResponse();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(message);
        response.setData(respMap);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleParcelAuditExceptions(Exception e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage(null == e.getMessage() ? "Internal Server Error" : e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
