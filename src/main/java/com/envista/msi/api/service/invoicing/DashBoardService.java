package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.DashBoardDao;
import com.envista.msi.api.web.rest.dto.invoicing.DashBoardDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 5/8/2017.
 */
@Service
@Transactional
public class DashBoardService {

    @Inject
    private DashBoardDao dao;

    public List<DashBoardDto> getDashBoardInfo(String fromDate, String toDate, String actionType) {

        return dao.getDashBoardInfo(fromDate, toDate, actionType);
    }

    public int getPendingCredits(String fromDate, String toDate, String actionType) {

        return dao.getPendingCredits(fromDate, toDate, actionType);
    }
}
