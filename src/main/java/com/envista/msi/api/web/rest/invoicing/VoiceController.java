package com.envista.msi.api.web.rest.invoicing;

import com.envista.msi.api.service.invoicing.VoiceService;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceDto;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceSearchBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 4/12/2017.
 */
@RestController
@RequestMapping("/api/voice")
public class VoiceController {

    private final Logger log = LoggerFactory.getLogger(VoiceController.class);

    @Inject
    private VoiceService service;

    /**
     * HTTP GET - Get all Voices
     * */
    @RequestMapping(value = "/getVoices", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<VoiceDto>> getAllVoices() {
        log.info("***getAllVoices method started****");
        List<VoiceDto> voices = service.getAllVoices(0L);
        log.info("***voices json***==== " + voices);
        return new ResponseEntity<List<VoiceDto>>(voices, HttpStatus.OK);
    }

    @RequestMapping(value = "/getParentVoiceNames/{voiceId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<VoiceDto>> getAllParentVoiceNames(@PathVariable("voiceId") long voiceId) {
        log.info("***getAllParentVoiceNames method started****voice name is : "+voiceId);

        List<VoiceDto> parentVoiceNamesList = service.getParentVoiceNames(voiceId);

        return new ResponseEntity<List<VoiceDto>>(parentVoiceNamesList, HttpStatus.OK);

    }

    @RequestMapping(value = "/getSearchCriteriaList", params = {"voiceNames", "voiceType", "voiceFlag", "pVoiceNames", "comments"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<VoiceDto>> getSearchCriteriaList(@RequestParam String voiceNames, @RequestParam String voiceType, @RequestParam String voiceFlag,
                                                                @RequestParam String pVoiceNames, @RequestParam String comments) {

        log.info("***getSearchCriteriaList method started****");

        VoiceSearchBean bean = new VoiceSearchBean();
        bean.setVoiceName(voiceNames);
        bean.setVoiceType(voiceType);
        bean.setVoiceFlag(voiceFlag);
        bean.setParentVoiceName(pVoiceNames);
        bean.setComments(comments);
        List<VoiceDto> voiceTbs = service.getVoicesBySearchCriteria(bean);

        log.info("***getSearchCriteriaList method dbvoice****" + voiceTbs.size());

        return new ResponseEntity<List<VoiceDto>>(voiceTbs, HttpStatus.OK);
    }

    /**
     * HTTP POST - Create new Voice
     * */
    @RequestMapping(value = "/createVoice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<VoiceDto> createVoice(@RequestBody VoiceDto dto) {
        log.info("***createVoice method started****" + dto);

        VoiceDto dbVoice = service.createVoice(dto);

        log.info("***createVoice method dbvoice****" + dbVoice);
        return new ResponseEntity<VoiceDto>(dbVoice, HttpStatus.OK);
    }

    /**
     * HTTP PUT - Update Voice
     * */
    @RequestMapping(value = "/updateVoice", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<VoiceDto> updateVoice(@RequestBody VoiceDto dto) {
        log.info("***updateVoice method started****" + dto);
        VoiceDto dbDto = service.updateVoice(dto);
        return new ResponseEntity<VoiceDto>(dbDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByVoiceName/{voiceName}/{prevVoiceName}", produces = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.GET)
    public ResponseEntity<String> findByVoiceName(@PathVariable("voiceName") String voiceName, @PathVariable("prevVoiceName") String prevVoiceName) {
        log.info("***findByVoiceName method started****voice name is : " + voiceName);

        VoiceDto dto = service.findByVoiceName(voiceName, prevVoiceName);

        String msg = "Voice Name is not exist";

        if (dto != null)
            msg = "Voice Name is already exist";

        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

/*    *//**
     * HTTP DELETE - Delete Voice
     * */
    @RequestMapping(value = "/deleteVoice", params = {"voiceIds"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> deleteVoice(@RequestParam String voiceIds) {
        log.info("***deleteVoice method started****voice ids are : " + voiceIds);

        int count = service.deleteVoice(voiceIds);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    /**
     * HTTP GET -  get voice by voice id
     */
    @RequestMapping(value = "/{voiceId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<VoiceDto> findByVoiceId(@PathVariable("voiceId") Long voiceId) {
        log.info("***findByVoiceId method started****voice id is : " + voiceId);
        return new ResponseEntity<VoiceDto>(service.findByVoiceId(voiceId), HttpStatus.OK);
    }
}
