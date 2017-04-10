package com.envista.msi.api.domain.util;

import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.filter.DashSavedFilterDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sujit kumar on 06/02/2017.
 */
public class DashboardUtil {

    /**
     * This method is used to set fromDate and toDate to the filter based on the monthAndYear Param.
     * @param filter
     * @param monthAndYear
     * @throws Exception
     */
    public static void setDatesFromMonth(DashboardsFilterCriteria filter, String monthAndYear) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String selectedMonth = monthAndYear.split("-")[0].trim();
        String lastTwoDigitsOfYear = monthAndYear.split(" ")[1].replace("-", "").trim();

        String yearOfFromDate = filter.getFromDate().split("-")[2];
        String monthOfFromDate = filter.getFromDate().split("-")[1];
        String yearOfToDate=filter.getToDate().split("-")[2];
        String monthOfToDate = filter.getToDate().split("-")[1];
        String firstTwoDigitsOfYear = yearOfFromDate.substring(0, 2);
        int year = 0;
        if (lastTwoDigitsOfYear.length() != 4) {
            year = Integer.parseInt(firstTwoDigitsOfYear + lastTwoDigitsOfYear);
        } else {
            year = Integer.parseInt(lastTwoDigitsOfYear);
        }

        Calendar c = Calendar.getInstance();
        Date date = (Date) new SimpleDateFormat("MMM", Locale.ENGLISH).parse(selectedMonth);
        c.setTime(date);
        int numericMonth = c.get(Calendar.MONTH);
        c.set(year, numericMonth, 1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String toDate = sdf.format(c.getTime());
        if (selectedMonth.equalsIgnoreCase(monthOfFromDate) && yearOfFromDate.equals(String.valueOf(year))) {
            if (!selectedMonth.equalsIgnoreCase(monthOfToDate)) {
                filter.setToDate(toDate);
            }
        } else if (selectedMonth.equalsIgnoreCase(monthOfToDate) && yearOfToDate.equals(String.valueOf(year))) {
            if (!selectedMonth.equalsIgnoreCase(monthOfFromDate)) {
                filter.setFromDate("01-" + selectedMonth + "-" + year);
            }
        } else {
            filter.setFromDate("01-" + selectedMonth + "-" + year);
            filter.setToDate(toDate);
        }
    }

    /**
     * Method to prepare query parameter set for stored procedure call.
     * @param paramNames
     * @param filter
     * @return
     */
    public static QueryParameter prepareDashboardFilterStoredProcParam(String[] paramNames, DashboardsFilterCriteria filter){
        QueryParameter queryParameter = null;
        for(String param : paramNames){
            switch(param){
                case DashboardStoredProcParam.DashboardFilterParams.ACCESSORIAL_NAME_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.CARRIER_IDS_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.CARRIER_IDS_PARAM, filter.getCarriers(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.CARRIER_ID_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.CARRIER_ID_PARAM, filter.getCarriers(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_CODE_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_ID_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_ID_PARAM, filter.getConvertCurrencyId(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.CUSTOMER_IDS_CSV_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.DATE_TYPE_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.DATE_TYPE_PARAM, filter.getDateType(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.FROM_DATE_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.FROM_DATE_PARAM, filter.getFromDate(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.LANES_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.LANES_PARAM, filter.getLanes(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.MODE_NAMES_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.MODE_NAMES_PARAM, filter.getModeNames(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.MODES_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.MODES_PARAM, filter.getModes(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.SERVICE_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.SERVICE_PARAM, filter.getService(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.SERVICES_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.SERVICES_PARAM, filter.getServices(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.TO_DATE_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.TO_DATE_PARAM, filter.getToDate(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.TOP_TEN_ACCESSORIAL_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.TOP_TEN_ACCESSORIAL_PARAM, (filter.isTopTenAccessorial() ? 1L : 0L), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.DELIVERY_FLAG_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.DELIVERY_FLAG_PARAM, filter.getDeliveryFlag(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.BILLED_APPROVED_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.BILLED_APPROVED_PARAM, filter.getBilledVsApproved(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.INVOICE_METHOD_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.INVOICE_METHOD_PARAM, filter.getInvoiceMethod(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.ORDER_MATCH_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.ORDER_MATCH_PARAM, filter.getOrderMatch(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.INVOICE_STATUS_ID_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.INVOICE_STATUS_ID_PARAM, filter.getInvoiceStatusId(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.ACCESSORIAL_DESC_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.ACCESSORIAL_DESC_PARAM, filter.getAccDesc(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.TAX_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.TAX_PARAM, filter.getTax(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.SCORE_TYPE_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.SCORE_TYPE_PARAM, filter.getScoreType(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.CONVERTED_WEIGHT_UNIT_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.CONVERTED_WEIGHT_UNIT_PARAM, filter.getConvertWeightUnit(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.ORIGINAL_FROM_DATE_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.ORIGINAL_FROM_DATE_PARAM, filter.getOriginalFromDate(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.ORIGINAL_TO_DATE_PARAM:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.ORIGINAL_TO_DATE_PARAM, filter.getOriginalToDate(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.POL:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.POL, filter.getPol(), queryParameter);
                    break;
                case DashboardStoredProcParam.DashboardFilterParams.POD:
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.POL, filter.getPod(), queryParameter);
                    break;
                default:
                    throw new RuntimeException("Invalid stored procedure param name.");
            }
        }
        return queryParameter;
    }

    /**
     * Method to prepare a query parameter object based on the key, value.
     * @param paramName
     * @param value
     * @param queryParam
     * @return
     */
    public static QueryParameter prepareQueryParam(String paramName, Object value, QueryParameter queryParam){
        if(null == queryParam){
            queryParam = StoredProcedureParameter.with(paramName, value);
        }else{
            queryParam = queryParam.and(paramName, value);
        }
        return queryParam;
    }

    public static DashSavedFilterDto findDefaultUserFilter(List<DashSavedFilterDto> userFilterList){
        for(DashSavedFilterDto userFilter : userFilterList){
            if(userFilter != null && userFilter.getDefaultFilter() == 1){
                return userFilter;
            }
        }
        return null;
    }
}
