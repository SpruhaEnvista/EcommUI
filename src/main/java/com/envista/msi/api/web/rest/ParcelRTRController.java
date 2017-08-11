package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.rtr.ParcelRTRService;
import com.envista.msi.api.web.rest.response.CommonResponse;
import com.envista.msi.api.web.rest.response.ErrorResponse;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by Sujit kumar on 08/06/2017.
 */

@RestController
@RequestMapping(value = "/api/parcel/rtr")
public class ParcelRTRController {

    @Inject
    private ParcelRTRService parcelRTRService;

    @RequestMapping(value = "/auditParcel", method = {RequestMethod.GET})
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

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleParcelAuditExceptions(Exception e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage(null == e.getMessage() ? "Internal Server Error" : e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
