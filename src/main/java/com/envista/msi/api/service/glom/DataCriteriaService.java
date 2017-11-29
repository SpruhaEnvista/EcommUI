package com.envista.msi.api.service.glom;

import com.envista.msi.api.dao.glom.DataCriteriaDao;
import com.envista.msi.api.dao.glom.DataObjectDao;
import com.envista.msi.api.web.rest.dto.glom.DataCriteriaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 11/29/2017.
 */
@Service
@Transactional
public class DataCriteriaService {

    private final Logger log = LoggerFactory.getLogger(DataCriteriaService.class);

    @Inject
    private DataCriteriaDao dao;

    public List<DataCriteriaDto> findById(long dataObjectId) {

        return dao.findById(dataObjectId);
    }
}
