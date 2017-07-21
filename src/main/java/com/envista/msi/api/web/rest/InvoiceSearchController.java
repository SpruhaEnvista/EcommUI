package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.DashboardsService;
import com.envista.msi.api.service.InvoiceLookupService;
import com.envista.msi.api.service.ReportsService;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.CarrierGroupDto;
import com.envista.msi.api.web.rest.dto.freight.invoice.InvoiceLookupParamsDto;
import com.envista.msi.api.web.rest.response.CommonResponse;
import com.envista.msi.api.web.rest.response.ErrorResponse;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by user1 on 1/19/2017.
 */
@RestController
@RequestMapping("/api/freight/inv")
public class InvoiceSearchController {

    private final Logger log = LoggerFactory.getLogger(InvoiceSearchController.class);

    @Inject
    InvoiceLookupService invoiceLookupService;

    @Inject
    UserService userService;

    @Inject
    private ReportsService reportsService;

    @Inject
    private DashboardsService dashboardsService;

    @RequestMapping(value = "/srchInvDflts", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> loadSearchInvoiceDefaults() throws Exception {
        CommonResponse response = new CommonResponse();
        response.setStatusCode(HttpStatus.OK.value());
        response.setData(invoiceLookupService.loadSearchInvoiceDefaults());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value="/cust/carr/{customerId}",method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarrierGroupDto>> getCarrierDetailsByCustomerId(@PathVariable String customerId){
        List<CarrierGroupDto> carriersList = null;
        if(customerId != null){
            carriersList = invoiceLookupService.getCarrierListByCustomerId(customerId);
        }
        return new ResponseEntity<List<CarrierGroupDto>>(carriersList, HttpStatus.OK);
    }

    @RequestMapping(value = "/invoicelist", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PaginationBean> loadInvoiceDetails(@RequestBody InvoiceLookupParamsDto invoiceLookupData) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(invoiceLookupService.loadInvoiceDetails(invoiceLookupData));
    }

    @ExceptionHandler({Exception.class})
    public ErrorResponse handleInvoiceSearchExceptions(){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Internal Server Error");
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorResponse;
    }
}
