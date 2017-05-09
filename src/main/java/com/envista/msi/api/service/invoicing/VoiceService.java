package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.VoiceDao;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceDto;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceSearchBean;
import com.envista.msi.api.web.rest.util.pagination.EnspirePagination;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KRISHNAREDDYM on 4/12/2017.
 */
@Service
@Transactional
public class VoiceService {

    private final Logger log = LoggerFactory.getLogger(VoiceService.class);

    @Inject
    private VoiceDao dao;

    public List<VoiceDto> getParentVoiceNames(long voiceId) {

        return dao.getParentVoiceNames(voiceId);
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

    //voices pagination data without filtering
    public PaginationBean getVoicesPaginationData(int offset, int limit) throws Exception {
        Map<String, Object> paginationFilterMap = new HashMap<String, Object>();

        return new EnspirePagination() {
            @Override
            protected int getTotalRowCount(Map<String, Object> paginationFilterMap) {
                return dao.getCountOfVoices(0L);
            }

            @Override
            protected Object loadPaginationData(Map<String, Object> paginationFilterMap, int offset, int limit, String sortOrder) throws Exception {
                return loadAllVoices(paginationFilterMap, offset,limit);
            }
        }.preparePaginationData(paginationFilterMap, offset, limit);
    }
    private List<VoiceDto> loadAllVoices(Map<String, Object> paginationFilterMap, int offset, int limit) throws Exception {
        List<VoiceDto> dto=dao.getAllVoices(0L,offset, limit);
        return dto;
    }

    //voices pagination data with filtering
    public PaginationBean getSearchVoicesPaginationData(VoiceSearchBean filter, int offset, int limit) throws Exception {
        Map<String, Object> paginationFilterMap = new HashMap<String, Object>();
        paginationFilterMap.put("filter", filter);

        return new EnspirePagination() {
            @Override
            protected int getTotalRowCount(Map<String, Object> paginationFilterMap) {
                return dao.getCountOfSearchVoices(filter,0L);
            }

            @Override
            protected Object loadPaginationData(Map<String, Object> paginationFilterMap, int offset, int limit, String sortOrder) throws Exception {
                return dao.getVoicesBySearchCriteria(filter, paginationFilterMap, offset,limit);
            }
        }.preparePaginationData(paginationFilterMap, offset, limit);
    }
}
