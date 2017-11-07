package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.CustomOmitsDao;
import com.envista.msi.api.web.rest.dto.CustomerDto;
import com.envista.msi.api.web.rest.dto.invoicing.CarrierDto;
import com.envista.msi.api.web.rest.dto.invoicing.CustomOmitsDto;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.util.pagination.EnspirePagination;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KRISHNAREDDYM on 4/20/2017.
 */
@Service
@Transactional
public class CustomOmitsService {

    private final Logger log = LoggerFactory.getLogger(CustomOmitsService.class);

    @Inject
    private CustomOmitsDao dao;

    public PaginationBean findByUserIdWithPagination(CustomOmitsDto dto1, int offset, int limit, String sort) throws Exception {
         Map<String, Object> paginationFilterMap = new HashMap<String, Object>();

         return new EnspirePagination() {
             @Override
             protected int getTotalRowCount(Map<String, Object> paginationFilterMap) {

                 return dao.getCountOfCustomOmits(dto1.getUserId());
             }

             @Override
             protected Object loadPaginationData(Map<String, Object> paginationFilterMap, int offset, int limit, String sortOrder) throws Exception {
                 List<CustomOmitsDto> dto = dao.findByUserId(dto1, offset, limit, sort);
                 return dto;
             }
         }.preparePaginationData(paginationFilterMap, offset, limit);
     }

    public CustomOmitsDto findById(Long customOmitsId) {

        return dao.findById(customOmitsId);
    }

    public List<CustomOmitsDto> findAll() {

        return dao.findAll();
    }

    public CustomOmitsDto insert(CustomOmitsDto dto) {

        return dao.insert(dto);
    }

    public CustomOmitsDto update(CustomOmitsDto dto) {

        return dao.update(dto);
    }

    public int delete(String customOmitIds) {

        return dao.delete(customOmitIds);
    }

    public PaginationBean findBySearchCriteria(CustomOmitsDto filter, int offset, int limit, String sort) throws Exception {
        Map<String, Object> paginationFilterMap = new HashMap<String, Object>();
        paginationFilterMap.put("filter", filter);

        return new EnspirePagination() {
            @Override
            protected int getTotalRowCount(Map<String, Object> paginationFilterMap) {
                return dao.getSearchCount(filter);
            }

            @Override
            protected Object loadPaginationData(Map<String, Object> paginationFilterMap, int offset, int limit, String sortOrder) throws Exception {
                return dao.findBySearchCriteria(filter, paginationFilterMap, offset, limit, sort);
            }
        }.preparePaginationData(paginationFilterMap, offset, limit);
    }

    public List<CarrierDto> getAllCarriers() {

        return dao.getAllCarriers();
    }

    public int getSeachCount(CustomOmitsDto dto) {

        return dao.getSearchCount(dto);
    }

    public List<ReportCustomerCarrierDto> getCustomers(long userId, long carrierId, long businessPartnerId, String actionType) {

        return dao.getCustomers(userId, carrierId, businessPartnerId, actionType);
    }

    public int getBusinessPartnerByCustomer(long customerId) {

        return dao.getBusinessPartnerByCustomer(customerId);
    }
}
