package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SHP_NSP_CODE_VALUE_GROUPS_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_CODE_VALUE_GROUPS_TB")
@NamedQuery(name="ShpNspCodeValueGroupsTb.findAll", query="SELECT s FROM ShpNspCodeValueGroupsTb s")
public class ShpNspCodeValueGroupsTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_CODE_GROUP_ID", unique=true, precision=10)
	private long nspCodeGroupId;

	@Column(name="CODE_GROUP_DESCRIPTION", length=500)
	private String codeGroupDescription;

	@Column(name="CODE_GROUP_GROUP", length=50)
	private String codeGroupGroup;

	@Column(name="CODE_GROUP_NAME", length=50)
	private String codeGroupName;

	@Column(name="CODE_GROUP_REFERENCE", length=50)
	private String codeGroupReference;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=50)
	private String createUser;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=50)
	private String lastUpdateUser;

	@Column(name="PRODUCT_ID", precision=10)
	private BigDecimal productId;

	@Column(name="PROPERTY_1_FORMAT", length=50)
	private String property1Format;

	@Column(name="PROPERTY_1_LABEL", length=50)
	private String property1Label;

	@Column(name="PROPERTY_1_WIDTH", precision=4)
	private BigDecimal property1Width;

	@Column(name="PROPERTY_2_FORMAT", length=50)
	private String property2Format;

	@Column(name="PROPERTY_2_LABEL", length=50)
	private String property2Label;

	@Column(name="PROPERTY_2_WIDTH", precision=4)
	private BigDecimal property2Width;

	@Column(name="PROPERTY_3_FORMAT", length=50)
	private String property3Format;

	@Column(name="PROPERTY_3_LABEL", length=50)
	private String property3Label;

	@Column(name="PROPERTY_3_WIDTH", precision=4)
	private BigDecimal property3Width;

	@Column(name="PROPERTY_4_FORMAT", length=50)
	private String property4Format;

	@Column(name="PROPERTY_4_LABEL", length=50)
	private String property4Label;

	@Column(name="PROPERTY_4_WIDTH", precision=4)
	private BigDecimal property4Width;

	@Column(name="PROPERTY_5_FORMAT", length=50)
	private String property5Format;

	@Column(name="PROPERTY_5_LABEL", length=50)
	private String property5Label;

	@Column(name="PROPERTY_5_WIDTH", precision=4)
	private BigDecimal property5Width;

	@Column(name="PROTECT_GROUP", length=1)
	private String protectGroup;

	//bi-directional many-to-one association to ShpNspCodeValuesTb
	@OneToMany(mappedBy="shpNspCodeValueGroupsTb")
	private List<ShpNspCodeValuesTb> shpNspCodeValuesTbs;

	public ShpNspCodeValueGroupsTb() {
	}

	public long getNspCodeGroupId() {
		return this.nspCodeGroupId;
	}

	public void setNspCodeGroupId(long nspCodeGroupId) {
		this.nspCodeGroupId = nspCodeGroupId;
	}

	public String getCodeGroupDescription() {
		return this.codeGroupDescription;
	}

	public void setCodeGroupDescription(String codeGroupDescription) {
		this.codeGroupDescription = codeGroupDescription;
	}

	public String getCodeGroupGroup() {
		return this.codeGroupGroup;
	}

	public void setCodeGroupGroup(String codeGroupGroup) {
		this.codeGroupGroup = codeGroupGroup;
	}

	public String getCodeGroupName() {
		return this.codeGroupName;
	}

	public void setCodeGroupName(String codeGroupName) {
		this.codeGroupName = codeGroupName;
	}

	public String getCodeGroupReference() {
		return this.codeGroupReference;
	}

	public void setCodeGroupReference(String codeGroupReference) {
		this.codeGroupReference = codeGroupReference;
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

	public BigDecimal getProductId() {
		return this.productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

	public String getProperty1Format() {
		return this.property1Format;
	}

	public void setProperty1Format(String property1Format) {
		this.property1Format = property1Format;
	}

	public String getProperty1Label() {
		return this.property1Label;
	}

	public void setProperty1Label(String property1Label) {
		this.property1Label = property1Label;
	}

	public BigDecimal getProperty1Width() {
		return this.property1Width;
	}

	public void setProperty1Width(BigDecimal property1Width) {
		this.property1Width = property1Width;
	}

	public String getProperty2Format() {
		return this.property2Format;
	}

	public void setProperty2Format(String property2Format) {
		this.property2Format = property2Format;
	}

	public String getProperty2Label() {
		return this.property2Label;
	}

	public void setProperty2Label(String property2Label) {
		this.property2Label = property2Label;
	}

	public BigDecimal getProperty2Width() {
		return this.property2Width;
	}

	public void setProperty2Width(BigDecimal property2Width) {
		this.property2Width = property2Width;
	}

	public String getProperty3Format() {
		return this.property3Format;
	}

	public void setProperty3Format(String property3Format) {
		this.property3Format = property3Format;
	}

	public String getProperty3Label() {
		return this.property3Label;
	}

	public void setProperty3Label(String property3Label) {
		this.property3Label = property3Label;
	}

	public BigDecimal getProperty3Width() {
		return this.property3Width;
	}

	public void setProperty3Width(BigDecimal property3Width) {
		this.property3Width = property3Width;
	}

	public String getProperty4Format() {
		return this.property4Format;
	}

	public void setProperty4Format(String property4Format) {
		this.property4Format = property4Format;
	}

	public String getProperty4Label() {
		return this.property4Label;
	}

	public void setProperty4Label(String property4Label) {
		this.property4Label = property4Label;
	}

	public BigDecimal getProperty4Width() {
		return this.property4Width;
	}

	public void setProperty4Width(BigDecimal property4Width) {
		this.property4Width = property4Width;
	}

	public String getProperty5Format() {
		return this.property5Format;
	}

	public void setProperty5Format(String property5Format) {
		this.property5Format = property5Format;
	}

	public String getProperty5Label() {
		return this.property5Label;
	}

	public void setProperty5Label(String property5Label) {
		this.property5Label = property5Label;
	}

	public BigDecimal getProperty5Width() {
		return this.property5Width;
	}

	public void setProperty5Width(BigDecimal property5Width) {
		this.property5Width = property5Width;
	}

	public String getProtectGroup() {
		return this.protectGroup;
	}

	public void setProtectGroup(String protectGroup) {
		this.protectGroup = protectGroup;
	}

	public List<ShpNspCodeValuesTb> getShpNspCodeValuesTbs() {
		return this.shpNspCodeValuesTbs;
	}

	public void setShpNspCodeValuesTbs(List<ShpNspCodeValuesTb> shpNspCodeValuesTbs) {
		this.shpNspCodeValuesTbs = shpNspCodeValuesTbs;
	}

	public ShpNspCodeValuesTb addShpNspCodeValuesTb(ShpNspCodeValuesTb shpNspCodeValuesTb) {
		getShpNspCodeValuesTbs().add(shpNspCodeValuesTb);
		shpNspCodeValuesTb.setShpNspCodeValueGroupsTb(this);

		return shpNspCodeValuesTb;
	}

	public ShpNspCodeValuesTb removeShpNspCodeValuesTb(ShpNspCodeValuesTb shpNspCodeValuesTb) {
		getShpNspCodeValuesTbs().remove(shpNspCodeValuesTb);
		shpNspCodeValuesTb.setShpNspCodeValueGroupsTb(null);

		return shpNspCodeValuesTb;
	}

}