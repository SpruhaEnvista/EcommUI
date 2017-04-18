package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.VoiceDao;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceDto;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceSearchBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by KRISHNAREDDYM on 4/12/2017.
 */
@Service
@Transactional
public class VoiceService {

    private final Logger log = LoggerFactory.getLogger(VoiceService.class);

    @Inject
    private VoiceDao dao;

    public List<VoiceDto> getAllVoices(Long userId) {

        return dao.getAllVoices(userId);
    }

    public List<VoiceDto> getParentVoiceNames(long voiceId) {

        return dao.getParentVoiceNames(voiceId);
    }

    public List<VoiceDto> getVoicesBySearchCriteria(VoiceSearchBean bean) {


        return dao.getVoicesBySearchCriteria(bean);
    }

    public VoiceDto createVoice(VoiceDto dto) {

        return dao.createVoice(dto);
    }

    public VoiceDto updateVoice(VoiceDto dto) {

        return dao.updateVoice(dto);
    }


    public VoiceDto findByVoiceName(String voiceName, String prevVoiceName) {

        return dao.findByVoiceName(voiceName,prevVoiceName);
    }

    public int deleteVoice(String voiceIds) {

        return dao.deleteVoice(voiceIds);
    }

    public VoiceDto findByVoiceId(Long voiceId) {

        return dao.findByVoiceId(voiceId);
    }
}
