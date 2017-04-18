package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceDto;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceSearchBean;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by KRISHNAREDDYM on 4/12/2017.
 */
@Repository("voiceDAO")
public class VoiceDao {

    @Inject
    private PersistentContext persistentContext;


    public List<VoiceDto> getAllVoices(Long userId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", userId)
                .and("P_VOICE_ID", 0L).and("P_PARENT_VOICE_ID", 0L);

        return persistentContext.findEntities("VoiceDto.getVoiceList", queryParameter);
    }

    public List<VoiceDto> getParentVoiceNames(long voiceId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", 0L)
                .and("P_VOICE_ID", 0L).and("P_PARENT_VOICE_ID", voiceId);

        return persistentContext.findEntities("VoiceDto.getVoiceList", queryParameter);
    }

    public VoiceDto findByVoiceId(Long voiceId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", 0L)
                .and("P_VOICE_ID", voiceId).and("P_PARENT_VOICE_ID", 0L);

        List<VoiceDto> dtos = persistentContext.findEntities("VoiceDto.getVoiceList", queryParameter);

        VoiceDto dto = null;
        if (dtos.size() > 0)
            dto = dtos.get(0);
        return dto;
    }

    public VoiceDto createVoice(VoiceDto dto) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_VOICE_ID", 0L)
                .and("P_VOICE_NAME", dto.getVoiceName()).and("P_VOICE_TYPE", dto.getVoiceType())
                .and("P_ALIAS_FOR", dto.getAliasFor()).and("P_PARENT_VOICE_ID", dto.getParentVoiceId())
                .and("P_COMMENTS", dto.getComments()).and("P_IS_ACTIVE", dto.isActive())
                .and("P_USER_ID", dto.getUserId()).and("P_ACTION_TYPE", "INSERT");


        return persistentContext.findEntityAndMapFields("VoiceDto.createOrUpdateVoice", queryParameter);
    }

    public VoiceDto updateVoice(VoiceDto dto) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_VOICE_ID", dto.getVoiceId())
                .and("P_VOICE_NAME", dto.getVoiceName()).and("P_VOICE_TYPE", dto.getVoiceType())
                .and("P_ALIAS_FOR", dto.getAliasFor()).and("P_PARENT_VOICE_ID", dto.getParentVoiceId())
                .and("P_COMMENTS", dto.getComments()).and("P_IS_ACTIVE", dto.isActive())
                .and("P_USER_ID", dto.getUserId()).and("P_ACTION_TYPE", "UPDATE");


        return persistentContext.findEntityAndMapFields("VoiceDto.createOrUpdateVoice", queryParameter);
    }

    public List<VoiceDto> getVoicesBySearchCriteria(VoiceSearchBean bean) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_VOICE_NAMES", bean.getVoiceName())
                .and("P_VOICE_TYPE", bean.getVoiceType()).and("P_VOICE_FLAG", bean.getVoiceFlag())
                .and("P_PARENT_VOICE_NAMES", bean.getParentVoiceName()).and("P_COMMENTS", bean.getComments());

        return persistentContext.findEntities("VoiceDto.searchVoice", queryParameter);
    }

    public VoiceDto findByVoiceName(String voiceName, String prevVoiceName) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_VOICE_NAME", voiceName)
                .and("P_PREV_VOICE_NAME", prevVoiceName);

       // return persistentContext.findEntity("VoiceDto.findByVoiceName", queryParameter);

        List<VoiceDto> dtos = persistentContext.findEntities("VoiceDto.findByVoiceName", queryParameter);

        VoiceDto dto = null;
        if (dtos.size() > 0)
            dto = dtos.get(0);

        return dto;

    }

    public int deleteVoice(String voiceIds) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_VOICE_IDS", voiceIds);

        List<VoiceDto> dtos = persistentContext.findEntities("VoiceDto.deleteVoice", queryParameter);

        int count = 0;
        if (dtos != null)
            count = dtos.size();

        return count;

    }
}
