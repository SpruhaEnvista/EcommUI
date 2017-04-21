package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.invoicing.CustomOmitsDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 4/20/2017.
 */
@Repository("customOmitsDao")
public class CustomOmitsDao {

    @Inject
    private PersistentContext persistentContext;
    
/*    public CustomOmitsDto save(CustomOmitsDto tb) {
        // TODO Auto-generated method stub
        tb.setCustomOmitsId(getNextSequenceVal("SHP_INV_CUSTOM_OMITS_S"));
        tb.setActive(true);
        persist(tb);

        return tb;
    }

    @SuppressWarnings("unchecked")
    
    public List<CustomOmitsDto> findByCriteria(String trackingNumber, Long creditTypeId, long customerId) {
        // TODO Auto-generated method stub
        Criteria criteria = getSession().createCriteria(CustomOmitsDto.class);
        criteria.add(Restrictions.eq("trackingNumber", trackingNumber));
        criteria.add(Restrictions.eq("creditTypeId", creditTypeId));
        criteria.add(Restrictions.eq("active", true));
        criteria.addOrder(Order.desc("customOmitsId"));
        criteria.add(Restrictions.like("customerIds", Long.toString(customerId), MatchMode.ANYWHERE));

        return criteria.list();
    }
	
	*//*
    public List<CustomOmitsDto> findByTrackingNumAndCarrier(String trackingNumber, String carrier){
		Criteria criteria = getSession().createCriteria(CustomOmitsDto.class);
		criteria.add(Restrictions.eq("trackingNumber", trackingNumber));
		criteria.add(Restrictions.eq("carrier", carrier));
		return (List<CustomOmitsDto>) criteria.list();
	}*//*


    
    public CustomOmitsDto findById(Long customOmitsId) {
        // TODO Auto-generated method stub
        return (CustomOmitsDto) getSession().get(CustomOmitsDto.class, customOmitsId);
    }

    
    public void update(CustomOmitsDto tb) {
        // TODO Auto-generated method stub
        getSession().update(tb);
    }

    @SuppressWarnings("unchecked")
    
    public List<CustomOmitsDto> findBySearchCriteria(CustomOmitsDto tb, Long[] customOmitIdsArray) {
        // TODO Auto-generated method stub
        Criteria criteria = getSession().createCriteria(CustomOmitsDto.class);

        if(tb.getCustomerId() != null && tb.getCustomerId() > 0)
            criteria.add(Restrictions.eq("customerId", tb.getCustomerId()));
        if(!StringUtils.equalsIgnoreCase(tb.getTrackingNumber(), "null"))
            criteria.add(Restrictions.eq("trackingNumber", tb.getTrackingNumber()));
        if(!StringUtils.equalsIgnoreCase(tb.getCreditType(), "null"))
            criteria.add(Restrictions.eq("creditType", tb.getCreditType()));
        if(customOmitIdsArray != null)
            criteria.add(Restrictions.in("customerId",  customOmitIdsArray));
        if(!StringUtils.equalsIgnoreCase(tb.getComments(), "null")){
            String [] comments = StringUtils.split(tb.getComments(), ",");
            Disjunction or = Restrictions.disjunction();
            for(int i =0; i< comments.length; i++){

                or.add(Restrictions.ilike("comments","%"+comments[i]+"%"));
            }
            criteria.add(or);
        }
        if((tb.getCarrierId() != null && tb.getCarrierId() > 0))
            criteria.add(Restrictions.eq("carrierId", tb.getCarrierId()));
        if((tb.getUserId() != null && tb.getUserId() > 0))
            criteria.add(Restrictions.eq("userId", tb.getUserId()));
        criteria.add(Restrictions.eq("active", true));
        criteria.addOrder(Order.desc("customOmitsId"));

        return (List<CustomOmitsDto>)criteria.list();
    }

    @SuppressWarnings("unchecked")
    
    public List<CustomOmitsDto> findAll() {
        // TODO Auto-generated method stub
        Criteria criteria = getSession().createCriteria(CustomOmitsDto.class);
        criteria.add(Restrictions.eq("active", true));
        criteria.addOrder(Order.desc("customOmitsId"));
        return (List<CustomOmitsDto>)criteria.list();
    }

    
    public int updateActiveFlag(Long [] cutomOmitIds) {
        // TODO Auto-generated method stub
        Query query = getSession().createSQLQuery("UPDATE SHP_INV_CUSTOM_OMITS_TB SET IS_ACTIVE = 0 where CUSTOM_OMITS_ID in(:customOmit_ids)");
        //query.setLong("voice_id", voiceId);
        query.setParameterList("customOmit_ids", cutomOmitIds);
        return query.executeUpdate();
    }

    
    public List<CustomOmitsDto> exportCustomOmitsData(long userId) {
        List<CustomOmitsDto> customOmitsList= findByuserId(userId);
        return customOmitsList;
    }*/


