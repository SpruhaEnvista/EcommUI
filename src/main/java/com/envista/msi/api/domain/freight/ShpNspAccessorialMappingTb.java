package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_ACCESSORIAL_MAPPING_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_ACCESSORIAL_MAPPING_TB")
@NamedQuery(name="ShpNspAccessorialMappingTb.findAll", query="SELECT s FROM ShpNspAccessorialMappingTb s")
public class ShpNspAccessorialMappingTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_ACCESSORIAL_MAPPING_ID", unique=true, precision=22)
	private long nspAccessorialMappingId;

	@Column(precision=22)
	private BigDecimal active;

	@Column(name="CARRIER_ACCESSORIAL", length=100)
	private String carrierAccessorial;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=100)
	private String createUser;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=100)
	private String lastUpdateUser;

	//bi-directional many-to-one association to ShpCarrierTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CARRIER_ID")
	private ShpCarrierTb shpCarrierTb;

	//bi-directional many-to-one association to ShpNspCodeValuesTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MSI_STANDARD_ID")
	private ShpNspCodeValuesTb shpNspCodeValuesTb;

	public ShpNspAccessorialMappingTb() {
	}

	public long getNspAccessorialMappingId() {
		return this.nspAccessorialMappingId;
	}

	public void setNspAccessorialMappingId(long nspAccessorialMappingId) {
		this.nspAccessorialMappingId = nspAccessorialMappingId;
	}

	public BigDecimal getActive() {
		return this.active;
	}

	public void setActive(BigDecimal active) {
		this.active = active;
	}

	public String getCarrierAccessorial() {
		return this.carrierAccessorial;
	}

	public void setCarrierAccessorial(String carrierAccessorial) {
		this.carrierAccessorial = carrierAccessorial;
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

	public ShpCarrierTb getShpCarrierTb() {
		return this.shpCarrierTb;
	}

	public void setShpCarrierTb(ShpCarrierTb shpCarrierTb) {
		this.shpCarrierTb = shpCarrierTb;
	}

	public ShpNspCodeValuesTb getShpNspCodeValuesTb() {
		return this.shpNspCodeValuesTb;
	}

	public void setShpNspCodeValuesTb(ShpNspCodeValuesTb shpNspCodeValuesTb) {
		this.shpNspCodeValuesTb = shpNspCodeValuesTb;
	}

}