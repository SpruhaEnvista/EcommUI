package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SHP_CARRIER_USER_SHIPPER_TB database table.
 * 
 */
@Entity
@Table(name="SHP_CARRIER_USER_SHIPPER_TB")
@NamedQuery(name="ShpCarrierUserShipperTb.findAll", query="SELECT s FROM ShpCarrierUserShipperTb s")
public class ShpCarrierUserShipperTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CARRIER_USER_SHIPPER_ID", unique=true, precision=22)
	private long carrierUserShipperId;

	@Column(name="CARRIER_USER_ID", precision=22)
	private java.math.BigDecimal carrierUserId;

	//bi-directional many-to-one association to ShpCarrierTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CARRIER_ID")
	private ShpCarrierTb shpCarrierTb;

	//bi-directional many-to-one association to ShpShipperTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SHIPPER_ID")
	private ShpShipperTb shpShipperTb;

	public ShpCarrierUserShipperTb() {
	}

	public long getCarrierUserShipperId() {
		return this.carrierUserShipperId;
	}

	public void setCarrierUserShipperId(long carrierUserShipperId) {
		this.carrierUserShipperId = carrierUserShipperId;
	}

	public java.math.BigDecimal getCarrierUserId() {
		return this.carrierUserId;
	}

	public void setCarrierUserId(java.math.BigDecimal carrierUserId) {
		this.carrierUserId = carrierUserId;
	}

	public ShpCarrierTb getShpCarrierTb() {
		return this.shpCarrierTb;
	}

	public void setShpCarrierTb(ShpCarrierTb shpCarrierTb) {
		this.shpCarrierTb = shpCarrierTb;
	}

	public ShpShipperTb getShpShipperTb() {
		return this.shpShipperTb;
	}

	public void setShpShipperTb(ShpShipperTb shpShipperTb) {
		this.shpShipperTb = shpShipperTb;
	}

}