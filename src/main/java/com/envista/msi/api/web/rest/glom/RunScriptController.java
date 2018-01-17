package com.envista.msi.api.web.rest.glom;

import com.envista.msi.api.service.glom.RunScriptService;
import com.envista.msi.api.web.rest.dto.glom.RunReportDto;
import com.envista.msi.api.web.rest.util.FileOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 1/4/2018.
 */
@RestController
@RequestMapping("/api/runScript")
public class RunScriptController {

    private final Logger log = LoggerFactory.getLogger(RunScriptController.class);

    @Autowired
    private RunScriptService service;

    @Autowired
    private FileOperations fileOperations;

    @RequestMapping(value = "/runReport", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<RunReportDto>> runReport(@RequestParam(required = false, defaultValue = "0") Long runScriptId) throws Exception {
        log.info("***runReport method started****");

        List<RunReportDto> runReportDtos = service.getRunScriptInfo(runScriptId);

        fileOperations.exportGlmReportData(runReportDtos);

        return ResponseEntity.status(HttpStatus.OK).body(runReportDtos);
    }

}
