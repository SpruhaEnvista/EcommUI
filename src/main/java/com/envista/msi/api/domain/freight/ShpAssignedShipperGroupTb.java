package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SHP_ASSIGNED_SHIPPER_GROUP_TB database table.
 * 
 */
@Entity
@Table(name="SHP_ASSIGNED_SHIPPER_GROUP_TB")
@NamedQuery(name="ShpAssignedShipperGroupTb.findAll", query="SELECT s FROM ShpAssignedShipperGroupTb s")
public class ShpAssignedShipperGroupTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ASSIGNED_SHIPPER_GROUP_ID", unique=true, precision=22)
	private long assignedShipperGroupId;

	//bi-directional many-to-one association to ShpShipperGroupTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SHIPPER_GROUP_ID")
	private ShpShipperGroupTb shpShipperGroupTb;

	//bi-directional many-to-one association to ShpShipperTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SHIPPER_ID")
	private ShpShipperTb shpShipperTb;

	public ShpAssignedShipperGroupTb() {
	}

	public long getAssignedShipperGroupId() {
		return this.assignedShipperGroupId;
	}

	public void setAssignedShipperGroupId(long assignedShipperGroupId) {
		this.assignedShipperGroupId = assignedShipperGroupId;
	}

	public ShpShipperGroupTb getShpShipperGroupTb() {
		return this.shpShipperGroupTb;
	}

	public void setShpShipperGroupTb(ShpShipperGroupTb shpShipperGroupTb) {
		this.shpShipperGroupTb = shpShipperGroupTb;
	}

	public ShpShipperTb getShpShipperTb() {
		return this.shpShipperTb;
	}

	public void setShpShipperTb(ShpShipperTb shpShipperTb) {
		this.shpShipperTb = shpShipperTb;
	}

}