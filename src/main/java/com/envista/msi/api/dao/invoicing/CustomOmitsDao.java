package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.invoicing.CarrierDto;
import com.envista.msi.api.web.rest.dto.invoicing.CustomOmitsDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Created by KRISHNAREDDYM on 4/20/2017.
 */
@Repository("customOmitsDao")
public class CustomOmitsDao {

    @Inject
    private PersistentContext persistentContext;

    public List<CustomOmitsDto> findByuserId(long userId,int offset, int limit) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", userId)
                .and("P_OMIT_ID", 0L)
                .and("P_OFFSET", offset).and("P_PAGE_SIZE", limit).and("P_ACTION_TYPE", "findByuserId");

        return persistentContext.findEntities("CustomOmitsDto.getCutomOmitsList", queryParameter);
    }

    public CustomOmitsDto findById(Long customOmitsId) {
        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", 0L)
                .and("P_OMIT_ID", customOmitsId).and("P_OFFSET", 0).and("P_PAGE_SIZE", 0).and("P_ACTION_TYPE", "findById");

        List<CustomOmitsDto> dtos = persistentContext.findEntities("CustomOmitsDto.getCutomOmitsList", queryParameter);
        CustomOmitsDto dto = null;
        if (dtos != null && dtos.size() > 0)
            return dtos.get(0);

        return dto;
    }

    public List<CustomOmitsDto> findAll() {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", 0L)
                .and("P_OMIT_ID", 0L).and("P_ACTION_TYPE", "test");

        return persistentContext.findEntities("CustomOmitsDto.getCutomOmitsList", queryParameter);
    }

    public CustomOmitsDto insert(CustomOmitsDto dto) {

        if (dto.getCustomerId() == null)
            dto.setCustomerId(-1L);
        if (dto.getCreditTypeId() == null)
            dto.setCreditTypeId(-1L);

        QueryParameter queryParameter = StoredProcedureParameter.with("P_CUSTOMER_NAME", dto.getCustomerName()).and("P_CUSTOM_OMITS_ID", dto.getCustomOmitsId())
                .and("P_TRACKING_NUMBER", dto.getTrackingNumber()).and("P_COMMENTS", dto.getComments())
                .and("P_CARRIER_ID", dto.getCarrierId()).and("P_CUSTOMER_ID", dto.getCustomerId())
                .and("P_CUSTOMER_IDS", dto.getCustomerIds()).and("P_CREDIT_TYPE_ID", dto.getCreditTypeId())
                .and("P_EXPIRY_DATE", dto.getExpiryDate()).and("P_USER_ID", dto.getUserId()).and("P_ACTION_TYPE", "INSERT");

        return persistentContext.findEntity("CustomOmitsDto.insertOrUpdateCustomOmit", queryParameter);
    }

    public CustomOmitsDto update(CustomOmitsDto dto) {

        if (dto.getCreditTypeId() == null)
            dto.setCreditTypeId(-1L);

        QueryParameter queryParameter = StoredProcedureParameter.with("P_CUSTOMER_NAME", dto.getCustomerName()).and("P_CUSTOM_OMITS_ID", dto.getCustomOmitsId())
                .and("P_TRACKING_NUMBER", dto.getTrackingNumber()).and("P_COMMENTS", dto.getComments())
                .and("P_CARRIER_ID", dto.getCarrierId()).and("P_CUSTOMER_ID", dto.getCustomerId())
                .and("P_CUSTOMER_IDS", dto.getCustomerIds()).and("P_CREDIT_TYPE_ID", dto.getCreditTypeId())
                .and("P_EXPIRY_DATE", dto.getExpiryDate()).and("P_USER_ID", dto.getUserId()).and("P_ACTION_TYPE", "UPDATE");

        List<CustomOmitsDto> dtos = persistentContext.findEntities("CustomOmitsDto.insertOrUpdateCustomOmit", queryParameter);
        CustomOmitsDto dbDto = null;
        if (dtos != null && dtos.size() > 0)
            dbDto = dtos.get(0);

        return dbDto;
    }

    public List<CustomOmitsDto> findBySearchCriteria(CustomOmitsDto dto, Map<String, Object> paginationFilterMap,int offset, int limit) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_TRACKING_NUMBER", dto.getTrackingNumber())
                .and("P_CREDIT_TYPE_ID", dto.getCreditTypeId()).and("P_CUSTOMER_IDS", dto.getCustomerIds())
                .and("P_COMMENTS", dto.getComments()).and("P_CARRIER_ID", dto.getCarrierId()).and("P_USER_ID", dto.getUserId())
                .and("P_OFFSET", offset).and("P_PAGE_SIZE", limit).and("P_CUTOM_OMIT_ID", 0L).and("P_ACTION_TYPE", "search");

        return persistentContext.findEntities("CustomOmitsDto.findBySearchCriteria", queryParameter);
    }

    public int delete(String customOmitIds) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_CUSTOM_OMITS_IDS", customOmitIds);

        List<CustomOmitsDto> dtos = persistentContext.findEntities("CustomOmitsDto.deleteCustomOmits", queryParameter);

        int count = 0;
        if (dtos != null)
            count = dtos.size();

        return count;

    }

    public int getCountOfCustomOmits(Long userId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", userId)
                .and("P_OMIT_ID", 0L).and("P_OFFSET", 0).and("P_PAGE_SIZE", 0).and("P_ACTION_TYPE", "count");

        List<CustomOmitsDto> dtos = persistentContext.findEntities("CustomOmitsDto.getCount", queryParameter);
        int count=0;
        if(null != dtos && dtos.size()>0){
            count=dtos.get(0).getTotalCount();
        }
        return count;
    }

    public int getSearchCount(CustomOmitsDto dto) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_TRACKING_NUMBER", dto.getTrackingNumber())
                .and("P_CREDIT_TYPE_ID", dto.getCreditTypeId()).and("P_CUSTOMER_IDS", dto.getCustomerIds())
                .and("P_COMMENTS", dto.getComments()).and("P_CARRIER_ID", dto.getCarrierId()).and("P_USER_ID", dto.getUserId())
                .and("P_OFFSET", 0).and("P_PAGE_SIZE", 0).and("P_CUTOM_OMIT_ID", 0L).and("P_ACTION_TYPE", "count");

        List<CustomOmitsDto> dtos = persistentContext.findEntities("CustomOmitsDto.searchCount", queryParameter);

        int count=0;
        if(null != dtos && dtos.size()>0){
            count=dtos.get(0).getTotalCount();
        }
        return count;
    }

    public List<CarrierDto> getAllCarriers() {

        QueryParameter queryParameter = null;

        return persistentContext.findEntities("CarrierDto.getAllCarriers", queryParameter);
    }

}
