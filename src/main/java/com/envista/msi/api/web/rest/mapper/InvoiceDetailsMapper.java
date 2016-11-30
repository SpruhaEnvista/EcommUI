/**
 * 
 */
package com.envista.msi.api.web.rest.mapper;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.envista.msi.api.domain.freight.ShpNspCodeValuesTb;
import com.envista.msi.api.domain.freight.ShpNspInvoiceDetailsTb;
import com.envista.msi.api.domain.freight.ShpNspTaskTb;
import com.envista.msi.api.web.rest.dto.InvoiceDetails;
import com.envista.msi.api.web.rest.dto.UserProfileBean;


public class InvoiceDetailsMapper {
	private static Set<String> columns = null;
	protected static String[] nameModifiedProperties = {
			"invoiceStatus shpNspInvoiceDetailsTb.shpNspCodeValuesTb1.codeValue",
			"billOption shpNspInvoiceDetailsTb.shpNspCodeValuesTb2.codeValue",
			"invoiceMode shpNspInvoiceDetailsTb.shpNspCodeValuesTb3.codeValue",
			"totalWeightUOM shpNspInvoiceDetailsTb.shpNspCodeValuesTb3.codeValue" 
			};
	
	private static InvoiceDetails invDetailsTbToInvDetails(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTb) {
        if ( shpNspInvoiceDetailsTb == null ) {
            return null;
        }

        InvoiceDetails invoiceDetails = new InvoiceDetails();

        invoiceDetails.setNspInvoiceDetailsId( shpNspInvoiceDetailsTb.getNspInvoiceDetailsId() );
        invoiceDetails.setAccountNumber( shpNspInvoiceDetailsTb.getAccountNumber() );
        invoiceDetails.setActualKilometers( shpNspInvoiceDetailsTb.getActualKilometers() );
        invoiceDetails.setActualMiles( shpNspInvoiceDetailsTb.getActualMiles() );
        invoiceDetails.setAddressType( shpNspInvoiceDetailsTb.getAddressType() );
        invoiceDetails.setAuditorComments( shpNspInvoiceDetailsTb.getAuditorComments() );
        invoiceDetails.setAverageTransitDays( shpNspInvoiceDetailsTb.getAverageTransitDays() );
        invoiceDetails.setBalanceAmount( shpNspInvoiceDetailsTb.getBalanceAmount() );
        invoiceDetails.setBillDate( shpNspInvoiceDetailsTb.getBillDate() );
        invoiceDetails.setBillToLocationCode( shpNspInvoiceDetailsTb.getBillToLocationCode() );
        invoiceDetails.setBilledMiles( shpNspInvoiceDetailsTb.getBilledMiles() );
        invoiceDetails.setBilltoAddress1( shpNspInvoiceDetailsTb.getBilltoAddress1() );
        invoiceDetails.setBilltoAddress2( shpNspInvoiceDetailsTb.getBilltoAddress2() );
        invoiceDetails.setBilltoAddress3( shpNspInvoiceDetailsTb.getBilltoAddress3() );
        invoiceDetails.setBilltoCity( shpNspInvoiceDetailsTb.getBilltoCity() );
        invoiceDetails.setBilltoCountry( shpNspInvoiceDetailsTb.getBilltoCountry() );
        invoiceDetails.setBilltoName( shpNspInvoiceDetailsTb.getBilltoName() );
        invoiceDetails.setBilltoState( shpNspInvoiceDetailsTb.getBilltoState() );
        invoiceDetails.setBilltoZipcode( shpNspInvoiceDetailsTb.getBilltoZipcode() );
        invoiceDetails.setBolNumber( shpNspInvoiceDetailsTb.getBolNumber() );
        invoiceDetails.setBrokParentInvId( shpNspInvoiceDetailsTb.getBrokParentInvId() );
        invoiceDetails.setCalculatedMiles( shpNspInvoiceDetailsTb.getCalculatedMiles() );
        invoiceDetails.setCarrierComments( shpNspInvoiceDetailsTb.getCarrierComments() );
        invoiceDetails.setCheckAmount( shpNspInvoiceDetailsTb.getCheckAmount() );
        invoiceDetails.setCheckDate( shpNspInvoiceDetailsTb.getCheckDate() );
        invoiceDetails.setCheckNo( shpNspInvoiceDetailsTb.getCheckNo() );
        invoiceDetails.setChildCarrierName( shpNspInvoiceDetailsTb.getChildCarrierName() );
        invoiceDetails.setClosedBy( shpNspInvoiceDetailsTb.getClosedBy() );
        invoiceDetails.setClosedDate( shpNspInvoiceDetailsTb.getClosedDate() );
        invoiceDetails.setCodAmount( shpNspInvoiceDetailsTb.getCodAmount() );
        invoiceDetails.setConsolParentId( shpNspInvoiceDetailsTb.getConsolParentId() );
        invoiceDetails.setCountOfPalletsReloaded( shpNspInvoiceDetailsTb.getCountOfPalletsReloaded() );
        invoiceDetails.setCreateDate( shpNspInvoiceDetailsTb.getCreateDate() );
        invoiceDetails.setCreateUser( shpNspInvoiceDetailsTb.getCreateUser() );
        invoiceDetails.setCreditMemo( shpNspInvoiceDetailsTb.getCreditMemo() );
        invoiceDetails.setCurrencyCode( shpNspInvoiceDetailsTb.getCurrencyCode() );
        invoiceDetails.setCurrencyExchangeDate( shpNspInvoiceDetailsTb.getCurrencyExchangeDate() );
        invoiceDetails.setCurrencyExchangeRate( shpNspInvoiceDetailsTb.getCurrencyExchangeRate() );
        invoiceDetails.setCustAction( shpNspInvoiceDetailsTb.getCustAction() );
        invoiceDetails.setCustActionBy( shpNspInvoiceDetailsTb.getCustActionBy() );
        invoiceDetails.setCustActionDate( shpNspInvoiceDetailsTb.getCustActionDate() );
        invoiceDetails.setCustActivityHistId( shpNspInvoiceDetailsTb.getCustActivityHistId() );
        invoiceDetails.setCustomerComments( shpNspInvoiceDetailsTb.getCustomerComments() );
        invoiceDetails.setCustomerDefined1( shpNspInvoiceDetailsTb.getCustomerDefined1() );
        invoiceDetails.setCustomerDefined2( shpNspInvoiceDetailsTb.getCustomerDefined2() );
        invoiceDetails.setCustomerDefined3( shpNspInvoiceDetailsTb.getCustomerDefined3() );
        invoiceDetails.setCustomsDocumentNumber( shpNspInvoiceDetailsTb.getCustomsDocumentNumber() );
        invoiceDetails.setDataEntryComments( shpNspInvoiceDetailsTb.getDataEntryComments() );
        invoiceDetails.setDeliveryDate( shpNspInvoiceDetailsTb.getDeliveryDate() );
        invoiceDetails.setDepartment( shpNspInvoiceDetailsTb.getDepartment() );
        invoiceDetails.setDirection( shpNspInvoiceDetailsTb.getDirection() );
        invoiceDetails.setDistanceUom( shpNspInvoiceDetailsTb.getDistanceUom() );
        invoiceDetails.setDupCheckQuery( shpNspInvoiceDetailsTb.getDupCheckQuery() );
        invoiceDetails.setDupChkInvoiceId( shpNspInvoiceDetailsTb.getDupChkInvoiceId() );
        invoiceDetails.setDwExtractFlag( shpNspInvoiceDetailsTb.getDwExtractFlag() );
        invoiceDetails.setEdiFileName( shpNspInvoiceDetailsTb.getEdiFileName() );
        invoiceDetails.setEquipmentType( shpNspInvoiceDetailsTb.getEquipmentType() );
        invoiceDetails.setExceptionReason( shpNspInvoiceDetailsTb.getExceptionReason() );
        invoiceDetails.setExportCode( shpNspInvoiceDetailsTb.getExportCode() );
        invoiceDetails.setFreightInvoiceType( shpNspInvoiceDetailsTb.getFreightInvoiceType() );
        invoiceDetails.setFtpserverLogId( shpNspInvoiceDetailsTb.getFtpserverLogId() );
        invoiceDetails.setGainShare( shpNspInvoiceDetailsTb.getGainShare() );
        invoiceDetails.setGlAccountsCode( shpNspInvoiceDetailsTb.getGlAccountsCode() );
        invoiceDetails.setGlAppliedDate( shpNspInvoiceDetailsTb.getGlAppliedDate() );
        invoiceDetails.setGlCodingAttempted( shpNspInvoiceDetailsTb.getGlCodingAttempted() );
        invoiceDetails.setGlSource( shpNspInvoiceDetailsTb.getGlSource() );
        invoiceDetails.setGlbEquipType( shpNspInvoiceDetailsTb.getGlbEquipType() );
        invoiceDetails.setGsaCarrier( shpNspInvoiceDetailsTb.getGsaCarrier() );
        invoiceDetails.setGuaranteedDate( shpNspInvoiceDetailsTb.getGuaranteedDate() );
        invoiceDetails.setGuaranteedService( shpNspInvoiceDetailsTb.getGuaranteedService() );
        invoiceDetails.setHazmat( shpNspInvoiceDetailsTb.getHazmat() );
        invoiceDetails.setHouseBolDate( shpNspInvoiceDetailsTb.getHouseBolDate() );
        invoiceDetails.setHouseBolNumber( shpNspInvoiceDetailsTb.getHouseBolNumber() );
        invoiceDetails.setImportCode( shpNspInvoiceDetailsTb.getImportCode() );
        invoiceDetails.setIncoTermCode( shpNspInvoiceDetailsTb.getIncoTermCode() );
        invoiceDetails.setIncoTermPoint( shpNspInvoiceDetailsTb.getIncoTermPoint() );
        invoiceDetails.setInterlineBolDate( shpNspInvoiceDetailsTb.getInterlineBolDate() );
        invoiceDetails.setInterlineBolNumber( shpNspInvoiceDetailsTb.getInterlineBolNumber() );
        invoiceDetails.setInterlineCarrier( shpNspInvoiceDetailsTb.getInterlineCarrier() );
        invoiceDetails.setInterlineDeliveryDate( shpNspInvoiceDetailsTb.getInterlineDeliveryDate() );
        invoiceDetails.setInterlineVesselName( shpNspInvoiceDetailsTb.getInterlineVesselName() );
        invoiceDetails.setInterlineVoyageNumber( shpNspInvoiceDetailsTb.getInterlineVoyageNumber() );
        invoiceDetails.setInvPageCount( shpNspInvoiceDetailsTb.getInvPageCount() );
        invoiceDetails.setInvoiceDueDate( shpNspInvoiceDetailsTb.getInvoiceDueDate() );
        invoiceDetails.setInvoiceHsTaxAmount( shpNspInvoiceDetailsTb.getInvoiceHsTaxAmount() );
        invoiceDetails.setInvoiceIsaNumber( shpNspInvoiceDetailsTb.getInvoiceIsaNumber() );
        invoiceDetails.setInvoiceNumber( shpNspInvoiceDetailsTb.getInvoiceNumber() );
        invoiceDetails.setInvoiceTax1Amount( shpNspInvoiceDetailsTb.getInvoiceTax1Amount() );
        invoiceDetails.setInvoiceTax2Amount( shpNspInvoiceDetailsTb.getInvoiceTax2Amount() );
        invoiceDetails.setInvoiceType( shpNspInvoiceDetailsTb.getInvoiceType() );
        invoiceDetails.setIsAccessorialsChecked( shpNspInvoiceDetailsTb.getIsAccessorialsChecked() );
        invoiceDetails.setIsBillOptionCorrect( shpNspInvoiceDetailsTb.getIsBillOptionCorrect() );
        invoiceDetails.setIsDocumentationChecked( shpNspInvoiceDetailsTb.getIsDocumentationChecked() );
        invoiceDetails.setIsDuplicateBillingChecked( shpNspInvoiceDetailsTb.getIsDuplicateBillingChecked() );
        invoiceDetails.setIsFreightChargeChecked( shpNspInvoiceDetailsTb.getIsFreightChargeChecked() );
        invoiceDetails.setIsFuelSurchargesChecked( shpNspInvoiceDetailsTb.getIsFuelSurchargesChecked() );
        invoiceDetails.setLadingDescription( shpNspInvoiceDetailsTb.getLadingDescription() );
        invoiceDetails.setLaneDescription( shpNspInvoiceDetailsTb.getLaneDescription() );
        invoiceDetails.setLaneId( shpNspInvoiceDetailsTb.getLaneId() );
        if ( shpNspInvoiceDetailsTb.getLastUpdateDate() != null ) {
            invoiceDetails.setLastUpdateDate( new Timestamp( shpNspInvoiceDetailsTb.getLastUpdateDate().getTime() ) );
        }
        invoiceDetails.setLastUpdateUser( shpNspInvoiceDetailsTb.getLastUpdateUser() );
        invoiceDetails.setLinkedDocuments( shpNspInvoiceDetailsTb.getLinkedDocuments() );
        invoiceDetails.setLoadMatchDate( shpNspInvoiceDetailsTb.getLoadMatchDate() );
        invoiceDetails.setLoadMatchExcpDate( shpNspInvoiceDetailsTb.getLoadMatchExcpDate() );
        invoiceDetails.setLoadMatched( shpNspInvoiceDetailsTb.getLoadMatched() );
        invoiceDetails.setLoadMatchingExcpComments( shpNspInvoiceDetailsTb.getLoadMatchingExcpComments() );
        invoiceDetails.setLookUpBolNo( shpNspInvoiceDetailsTb.getLookUpBolNo() );
        invoiceDetails.setLookUpInvoiceNo( shpNspInvoiceDetailsTb.getLookUpInvoiceNo() );
        invoiceDetails.setLookUpProNo( shpNspInvoiceDetailsTb.getLookUpProNo() );
        invoiceDetails.setMasterBolDate( shpNspInvoiceDetailsTb.getMasterBolDate() );
        invoiceDetails.setMasterBolNumber( shpNspInvoiceDetailsTb.getMasterBolNumber() );
        invoiceDetails.setMerchandiseCategory( shpNspInvoiceDetailsTb.getMerchandiseCategory() );
        invoiceDetails.setNonMerchandiseCategory( shpNspInvoiceDetailsTb.getNonMerchandiseCategory() );
        invoiceDetails.setOrigInvoiceId( shpNspInvoiceDetailsTb.getOrigInvoiceId() );
        invoiceDetails.setPaidAmount( shpNspInvoiceDetailsTb.getPaidAmount() );
        invoiceDetails.setPaymentType( shpNspInvoiceDetailsTb.getPaymentType() );
        invoiceDetails.setPoNumber( shpNspInvoiceDetailsTb.getPoNumber() );
        invoiceDetails.setPortOfDestination( shpNspInvoiceDetailsTb.getPortOfDestination() );
        invoiceDetails.setPortOfDischargeCountry( shpNspInvoiceDetailsTb.getPortOfDischargeCountry() );
        invoiceDetails.setPortOfLoadingCountry( shpNspInvoiceDetailsTb.getPortOfLoadingCountry() );
        invoiceDetails.setPortOfOrigin( shpNspInvoiceDetailsTb.getPortOfOrigin() );
        invoiceDetails.setProDate( shpNspInvoiceDetailsTb.getProDate() );
        invoiceDetails.setProNumber( shpNspInvoiceDetailsTb.getProNumber() );
        invoiceDetails.setProductCode( shpNspInvoiceDetailsTb.getProductCode() );
        invoiceDetails.setProductDescription( shpNspInvoiceDetailsTb.getProductDescription() );
        invoiceDetails.setProofOfDelivery( shpNspInvoiceDetailsTb.getProofOfDelivery() );
        invoiceDetails.setRatedMiles( shpNspInvoiceDetailsTb.getRatedMiles() );
        invoiceDetails.setRatingComments( shpNspInvoiceDetailsTb.getRatingComments() );
        invoiceDetails.setRatingContract( shpNspInvoiceDetailsTb.getRatingContract() );
        invoiceDetails.setReceiptNumber( shpNspInvoiceDetailsTb.getReceiptNumber() );
        invoiceDetails.setReceivedDate( shpNspInvoiceDetailsTb.getReceivedDate() );
        invoiceDetails.setReceiverAddress1( shpNspInvoiceDetailsTb.getReceiverAddress1() );
        invoiceDetails.setReceiverAddress2( shpNspInvoiceDetailsTb.getReceiverAddress2() );
        invoiceDetails.setReceiverAddress3( shpNspInvoiceDetailsTb.getReceiverAddress3() );
        invoiceDetails.setReceiverCity( shpNspInvoiceDetailsTb.getReceiverCity() );
        invoiceDetails.setReceiverCountry( shpNspInvoiceDetailsTb.getReceiverCountry() );
        invoiceDetails.setReceiverLocationCode( shpNspInvoiceDetailsTb.getReceiverLocationCode() );
        invoiceDetails.setReceiverName( shpNspInvoiceDetailsTb.getReceiverName() );
        invoiceDetails.setReceiverRegion( shpNspInvoiceDetailsTb.getReceiverRegion() );
        invoiceDetails.setReceiverState( shpNspInvoiceDetailsTb.getReceiverState() );
        invoiceDetails.setReceiverVatin( shpNspInvoiceDetailsTb.getReceiverVatin() );
        invoiceDetails.setReceiverZipcode( shpNspInvoiceDetailsTb.getReceiverZipcode() );
        invoiceDetails.setReference1( shpNspInvoiceDetailsTb.getReference1() );
        invoiceDetails.setReference2( shpNspInvoiceDetailsTb.getReference2() );
        invoiceDetails.setReference3( shpNspInvoiceDetailsTb.getReference3() );
        invoiceDetails.setReference4( shpNspInvoiceDetailsTb.getReference4() );
        invoiceDetails.setRemitComments( shpNspInvoiceDetailsTb.getRemitComments() );
        invoiceDetails.setRemitToAddress( shpNspInvoiceDetailsTb.getRemitToAddress() );
        invoiceDetails.setRoutingNotes( shpNspInvoiceDetailsTb.getRoutingNotes() );
        invoiceDetails.setRtpFlag( shpNspInvoiceDetailsTb.getRtpFlag() );
        invoiceDetails.setRunNo( shpNspInvoiceDetailsTb.getRunNo() );
        invoiceDetails.setScannedInvoiceId( shpNspInvoiceDetailsTb.getScannedInvoiceId() );
        invoiceDetails.setScannerComments( shpNspInvoiceDetailsTb.getScannerComments() );
        invoiceDetails.setServiceCodeId( shpNspInvoiceDetailsTb.getServiceCodeId() );
        invoiceDetails.setShipDate( shpNspInvoiceDetailsTb.getShipDate() );
        invoiceDetails.setShipmentDirection( shpNspInvoiceDetailsTb.getShipmentDirection() );
        invoiceDetails.setShipperAddress1( shpNspInvoiceDetailsTb.getShipperAddress1() );
        invoiceDetails.setShipperAddress2( shpNspInvoiceDetailsTb.getShipperAddress2() );
        invoiceDetails.setShipperAddress3( shpNspInvoiceDetailsTb.getShipperAddress3() );
        invoiceDetails.setShipperCity( shpNspInvoiceDetailsTb.getShipperCity() );
        invoiceDetails.setShipperCountry( shpNspInvoiceDetailsTb.getShipperCountry() );
        invoiceDetails.setShipperLocationCode( shpNspInvoiceDetailsTb.getShipperLocationCode() );
        invoiceDetails.setShipperName( shpNspInvoiceDetailsTb.getShipperName() );
        invoiceDetails.setShipperRegion( shpNspInvoiceDetailsTb.getShipperRegion() );
        invoiceDetails.setShipperState( shpNspInvoiceDetailsTb.getShipperState() );
        invoiceDetails.setShipperVatin( shpNspInvoiceDetailsTb.getShipperVatin() );
        invoiceDetails.setShipperZipcode( shpNspInvoiceDetailsTb.getShipperZipcode() );
        invoiceDetails.setStandardTransitDays( shpNspInvoiceDetailsTb.getStandardTransitDays() );
        invoiceDetails.setSubtotalAmount( shpNspInvoiceDetailsTb.getSubtotalAmount() );
        invoiceDetails.setTarrifCode( shpNspInvoiceDetailsTb.getTarrifCode() );
        invoiceDetails.setTemp1( shpNspInvoiceDetailsTb.getTemp1() );
        invoiceDetails.setTotalCharges( shpNspInvoiceDetailsTb.getTotalCharges() );
        invoiceDetails.setTotalCharges2( shpNspInvoiceDetailsTb.getTotalCharges2() );
        invoiceDetails.setTotalCharges2Currency( shpNspInvoiceDetailsTb.getTotalCharges2Currency() );
        invoiceDetails.setTotalCharges3( shpNspInvoiceDetailsTb.getTotalCharges3() );
        invoiceDetails.setTotalCharges3Currency( shpNspInvoiceDetailsTb.getTotalCharges3Currency() );
        invoiceDetails.setTotalCharges4( shpNspInvoiceDetailsTb.getTotalCharges4() );
        invoiceDetails.setTotalCharges4Currency( shpNspInvoiceDetailsTb.getTotalCharges4Currency() );
        invoiceDetails.setTotalDueAmount( shpNspInvoiceDetailsTb.getTotalDueAmount() );
        invoiceDetails.setTotalInvoicedUom( shpNspInvoiceDetailsTb.getTotalInvoicedUom() );
        invoiceDetails.setTotalInvoicedVolume( shpNspInvoiceDetailsTb.getTotalInvoicedVolume() );
        invoiceDetails.setTotalQty( shpNspInvoiceDetailsTb.getTotalQty() );
        invoiceDetails.setTotalShipUnits( shpNspInvoiceDetailsTb.getTotalShipUnits() );
        invoiceDetails.setTotalShipUom( shpNspInvoiceDetailsTb.getTotalShipUom() );
        invoiceDetails.setTotalWeight( shpNspInvoiceDetailsTb.getTotalWeight() );
        invoiceDetails.setUsdApprovedCharges( shpNspInvoiceDetailsTb.getUsdApprovedCharges() );
        invoiceDetails.setUsdTotalCharges( shpNspInvoiceDetailsTb.getUsdTotalCharges() );
        invoiceDetails.setVendorNumber( shpNspInvoiceDetailsTb.getVendorNumber() );
        invoiceDetails.setShpCarrierTb( shpNspInvoiceDetailsTb.getShpCarrierTb() );
        invoiceDetails.setShpCustomerProfileTb( shpNspInvoiceDetailsTb.getShpCustomerProfileTb() );
        invoiceDetails.setInvoiceStatus(shpNspInvoiceDetailsTb.getShpNspCodeValuesTb1().getCodeValue());
        invoiceDetails.setBillOption(setCodeValueFromEntity(shpNspInvoiceDetailsTb.getShpNspCodeValuesTb2()));
        invoiceDetails.setInvoiceMode(setCodeValueFromEntity(shpNspInvoiceDetailsTb.getShpNspCodeValuesTb3()));
        invoiceDetails.setTotalWeightUOM(setCodeValueFromEntity(shpNspInvoiceDetailsTb.getShpNspCodeValuesTb4()));
        List<ShpNspTaskTb> list = shpNspInvoiceDetailsTb.getShpNspTaskTbs();
        if ( list != null ) {
            invoiceDetails.setShpNspTaskTbs(       new ArrayList<ShpNspTaskTb>( list )
            );
        }

        return invoiceDetails;
    }
	
