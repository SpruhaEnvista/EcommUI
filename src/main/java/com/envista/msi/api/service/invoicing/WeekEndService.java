package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.WeekEndDao;
import com.envista.msi.api.web.rest.dto.invoicing.WeekEndDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by admin on 5/15/2017.
 */
@Service
@Transactional
public class WeekEndService {

    private final Logger log = LoggerFactory.getLogger(WeekEndService.class);

    @Inject
    private WeekEndDao dao;

    public List<WeekEndDto> getAll() {

        return dao.getAll();
    }

    public WeekEndDto findByWeekEndDate(String weekEndDate) {

        return dao.findByWeekEndDate(weekEndDate);
    }
}
