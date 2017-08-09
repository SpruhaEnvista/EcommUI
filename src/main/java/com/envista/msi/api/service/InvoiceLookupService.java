package com.envista.msi.api.service;

import com.envista.msi.api.dao.InvoiceLookupDao;
import com.envista.msi.api.web.rest.dto.CarrierDto;
import com.envista.msi.api.web.rest.dto.CarrierGroupDto;
import com.envista.msi.api.web.rest.dto.InvoiceSearchDtlsDto;
import com.envista.msi.api.web.rest.dto.freight.DynamicColumnsDto;
import com.envista.msi.api.web.rest.dto.freight.invoice.InvoiceCodeValuesDto;
import com.envista.msi.api.web.rest.dto.freight.invoice.InvoiceLookupParamsDto;
import com.envista.msi.api.web.rest.dto.freight.invoice.UserDefinedColumnsDto;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.util.JSONUtil;
import com.envista.msi.api.web.rest.util.pac.GlobalConstants;
import com.envista.msi.api.web.rest.util.pagination.EnspirePagination;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by user1 on 1/19/2017.
 */
@Service
@Transactional
public class InvoiceLookupService {

    @Inject
    private InvoiceLookupDao invoiceLookupDao;

    @Inject
    private UserService userService;

    @Inject
    private ReportsService reportsService;

    @Inject
    private DashboardsService dashboardsService;


    public Map<String, Object> loadSearchInvoiceDefaults() throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("columnConfigs", getUserDefinedColumnDetails());

