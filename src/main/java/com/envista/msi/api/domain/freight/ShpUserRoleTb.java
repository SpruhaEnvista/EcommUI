package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SHP_USER_ROLE_TB database table.
 * 
 */
@Entity
@Table(name="SHP_USER_ROLE_TB")
@NamedQuery(name="ShpUserRoleTb.findAll", query="SELECT s FROM ShpUserRoleTb s")
public class ShpUserRoleTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ROLE_ID", unique=true, precision=22)
	private long userRoleId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ROLE_ID")
	private ShpRoleTb shpRoleTb;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private ShpUserProfileTb shpUserProfileTb;

	public ShpUserRoleTb() {
	}

	public long getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	public ShpRoleTb getShpRoleTb() {
		return this.shpRoleTb;
	}

	public void setShpRoleTb(ShpRoleTb shpRoleTb) {
		this.shpRoleTb = shpRoleTb;
	}
	
	public ShpUserProfileTb getShpUserProfileTb() {
		return this.shpUserProfileTb;
	}

	public void setShpUserProfileTb(ShpUserProfileTb shpUserProfileTb) {
		this.shpUserProfileTb = shpUserProfileTb;
	}

}