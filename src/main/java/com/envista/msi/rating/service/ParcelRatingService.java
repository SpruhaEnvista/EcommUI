package com.envista.msi.rating.service;

import com.envista.msi.rating.dao.DirectJDBCDAO;
import com.envista.msi.rating.entity.ParcelRatingInputCriteriaDto;

/**
 * Created by Sujit kumar on 04/06/2018.
 */
public class ParcelRatingService {
    public ParcelRatingInputCriteriaDto getRatingInputCriteria(String status) {
        return new DirectJDBCDAO().getRatingInputCriteria(status);
    }

    public void updateRatingInputCriteriaStatus(Long id, String status) {
        new DirectJDBCDAO().updateRatingInputCriteriaStatus(id, status);
    }
}
