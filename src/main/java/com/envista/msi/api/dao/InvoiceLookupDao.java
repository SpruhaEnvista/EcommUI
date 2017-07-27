package com.envista.msi.api.dao;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.FreightStoreProcParam;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.CarrierDto;
import com.envista.msi.api.web.rest.dto.CustomerDto;
import com.envista.msi.api.web.rest.dto.InvoiceSearchDtlsCount;
import com.envista.msi.api.web.rest.dto.InvoiceSearchDtlsDto;
import com.envista.msi.api.web.rest.dto.freight.DynamicColumnsDto;
import com.envista.msi.api.web.rest.dto.freight.invoice.InvoiceCodeValuesDto;
import com.envista.msi.api.web.rest.dto.freight.invoice.InvoiceLookupParamsDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.ParameterMode;
import java.util.List;

/**
 * Created by user1 on 1/18/2017.
 */
@Repository("invoiceLookupDao")
public class InvoiceLookupDao {

    @Inject
    private PersistentContext persistentContext;

    public List<CustomerDto> getCustomerDetailsUsingProcAndFieldMap(Long userId) {
        List<CustomerDto> dtoList = persistentContext.findEntitiesAndMapFields("CustomerTb.getCustomersList",
                StoredProcedureParameter.with("p_user_id", userId));

       return dtoList;
    }

    public List<CarrierDto> getCarrierListForCustomer(String customerId){
        List<CarrierDto> crrDtoList = persistentContext.findEntitiesAndMapFields("carrTb.getCarrierList",
                StoredProcedureParameter.with("p_customer_ids",customerId));
        return crrDtoList;
    }

    public List<InvoiceSearchDtlsDto> loadInvoiceDetails(InvoiceLookupParamsDto invoiceLookupParams){
        QueryParameter queryParameter = StoredProcedureParameter.with(FreightStoreProcParam.InvoiceLookupParam.CUSTOMER_ID_CSV_PARAM, invoiceLookupParams.getCustomerId())
                .and(FreightStoreProcParam.InvoiceLookupParam.INVOICE_NUMBER_PARAM, invoiceLookupParams.getInvoiceNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.PRO_NUMBER_PARAM, invoiceLookupParams.getProNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.BOL_NUMBER_PARAM, invoiceLookupParams.getBolNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.INVOICE_ID_PARAM, invoiceLookupParams.getInvoiceId())
                .and(FreightStoreProcParam.InvoiceLookupParam.ACCOUNT_NUMBER_PARAM, invoiceLookupParams.getAccountNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.CARRIER_ID_PARAM, invoiceLookupParams.getCarrierId())
                .and(FreightStoreProcParam.InvoiceLookupParam.DATE_TYPE_PARAM, invoiceLookupParams.getDateType())
                .and(FreightStoreProcParam.InvoiceLookupParam.FROM_DATE_PARAM, invoiceLookupParams.getFromDate())
                .and(FreightStoreProcParam.InvoiceLookupParam.TO_DATE_PARAM, invoiceLookupParams.getToDate())
                .and(FreightStoreProcParam.InvoiceLookupParam.SHIPPER_ZIP_CODE_PARAM, invoiceLookupParams.getShipperZipCode())
                .and(FreightStoreProcParam.InvoiceLookupParam.RECEIVER_ZIP_CODE_PARAM, invoiceLookupParams.getReceiverZipCode())
                .and(FreightStoreProcParam.InvoiceLookupParam.BILLTO_ZIP_CODE_PARAM, invoiceLookupParams.getBillToZipCode())
                .and(FreightStoreProcParam.InvoiceLookupParam.INVOICE_STATUS_PARAM, invoiceLookupParams.getInvoiceStatus())
                .and(FreightStoreProcParam.InvoiceLookupParam.CHECK_NUMBER_PARAM, invoiceLookupParams.getCheckNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.RUN_NUMBER_PARAM, invoiceLookupParams.getRunNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.INVOICE_MODE_PARAM, invoiceLookupParams.getInvoiceMode())
                .and(FreightStoreProcParam.InvoiceLookupParam.INVOICE_METHOD_PARAM, invoiceLookupParams.getInvoiceMethod())
                .and(FreightStoreProcParam.InvoiceLookupParam.PO_NUMBER_PARAM, invoiceLookupParams.getPoNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.INCLUDE_EXCP_INV_PARAM, invoiceLookupParams.getExcludeExceptionInvoice() != null && invoiceLookupParams.getExcludeExceptionInvoice() == true ? 1 :0)
                .and(FreightStoreProcParam.InvoiceLookupParam.SERVICE_CODE_PARAM, invoiceLookupParams.getServiceCode())
                .and(FreightStoreProcParam.InvoiceLookupParam.CHECK_AMOUNT_PARAM, invoiceLookupParams.getCheckAmount())
                .and(FreightStoreProcParam.InvoiceLookupParam.CHECK_DATE_PARAM, invoiceLookupParams.getCheckDate())
                .and(FreightStoreProcParam.InvoiceLookupParam.CURRENCY_CODE_PARAM, invoiceLookupParams.getCurrencyCode())
                .and(FreightStoreProcParam.InvoiceLookupParam.IS_LOOKUP_PARAM, 1)
                .and(FreightStoreProcParam.InvoiceLookupParam.IS_COUNT_PARAM, 0)
                .and(FreightStoreProcParam.InvoiceLookupParam.OFFSET_PARAM, invoiceLookupParams.getOffset())
                .and(FreightStoreProcParam.InvoiceLookupParam.LIMIT_PARAM, invoiceLookupParams.getLimit())
                .and(FreightStoreProcParam.InvoiceLookupParam.USER_ID_PARAM, invoiceLookupParams.getUserId());
        return persistentContext.findEntities("InvoiceSearchDtlsDto.getInvoiceDetails", queryParameter);
    }