	private static String setCodeValueFromEntity(ShpNspCodeValuesTb shpNspCodeValues){
		if(shpNspCodeValues != null && !StringUtils.isEmpty(shpNspCodeValues.getCodeValue())){
			return shpNspCodeValues.getCodeValue();
		}
		return null;
	}
	
	public static Page<InvoiceDetails> mapToDTO(List<ShpNspInvoiceDetailsTb> shpNspInvoiceDetailsTbList, Pageable page,
			long l) throws Exception {
		List<InvoiceDetails> invDetails = new ArrayList<InvoiceDetails>();
		for (ShpNspInvoiceDetailsTb content : shpNspInvoiceDetailsTbList)
			invDetails.add(invDetailsTbToInvDetails(content));
		return new PageImpl<InvoiceDetails>(invDetails, page, l);
	}
		
	public static InvoiceDetails buildBean(ShpNspInvoiceDetailsTb invoiceDetailsTb) throws Exception {
		InvoiceDetails invDetailsBean = new InvoiceDetails();
		if (invoiceDetailsTb != null) {
			invDetailsBean.setAccountNumber(invoiceDetailsTb.getAccountNumber());
			invDetailsBean.setActualKilometers(invoiceDetailsTb.getActualKilometers());
			invDetailsBean.setActualMiles(invoiceDetailsTb.getActualMiles());
			invDetailsBean.setAddressType(invoiceDetailsTb.getAddressType());
			invDetailsBean.setAuditorComments(invoiceDetailsTb.getAuditorComments());
			invDetailsBean.setAverageTransitDays(invoiceDetailsTb.getAverageTransitDays());
			
			invDetailsBean.setBalanceAmount(invoiceDetailsTb.getBalanceAmount());
			
			invDetailsBean.setBillDate(invoiceDetailsTb.getBillDate());
			invDetailsBean.setBilledMiles(invoiceDetailsTb.getBilledMiles());
			
			invDetailsBean.setBilltoName(invoiceDetailsTb.getBilltoName());
			
			invDetailsBean.setBilltoAddress1(invoiceDetailsTb.getBilltoAddress1());
			invDetailsBean.setBilltoAddress2(invoiceDetailsTb.getBilltoAddress2());
			invDetailsBean.setBilltoAddress3(invoiceDetailsTb.getBilltoAddress3());
			invDetailsBean.setBilltoCity(invoiceDetailsTb.getBilltoCity());
			invDetailsBean.setBilltoState(invoiceDetailsTb.getBilltoState());
			invDetailsBean.setBilltoZipcode(invoiceDetailsTb.getBilltoZipcode());
			invDetailsBean.setBilltoCountry(invoiceDetailsTb.getBilltoCountry());
			invDetailsBean.setBillToLocationCode(invoiceDetailsTb.getBillToLocationCode());
			
			
			invDetailsBean.setBolNumber(invoiceDetailsTb.getBolNumber());
			invDetailsBean.setBrokParentInvId(invoiceDetailsTb.getBrokParentInvId());
			
			invDetailsBean.setCalculatedMiles(invoiceDetailsTb.getCalculatedMiles());
			invDetailsBean.setCarrierComments(invoiceDetailsTb.getCarrierComments());
			invDetailsBean.setCheckAmount(invoiceDetailsTb.getCheckAmount());
			invDetailsBean.setCheckDate(invoiceDetailsTb.getCheckDate());
			invDetailsBean.setCheckNo(invoiceDetailsTb.getCheckNo());
			
			invDetailsBean.setChildCarrierName(invoiceDetailsTb.getChildCarrierName());
			
			invDetailsBean.setClosedBy(invoiceDetailsTb.getClosedBy());
			invDetailsBean.setClosedDate(invoiceDetailsTb.getCheckDate());
			invDetailsBean.setCodAmount(invoiceDetailsTb.getCodAmount());
			invDetailsBean.setConsolParentId(invoiceDetailsTb.getConsolParentId());
			invDetailsBean.setCountOfPalletsReloaded(invoiceDetailsTb.getCountOfPalletsReloaded());
			
			invDetailsBean.setCreateDate(invoiceDetailsTb.getCreateDate());
			invDetailsBean.setCreateUser(invoiceDetailsTb.getCreateUser());
			invDetailsBean.setCustomerComments(invoiceDetailsTb.getCustomerComments());
		
			
			
			
			invDetailsBean.setInvoiceNumber(invoiceDetailsTb.getInvoiceNumber());
			//invDetailsBean.set
			//setFlagsJsonToBean(userbean, shpUserProfileTb.getFlagsJson());
		}
		return invDetailsBean;
	}
	