    public List<CustomOmitsDto> findByuserId(long userId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", userId)
                .and("P_OMIT_ID", 0L).and("P_ACTION_TYPE", "findByuserId");

        return persistentContext.findEntities("CustomOmitsDto.getCutomOmitsList", queryParameter);
    }

    public CustomOmitsDto findById(Long customOmitsId) {
        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", 0L)
                .and("P_OMIT_ID", customOmitsId).and("P_ACTION_TYPE", "findById");

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

        QueryParameter queryParameter = StoredProcedureParameter.with("P_CUSTOMER_NAME", dto.getCustomerName()).and("P_CUSTOM_OMITS_ID", dto.getCustomOmitsId())
                .and("P_TRACKING_NUMBER", dto.getTrackingNumber()).and("P_COMMENTS", dto.getComments())
                .and("P_EBILL_MANIFEST_ID", dto.getEbillManifestId()).and("P_IS_ACTIVE", dto.isActive())
                .and("P_CARRIER_ID", dto.getCarrierId()).and("P_CUSTOMER_ID", dto.getCustomerId())
                .and("P_PARENT_CUSTOMER_ID", dto.getParentCustomerId()).and("P_CUSTOMER_IDS", dto.getCustomerIds())
                .and("P_CREDIT_TYPE_ID", dto.getCreditTypeId()).and("P_ACTION_TYPE", "INSERT");

        return persistentContext.findEntity("CustomOmitsDto.insertOrUpdateCustomOmit", queryParameter);
    }

    public CustomOmitsDto update(CustomOmitsDto dto) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_CUSTOMER_NAME", dto.getCustomerName()).and("P_CUSTOM_OMITS_ID", dto.getCustomOmitsId())
                .and("P_TRACKING_NUMBER", dto.getTrackingNumber()).and("P_COMMENTS", dto.getComments())
                .and("P_EBILL_MANIFEST_ID", dto.getEbillManifestId()).and("P_IS_ACTIVE", dto.isActive())
                .and("P_CARRIER_ID", dto.getCarrierId()).and("P_CUSTOMER_ID", dto.getCustomerId())
                .and("P_PARENT_CUSTOMER_ID", dto.getParentCustomerId()).and("P_CUSTOMER_IDS", dto.getCustomerIds())
                .and("P_CREDIT_TYPE_ID", dto.getCreditTypeId()).and("P_ACTION_TYPE", "UPDATE");

        return persistentContext.findEntity("CustomOmitsDto.insertOrUpdateCustomOmit", queryParameter);
    }

    public List<CustomOmitsDto> findBySearchCriteria(CustomOmitsDto dto) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_TRACKING_NUMBER", dto.getTrackingNumber())
                .and("P_CREDIT_TYPE_ID", dto.getCreditTypeId()).and("P_CUSTOMER_IDS", dto.getCustomerIds())
                .and("P_COMMENTS", dto.getComments()).and("P_CARRIER_ID", dto.getCarrierId());

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
}
