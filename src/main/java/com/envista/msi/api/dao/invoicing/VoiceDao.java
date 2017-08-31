package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceDto;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceSearchBean;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Created by KRISHNAREDDYM on 4/12/2017.
 */
@Repository("voiceDAO")
public class VoiceDao {

    @Inject
    private PersistentContext persistentContext;


    public List<VoiceDto> getAllVoices(Long userId, int offset, int limit,VoiceSearchBean bean) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", userId)
                .and("P_VOICE_ID", 0L).and("P_PARENT_VOICE_ID", 0L).and("P_OFFSET", offset).and("P_PAGE_SIZE", limit).and("P_SORT_COLUMN", bean.getSort()).and("P_ACTION_TYPE", "getAllVoices");

        return persistentContext.findEntities("VoiceDto.getVoiceList", queryParameter);
    }

    public int getCountOfVoices(Long userId,String sort) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", userId)
                .and("P_VOICE_ID", 0L).and("P_PARENT_VOICE_ID", 0L).and("P_OFFSET", 0).and("P_PAGE_SIZE", 0).and("P_SORT_COLUMN", sort).and("P_ACTION_TYPE", "countOfVoices");

        List<VoiceDto> dtos = persistentContext.findEntities("VoiceDto.getTotalCount", queryParameter);
        int count=0;
        if(null != dtos && dtos.size()>0){
            count=dtos.get(0).getTotalCount();
        }
        return count;
    }

    public List<VoiceDto> getParentVoiceNames(long voiceId,String sort) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", 0L)
                .and("P_VOICE_ID", 0L).and("P_PARENT_VOICE_ID", voiceId).and("P_OFFSET", 0).and("P_PAGE_SIZE", 0).and("P_SORT_COLUMN", sort).and("P_ACTION_TYPE", "getAllParentVoices");

        return persistentContext.findEntities("VoiceDto.getVoiceList", queryParameter);
    }

    public VoiceDto findByVoiceId(Long voiceId,String sort) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", 0L)
                .and("P_VOICE_ID", voiceId).and("P_PARENT_VOICE_ID", 0L).and("P_OFFSET", 0).and("P_PAGE_SIZE", 0).and("P_SORT_COLUMN", sort).and("P_ACTION_TYPE", "findByVoiceId");

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

    public List<VoiceDto> getVoicesBySearchCriteria(VoiceSearchBean bean, Map<String, Object> paginationFilterMap,int offset, int limit) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_VOICE_NAMES", bean.getVoiceName())
                .and("P_VOICE_TYPE", bean.getVoiceType()).and("P_VOICE_FLAG", bean.getVoiceFlag())
                .and("P_PARENT_VOICE_NAMES", bean.getParentVoiceName()).and("P_COMMENTS", bean.getComments()).and("P_SORT_COLUMN", bean.getSort())
                .and("P_OFFSET", offset).and("P_PAGE_SIZE", limit).and("P_ACTION_TYPE", "search");

        return persistentContext.findEntities("VoiceDto.searchVoice", queryParameter);
    }

    public int getCountOfSearchVoices(VoiceSearchBean bean,Long userId) {
        QueryParameter queryParameter = StoredProcedureParameter.with("P_VOICE_NAMES", bean.getVoiceName())
                .and("P_VOICE_TYPE", bean.getVoiceType()).and("P_VOICE_FLAG", bean.getVoiceFlag())
                .and("P_PARENT_VOICE_NAMES", bean.getParentVoiceName()).and("P_COMMENTS", bean.getComments()).and("P_SORT_COLUMN", bean.getSort())
                .and("P_OFFSET", 0).and("P_PAGE_SIZE", 0).and("P_ACTION_TYPE", "getCount");

        List<VoiceDto> dtos = persistentContext.findEntities("VoiceDto.searchCount", queryParameter);

        int count=0;
        if(null != dtos && dtos.size()>0){
            count = dtos.get(0).getTotalCount();
        }
        return count;
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

    public List<VoiceDto> getUVVoices() {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", 0L)
                .and("P_VOICE_ID", 0L).and("P_PARENT_VOICE_ID", 0L).and("P_OFFSET", 0).and("P_PAGE_SIZE", 0).and("P_SORT_COLUMN", null).and("P_ACTION_TYPE", "getUVvoices");

        return persistentContext.findEntities("VoiceDto.getVoiceList", queryParameter);
    }
}
