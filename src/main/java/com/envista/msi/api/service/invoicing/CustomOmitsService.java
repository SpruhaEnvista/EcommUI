package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.CustomOmitsDao;
import com.envista.msi.api.web.rest.dto.invoicing.CustomOmitsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 4/20/2017.
 */
@Service
@Transactional
public class CustomOmitsService {

    private final Logger log = LoggerFactory.getLogger(CustomOmitsService.class);

    @Inject
    private CustomOmitsDao dao;

    public List<CustomOmitsDto> findByuserId(long userId) {

        return dao.findByuserId(userId);
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

    public List<CustomOmitsDto> findBySearchCriteria(CustomOmitsDto dto) {

        return dao.findBySearchCriteria(dto);
    }

    public int delete(String customOmitIds) {

        return dao.delete(customOmitIds);
    }
}
