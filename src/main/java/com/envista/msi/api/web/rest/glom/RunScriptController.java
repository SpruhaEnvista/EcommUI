package com.envista.msi.api.web.rest.glom;

import com.envista.msi.api.service.glom.RunScriptService;
import com.envista.msi.api.web.rest.dto.glom.RunReportDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 1/4/2018.
 */
@RestController
@RequestMapping("/api/runScript")
public class RunScriptController {

    private final Logger log = LoggerFactory.getLogger(RunScriptController.class);

    @Inject
    private RunScriptService service;

    @RequestMapping(value = "/runReport", produces = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.GET)
    public ResponseEntity<String> runReport(@RequestParam(required = false, defaultValue = "0") Long runScriptId) throws Exception {
        log.info("***runReport method started****");

        List<RunReportDto> runReportDtos = service.getRunScriptInfo(runScriptId);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

}
