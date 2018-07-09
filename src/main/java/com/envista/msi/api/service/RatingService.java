package com.envista.msi.api.service;

import com.envista.msi.api.dao.rating.RatingDao;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.dto.rtr.EventLogDto;
import com.envista.msi.api.web.rest.dto.rtr.StoreRatingDetailsDto;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class RatingService {
    @Inject
    private RatingDao ratingDao;

    public List<ReportCustomerCarrierDto> getRateCarrierList(Long userId, String customerIds){
        return ratingDao.getRateCarrierList(userId, customerIds);
    }

    public List<EventLogDto> getEventLog(Long jobId){
        return ratingDao.getEventLog(jobId);
    }

    public List<StoreRatingDetailsDto> saveRatingDetailsList(StoreRatingDetailsDto storeRatingDetailsDto){
        return ratingDao.saveRatingDetailsList(storeRatingDetailsDto);
    }
}