    public Integer getInvoiceCount(InvoiceLookupParamsDto invoiceLookupParams){
        QueryParameter queryParameter = StoredProcedureParameter.with(FreightStoreProcParam.InvoiceLookupParam.CUSTOMER_ID_CSV_PARAM, invoiceLookupParams.getCustomerId())
                .and(FreightStoreProcParam.InvoiceLookupParam.INVOICE_NUMBER_PARAM, invoiceLookupParams.getInvoiceNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.PRO_NUMBER_PARAM, invoiceLookupParams.getProNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.BOL_NUMBER_PARAM, invoiceLookupParams.getBolNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.INVOICE_ID_PARAM, invoiceLookupParams.getInvoiceId())
                .and(FreightStoreProcParam.InvoiceLookupParam.ACCOUNT_NUMBER_PARAM, invoiceLookupParams.getAccountNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.CARRIER_ID_PARAM, invoiceLookupParams.getCarrierId())
                .and(FreightStoreProcParam.InvoiceLookupParam.DATE_TYPE_PARAM, invoiceLookupParams.getDateType())
                .and(FreightStoreProcParam.InvoiceLookupParam.FROM_DATE_PARAM, invoiceLookupParams.getFromDate())
                .and(FreightStoreProcParam.InvoiceLookupParam.TO_DATE_PARAM, invoiceLookupParams.getToDate())
                .and(FreightStoreProcParam.InvoiceLookupParam.SHIPPER_ZIP_CODE_PARAM, invoiceLookupParams.getShipperZipCode())
                .and(FreightStoreProcParam.InvoiceLookupParam.RECEIVER_ZIP_CODE_PARAM, invoiceLookupParams.getReceiverZipCode())
                .and(FreightStoreProcParam.InvoiceLookupParam.BILLTO_ZIP_CODE_PARAM, invoiceLookupParams.getBillToZipCode())
                .and(FreightStoreProcParam.InvoiceLookupParam.INVOICE_STATUS_PARAM, invoiceLookupParams.getInvoiceStatus())
                .and(FreightStoreProcParam.InvoiceLookupParam.CHECK_NUMBER_PARAM, invoiceLookupParams.getCheckNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.RUN_NUMBER_PARAM, invoiceLookupParams.getRunNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.INVOICE_MODE_PARAM, invoiceLookupParams.getInvoiceMode())
                .and(FreightStoreProcParam.InvoiceLookupParam.INVOICE_METHOD_PARAM, invoiceLookupParams.getInvoiceMethod())
                .and(FreightStoreProcParam.InvoiceLookupParam.PO_NUMBER_PARAM, invoiceLookupParams.getPoNumber())
                .and(FreightStoreProcParam.InvoiceLookupParam.INCLUDE_EXCP_INV_PARAM, invoiceLookupParams.getExcludeExceptionInvoice() != null && invoiceLookupParams.getExcludeExceptionInvoice() == true ? 1 :0)
                .and(FreightStoreProcParam.InvoiceLookupParam.SERVICE_CODE_PARAM, invoiceLookupParams.getServiceCode())
                .and(FreightStoreProcParam.InvoiceLookupParam.CHECK_AMOUNT_PARAM, invoiceLookupParams.getCheckAmount())
                .and(FreightStoreProcParam.InvoiceLookupParam.CHECK_DATE_PARAM, invoiceLookupParams.getCheckDate())
                .and(FreightStoreProcParam.InvoiceLookupParam.CURRENCY_CODE_PARAM, invoiceLookupParams.getCurrencyCode())
                .and(FreightStoreProcParam.InvoiceLookupParam.IS_LOOKUP_PARAM, 1)
                .and(FreightStoreProcParam.InvoiceLookupParam.IS_COUNT_PARAM, 1)
                .and(FreightStoreProcParam.InvoiceLookupParam.OFFSET_PARAM, invoiceLookupParams.getOffset())
                .and(FreightStoreProcParam.InvoiceLookupParam.LIMIT_PARAM, invoiceLookupParams.getLimit())
                .and(FreightStoreProcParam.InvoiceLookupParam.USER_ID_PARAM, invoiceLookupParams.getUserId());
        InvoiceSearchDtlsCount invoiceSearchCount = persistentContext.findEntityAndMapFields("InvoiceSearchDtlsDto.getInvoiceDetailsCount", queryParameter);
        return invoiceSearchCount.getRecordCount();
    }