	public static String getSortableProperty(String sortColumn) {
		if(StringUtils.isEmpty(sortColumn)){
			return null;
		}
		if(columns == null || columns.isEmpty()){
			initSortableColumns();
		}
		if(columns.contains(sortColumn)){
			return sortColumn;
		}
		return null;
	}

	/**
	 * Method to create json string from the bean. 
	 * 
	 * @param userBean
	 * @return
	 * @throws SQLException
	 */
	/*public String getFlagsJson(UserProfileBean userBean) throws Exception {
		if (m_listFlagsJson != null && m_listFlagsJson.size() > 0) {
			JSONObject jsonObject = new JSONObject();
			boolean anyFlagPresent = false;
			for (String flagName : m_listFlagsJson) {
				boolean flagValue;
				try {
					flagValue = new Boolean(BeanUtils.getProperty(userBean, flagName));
					if (flagValue) {
						jsonObject.put(flagName, flagValue);
						anyFlagPresent = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception(e.getMessage());
				}

			}
			if(anyFlagPresent) {
				return jsonObject.toString();
			}
		} 
		return null;
	}*/

	/**
	 * Method to set properties in bean from flags_json in DB.
	 * 
	 * @param userBean
	 * @param flagsJson
	 * @throws Exception 
	 * @throws SQLException
	 */
	private static void setFlagsJsonToBean(UserProfileBean userBean, String flagsJson) throws Exception{
		if (flagsJson != null && !flagsJson.isEmpty()) {
			try {
				JSONObject jsonObject = new JSONObject(flagsJson);
				
					if (jsonObject.has(flagsJson)) {
						boolean flagValue = jsonObject.getBoolean(flagsJson);
						BeanUtils.setProperty(userBean, flagsJson, flagValue);
					}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			}
		}
	}
	
	private static void initSortableColumns() {
		columns =  new ConcurrentSkipListSet<String>();
		Field[] fields = InvoiceDetails.class.getDeclaredFields();
		Field[] superFields = InvoiceDetails.class.getFields();
		
		if (fields != null && fields.length > 0) {
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				columns.add(field.getName());
			} 
		}
		if (superFields != null && superFields.length > 0) {
		for (int i = 0; i < superFields.length; i++)
			columns.add( superFields[i].getName());
		}
	}

}
