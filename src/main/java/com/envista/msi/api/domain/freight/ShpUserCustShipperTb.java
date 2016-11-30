package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SHP_USER_CUST_SHIPPER_TB database table.
 * 
 */
@Entity
@Table(name="SHP_USER_CUST_SHIPPER_TB")
@NamedQuery(name="ShpUserCustShipperTb.findAll", query="SELECT s FROM ShpUserCustShipperTb s")
public class ShpUserCustShipperTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_CUST_SHIPPER_ID", unique=true, precision=22)
	private long userCustShipperId;

	//bi-directional many-to-one association to ShpCustomerProfileTb
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CUSTOMER_ID")
	private ShpCustomerProfileTb shpCustomerProfileTb;

	//bi-directional many-to-one association to ShpShipperTb
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SHIPPER_ID")
	private ShpShipperTb shpShipperTb;

	//bi-directional many-to-one association to ShpUserProfileTb
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID")
	private ShpUserProfileTb shpUserProfileTb;

	public ShpUserCustShipperTb() {
	}

	public long getUserCustShipperId() {
		return this.userCustShipperId;
	}

	public void setUserCustShipperId(long userCustShipperId) {
		this.userCustShipperId = userCustShipperId;
	}

	public ShpCustomerProfileTb getShpCustomerProfileTb() {
		return this.shpCustomerProfileTb;
	}

	public void setShpCustomerProfileTb(ShpCustomerProfileTb shpCustomerProfileTb) {
		this.shpCustomerProfileTb = shpCustomerProfileTb;
	}

	public ShpShipperTb getShpShipperTb() {
		return this.shpShipperTb;
	}

	public void setShpShipperTb(ShpShipperTb shpShipperTb) {
		this.shpShipperTb = shpShipperTb;
	}

	public ShpUserProfileTb getShpUserProfileTb() {
		return this.shpUserProfileTb;
	}

	public void setShpUserProfileTb(ShpUserProfileTb shpUserProfileTb) {
		this.shpUserProfileTb = shpUserProfileTb;
	}

}