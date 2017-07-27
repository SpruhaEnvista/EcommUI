package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.DashboardsService;
import com.envista.msi.api.service.InvoiceLookupService;
import com.envista.msi.api.service.ReportsService;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.CarrierGroupDto;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.freight.DynamicColumnsDto;
import com.envista.msi.api.web.rest.dto.freight.invoice.InvoiceLookupParamsDto;
import com.envista.msi.api.web.rest.response.CommonResponse;
import com.envista.msi.api.web.rest.response.ErrorResponse;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by user1 on 1/19/2017.
 */
@RestController
@RequestMapping("/api/freight/inv")
public class InvoiceSearchController {

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

    @RequestMapping(value = "/cust/carr/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarrierGroupDto>> getCarrierDetailsByCustomerId(@PathVariable String customerId) {
        List<CarrierGroupDto> carriersList = null;
        if (customerId != null) {
            carriersList = invoiceLookupService.getCarrierListByCustomerId(customerId);
        }
        return new ResponseEntity<List<CarrierGroupDto>>(carriersList, HttpStatus.OK);
    }

    @RequestMapping(value = "/invoicelist", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PaginationBean> loadInvoiceDetails(@RequestParam Map<String, String> invoiceLookupData) throws Exception {
        InvoiceLookupParamsDto lookupParams = new InvoiceLookupParamsDto();
        lookupParams.setUserId(userService.getLoggedInUser().getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(invoiceLookupService.loadInvoiceDetails(prepareInvoiceLookupParams(invoiceLookupData, lookupParams)));
    }

    private InvoiceLookupParamsDto prepareInvoiceLookupParams(Map<String, String> map, InvoiceLookupParamsDto lookupParams){
        if(map != null && !map.isEmpty()){
            for(Field field : InvoiceLookupParamsDto.class.getDeclaredFields()){
                field.setAccessible(true);
                try{
                    String fieldName = field.getName();
                    if(map.containsKey(fieldName)){
                        String val = map.get(fieldName);
                        if(val != null && !val.isEmpty()){
                            try {
                                if(val != null && !val.isEmpty()){
                                    if(field.getType().equals(Integer.class)){
                                        field.set(lookupParams, Integer.parseInt(val));
                                    }else if(field.getType().equals(Double.class)){
                                        field.set(lookupParams, Double.parseDouble(val));
                                    }else if(field.getType().equals(Long.class)){
                                        field.set(lookupParams, Long.parseLong(val));
                                    }else if(field.getType().equals(Float.class)){
                                        field.set(lookupParams, Float.parseFloat(val));
                                    }else if(field.getType().equals(BigDecimal.class)){
                                        field.set(lookupParams, new BigDecimal(val));
                                    }else if(field.getType().equals(Boolean.class)){
                                        field.set(lookupParams, Boolean.parseBoolean(val));
                                    } else{
                                        field.set(lookupParams, val);
                                    }
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                                //Nothing
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    //Nothing
                }
            }
        }
        return lookupParams;
    }

    @RequestMapping(value = "/dynaCols/save", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommonResponse> saveOrUpdateUserDynamicColumns(@RequestBody DynamicColumnsDto dynamicColumns) throws Exception {
        UserProfileDto user = userService.getLoggedInUser();
        dynamicColumns.setUserId(user.getUserId());
        dynamicColumns.setUserName(user.getUserName());
        dynamicColumns.setFilterId(0L);
        invoiceLookupService.saveOrUpdateDynamicColumns(dynamicColumns);
        CommonResponse response = new CommonResponse();
        response.setStatusCode(HttpStatus.OK.value());
        response.setData(invoiceLookupService.getUserDefinedColumnDetails());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleInvoiceSearchExceptions(){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Internal Server Error");
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
