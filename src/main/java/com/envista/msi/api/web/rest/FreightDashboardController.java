package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.DashboardsService;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.auditactivity.CustomisedFreightAuditSavingDto;
import com.envista.msi.api.web.rest.response.CommonResponse;
import com.envista.msi.api.web.rest.util.CommonUtil;
import com.envista.msi.api.web.rest.util.JSONUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sujit kumar on 02/01/2018.
 */

@RestController
@RequestMapping("/api/dashboards/frt")
public class FreightDashboardController extends DashboardBaseController{
    @Autowired
    private DashboardsService dashboardsService;

    @RequestMapping(value = "/frtSaving", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> getFreightSavings(){
        DashboardsFilterCriteria filter = populateDashboardFilterCriteria(dashboardsService.getUserAppliedFilter(getUserProfile().getUserId()));
        return prepareCommonResponseEntity(dashboardsService.getFreightAuditSavings(filter));
    }

    @RequestMapping(value = "/frtSavingsByAjdRsn", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> getFreightSavingsByCarrierByAdjustmentReason(){
        DashboardsFilterCriteria filter = populateDashboardFilterCriteria(dashboardsService.getUserAppliedFilter(getUserProfile().getUserId()));
        return prepareCommonResponseEntity(dashboardsService.getFreightSavingsByCarrierByAdjustmentReason(filter));
    }

    @RequestMapping(value = "/exportFrtSaving", method = {RequestMethod.GET}, produces = "application/text")
    public @ResponseBody void getExportFreightSavings( HttpServletResponse response)throws Exception{
        final JSONArray dataJSONArray=new JSONArray();
        Map<String,String> headersDtMap = new LinkedHashMap();
        Map<String,String> headersPropMap = new LinkedHashMap();

        DashboardsFilterCriteria filter = populateDashboardFilterCriteria(dashboardsService.getUserAppliedFilter(getUserProfile().getUserId()));
        List<CustomisedFreightAuditSavingDto> customisedFreightAuditSavingDtos = dashboardsService.getFreightAuditSavings(filter);

            for (CustomisedFreightAuditSavingDto customisedFreightAuditSavingDto : customisedFreightAuditSavingDtos) {
                dataJSONArray.put(new JSONObject(JSONUtil.ConvertObject2JSON(customisedFreightAuditSavingDto)));
            }
            headersDtMap.put("Carrier Name","String");
            headersDtMap.put("Invoiced Amount","String");
            headersDtMap.put("Approved Amount","String");
            headersDtMap.put("Freight Savings","String");
            headersDtMap.put("Savings","String");
            headersPropMap.put("Carrier Name","carrierName");
            headersPropMap.put("Invoiced Amount","invoicedAmount");
            headersPropMap.put("Approved Amount","approvedAmount");
            headersPropMap.put("Freight Savings","freightSaving");
            headersPropMap.put("Savings","savingPercentage");


        Workbook workbook= CommonUtil.generateXlsxFromJson(dataJSONArray,headersDtMap,headersPropMap,"Freight Audit Savings");
        response.setContentType("application/text");
        response.setHeader("Content-Disposition", "attachment; filename=Freight Audit Savings.xlsx");

        if (workbook != null) {
            workbook.write(response.getOutputStream());
            workbook.close();
        }
    }
    @RequestMapping(value = "/exportFrtSavingByAdj", method = {RequestMethod.GET}, produces = "application/text")
    public @ResponseBody void getExportFreightSavingsByCarrierByAdjustmentReason(HttpServletResponse response)throws  Exception{
        DashboardsFilterCriteria filter= populateDashboardFilterCriteria(dashboardsService.getUserAppliedFilter(getUserProfile().getUserId()));
        Map<String, List<CustomisedFreightAuditSavingDto>> freightAuditSvngByadjust= dashboardsService.getFreightSavingsByCarrierByAdjustmentReason(filter);

        Workbook workbook= CommonUtil.generateXlsxForFreightSvngsByAdjustRsn(freightAuditSvngByadjust);
        response.setContentType("application/text");
        response.setHeader("Content-Disposition", "attachment; filename=FREIGHT SAVINGS BY CARRIER BY ADJUSTMENT REASON.xlsx");

        if (workbook != null) {
            workbook.write(response.getOutputStream());
            workbook.close();
        }
    }

}
