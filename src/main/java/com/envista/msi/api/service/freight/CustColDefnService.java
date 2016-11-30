package com.envista.msi.api.service.freight;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpCustColDefnTb;
import com.envista.msi.api.repository.freight.ShpCustColDefnTbRepository;

@Service
@Transactional
public class CustColDefnService {

	private final Logger log = LoggerFactory.getLogger(CustColDefnService.class);
	
	@Inject
	private ShpCustColDefnTbRepository shpCustColDefnTbRepository;
	
	public List<ShpCustColDefnTb> getAllByCustomerIdandRptId(long customerId,long rptType)  {
		return shpCustColDefnTbRepository.findAllByCustomerIdAndRptType(new BigDecimal(customerId), new BigDecimal(rptType));
	}
}
