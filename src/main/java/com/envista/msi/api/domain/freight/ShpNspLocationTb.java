package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_LOCATION_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_LOCATION_TB")
@NamedQuery(name="ShpNspLocationTb.findAll", query="SELECT s FROM ShpNspLocationTb s")
public class ShpNspLocationTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_LOCATION_ID", unique=true, precision=22)
	private long nspLocationId;

	@Column(name="CARRIER_ID", precision=22)
	private BigDecimal carrierId;

	@Column(name="CARRIER_VATIN", length=50)
	private String carrierVatin;

	@Column(length=100)
	private String city;

	@Column(length=500)
	private String comments;

	@Column(length=30)
	private String country;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=50)
	private String createUser;

	@Column(name="CUSTOMER_ID", precision=22)
	private BigDecimal customerId;

	@Column(name="IS_ACTIVE", precision=22)
	private BigDecimal isActive;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=50)
	private String lastUpdateUser;

	@Column(name="LOCATION_ADDRESS", length=500)
	private String locationAddress;

	@Column(name="LOCATION_ADDRESS2", length=500)
	private String locationAddress2;

	@Column(name="LOCATION_NAME", length=500)
	private String locationName;

	@Column(name="LOCATION_NUMBER", length=100)
	private String locationNumber;

	@Column(length=20)
	private String phone;

	@Column(name="\"PRIMARY\"", precision=22)
	private BigDecimal primary;

	@Column(name="\"STATE\"", length=100)
	private String state;

	@Column(length=50)
	private String zip;

	//bi-directional many-to-one association to ShpNspCodeValuesTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LOCATION_TYPE")
	private ShpNspCodeValuesTb shpNspCodeValuesTb;

	public ShpNspLocationTb() {
	}

	public long getNspLocationId() {
		return this.nspLocationId;
	}

	public void setNspLocationId(long nspLocationId) {
		this.nspLocationId = nspLocationId;
	}

	public BigDecimal getCarrierId() {
		return this.carrierId;
	}

	public void setCarrierId(BigDecimal carrierId) {
		this.carrierId = carrierId;
	}

	public String getCarrierVatin() {
		return this.carrierVatin;
	}

	public void setCarrierVatin(String carrierVatin) {
		this.carrierVatin = carrierVatin;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public BigDecimal getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getIsActive() {
		return this.isActive;
	}

	public void setIsActive(BigDecimal isActive) {
		this.isActive = isActive;
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

	public String getLocationAddress() {
		return this.locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getLocationAddress2() {
		return this.locationAddress2;
	}

	public void setLocationAddress2(String locationAddress2) {
		this.locationAddress2 = locationAddress2;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationNumber() {
		return this.locationNumber;
	}

	public void setLocationNumber(String locationNumber) {
		this.locationNumber = locationNumber;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getPrimary() {
		return this.primary;
	}

	public void setPrimary(BigDecimal primary) {
		this.primary = primary;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public ShpNspCodeValuesTb getShpNspCodeValuesTb() {
		return this.shpNspCodeValuesTb;
	}

	public void setShpNspCodeValuesTb(ShpNspCodeValuesTb shpNspCodeValuesTb) {
		this.shpNspCodeValuesTb = shpNspCodeValuesTb;
	}

}