    public List<InvoiceCodeValuesDto> getCodeValuesDetails(String codeGroupId, String property3, boolean allActiveAndInactive, String orderBy){
        QueryParameter queryParameter = StoredProcedureParameter.with(FreightStoreProcParam.CodeValuesParam.CODE_VALUE_ID_PARAM, null)
                .and(FreightStoreProcParam.CodeValuesParam.CODE_GROUP_ID_PARAM, codeGroupId)
                .and(FreightStoreProcParam.CodeValuesParam.PROPERTY_1_PARAM, null)
                .and(FreightStoreProcParam.CodeValuesParam.PROPERTY_2_PARAM, null)
                .and(FreightStoreProcParam.CodeValuesParam.PROPERTY_3_PARAM, property3)
                .and(FreightStoreProcParam.CodeValuesParam.PROPERTY_4_PARAM, null)
                .and(FreightStoreProcParam.CodeValuesParam.PROPERTY_5_PARAM, null)
                .and(FreightStoreProcParam.CodeValuesParam.PROPERTY_6_PARAM, null)
                .and(FreightStoreProcParam.CodeValuesParam.PROPERTY_7_PARAM, null)
                .and(FreightStoreProcParam.CodeValuesParam.PROPERTY_8_PARAM, null)
                .and(FreightStoreProcParam.CodeValuesParam.PROPERTY_9_PARAM, null)
                .and(FreightStoreProcParam.CodeValuesParam.ORDER_BY_PARAM, orderBy)
                .and(FreightStoreProcParam.CodeValuesParam.SELECT_ACTIVE_INACTIVE_PARAM, allActiveAndInactive ? 1 : 0);

        return persistentContext.findEntities("InvoiceCodeValuesDto.getCodeValuesByDynamicParamValues", queryParameter);
    }

    public List<InvoiceCodeValuesDto> getInvoiceLookupCustomColumns(){
        return getInvoiceLookupCustomColumns(null);
    }

    public List<InvoiceCodeValuesDto> getInvoiceLookupCustomColumns(Long userId){
        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", null == userId ? userId : userId.toString());
        return persistentContext.findEntities("InvoiceCodeValuesDto.getFreightInvoiceLookupColumns", queryParameter);
    }

    public void saveOrUpdateDynamicColumns(DynamicColumnsDto dynamicColumns){
        try{
            QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, Long.class, dynamicColumns.getUserId())
                    .andPosition(2, ParameterMode.IN, String.class, dynamicColumns.getUserName())
                    .andPosition(3, ParameterMode.IN, String.class, dynamicColumns.getFilterId())
                    .andPosition(4, ParameterMode.IN, String.class, dynamicColumns.getIncludedColumns())
                    .andPosition(5, ParameterMode.IN, String.class, dynamicColumns.getExcludedColumns());
            persistentContext.executeStoredProcedure("SHP_FRT_SAVE_USER_DYN_COLS_PRO", queryParameter);
        }catch (Exception e){
            throw new DaoException(e.getMessage(), e);
        }
    }
}
