package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.CodeValueDao;
import com.envista.msi.api.web.rest.dto.invoicing.CodeValueDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 4/25/2017.
 */
@Service
@Transactional
public class CodeValueService {

    private final Logger log = LoggerFactory.getLogger(CustomOmitsService.class);

    @Inject
    private CodeValueDao dao;

    public List<CodeValueDto> GetCodeValues(String groupName, String property1, String codeValue, String actionType) {

        return dao.getCodeValues(groupName, property1, codeValue, actionType);
    }

    public String getClaimFlagByVoiceId(Long voiceId) {

        return dao.getClaimFlagByVoiceId(voiceId);
    }
}
