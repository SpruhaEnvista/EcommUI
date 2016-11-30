package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SHP_SHIPPER_GROUP_TB database table.
 * 
 */
@Entity
@Table(name="SHP_SHIPPER_GROUP_TB")
@NamedQuery(name="ShpShipperGroupTb.findAll", query="SELECT s FROM ShpShipperGroupTb s")
public class ShpShipperGroupTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SHIPPER_GROUP_ID", unique=true, precision=22)
	private long shipperGroupId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=15)
	private String createUser;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=15)
	private String lastUpdateUser;

	@Column(name="SHIPPER_GROUP_DESCRIPTION", length=500)
	private String shipperGroupDescription;

	@Column(name="SHIPPER_GROUP_NAME", length=50)
	private String shipperGroupName;

	//bi-directional many-to-one association to ShpAssignedShipperGroupTb
	@OneToMany(mappedBy="shpShipperGroupTb")
	private List<ShpAssignedShipperGroupTb> shpAssignedShipperGroupTbs;

	//bi-directional many-to-one association to ShpCustomerProfileTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_ID")
	private ShpCustomerProfileTb shpCustomerProfileTb;

	public ShpShipperGroupTb() {
	}

	public long getShipperGroupId() {
		return this.shipperGroupId;
	}

	public void setShipperGroupId(long shipperGroupId) {
		this.shipperGroupId = shipperGroupId;
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

	public String getShipperGroupDescription() {
		return this.shipperGroupDescription;
	}

	public void setShipperGroupDescription(String shipperGroupDescription) {
		this.shipperGroupDescription = shipperGroupDescription;
	}

	public String getShipperGroupName() {
		return this.shipperGroupName;
	}

	public void setShipperGroupName(String shipperGroupName) {
		this.shipperGroupName = shipperGroupName;
	}

	public List<ShpAssignedShipperGroupTb> getShpAssignedShipperGroupTbs() {
		return this.shpAssignedShipperGroupTbs;
	}

	public void setShpAssignedShipperGroupTbs(List<ShpAssignedShipperGroupTb> shpAssignedShipperGroupTbs) {
		this.shpAssignedShipperGroupTbs = shpAssignedShipperGroupTbs;
	}

	public ShpAssignedShipperGroupTb addShpAssignedShipperGroupTb(ShpAssignedShipperGroupTb shpAssignedShipperGroupTb) {
		getShpAssignedShipperGroupTbs().add(shpAssignedShipperGroupTb);
		shpAssignedShipperGroupTb.setShpShipperGroupTb(this);

		return shpAssignedShipperGroupTb;
	}

	public ShpAssignedShipperGroupTb removeShpAssignedShipperGroupTb(ShpAssignedShipperGroupTb shpAssignedShipperGroupTb) {
		getShpAssignedShipperGroupTbs().remove(shpAssignedShipperGroupTb);
		shpAssignedShipperGroupTb.setShpShipperGroupTb(null);

		return shpAssignedShipperGroupTb;
	}

	public ShpCustomerProfileTb getShpCustomerProfileTb() {
		return this.shpCustomerProfileTb;
	}

	public void setShpCustomerProfileTb(ShpCustomerProfileTb shpCustomerProfileTb) {
		this.shpCustomerProfileTb = shpCustomerProfileTb;
	}

}