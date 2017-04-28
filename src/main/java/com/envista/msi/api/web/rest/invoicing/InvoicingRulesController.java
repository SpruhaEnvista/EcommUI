package com.envista.msi.api.web.rest.invoicing;

import com.envista.msi.api.service.invoicing.InvoicingRulesService;
import com.envista.msi.api.web.rest.dto.invoicing.InvoicingRuleDetailsDto;
import com.envista.msi.api.web.rest.dto.invoicing.InvoicingRuleDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 4/27/2017.
 */
@RestController
@RequestMapping("/api/invRules")
public class InvoicingRulesController {

    private final Logger LOG = LoggerFactory.getLogger(InvoicingRulesController.class);

    @Inject
    private InvoicingRulesService service;

    /**
     * HTTP GET - get by customerIds
     */
    @RequestMapping(value = "/findByCustomerIds/{customerIds}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<InvoicingRuleDto>> findByCustomerIds(@PathVariable("customerIds") String customerIds) {
        LOG.info("***findByCustomerIds method started**** customerIds is : " + customerIds);

        Long[] customOmitIdsArray = null;
        if (customerIds != null && !StringUtils.equalsIgnoreCase(customerIds, "0")) {
            if (StringUtils.containsIgnoreCase(customerIds, "CU")) {
                customerIds = StringUtils.remove(customerIds, "CU");
            }

        }
        return new ResponseEntity<List<InvoicingRuleDto>>(service.findByCustomerIds(customerIds), HttpStatus.OK);
    }

    /**
     * HTTP GET - get by id
     */
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<InvoicingRuleDetailsDto>> findById(@PathVariable("id") Long id) {
        LOG.info("***findById method started**** id is : " + id);
        return new ResponseEntity<List<InvoicingRuleDetailsDto>>(service.findById(id), HttpStatus.OK);
    }
}
