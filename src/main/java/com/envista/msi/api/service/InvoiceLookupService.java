package com.envista.msi.api.service;

import com.envista.msi.api.dao.InvoiceLookupDao;
import com.envista.msi.api.web.rest.dto.CarrierDto;
import com.envista.msi.api.web.rest.dto.CarrierGroupDto;
import com.envista.msi.api.web.rest.dto.CustomerDto;
import com.envista.msi.api.web.rest.dto.InvoiceSearchDtlsDto;
import com.envista.msi.api.web.rest.dto.freight.invoice.InvoiceCodeValuesDto;
import com.envista.msi.api.web.rest.dto.freight.invoice.InvoiceLookupParamsDto;
import com.envista.msi.api.web.rest.dto.freight.invoice.UserDefinedColumnsDto;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.util.JSONUtil;
import com.envista.msi.api.web.rest.util.pagination.EnspirePagination;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(InvoiceLookupService.class);

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
        dataMap.put("columnConfigs", getUserDefinedColumnNames());

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
        return dataMap;
    }

    /**
     * Hardcoded for demo purpose.
     * @return
     * @throws Exception
     */
    private List<UserDefinedColumnsDto> getUserDefinedColumnNames() throws Exception {
        List<UserDefinedColumnsDto> userDefinedColumns = new ArrayList<>();
        userDefinedColumns.add(new UserDefinedColumnsDto("Customer Name", "Customer Name", "CUSTOMER NAME", "CUSTOMER_NAME", "customerName", true));
        userDefinedColumns.add(new UserDefinedColumnsDto("Carrier Name", "Carrier Name", "CARRIER NAME", "CARRIER_NAME", "carrierName", true));
        userDefinedColumns.add(new UserDefinedColumnsDto("Ship Date", "Ship Date", "SHIP DATE", "SHIP_DATE", "shipDate", true));
        userDefinedColumns.add(new UserDefinedColumnsDto("Run Number", "Run Number", "RUN NUMBER", "RUM_NO", "runNumber", true));
        userDefinedColumns.add(new UserDefinedColumnsDto("Invoice Status", "Status", "INVOICE STATUS", "INVOICE_STATUS", "invoiceStatus", true));
        userDefinedColumns.add(new UserDefinedColumnsDto("Load Date", "Load Date", "LOAD DATE", "LOAD_DATE", "loadMatchDate", true));
        userDefinedColumns.add(new UserDefinedColumnsDto("Invoice Date", "Invoice Date", "INVOICE DATE", "INVOICE_DATE", "invoiceDate", true));
        userDefinedColumns.add(new UserDefinedColumnsDto("Approved Charges", "Approved Charges", "APPROVED CHARGES", "APPROVED_AMOUNT", "approvedAmount", true));
        userDefinedColumns.add(new UserDefinedColumnsDto("Total Charges", "Total Charges", "TOTAL CHARGES", "TOTAL_CHARGES", "totalCharges", true));
        userDefinedColumns.add(new UserDefinedColumnsDto("Bol Number", "BOL #", "BOL NUMBER", "BOL_NUMBER", "bolNumber", true));
        userDefinedColumns.add(new UserDefinedColumnsDto("Pro Number", "PRO #", "PRO NUMBER", "PRO_NUMBER", "proNumber", true));
        userDefinedColumns.add(new UserDefinedColumnsDto("Invoice Number", "Invoice #", "INVOICE NUMBER", "INVOICE_NUMBER", "invoiceNumber", true));

        return userDefinedColumns;
    }

    public List<CustomerDto> getCustomerListByUserId(Long userId){
       return invoiceLookupDao.getCustomerDetailsUsingProcAndFieldMap(userId);
    }

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
                if(carrEntry != null && !containsCarrierGroup(carrierGroupList, carrEntry.getKey())){
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

    private boolean containsCarrierGroup(List<CarrierGroupDto> carrierGroupList, Long carrierGroupId){
        if(carrierGroupList != null && !carrierGroupList.isEmpty()){
            for(CarrierGroupDto carrierGroup : carrierGroupList){
                if(carrierGroup != null && carrierGroup.getCarrierGroupId() != null
                        && carrierGroup.getCarrierGroupId().equals(carrierGroupId)){
                    return true;
                }
            }
        }
        return false;
    }

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

    public List<InvoiceCodeValuesDto> getCodeValuesDetails(String codeGroupId, String property3, String orderBy){
        return invoiceLookupDao.getCodeValuesDetails(codeGroupId, property3, orderBy);
    }

    public List<InvoiceCodeValuesDto> getCodeValuesDetails(String codeGroupId, String orderBy){
        return invoiceLookupDao.getCodeValuesDetails(codeGroupId, null, orderBy);
    }
}
