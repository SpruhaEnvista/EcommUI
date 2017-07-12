package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.rtr.ParcelRTRService;
import com.envista.msi.api.web.rest.response.ErrorResponse;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public void auditParcelInvoice(String fromDate, String toDate, String customerId){
        try{
            parcelRTRService.parcelRTRRating(fromDate, toDate, customerId);
        }catch (InvalidDataAccessResourceUsageException e){
            //Need to check this case later. Keeping for testing.
        }
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleParcelAuditExceptions(Exception e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage(null == e.getMessage() ? "Internal Server Error" : e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
