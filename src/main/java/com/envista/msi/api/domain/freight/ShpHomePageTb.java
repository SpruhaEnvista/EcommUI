package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SHP_HOME_PAGE_TB database table.
 * 
 */
@Entity
@Table(name="SHP_HOME_PAGE_TB")
@NamedQuery(name="ShpHomePageTb.findAll", query="SELECT s FROM ShpHomePageTb s")
public class ShpHomePageTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HOME_PAGE_ID", unique=true, precision=22)
	private long homePageId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="IS_EBILL", precision=22)
	private BigDecimal isEbill;

	@Column(name="MODULE_ID", precision=22)
	private BigDecimal moduleId;

	@Column(name="OTHER_ACCESS_CSV", length=500)
	private String otherAccessCsv;

	@Column(name="PAGE_CONSTANT", length=50)
	private String pageConstant;

	@Column(name="PAGE_NAME", length=50)
	private String pageName;

	@Column(name="ROLES_CSV", length=300)
	private String rolesCsv;

	//bi-directional many-to-one association to ShpUserProfileTb
	@OneToMany(mappedBy="shpHomePageTb")
	private List<ShpUserProfileTb> shpUserProfileTbs;

	public ShpHomePageTb() {
	}

	public long getHomePageId() {
		return this.homePageId;
	}

	public void setHomePageId(long homePageId) {
		this.homePageId = homePageId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getIsEbill() {
		return this.isEbill;
	}

	public void setIsEbill(BigDecimal isEbill) {
		this.isEbill = isEbill;
	}

	public BigDecimal getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(BigDecimal moduleId) {
		this.moduleId = moduleId;
	}

	public String getOtherAccessCsv() {
		return this.otherAccessCsv;
	}

	public void setOtherAccessCsv(String otherAccessCsv) {
		this.otherAccessCsv = otherAccessCsv;
	}

	public String getPageConstant() {
		return this.pageConstant;
	}

	public void setPageConstant(String pageConstant) {
		this.pageConstant = pageConstant;
	}

	public String getPageName() {
		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getRolesCsv() {
		return this.rolesCsv;
	}

	public void setRolesCsv(String rolesCsv) {
		this.rolesCsv = rolesCsv;
	}

	public List<ShpUserProfileTb> getShpUserProfileTbs() {
		return this.shpUserProfileTbs;
	}

	public void setShpUserProfileTbs(List<ShpUserProfileTb> shpUserProfileTbs) {
		this.shpUserProfileTbs = shpUserProfileTbs;
	}

	public ShpUserProfileTb addShpUserProfileTb(ShpUserProfileTb shpUserProfileTb) {
		getShpUserProfileTbs().add(shpUserProfileTb);
		shpUserProfileTb.setShpHomePageTb(this);

		return shpUserProfileTb;
	}

	public ShpUserProfileTb removeShpUserProfileTb(ShpUserProfileTb shpUserProfileTb) {
		getShpUserProfileTbs().remove(shpUserProfileTb);
		shpUserProfileTb.setShpHomePageTb(null);

		return shpUserProfileTb;
	}

}