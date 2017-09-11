package com.envista.msi.api.domain.util;

import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.filter.DashSavedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.filter.UserFilterUtilityDataDto;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.util.JSONUtil;
import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

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
                    queryParameter = prepareQueryParam(DashboardStoredProcParam.DashboardFilterParams.POD, filter.getPod(), queryParameter);
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

    /**
     * Find default Filter from list of filters.
     * @param userFilterList
     * @return
     */
    public static DashSavedFilterDto findDefaultUserFilter(List<DashSavedFilterDto> userFilterList){
        for(DashSavedFilterDto userFilter : userFilterList){
            if(userFilter != null && userFilter.getDefaultFilter() == 1){
                return userFilter;
            }
        }
        return null;
    }

    /**
     *
     * @param savedFilter
     * @param userName
     * @param userid
     * @return
     */
    public static DashboardAppliedFilterDto prepareAppliedFilter(DashSavedFilterDto savedFilter, String userName, long userid){
        DashboardAppliedFilterDto appliedFilter = new DashboardAppliedFilterDto();
        appliedFilter.setDateType(savedFilter.getDateType());
        appliedFilter.setLoginUserId(userid);
        appliedFilter.setCarrierIds(savedFilter.getCarrierIds());
        appliedFilter.setCurrencyId(String.valueOf(savedFilter.getCurrencyId()));
        appliedFilter.setCustomerIds(savedFilter.getCustomerIds());
        appliedFilter.setFromDate(savedFilter.getFromDate());
        appliedFilter.setToDate(savedFilter.getToDate());
        appliedFilter.setModes(savedFilter.getModes());
        appliedFilter.setServices(savedFilter.getServices());
        appliedFilter.setWeightUnit(savedFilter.getWeightUnit());
        String lanesInfo = prepareLanesInfo(savedFilter);
        if(lanesInfo != null && !lanesInfo.isEmpty()){
            appliedFilter.setLanes(lanesInfo);
        }
        return appliedFilter;
    }

    public static String prepareLanesInfo(DashSavedFilterDto savedFilter){
        StringBuilder lanesInfo = new StringBuilder();
        if(savedFilter != null){
            populateLanesInfo(lanesInfo, "a.shipper_country", savedFilter.getShipperCountries());
            populateLanesInfo(lanesInfo, "a.shipper_state", savedFilter.getShipperStates());
            populateLanesInfo(lanesInfo, "a.shipper_city", savedFilter.getShipperCities());
            populateLanesInfo(lanesInfo, "a.receiver_country", savedFilter.getReceiverCountries());
            populateLanesInfo(lanesInfo, "a.receiver_state", savedFilter.getReceiverStates());
            populateLanesInfo(lanesInfo, "a.receiver_city", savedFilter.getReceiverCities());
        }
        return lanesInfo.toString();
    }

    private static void populateLanesInfo(StringBuilder lanesInfo, String fieldName, String fieldValuesCSV){
        if(fieldValuesCSV != null && !fieldValuesCSV.isEmpty()){
            StringJoiner stringJoiner = new StringJoiner(",");
            for(String value : fieldValuesCSV.split(",")){
                if(value != null && !value.isEmpty()){
                    stringJoiner.add("'" + value + "'");
                }
            }
            if(lanesInfo.toString().isEmpty()){
                lanesInfo.append(" "+ fieldName + " IN (" + stringJoiner.toString() + ") ");
            }else{
                lanesInfo.append(" AND " + fieldName + " IN (" + stringJoiner.toString() + ") ");
            }
        }
    }

    /**
     * To prepare search filter criteria from enspire search filter.
     * @param filter
     * @return
     */
    public static String prepareSearchFilterCriteria(String filter){
        filter = filter.substring(1, filter.length() - 1);
        String delimiter = filter.contains("||") ? "||" : "&&";
        String sqlCondition = delimiter.equals("||") ? "OR" : "AND";
        int i = 0;
        int size = filter.split(Pattern.quote(delimiter)).length;
        StringBuilder finalCondition = new StringBuilder("(");
        for(String params : filter.split(Pattern.quote(delimiter))){
            finalCondition.append(" " + params.split(":")[0] + " LIKE " + "'" + params.split(":")[1] + "'");
            if(i != size - 1){
                finalCondition.append(" " + sqlCondition + " ");
            }
            i++;
        }
        finalCondition.append(")");
        return finalCondition.toString();
    }

    /**
     * To prepare dashboard filter details.
     * @param carriers
     * @param services
     * @param modes
     * @param savedCarrList
     * @param savedServices
     * @param savedFilter
     * @param modeWiseCarriers
     * @param isParcelDashlettes
     * @return
     * @throws JSONException
     */
    public static Map<String, Object> prepareFilterDetails(List<UserFilterUtilityDataDto> carriers, List<UserFilterUtilityDataDto> services, List<UserFilterUtilityDataDto> modes,
                                                           List<Long> savedCarrList, List<Long> savedModes, List<Long> savedServices, DashSavedFilterDto savedFilter, Map<String, String> modeWiseCarriers,
                                                           boolean isParcelDashlettes, boolean isNew) throws JSONException {
        Map<String, Object> userFilterDetailsMap = new HashMap<String, Object>();
        JSONArray modesArray = new JSONArray();
        if (savedCarrList.size() > 0) {
            modesArray = JSONUtil.prepareFilterModesJson(modes, savedModes, modeWiseCarriers, isParcelDashlettes, isNew);
        }
        userFilterDetailsMap.put("carrDetails", JSONUtil.prepareFilterCarrierJson(carriers, savedCarrList, isNew));
        userFilterDetailsMap.put("modesDetails", modesArray);
        userFilterDetailsMap.put("servicesDetails", JSONUtil.prepareFilterServiceJson(services, savedServices, isNew));
        userFilterDetailsMap.put("filterDetails", savedFilter);
        return userFilterDetailsMap;
    }

    /**
     * To check contains of customerId in customers list.
     * @param customerId
     * @param customers
     * @return
     */
    public static boolean isContainsCustomer(String customerId, List<ReportCustomerCarrierDto> customers){
        if(customers != null && customerId != null){
            for(String custId : customerId.split(",")){
                for(ReportCustomerCarrierDto customer : customers){
                    if(customer != null && custId != null && customer.getCustomerId() != null && custId.equals(customer.getCustomerId().toString())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Create DashboardsFilterCriteria object from DashboardAppliedFilterDto object.
     * @param dashboardAppliedFilter
     * @return
     */
    public static DashboardsFilterCriteria prepareDashboardFilterCriteria(DashboardAppliedFilterDto dashboardAppliedFilter){
        if(null == dashboardAppliedFilter){ return null; }

        DashboardsFilterCriteria dashboardsFilterCriteria = new DashboardsFilterCriteria();
        dashboardsFilterCriteria.setConvertCurrencyCode(dashboardAppliedFilter.getCurrencyCode());
        if(dashboardAppliedFilter.getCurrencyId() != null && !dashboardAppliedFilter.getCurrencyId().isEmpty()){
            dashboardsFilterCriteria.setConvertCurrencyId(Long.parseLong(dashboardAppliedFilter.getCurrencyId()));
        }


        dashboardsFilterCriteria.setConvertWeightUnit(dashboardAppliedFilter.getWeightUnit());
        dashboardsFilterCriteria.setCarriers(dashboardAppliedFilter.getCarrierIds());
        dashboardsFilterCriteria.setDateType(dashboardAppliedFilter.getDateType());
        dashboardsFilterCriteria.setFromDate(dashboardAppliedFilter.getFromDate());
        dashboardsFilterCriteria.setLanes(dashboardAppliedFilter.getLanes());
        dashboardsFilterCriteria.setModes(dashboardAppliedFilter.getModes());
        dashboardsFilterCriteria.setService(dashboardAppliedFilter.getServices());
        dashboardsFilterCriteria.setToDate(dashboardAppliedFilter.getToDate());
        dashboardsFilterCriteria.setConvertWeightUnit(dashboardAppliedFilter.getWeightUnit());
        dashboardsFilterCriteria.setUserId(dashboardAppliedFilter.getLoginUserId());
        dashboardsFilterCriteria.setCustomerIdsCSV(dashboardAppliedFilter.getCustomerIds());
        return dashboardsFilterCriteria;
    }

    /**
     * Create DashboardsFilterCriteria object from DashSavedFilterDto object.
     * @param dashSavedFilter
     * @return
     */
    public static DashboardsFilterCriteria prepareDashboardFilterCriteria(DashSavedFilterDto dashSavedFilter){
        if(null == dashSavedFilter){ return null; }

        DashboardsFilterCriteria dashboardsFilterCriteria = new DashboardsFilterCriteria();
        dashboardsFilterCriteria.setConvertCurrencyId(dashSavedFilter.getCurrencyId());
        dashboardsFilterCriteria.setCarriers(dashSavedFilter.getCarrierIds());
        dashboardsFilterCriteria.setDateType(dashSavedFilter.getDateType());
        dashboardsFilterCriteria.setFromDate(dashSavedFilter.getFromDate());
        dashboardsFilterCriteria.setModes(dashSavedFilter.getModes());
        dashboardsFilterCriteria.setService(dashSavedFilter.getServices());
        dashboardsFilterCriteria.setToDate(dashSavedFilter.getToDate());
        dashboardsFilterCriteria.setConvertWeightUnit(dashSavedFilter.getWeightUnit());
        dashboardsFilterCriteria.setUserId(dashSavedFilter.getUserId());
        dashboardsFilterCriteria.setCustomerIdsCSV(dashSavedFilter.getCustomerIds());
        return dashboardsFilterCriteria;
    }
}
