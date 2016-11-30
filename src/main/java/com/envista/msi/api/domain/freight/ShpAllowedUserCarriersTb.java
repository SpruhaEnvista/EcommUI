package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SHP_ALLOWED_USER_CARRIERS_TB database table.
 * 
 */
@Entity
@Table(name="SHP_ALLOWED_USER_CARRIERS_TB")
@NamedQuery(name="ShpAllowedUserCarriersTb.findAll", query="SELECT s FROM ShpAllowedUserCarriersTb s")
public class ShpAllowedUserCarriersTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_CARRIER_ID", unique=true, precision=22)
	private long userCarrierId;

	//bi-directional many-to-one association to ShpCarrierTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CARRIER_ID")
	private ShpCarrierTb shpCarrierTb;

	//bi-directional many-to-one association to ShpUserProfileTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private ShpUserProfileTb shpUserProfileTb;

	public ShpAllowedUserCarriersTb() {
	}

	public long getUserCarrierId() {
		return this.userCarrierId;
	}

	public void setUserCarrierId(long userCarrierId) {
		this.userCarrierId = userCarrierId;
	}

	public ShpCarrierTb getShpCarrierTb() {
		return this.shpCarrierTb;
	}

	public void setShpCarrierTb(ShpCarrierTb shpCarrierTb) {
		this.shpCarrierTb = shpCarrierTb;
	}

	public ShpUserProfileTb getShpUserProfileTb() {
		return this.shpUserProfileTb;
	}

	public void setShpUserProfileTb(ShpUserProfileTb shpUserProfileTb) {
		this.shpUserProfileTb = shpUserProfileTb;
	}

}