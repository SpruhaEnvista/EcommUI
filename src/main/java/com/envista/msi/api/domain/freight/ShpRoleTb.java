package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SHP_ROLE_TB database table.
 * 
 */
@Entity
@Table(name="SHP_ROLE_TB")
@NamedQuery(name="ShpRoleTb.findAll", query="SELECT s FROM ShpRoleTb s")
public class ShpRoleTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROLE_ID", unique=true, precision=22)
	private long roleId;

	@Column(name="ROLE_DESCRIPTION", length=100)
	private String roleDescription;

	@Column(name="ROLE_NAME", length=25)
	private String roleName;

	@OneToMany(mappedBy="shpRoleTb")
	private List<ShpUserRoleTb> shpUserRoleTbs;

	public ShpRoleTb() {
	}

	public long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<ShpUserRoleTb> getShpUserRoleTbs() {
		return this.shpUserRoleTbs;
	}

	public void setShpUserRoleTbs(List<ShpUserRoleTb> shpUserRoleTbs) {
		this.shpUserRoleTbs = shpUserRoleTbs;
	}

	public ShpUserRoleTb addShpUserRoleTb(ShpUserRoleTb shpUserRoleTb) {
		getShpUserRoleTbs().add(shpUserRoleTb);
		shpUserRoleTb.setShpRoleTb(this);

		return shpUserRoleTb;
	}

	public ShpUserRoleTb removeShpUserRoleTb(ShpUserRoleTb shpUserRoleTb) {
		getShpUserRoleTbs().remove(shpUserRoleTb);
		shpUserRoleTb.setShpRoleTb(null);

		return shpUserRoleTb;
	}

}