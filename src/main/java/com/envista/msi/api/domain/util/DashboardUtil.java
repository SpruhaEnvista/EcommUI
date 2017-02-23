package com.envista.msi.api.domain.util;

import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        String selectedMonth = monthAndYear.split(" ")[0];
        String lastTwoDigitsOfYear = monthAndYear.split(" ")[1].replace("-", "");

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
                case DashboardSroredProcParam.DashboardFilterParams.ACCESSORIAL_NAME_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.CARRIER_IDS_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.CARRIER_IDS_PARAM, filter.getCarriers(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_CODE_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_ID_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_ID_PARAM, filter.getConvertCurrencyId(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.CUSTOMER_IDS_CSV_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.DATE_TYPE_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.DATE_TYPE_PARAM, filter.getDateType(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.FROM_DATE_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.FROM_DATE_PARAM, filter.getFromDate(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.LANES_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.LANES_PARAM, filter.getLanes(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.MODE_NAMES_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.MODE_NAMES_PARAM, filter.getModeNames(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.MODES_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.MODES_PARAM, filter.getModes(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.SERVICE_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.SERVICE_PARAM, filter.getService(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.SERVICES_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.SERVICES_PARAM, filter.getServices(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.TO_DATE_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.TO_DATE_PARAM, filter.getToDate(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.TOP_TEN_ACCESSORIAL_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.TOP_TEN_ACCESSORIAL_PARAM, (filter.isTopTenAccessorial() ? 1L : 0L), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.DELIVERY_FLAG_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.DELIVERY_FLAG_PARAM, filter.getDeliveryFlag(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.BILLED_APPROVED_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.BILLED_APPROVED_PARAM, filter.getBilledVsApproved(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.INVOICE_METHOD_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.INVOICE_METHOD_PARAM, filter.getInvoiceMethod(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.ORDER_MATCH_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.ORDER_MATCH_PARAM, filter.getOrderMatch(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.INVOICE_STATUS_ID_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.INVOICE_STATUS_ID_PARAM, filter.getInvoiceStatusId(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.ACCESSORIAL_DESC_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.ACCESSORIAL_DESC_PARAM, filter.getAccDesc(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.TAX_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.TAX_PARAM, filter.getTax(), queryParameter);
                    break;
                case DashboardSroredProcParam.DashboardFilterParams.SCORE_TYPE_PARAM:
                    queryParameter = prepareQueryParam(DashboardSroredProcParam.DashboardFilterParams.SCORE_TYPE_PARAM, filter.getScoreType(), queryParameter);
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
}