        List<ReportCustomerCarrierDto> customers = dashboardsService.getDashboardCustomers(userService.getLoggedInUser().getUserId());
        JSONArray customerJson = null;
        if(customers != null && !customers.isEmpty()){
            customerJson = JSONUtil.customerHierarchyJson(reportsService.getCustomerHierarchyObject(customers, false));
        }
        dataMap.put("customers", customerJson);
        dataMap.put("invoiceStatus", getCodeValuesDetails("1", "INVOICE LOOKUP DROP DOWN VALUES", "upper(code_value)"));
        dataMap.put("invoiceMode", getCodeValuesDetails("20", "sequence"));
        dataMap.put("serviceCode", getCodeValuesDetails("161", "sequence"));
        dataMap.put("currencyCode", getCodeValuesDetails("468", "sequence"));
        dataMap.put("invoiceMethod", getCodeValuesDetails("621", "sequence"));
        dataMap.put("dateTypeList", getCodeValuesDetails("701", true,"code_value"));
        return dataMap;
    }

    public Map<String, Object> getUserDefinedColumnDetails() throws Exception {
        Map<String, Object> columnMap = new HashMap<>();
        List<InvoiceCodeValuesDto> allColumns = getInvoiceLookupCustomColumns();
        List<InvoiceCodeValuesDto> userSavedColumns = getInvoiceLookupCustomColumns(userService.getLoggedInUser().getUserId());
        boolean hasUserSavedCols = (null != userSavedColumns && !userSavedColumns.isEmpty() && userSavedColumns.get(0) != null && userSavedColumns.get(0).getId() != null && userSavedColumns.get(0).getId() > 0);
        List<String> defaultColumnsList = hasUserSavedCols ? null : new ArrayList<>(Arrays.asList(GlobalConstants.FREIGHT_INVOICE_LOOKUP_DEFAULT_COLUMNS.split(",")));
        columnMap.put("columns", getUserDefinedColumnNames(allColumns, userSavedColumns, defaultColumnsList));
        columnMap.put("savedColumns", hasUserSavedCols);
        return columnMap;
    }

    /**
     * To get All columns invoice column with user saved columns as well.
     * @return
     * @throws Exception
     */
    private List<UserDefinedColumnsDto> getUserDefinedColumnNames(List<InvoiceCodeValuesDto> allColumns, List<InvoiceCodeValuesDto> userSavedColumns, List<String> defaultColumnsList) throws Exception {
        List<UserDefinedColumnsDto> userDefinedColumns = new ArrayList<>();
        Map<String, String> columnJsonKeyMap = new HashMap<>();
        for(String columnJsonKeys : GlobalConstants.FREIGHT_INVOICE_LOOKUP_COLUMN_WITH_JSON_KEY.split(",")){
            String[] keyVal = null;
            try{
                keyVal = columnJsonKeys.split("@#@");
                columnJsonKeyMap.put(keyVal[0].trim(), keyVal[1].trim());
            }catch (IndexOutOfBoundsException e){
                columnJsonKeyMap.put(keyVal[0], "");
            }catch (Exception e){
                //Nothing.
            }
        }
        if(allColumns != null && !allColumns.isEmpty()){
            boolean hasUserSavedCols = (null != userSavedColumns && !userSavedColumns.isEmpty() && userSavedColumns.get(0) != null && userSavedColumns.get(0).getId() != null && userSavedColumns.get(0).getId() > 0);
            for(InvoiceCodeValuesDto codeValue : allColumns){
                UserDefinedColumnsDto userDefinedColumn = new UserDefinedColumnsDto();
                userDefinedColumn.setColumnId(codeValue.getId());
                userDefinedColumn.setColumnName(codeValue.getCodeValue());
                userDefinedColumn.setLabelsColumnName(null == codeValue.getCodeValue() ? "" : codeValue.getCodeValue().replaceAll("\\s+","").toLowerCase());
                userDefinedColumn.setOriginalColumnName(null == codeValue.getCodeValue() ? "" : codeValue.getCodeValue().toUpperCase());
                userDefinedColumn.setChecked(null == userSavedColumns || userSavedColumns.isEmpty() ? false : userSavedColumns.contains(codeValue));
                userDefinedColumn.setTableFieldName(codeValue.getProperty1());
                userDefinedColumn.setJsonKey(columnJsonKeyMap.containsKey(codeValue.getCodeValue()) ? columnJsonKeyMap.get(codeValue.getCodeValue()) : "");
                userDefinedColumn.setDefaultColumn(hasUserSavedCols ? false : null == defaultColumnsList || defaultColumnsList.isEmpty() ? false : defaultColumnsList.contains(codeValue.getCodeValue()));
                userDefinedColumns.add(userDefinedColumn);
            }
        }
        return userDefinedColumns;
    }

    /**
     * Get carriers details with the group it belongs to for the given customer.
     * @param customerId
     * @return
     */
    public List<CarrierGroupDto> getCarrierListByCustomerId(String customerId){
        List<CarrierDto> carrierList = invoiceLookupDao.getCarrierListForCustomer(customerId);
        List<CarrierGroupDto> carrierGroupList = null;
        Map<Long, List<CarrierDto>> carriersMap = null;
        if(carrierList != null && !carrierList.isEmpty()){
            carrierGroupList = new ArrayList<>();
            carriersMap = new HashMap<>();
            for(CarrierDto carrier : carrierList){
                if(carrier != null){
                    if(carriersMap.containsKey(carrier.getCarrierGroupId())){
                        carriersMap.get(carrier.getCarrierGroupId()).add(carrier);
                    }else{
                        carriersMap.put(carrier.getCarrierGroupId(), new ArrayList<>(Arrays.asList(carrier)));
                    }
                }
            }

            for(Map.Entry<Long, List<CarrierDto>> carrEntry : carriersMap.entrySet()){
                if(carrEntry != null && !carrierGroupList.contains(new CarrierGroupDto(carrEntry.getKey()))){
                    List<CarrierDto> carriers = carrEntry.getValue();
                    StringJoiner carrierCsv = new StringJoiner(",");
                    for(CarrierDto carr : carriers){
                        if(carr != null && carr.getCarrierId() != null){
                            carrierCsv.add(carr.getCarrierId().toString());
                        }
                    }
                    CarrierDto carrierDto = carriers.get(0);
                    carrierGroupList.add(new CarrierGroupDto(carrierDto.getCarrierGroupId(), carrierDto.getGetCarrierGroupName(), carrierDto.getCarrierGroupDescription(), carriers, carrierCsv.toString()));
                }
            }
        }
        return carrierGroupList;
    }

    /**
     * Get Invoice details with pagination enabled.
     * @param invoiceLookupParams
     * @return
     * @throws Exception
     */
    public PaginationBean loadInvoiceDetails(InvoiceLookupParamsDto invoiceLookupParams) throws Exception {
        Map<String, Object> paginationFilterMap = new HashMap<String, Object>();
        paginationFilterMap.put("invoiceLookupParams", invoiceLookupParams);
        return new EnspirePagination() {
            @Override
            protected int getTotalRowCount(Map<String, Object> paginationFilterMap) {
                return getInvoiceSearchCount(paginationFilterMap);
            }

            @Override
            protected Object loadPaginationData(Map<String, Object> paginationFilterMap, int offset, int limit, String sortOrder) throws Exception {
                return getInvoiceSearchData(paginationFilterMap);
            }
        }.preparePaginationData(paginationFilterMap, invoiceLookupParams.getOffset(), invoiceLookupParams.getLimit());
    }

    public Integer getInvoiceSearchCount(Map<String, Object> paginationFilterMap){
        return invoiceLookupDao.getInvoiceCount((InvoiceLookupParamsDto) paginationFilterMap.get("invoiceLookupParams"));
    }

    public List<InvoiceSearchDtlsDto> getInvoiceSearchData(Map<String, Object> paginationFilterMap) throws Exception {
        return invoiceLookupDao.loadInvoiceDetails((InvoiceLookupParamsDto) paginationFilterMap.get("invoiceLookupParams"));
    }

    public List<InvoiceCodeValuesDto> getCodeValuesDetails(String codeGroupId, String property3, boolean allActiveAndInactive, String orderBy){
        return invoiceLookupDao.getCodeValuesDetails(codeGroupId, property3, allActiveAndInactive, orderBy);
    }

    public List<InvoiceCodeValuesDto> getCodeValuesDetails(String codeGroupId, String property3, String orderBy){
        return invoiceLookupDao.getCodeValuesDetails(codeGroupId, property3, false, orderBy);
    }

    public List<InvoiceCodeValuesDto> getCodeValuesDetails(String codeGroupId, String orderBy){
        return invoiceLookupDao.getCodeValuesDetails(codeGroupId, null, false, orderBy);
    }

    public List<InvoiceCodeValuesDto> getCodeValuesDetails(String codeGroupId, boolean allActiveAndInactive, String orderBy){
        return invoiceLookupDao.getCodeValuesDetails(codeGroupId, null, allActiveAndInactive, orderBy);
    }

    public List<InvoiceCodeValuesDto> getInvoiceLookupCustomColumns(Long userId){
        return invoiceLookupDao.getInvoiceLookupCustomColumns(userId);
    }

    public List<InvoiceCodeValuesDto> getInvoiceLookupCustomColumns(){
        return invoiceLookupDao.getInvoiceLookupCustomColumns();
    }

    public void saveOrUpdateDynamicColumns(DynamicColumnsDto dynamicColumns){
        invoiceLookupDao.saveOrUpdateDynamicColumns(dynamicColumns);
    }
}
