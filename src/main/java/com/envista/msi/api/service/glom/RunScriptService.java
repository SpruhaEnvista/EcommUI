package com.envista.msi.api.service.glom;

import com.envista.msi.api.dao.glom.RunScriptDao;
import com.envista.msi.api.web.rest.dto.glom.RunReportDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 1/4/2018.
 */
@Service
@Transactional
public class RunScriptService {

    private final Logger log = LoggerFactory.getLogger(RunScriptService.class);

    @Inject
    private RunScriptDao dao;

    public List<RunReportDto> getRunScriptInfo(Long runScriptId) {

        return dao.getRunScriptInfo(runScriptId);

    }
}
