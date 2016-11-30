package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SHP_SHIPPER_TB database table.
 * 
 */
@Entity
@Table(name="SHP_SHIPPER_TB")
@NamedQuery(name="ShpShipperTb.findAll", query="SELECT s FROM ShpShipperTb s")
public class ShpShipperTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SHIPPER_ID", unique=true, precision=22)
	private long shipperId;

	@Column(name="ACTIVATE_SIGNATURE", precision=1)
	private BigDecimal activateSignature;

	@Column(name="ADD_TO_DB", precision=1)
	private BigDecimal addToDb;

	@Column(length=100)
	private String address1;

	@Column(length=100)
	private String address2;

	@Temporal(TemporalType.DATE)
	@Column(name="AIR_SERVICE")
	private Date airService;

	@Column(name="AUDIT_SHIPPER", precision=22)
	private BigDecimal auditShipper;

	@Column(length=40)
	private String city;

	@Column(length=30)
	private String country;

	@Column(name="CREATED_BY", length=15)
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="DHL_AT_HOME", precision=1)
	private BigDecimal dhlAtHome;

	@Column(name="DHL_SHIPPING_KEY", length=100)
	private String dhlShippingKey;

	@Column(name="DISCOUNT_RATE", length=6)
	private String discountRate;

	@Column(length=150)
	private String email;

	@Column(length=20)
	private String fax;

	@Column(name="FEDEX_EXPRESS_GSR_ONLINE", precision=22)
	private BigDecimal fedexExpressGsrOnline;

	@Column(name="FEDEX_GROUND_GSR_ONLINE", precision=22)
	private BigDecimal fedexGroundGsrOnline;

	@Column(name="FEDEX_ONLINE_FILING", precision=22)
	private BigDecimal fedexOnlineFiling;

	@Column(name="GL_CODE", length=40)
	private String glCode;

	@Temporal(TemporalType.DATE)
	@Column(name="GROUND_SERVICE")
	private Date groundService;

	@Column(name="IS_SHIPPER_CLOSE", precision=22)
	private BigDecimal isShipperClose;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_ADDRESS_CORRECT_CHECK_ON")
	private Date lastAddressCorrectCheckOn;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_SCANNED_AIR")
	private Date lastScannedAir;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_SCANNED_GROUND")
	private Date lastScannedGround;

	@Column(name="LATE_PICKUP", precision=1)
	private BigDecimal latePickup;

	@Temporal(TemporalType.DATE)
	@Column(name="MANIFEST_NOT_SHIP_VER_AFTER")
	private Date manifestNotShipVerAfter;

	@Column(name="NOTIFY_CLIENT_SERVICES", precision=22)
	private BigDecimal notifyClientServices;

	@Column(length=20)
	private String phone;

	@Temporal(TemporalType.DATE)
	@Column(name="RATE_SHOPPER_EXPIRE")
	private Date rateShopperExpire;

	@Column(name="REJECT_INVOICES", precision=22)
	private BigDecimal rejectInvoices;

	@Column(name="SERVICE_CHARGE", precision=1)
	private BigDecimal serviceCharge;

	@Temporal(TemporalType.DATE)
	@Column(name="SHIPPER_CLOSE_DATE")
	private Date shipperCloseDate;

	@Column(name="SHIPPER_CODE", length=12)
	private String shipperCode;

	@Column(name="SHIPPER_NAME", length=100)
	private String shipperName;

	@Column(name="SHIPPER_ZIP_AS_ORIGIN_ZIP", precision=1)
	private BigDecimal shipperZipAsOriginZip;

	@Column(name="\"STATE\"", length=20)
	private String state;

	@Column(name="UPDATED_BY", length=15)
	private String updatedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_ON")
	private Date updatedOn;

	@Column(precision=22)
	private BigDecimal uplift;

	@Column(name="UPLIFT_CUSTOMER_ID", precision=22)
	private BigDecimal upliftCustomerId;

	@Column(name="UPS_GSR_FLAG", precision=22)
	private BigDecimal upsGsrFlag;

	@Column(name="VALIDATE_SCAN", precision=1)
	private BigDecimal validateScan;

	@Column(name="VOID_DAYS", precision=22)
	private BigDecimal voidDays;

	@Column(length=10)
	private String zipcode;

	//bi-directional many-to-one association to ShpAssignedShipperGroupTb
	@OneToMany(mappedBy="shpShipperTb")
	private List<ShpAssignedShipperGroupTb> shpAssignedShipperGroupTbs;

	//bi-directional many-to-one association to ShpCarrierUserShipperTb
	@OneToMany(mappedBy="shpShipperTb")
	private List<ShpCarrierUserShipperTb> shpCarrierUserShipperTbs;

	//bi-directional many-to-one association to ShpCustomerProfileTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_ID")
	private ShpCustomerProfileTb shpCustomerProfileTb;

	//bi-directional many-to-one association to ShpUserCustShipperTb
	@OneToMany(mappedBy="shpShipperTb")
	private List<ShpUserCustShipperTb> shpUserCustShipperTbs;

	public ShpShipperTb() {
	}

	public long getShipperId() {
		return this.shipperId;
	}

	public void setShipperId(long shipperId) {
		this.shipperId = shipperId;
	}

	public BigDecimal getActivateSignature() {
		return this.activateSignature;
	}

	public void setActivateSignature(BigDecimal activateSignature) {
		this.activateSignature = activateSignature;
	}

	public BigDecimal getAddToDb() {
		return this.addToDb;
	}

	public void setAddToDb(BigDecimal addToDb) {
		this.addToDb = addToDb;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Date getAirService() {
		return this.airService;
	}

	public void setAirService(Date airService) {
		this.airService = airService;
	}

	public BigDecimal getAuditShipper() {
		return this.auditShipper;
	}

	public void setAuditShipper(BigDecimal auditShipper) {
		this.auditShipper = auditShipper;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public BigDecimal getDhlAtHome() {
		return this.dhlAtHome;
	}

	public void setDhlAtHome(BigDecimal dhlAtHome) {
		this.dhlAtHome = dhlAtHome;
	}

	public String getDhlShippingKey() {
		return this.dhlShippingKey;
	}

	public void setDhlShippingKey(String dhlShippingKey) {
		this.dhlShippingKey = dhlShippingKey;
	}

	public String getDiscountRate() {
		return this.discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public BigDecimal getFedexExpressGsrOnline() {
		return this.fedexExpressGsrOnline;
	}

	public void setFedexExpressGsrOnline(BigDecimal fedexExpressGsrOnline) {
		this.fedexExpressGsrOnline = fedexExpressGsrOnline;
	}

	public BigDecimal getFedexGroundGsrOnline() {
		return this.fedexGroundGsrOnline;
	}

	public void setFedexGroundGsrOnline(BigDecimal fedexGroundGsrOnline) {
		this.fedexGroundGsrOnline = fedexGroundGsrOnline;
	}

	public BigDecimal getFedexOnlineFiling() {
		return this.fedexOnlineFiling;
	}

	public void setFedexOnlineFiling(BigDecimal fedexOnlineFiling) {
		this.fedexOnlineFiling = fedexOnlineFiling;
	}

	public String getGlCode() {
		return this.glCode;
	}

	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}

	public Date getGroundService() {
		return this.groundService;
	}

	public void setGroundService(Date groundService) {
		this.groundService = groundService;
	}

	public BigDecimal getIsShipperClose() {
		return this.isShipperClose;
	}

	public void setIsShipperClose(BigDecimal isShipperClose) {
		this.isShipperClose = isShipperClose;
	}

	public Date getLastAddressCorrectCheckOn() {
		return this.lastAddressCorrectCheckOn;
	}

	public void setLastAddressCorrectCheckOn(Date lastAddressCorrectCheckOn) {
		this.lastAddressCorrectCheckOn = lastAddressCorrectCheckOn;
	}

	public Date getLastScannedAir() {
		return this.lastScannedAir;
	}

	public void setLastScannedAir(Date lastScannedAir) {
		this.lastScannedAir = lastScannedAir;
	}

	public Date getLastScannedGround() {
		return this.lastScannedGround;
	}

	public void setLastScannedGround(Date lastScannedGround) {
		this.lastScannedGround = lastScannedGround;
	}

	public BigDecimal getLatePickup() {
		return this.latePickup;
	}

	public void setLatePickup(BigDecimal latePickup) {
		this.latePickup = latePickup;
	}

	public Date getManifestNotShipVerAfter() {
		return this.manifestNotShipVerAfter;
	}

	public void setManifestNotShipVerAfter(Date manifestNotShipVerAfter) {
		this.manifestNotShipVerAfter = manifestNotShipVerAfter;
	}

	public BigDecimal getNotifyClientServices() {
		return this.notifyClientServices;
	}

	public void setNotifyClientServices(BigDecimal notifyClientServices) {
		this.notifyClientServices = notifyClientServices;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRateShopperExpire() {
		return this.rateShopperExpire;
	}

	public void setRateShopperExpire(Date rateShopperExpire) {
		this.rateShopperExpire = rateShopperExpire;
	}

	public BigDecimal getRejectInvoices() {
		return this.rejectInvoices;
	}

	public void setRejectInvoices(BigDecimal rejectInvoices) {
		this.rejectInvoices = rejectInvoices;
	}

	public BigDecimal getServiceCharge() {
		return this.serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public Date getShipperCloseDate() {
		return this.shipperCloseDate;
	}

	public void setShipperCloseDate(Date shipperCloseDate) {
		this.shipperCloseDate = shipperCloseDate;
	}

	public String getShipperCode() {
		return this.shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public String getShipperName() {
		return this.shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public BigDecimal getShipperZipAsOriginZip() {
		return this.shipperZipAsOriginZip;
	}

	public void setShipperZipAsOriginZip(BigDecimal shipperZipAsOriginZip) {
		this.shipperZipAsOriginZip = shipperZipAsOriginZip;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public BigDecimal getUplift() {
		return this.uplift;
	}

	public void setUplift(BigDecimal uplift) {
		this.uplift = uplift;
	}

	public BigDecimal getUpliftCustomerId() {
		return this.upliftCustomerId;
	}

	public void setUpliftCustomerId(BigDecimal upliftCustomerId) {
		this.upliftCustomerId = upliftCustomerId;
	}

	public BigDecimal getUpsGsrFlag() {
		return this.upsGsrFlag;
	}

	public void setUpsGsrFlag(BigDecimal upsGsrFlag) {
		this.upsGsrFlag = upsGsrFlag;
	}

	public BigDecimal getValidateScan() {
		return this.validateScan;
	}

	public void setValidateScan(BigDecimal validateScan) {
		this.validateScan = validateScan;
	}

	public BigDecimal getVoidDays() {
		return this.voidDays;
	}

	public void setVoidDays(BigDecimal voidDays) {
		this.voidDays = voidDays;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public List<ShpAssignedShipperGroupTb> getShpAssignedShipperGroupTbs() {
		return this.shpAssignedShipperGroupTbs;
	}

	public void setShpAssignedShipperGroupTbs(List<ShpAssignedShipperGroupTb> shpAssignedShipperGroupTbs) {
		this.shpAssignedShipperGroupTbs = shpAssignedShipperGroupTbs;
	}

	public ShpAssignedShipperGroupTb addShpAssignedShipperGroupTb(ShpAssignedShipperGroupTb shpAssignedShipperGroupTb) {
		getShpAssignedShipperGroupTbs().add(shpAssignedShipperGroupTb);
		shpAssignedShipperGroupTb.setShpShipperTb(this);

		return shpAssignedShipperGroupTb;
	}

	public ShpAssignedShipperGroupTb removeShpAssignedShipperGroupTb(ShpAssignedShipperGroupTb shpAssignedShipperGroupTb) {
		getShpAssignedShipperGroupTbs().remove(shpAssignedShipperGroupTb);
		shpAssignedShipperGroupTb.setShpShipperTb(null);

		return shpAssignedShipperGroupTb;
	}

	public List<ShpCarrierUserShipperTb> getShpCarrierUserShipperTbs() {
		return this.shpCarrierUserShipperTbs;
	}

	public void setShpCarrierUserShipperTbs(List<ShpCarrierUserShipperTb> shpCarrierUserShipperTbs) {
		this.shpCarrierUserShipperTbs = shpCarrierUserShipperTbs;
	}

	public ShpCarrierUserShipperTb addShpCarrierUserShipperTb(ShpCarrierUserShipperTb shpCarrierUserShipperTb) {
		getShpCarrierUserShipperTbs().add(shpCarrierUserShipperTb);
		shpCarrierUserShipperTb.setShpShipperTb(this);

		return shpCarrierUserShipperTb;
	}

	public ShpCarrierUserShipperTb removeShpCarrierUserShipperTb(ShpCarrierUserShipperTb shpCarrierUserShipperTb) {
		getShpCarrierUserShipperTbs().remove(shpCarrierUserShipperTb);
		shpCarrierUserShipperTb.setShpShipperTb(null);

		return shpCarrierUserShipperTb;
	}

	public ShpCustomerProfileTb getShpCustomerProfileTb() {
		return this.shpCustomerProfileTb;
	}

	public void setShpCustomerProfileTb(ShpCustomerProfileTb shpCustomerProfileTb) {
		this.shpCustomerProfileTb = shpCustomerProfileTb;
	}

	public List<ShpUserCustShipperTb> getShpUserCustShipperTbs() {
		return this.shpUserCustShipperTbs;
	}

	public void setShpUserCustShipperTbs(List<ShpUserCustShipperTb> shpUserCustShipperTbs) {
		this.shpUserCustShipperTbs = shpUserCustShipperTbs;
	}

	public ShpUserCustShipperTb addShpUserCustShipperTb(ShpUserCustShipperTb shpUserCustShipperTb) {
		getShpUserCustShipperTbs().add(shpUserCustShipperTb);
		shpUserCustShipperTb.setShpShipperTb(this);

		return shpUserCustShipperTb;
	}

	public ShpUserCustShipperTb removeShpUserCustShipperTb(ShpUserCustShipperTb shpUserCustShipperTb) {
		getShpUserCustShipperTbs().remove(shpUserCustShipperTb);
		shpUserCustShipperTb.setShpShipperTb(null);

		return shpUserCustShipperTb;
	}

}