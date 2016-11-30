package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;

import com.envista.msi.api.domain.util.BasicNameValuePair;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SHP_CARRIER_TB database table.
 * 
 */
@Entity
@Table(name="SHP_CARRIER_TB")
@SqlResultSetMapping(
	    name="carrierIdNameMapping",
	    classes={
	        @ConstructorResult(
	            targetClass=BasicNameValuePair.class,
	            columns={
	                @ColumnResult(name="CARRIER_ID"),
	                @ColumnResult(name="CARRIER_NAME")
	            }
	        )
	    }
	)



@NamedQuery(name="ShpCarrierTb.findAll", query="SELECT s FROM ShpCarrierTb s")
@NamedNativeQuery(name="carrierIdName",query="SELECT CRR.carrier_id CARRIER_ID,CRR.carrier_name CARRIER_NAME FROM SHP_NSP_CUST_CARRIER_RELN_TB RELNTB,SHP_CARRIER_TB CRR WHERE RELNTB.CUSTOMER_ID = ?1 and CRR.CARRIER_ID = RELNTB.CARRIER_ID", resultSetMapping="carrierIdNameMapping")
public class ShpCarrierTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CARRIER_ID", unique=true, precision=22)
	private long carrierId;

	@Column(name="AIR_EDI_METHOD", precision=22)
	private BigDecimal airEdiMethod;

	@Column(name="ALWAYS_USE_THIS_ADDRESS", precision=22)
	private BigDecimal alwaysUseThisAddress;

	@Column(name="CARRIER_DESCRIPTION", length=100)
	private String carrierDescription;

	@Column(name="CARRIER_MODES", length=100)
	private String carrierModes;

	@Column(name="CARRIER_NAME", length=100)
	private String carrierName;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=100)
	private String createUser;

	@Column(name="EDI_ADDR", length=500)
	private String ediAddr;

	@Column(name="EDI_METHODS", length=500)
	private String ediMethods;

	@Column(name="EDI_PASSWORD", length=100)
	private String ediPassword;

	@Column(name="EDI_USER_ID", length=100)
	private String ediUserId;

	@Column(name="INTERMODAL_EDI_METHOD", precision=22)
	private BigDecimal intermodalEdiMethod;

	@Column(name="INVOICE_FORMAT", length=500)
	private String invoiceFormat;

	@Column(name="IS_ACTIVE", precision=22)
	private BigDecimal isActive;

	@Column(name="IS_AIR", precision=1)
	private BigDecimal isAir;

	@Column(name="IS_AIR_PRIMARY", precision=22)
	private BigDecimal isAirPrimary;

	@Column(name="IS_FTL", precision=1)
	private BigDecimal isFtl;

	@Column(name="IS_INTERMODAL", precision=22)
	private BigDecimal isIntermodal;

	@Column(name="IS_INTERMODAL_PRIMARY", precision=22)
	private BigDecimal isIntermodalPrimary;

	@Column(name="IS_LTL", precision=1)
	private BigDecimal isLtl;

	@Column(name="IS_LTL_PRIMARY", precision=22)
	private BigDecimal isLtlPrimary;

	@Column(name="IS_OCEAN", precision=1)
	private BigDecimal isOcean;

	@Column(name="IS_OCEAN_PRIMARY", precision=22)
	private BigDecimal isOceanPrimary;

	@Column(name="IS_TL", precision=22)
	private BigDecimal isTl;

	@Column(name="IS_TL_PRIMARY", precision=22)
	private BigDecimal isTlPrimary;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=100)
	private String lastUpdateUser;

	@Column(name="LTL_EDI_METHOD", precision=22)
	private BigDecimal ltlEdiMethod;

	@Column(name="NSP_CARRIER_GROUP_ID", precision=22)
	private BigDecimal nspCarrierGroupId;

	@Column(name="NSP_MANDATE_CAR_GRP_ID", precision=22)
	private BigDecimal nspMandateCarGrpId;

	@Column(name="NSP_ODE_CAR_GRP_ID", precision=22)
	private BigDecimal nspOdeCarGrpId;

	@Column(name="OCEAN_EDI_METHOD", precision=22)
	private BigDecimal oceanEdiMethod;

	@Column(name="PRIMARY_MODE", length=50)
	private String primaryMode;

	@Column(name="REMIT_TO_ADDRESS_1", length=100)
	private String remitToAddress1;

	@Column(name="REMIT_TO_ADDRESS_2", length=100)
	private String remitToAddress2;

	@Column(name="REMIT_TO_CITY", length=40)
	private String remitToCity;

	@Column(name="REMIT_TO_CONTACT", length=50)
	private String remitToContact;

	@Column(name="REMIT_TO_COUNTRY", length=30)
	private String remitToCountry;

	@Column(name="REMIT_TO_EMAIL", length=150)
	private String remitToEmail;

	@Column(name="REMIT_TO_FAX", length=20)
	private String remitToFax;

	@Column(name="REMIT_TO_PHONE", length=20)
	private String remitToPhone;

	@Column(name="REMIT_TO_STATE", length=20)
	private String remitToState;

	@Column(name="REMIT_TO_ZIPCODE", length=10)
	private String remitToZipcode;

	@Column(name="SCAC_CODE", length=8)
	private String scacCode;

	@Column(precision=22)
	private BigDecimal send997;

	@Column(name="TL_EDI_METHOD", precision=22)
	private BigDecimal tlEdiMethod;

	@Column(name="ZOOM_URL", length=200)
	private String zoomUrl;

	//bi-directional many-to-one association to ShpAllowedUserCarriersTb
	@OneToMany(mappedBy="shpCarrierTb")
	private List<ShpAllowedUserCarriersTb> shpAllowedUserCarriersTbs;

	//bi-directional many-to-one association to ShpCarrierUserShipperTb
	@OneToMany(mappedBy="shpCarrierTb")
	private List<ShpCarrierUserShipperTb> shpCarrierUserShipperTbs;

	//bi-directional many-to-one association to ShpNspAccessorialMappingTb
	@OneToMany(mappedBy="shpCarrierTb")
	private List<ShpNspAccessorialMappingTb> shpNspAccessorialMappingTbs;

	//bi-directional many-to-one association to ShpNspCustCarrierRelnTb
	@OneToMany(mappedBy="shpCarrierTb")
	private List<ShpNspCustCarrierRelnTb> shpNspCustCarrierRelnTbs;

	//bi-directional many-to-one association to ShpNspInvoiceDetailsTb
	@OneToMany(mappedBy="shpCarrierTb")
	private List<ShpNspInvoiceDetailsTb> shpNspInvoiceDetailsTbs;

	//bi-directional many-to-one association to ShpNspTaskTb
	@OneToMany(mappedBy="shpCarrierTb")
	private List<ShpNspTaskTb> shpNspTaskTbs;

	//bi-directional many-to-one association to ShpUserProfileTb
	@OneToMany(mappedBy="shpCarrierTb")
	private List<ShpUserProfileTb> shpUserProfileTbs;

	public ShpCarrierTb() {
	}

	public long getCarrierId() {
		return this.carrierId;
	}

	public void setCarrierId(long carrierId) {
		this.carrierId = carrierId;
	}

	public BigDecimal getAirEdiMethod() {
		return this.airEdiMethod;
	}

	public void setAirEdiMethod(BigDecimal airEdiMethod) {
		this.airEdiMethod = airEdiMethod;
	}

	public BigDecimal getAlwaysUseThisAddress() {
		return this.alwaysUseThisAddress;
	}

	public void setAlwaysUseThisAddress(BigDecimal alwaysUseThisAddress) {
		this.alwaysUseThisAddress = alwaysUseThisAddress;
	}

	public String getCarrierDescription() {
		return this.carrierDescription;
	}

	public void setCarrierDescription(String carrierDescription) {
		this.carrierDescription = carrierDescription;
	}

	public String getCarrierModes() {
		return this.carrierModes;
	}

	public void setCarrierModes(String carrierModes) {
		this.carrierModes = carrierModes;
	}

	public String getCarrierName() {
		return this.carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getEdiAddr() {
		return this.ediAddr;
	}

	public void setEdiAddr(String ediAddr) {
		this.ediAddr = ediAddr;
	}

	public String getEdiMethods() {
		return this.ediMethods;
	}

	public void setEdiMethods(String ediMethods) {
		this.ediMethods = ediMethods;
	}

	public String getEdiPassword() {
		return this.ediPassword;
	}

	public void setEdiPassword(String ediPassword) {
		this.ediPassword = ediPassword;
	}

	public String getEdiUserId() {
		return this.ediUserId;
	}

	public void setEdiUserId(String ediUserId) {
		this.ediUserId = ediUserId;
	}

	public BigDecimal getIntermodalEdiMethod() {
		return this.intermodalEdiMethod;
	}

	public void setIntermodalEdiMethod(BigDecimal intermodalEdiMethod) {
		this.intermodalEdiMethod = intermodalEdiMethod;
	}

	public String getInvoiceFormat() {
		return this.invoiceFormat;
	}

	public void setInvoiceFormat(String invoiceFormat) {
		this.invoiceFormat = invoiceFormat;
	}

	public BigDecimal getIsActive() {
		return this.isActive;
	}

	public void setIsActive(BigDecimal isActive) {
		this.isActive = isActive;
	}

	public BigDecimal getIsAir() {
		return this.isAir;
	}

	public void setIsAir(BigDecimal isAir) {
		this.isAir = isAir;
	}

	public BigDecimal getIsAirPrimary() {
		return this.isAirPrimary;
	}

	public void setIsAirPrimary(BigDecimal isAirPrimary) {
		this.isAirPrimary = isAirPrimary;
	}

	public BigDecimal getIsFtl() {
		return this.isFtl;
	}

	public void setIsFtl(BigDecimal isFtl) {
		this.isFtl = isFtl;
	}

	public BigDecimal getIsIntermodal() {
		return this.isIntermodal;
	}

	public void setIsIntermodal(BigDecimal isIntermodal) {
		this.isIntermodal = isIntermodal;
	}

	public BigDecimal getIsIntermodalPrimary() {
		return this.isIntermodalPrimary;
	}

	public void setIsIntermodalPrimary(BigDecimal isIntermodalPrimary) {
		this.isIntermodalPrimary = isIntermodalPrimary;
	}

	public BigDecimal getIsLtl() {
		return this.isLtl;
	}

	public void setIsLtl(BigDecimal isLtl) {
		this.isLtl = isLtl;
	}

	public BigDecimal getIsLtlPrimary() {
		return this.isLtlPrimary;
	}

	public void setIsLtlPrimary(BigDecimal isLtlPrimary) {
		this.isLtlPrimary = isLtlPrimary;
	}

	public BigDecimal getIsOcean() {
		return this.isOcean;
	}

	public void setIsOcean(BigDecimal isOcean) {
		this.isOcean = isOcean;
	}

	public BigDecimal getIsOceanPrimary() {
		return this.isOceanPrimary;
	}

	public void setIsOceanPrimary(BigDecimal isOceanPrimary) {
		this.isOceanPrimary = isOceanPrimary;
	}

	public BigDecimal getIsTl() {
		return this.isTl;
	}

	public void setIsTl(BigDecimal isTl) {
		this.isTl = isTl;
	}

	public BigDecimal getIsTlPrimary() {
		return this.isTlPrimary;
	}

	public void setIsTlPrimary(BigDecimal isTlPrimary) {
		this.isTlPrimary = isTlPrimary;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public BigDecimal getLtlEdiMethod() {
		return this.ltlEdiMethod;
	}

	public void setLtlEdiMethod(BigDecimal ltlEdiMethod) {
		this.ltlEdiMethod = ltlEdiMethod;
	}

	public BigDecimal getNspCarrierGroupId() {
		return this.nspCarrierGroupId;
	}

	public void setNspCarrierGroupId(BigDecimal nspCarrierGroupId) {
		this.nspCarrierGroupId = nspCarrierGroupId;
	}

	public BigDecimal getNspMandateCarGrpId() {
		return this.nspMandateCarGrpId;
	}

	public void setNspMandateCarGrpId(BigDecimal nspMandateCarGrpId) {
		this.nspMandateCarGrpId = nspMandateCarGrpId;
	}

	public BigDecimal getNspOdeCarGrpId() {
		return this.nspOdeCarGrpId;
	}

	public void setNspOdeCarGrpId(BigDecimal nspOdeCarGrpId) {
		this.nspOdeCarGrpId = nspOdeCarGrpId;
	}

	public BigDecimal getOceanEdiMethod() {
		return this.oceanEdiMethod;
	}

	public void setOceanEdiMethod(BigDecimal oceanEdiMethod) {
		this.oceanEdiMethod = oceanEdiMethod;
	}

	public String getPrimaryMode() {
		return this.primaryMode;
	}

	public void setPrimaryMode(String primaryMode) {
		this.primaryMode = primaryMode;
	}

	public String getRemitToAddress1() {
		return this.remitToAddress1;
	}

	public void setRemitToAddress1(String remitToAddress1) {
		this.remitToAddress1 = remitToAddress1;
	}

	public String getRemitToAddress2() {
		return this.remitToAddress2;
	}

	public void setRemitToAddress2(String remitToAddress2) {
		this.remitToAddress2 = remitToAddress2;
	}

	public String getRemitToCity() {
		return this.remitToCity;
	}

	public void setRemitToCity(String remitToCity) {
		this.remitToCity = remitToCity;
	}

	public String getRemitToContact() {
		return this.remitToContact;
	}

	public void setRemitToContact(String remitToContact) {
		this.remitToContact = remitToContact;
	}

	public String getRemitToCountry() {
		return this.remitToCountry;
	}

	public void setRemitToCountry(String remitToCountry) {
		this.remitToCountry = remitToCountry;
	}

	public String getRemitToEmail() {
		return this.remitToEmail;
	}

	public void setRemitToEmail(String remitToEmail) {
		this.remitToEmail = remitToEmail;
	}

	public String getRemitToFax() {
		return this.remitToFax;
	}

	public void setRemitToFax(String remitToFax) {
		this.remitToFax = remitToFax;
	}

	public String getRemitToPhone() {
		return this.remitToPhone;
	}

	public void setRemitToPhone(String remitToPhone) {
		this.remitToPhone = remitToPhone;
	}

	public String getRemitToState() {
		return this.remitToState;
	}

	public void setRemitToState(String remitToState) {
		this.remitToState = remitToState;
	}

	public String getRemitToZipcode() {
		return this.remitToZipcode;
	}

	public void setRemitToZipcode(String remitToZipcode) {
		this.remitToZipcode = remitToZipcode;
	}

	public String getScacCode() {
		return this.scacCode;
	}

	public void setScacCode(String scacCode) {
		this.scacCode = scacCode;
	}

	public BigDecimal getSend997() {
		return this.send997;
	}

	public void setSend997(BigDecimal send997) {
		this.send997 = send997;
	}

	public BigDecimal getTlEdiMethod() {
		return this.tlEdiMethod;
	}

	public void setTlEdiMethod(BigDecimal tlEdiMethod) {
		this.tlEdiMethod = tlEdiMethod;
	}

	public String getZoomUrl() {
		return this.zoomUrl;
	}

	public void setZoomUrl(String zoomUrl) {
		this.zoomUrl = zoomUrl;
	}

	public List<ShpAllowedUserCarriersTb> getShpAllowedUserCarriersTbs() {
		return this.shpAllowedUserCarriersTbs;
	}

	public void setShpAllowedUserCarriersTbs(List<ShpAllowedUserCarriersTb> shpAllowedUserCarriersTbs) {
		this.shpAllowedUserCarriersTbs = shpAllowedUserCarriersTbs;
	}

	public ShpAllowedUserCarriersTb addShpAllowedUserCarriersTb(ShpAllowedUserCarriersTb shpAllowedUserCarriersTb) {
		getShpAllowedUserCarriersTbs().add(shpAllowedUserCarriersTb);
		shpAllowedUserCarriersTb.setShpCarrierTb(this);

		return shpAllowedUserCarriersTb;
	}

	public ShpAllowedUserCarriersTb removeShpAllowedUserCarriersTb(ShpAllowedUserCarriersTb shpAllowedUserCarriersTb) {
		getShpAllowedUserCarriersTbs().remove(shpAllowedUserCarriersTb);
		shpAllowedUserCarriersTb.setShpCarrierTb(null);

		return shpAllowedUserCarriersTb;
	}

	public List<ShpCarrierUserShipperTb> getShpCarrierUserShipperTbs() {
		return this.shpCarrierUserShipperTbs;
	}

	public void setShpCarrierUserShipperTbs(List<ShpCarrierUserShipperTb> shpCarrierUserShipperTbs) {
		this.shpCarrierUserShipperTbs = shpCarrierUserShipperTbs;
	}

	public ShpCarrierUserShipperTb addShpCarrierUserShipperTb(ShpCarrierUserShipperTb shpCarrierUserShipperTb) {
		getShpCarrierUserShipperTbs().add(shpCarrierUserShipperTb);
		shpCarrierUserShipperTb.setShpCarrierTb(this);

		return shpCarrierUserShipperTb;
	}

	public ShpCarrierUserShipperTb removeShpCarrierUserShipperTb(ShpCarrierUserShipperTb shpCarrierUserShipperTb) {
		getShpCarrierUserShipperTbs().remove(shpCarrierUserShipperTb);
		shpCarrierUserShipperTb.setShpCarrierTb(null);

		return shpCarrierUserShipperTb;
	}

	public List<ShpNspAccessorialMappingTb> getShpNspAccessorialMappingTbs() {
		return this.shpNspAccessorialMappingTbs;
	}

	public void setShpNspAccessorialMappingTbs(List<ShpNspAccessorialMappingTb> shpNspAccessorialMappingTbs) {
		this.shpNspAccessorialMappingTbs = shpNspAccessorialMappingTbs;
	}

	public ShpNspAccessorialMappingTb addShpNspAccessorialMappingTb(ShpNspAccessorialMappingTb shpNspAccessorialMappingTb) {
		getShpNspAccessorialMappingTbs().add(shpNspAccessorialMappingTb);
		shpNspAccessorialMappingTb.setShpCarrierTb(this);

		return shpNspAccessorialMappingTb;
	}

	public ShpNspAccessorialMappingTb removeShpNspAccessorialMappingTb(ShpNspAccessorialMappingTb shpNspAccessorialMappingTb) {
		getShpNspAccessorialMappingTbs().remove(shpNspAccessorialMappingTb);
		shpNspAccessorialMappingTb.setShpCarrierTb(null);

		return shpNspAccessorialMappingTb;
	}

	public List<ShpNspCustCarrierRelnTb> getShpNspCustCarrierRelnTbs() {
		return this.shpNspCustCarrierRelnTbs;
	}

	public void setShpNspCustCarrierRelnTbs(List<ShpNspCustCarrierRelnTb> shpNspCustCarrierRelnTbs) {
		this.shpNspCustCarrierRelnTbs = shpNspCustCarrierRelnTbs;
	}

	public ShpNspCustCarrierRelnTb addShpNspCustCarrierRelnTb(ShpNspCustCarrierRelnTb shpNspCustCarrierRelnTb) {
		getShpNspCustCarrierRelnTbs().add(shpNspCustCarrierRelnTb);
		shpNspCustCarrierRelnTb.setShpCarrierTb(this);

		return shpNspCustCarrierRelnTb;
	}

	public ShpNspCustCarrierRelnTb removeShpNspCustCarrierRelnTb(ShpNspCustCarrierRelnTb shpNspCustCarrierRelnTb) {
		getShpNspCustCarrierRelnTbs().remove(shpNspCustCarrierRelnTb);
		shpNspCustCarrierRelnTb.setShpCarrierTb(null);

		return shpNspCustCarrierRelnTb;
	}

	public List<ShpNspInvoiceDetailsTb> getShpNspInvoiceDetailsTbs() {
		return this.shpNspInvoiceDetailsTbs;
	}

	public void setShpNspInvoiceDetailsTbs(List<ShpNspInvoiceDetailsTb> shpNspInvoiceDetailsTbs) {
		this.shpNspInvoiceDetailsTbs = shpNspInvoiceDetailsTbs;
	}

	public ShpNspInvoiceDetailsTb addShpNspInvoiceDetailsTb(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTb) {
		getShpNspInvoiceDetailsTbs().add(shpNspInvoiceDetailsTb);
		shpNspInvoiceDetailsTb.setShpCarrierTb(this);

		return shpNspInvoiceDetailsTb;
	}

	public ShpNspInvoiceDetailsTb removeShpNspInvoiceDetailsTb(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTb) {
		getShpNspInvoiceDetailsTbs().remove(shpNspInvoiceDetailsTb);
		shpNspInvoiceDetailsTb.setShpCarrierTb(null);

		return shpNspInvoiceDetailsTb;
	}

	public List<ShpNspTaskTb> getShpNspTaskTbs() {
		return this.shpNspTaskTbs;
	}

	public void setShpNspTaskTbs(List<ShpNspTaskTb> shpNspTaskTbs) {
		this.shpNspTaskTbs = shpNspTaskTbs;
	}

	public ShpNspTaskTb addShpNspTaskTb(ShpNspTaskTb shpNspTaskTb) {
		getShpNspTaskTbs().add(shpNspTaskTb);
		shpNspTaskTb.setShpCarrierTb(this);

		return shpNspTaskTb;
	}

	public ShpNspTaskTb removeShpNspTaskTb(ShpNspTaskTb shpNspTaskTb) {
		getShpNspTaskTbs().remove(shpNspTaskTb);
		shpNspTaskTb.setShpCarrierTb(null);

		return shpNspTaskTb;
	}

	public List<ShpUserProfileTb> getShpUserProfileTbs() {
		return this.shpUserProfileTbs;
	}

	public void setShpUserProfileTbs(List<ShpUserProfileTb> shpUserProfileTbs) {
		this.shpUserProfileTbs = shpUserProfileTbs;
	}

	public ShpUserProfileTb addShpUserProfileTb(ShpUserProfileTb shpUserProfileTb) {
		getShpUserProfileTbs().add(shpUserProfileTb);
		shpUserProfileTb.setShpCarrierTb(this);

		return shpUserProfileTb;
	}

	public ShpUserProfileTb removeShpUserProfileTb(ShpUserProfileTb shpUserProfileTb) {
		getShpUserProfileTbs().remove(shpUserProfileTb);
		shpUserProfileTb.setShpCarrierTb(null);

		return shpUserProfileTb;
	}

}