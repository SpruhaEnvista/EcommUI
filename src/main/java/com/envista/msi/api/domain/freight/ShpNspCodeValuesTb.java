package com.envista.msi.api.domain.freight;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SHP_NSP_CODE_VALUES_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_CODE_VALUES_TB")
@NamedQuery(name="ShpNspCodeValuesTb.findAll", query="SELECT s FROM ShpNspCodeValuesTb s")
public class ShpNspCodeValuesTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_CODE_VALUE_ID", unique=true, nullable=false, precision=22)
	private long nspCodeValueId;

	@Column(length=1)
	private String active;

	@Column(name="CODE_VALUE", length=4000)
	private String codeValue;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=50)
	private String createUser;

	@Column(name="DEFAULT_VALUE", length=1)
	private String defaultValue;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=50)
	private String lastUpdateUser;

	@Column(name="PRODUCT_ID", precision=10)
	private BigDecimal productId;

	@Column(name="PROPERTY_1", length=500)
	private String property1;

	@Column(name="PROPERTY_2", length=500)
	private String property2;

	@Column(name="PROPERTY_3", length=500)
	private String property3;

	@Column(name="PROPERTY_4", length=500)
	private String property4;

	@Column(name="PROPERTY_5", length=75)
	private String property5;

	@Column(name="PROPERTY_6", length=300)
	private String property6;

	@Column(name="PROPERTY_7", length=300)
	private String property7;

	@Column(length=1)
	private String protect;

	/*@Column(name="\"SEQUENCE\"", precision=6)
	private BigDecimal sequence;*/

	@Column(length=1)
	private String taxable;

	//bi-directional many-to-one association to ShpNspAccessorialMappingTb
	@OneToMany(mappedBy="shpNspCodeValuesTb")
	private List<ShpNspAccessorialMappingTb> shpNspAccessorialMappingTbs;

	//bi-directional many-to-one association to ShpNspCodeValueGroupsTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CODE_GROUP_ID")
	private ShpNspCodeValueGroupsTb shpNspCodeValueGroupsTb;

	//bi-directional many-to-one association to ShpNspCustCarrierRelnTb
	@OneToMany(mappedBy="shpNspCodeValuesTb1")
	private List<ShpNspCustCarrierRelnTb> shpNspCustCarrierRelnTbs1;

	//bi-directional many-to-one association to ShpNspCustCarrierRelnTb
	@OneToMany(mappedBy="shpNspCodeValuesTb2")
	private List<ShpNspCustCarrierRelnTb> shpNspCustCarrierRelnTbs2;

	//bi-directional many-to-one association to ShpNspCustCarrierRelnTb
	@OneToMany(mappedBy="shpNspCodeValuesTb3")
	private List<ShpNspCustCarrierRelnTb> shpNspCustCarrierRelnTbs3;

	//bi-directional many-to-one association to ShpNspInvoiceChargesTb
	@OneToMany(mappedBy="shpNspCodeValuesTb")
	private List<ShpNspInvoiceChargesTb> shpNspInvoiceChargesTbs;

	//bi-directional many-to-one association to ShpNspInvoiceDetailsTb
	@OneToMany(mappedBy="shpNspCodeValuesTb1")
	private List<ShpNspInvoiceDetailsTb> shpNspInvoiceDetailsTbs1;

	//bi-directional many-to-one association to ShpNspInvoiceDetailsTb
	@OneToMany(mappedBy="shpNspCodeValuesTb2")
	private List<ShpNspInvoiceDetailsTb> shpNspInvoiceDetailsTbs2;

	//bi-directional many-to-one association to ShpNspInvoiceDetailsTb
	@OneToMany(mappedBy="shpNspCodeValuesTb3")
	private List<ShpNspInvoiceDetailsTb> shpNspInvoiceDetailsTbs3;

	//bi-directional many-to-one association to ShpNspInvoiceDetailsTb
	@OneToMany(mappedBy="shpNspCodeValuesTb4")
	private List<ShpNspInvoiceDetailsTb> shpNspInvoiceDetailsTbs4;

	//bi-directional many-to-one association to ShpNspInvFilterDetailsTb
	@OneToMany(mappedBy="shpNspCodeValuesTb")
	private List<ShpNspInvFilterDetailsTb> shpNspInvFilterDetailsTbs;

	//bi-directional many-to-one association to ShpNspLocationTb
	@OneToMany(mappedBy="shpNspCodeValuesTb")
	private List<ShpNspLocationTb> shpNspLocationTbs;

	//bi-directional many-to-one association to ShpNspScannedInvoiceTb
	@OneToMany(mappedBy="shpNspCodeValuesTb")
	private List<ShpNspScannedInvoiceTb> shpNspScannedInvoiceTbs;

	//bi-directional many-to-one association to ShpNspStandardFeeTb
	@OneToMany(mappedBy="shpNspCodeValuesTb")
	private List<ShpNspStandardFeeTb> shpNspStandardFeeTbs;

	public ShpNspCodeValuesTb() {
	}

	public long getNspCodeValueId() {
		return this.nspCodeValueId;
	}

	public void setNspCodeValueId(long nspCodeValueId) {
		this.nspCodeValueId = nspCodeValueId;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCodeValue() {
		return this.codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
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

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
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

	public String getProperty1() {
		return this.property1;
	}

	public void setProperty1(String property1) {
		this.property1 = property1;
	}

	public String getProperty2() {
		return this.property2;
	}

	public void setProperty2(String property2) {
		this.property2 = property2;
	}

	public String getProperty3() {
		return this.property3;
	}

	public void setProperty3(String property3) {
		this.property3 = property3;
	}

	public String getProperty4() {
		return this.property4;
	}

	public void setProperty4(String property4) {
		this.property4 = property4;
	}

	public String getProperty5() {
		return this.property5;
	}

	public void setProperty5(String property5) {
		this.property5 = property5;
	}

	public String getProperty6() {
		return this.property6;
	}

	public void setProperty6(String property6) {
		this.property6 = property6;
	}

	public String getProperty7() {
		return this.property7;
	}

	public void setProperty7(String property7) {
		this.property7 = property7;
	}

	public String getProtect() {
		return this.protect;
	}

	public void setProtect(String protect) {
		this.protect = protect;
	}

	/*public BigDecimal getSequence() {
		return this.sequence;
	}

	public void setSequence(BigDecimal sequence) {
		this.sequence = sequence;
	}*/

	public String getTaxable() {
		return this.taxable;
	}

	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}

	public List<ShpNspAccessorialMappingTb> getShpNspAccessorialMappingTbs() {
		return this.shpNspAccessorialMappingTbs;
	}

	public void setShpNspAccessorialMappingTbs(List<ShpNspAccessorialMappingTb> shpNspAccessorialMappingTbs) {
		this.shpNspAccessorialMappingTbs = shpNspAccessorialMappingTbs;
	}

	public ShpNspAccessorialMappingTb addShpNspAccessorialMappingTb(ShpNspAccessorialMappingTb shpNspAccessorialMappingTb) {
		getShpNspAccessorialMappingTbs().add(shpNspAccessorialMappingTb);
		shpNspAccessorialMappingTb.setShpNspCodeValuesTb(this);

		return shpNspAccessorialMappingTb;
	}

	public ShpNspAccessorialMappingTb removeShpNspAccessorialMappingTb(ShpNspAccessorialMappingTb shpNspAccessorialMappingTb) {
		getShpNspAccessorialMappingTbs().remove(shpNspAccessorialMappingTb);
		shpNspAccessorialMappingTb.setShpNspCodeValuesTb(null);

		return shpNspAccessorialMappingTb;
	}

	public ShpNspCodeValueGroupsTb getShpNspCodeValueGroupsTb() {
		return this.shpNspCodeValueGroupsTb;
	}

	public void setShpNspCodeValueGroupsTb(ShpNspCodeValueGroupsTb shpNspCodeValueGroupsTb) {
		this.shpNspCodeValueGroupsTb = shpNspCodeValueGroupsTb;
	}

	public List<ShpNspCustCarrierRelnTb> getShpNspCustCarrierRelnTbs1() {
		return this.shpNspCustCarrierRelnTbs1;
	}

	public void setShpNspCustCarrierRelnTbs1(List<ShpNspCustCarrierRelnTb> shpNspCustCarrierRelnTbs1) {
		this.shpNspCustCarrierRelnTbs1 = shpNspCustCarrierRelnTbs1;
	}

	public ShpNspCustCarrierRelnTb addShpNspCustCarrierRelnTbs1(ShpNspCustCarrierRelnTb shpNspCustCarrierRelnTbs1) {
		getShpNspCustCarrierRelnTbs1().add(shpNspCustCarrierRelnTbs1);
		shpNspCustCarrierRelnTbs1.setShpNspCodeValuesTb1(this);

		return shpNspCustCarrierRelnTbs1;
	}

	public ShpNspCustCarrierRelnTb removeShpNspCustCarrierRelnTbs1(ShpNspCustCarrierRelnTb shpNspCustCarrierRelnTbs1) {
		getShpNspCustCarrierRelnTbs1().remove(shpNspCustCarrierRelnTbs1);
		shpNspCustCarrierRelnTbs1.setShpNspCodeValuesTb1(null);

		return shpNspCustCarrierRelnTbs1;
	}

	public List<ShpNspCustCarrierRelnTb> getShpNspCustCarrierRelnTbs2() {
		return this.shpNspCustCarrierRelnTbs2;
	}

	public void setShpNspCustCarrierRelnTbs2(List<ShpNspCustCarrierRelnTb> shpNspCustCarrierRelnTbs2) {
		this.shpNspCustCarrierRelnTbs2 = shpNspCustCarrierRelnTbs2;
	}

	public ShpNspCustCarrierRelnTb addShpNspCustCarrierRelnTbs2(ShpNspCustCarrierRelnTb shpNspCustCarrierRelnTbs2) {
		getShpNspCustCarrierRelnTbs2().add(shpNspCustCarrierRelnTbs2);
		shpNspCustCarrierRelnTbs2.setShpNspCodeValuesTb2(this);

		return shpNspCustCarrierRelnTbs2;
	}

	public ShpNspCustCarrierRelnTb removeShpNspCustCarrierRelnTbs2(ShpNspCustCarrierRelnTb shpNspCustCarrierRelnTbs2) {
		getShpNspCustCarrierRelnTbs2().remove(shpNspCustCarrierRelnTbs2);
		shpNspCustCarrierRelnTbs2.setShpNspCodeValuesTb2(null);

		return shpNspCustCarrierRelnTbs2;
	}

	public List<ShpNspCustCarrierRelnTb> getShpNspCustCarrierRelnTbs3() {
		return this.shpNspCustCarrierRelnTbs3;
	}

	public void setShpNspCustCarrierRelnTbs3(List<ShpNspCustCarrierRelnTb> shpNspCustCarrierRelnTbs3) {
		this.shpNspCustCarrierRelnTbs3 = shpNspCustCarrierRelnTbs3;
	}

	public ShpNspCustCarrierRelnTb addShpNspCustCarrierRelnTbs3(ShpNspCustCarrierRelnTb shpNspCustCarrierRelnTbs3) {
		getShpNspCustCarrierRelnTbs3().add(shpNspCustCarrierRelnTbs3);
		shpNspCustCarrierRelnTbs3.setShpNspCodeValuesTb3(this);

		return shpNspCustCarrierRelnTbs3;
	}

	public ShpNspCustCarrierRelnTb removeShpNspCustCarrierRelnTbs3(ShpNspCustCarrierRelnTb shpNspCustCarrierRelnTbs3) {
		getShpNspCustCarrierRelnTbs3().remove(shpNspCustCarrierRelnTbs3);
		shpNspCustCarrierRelnTbs3.setShpNspCodeValuesTb3(null);

		return shpNspCustCarrierRelnTbs3;
	}

	public List<ShpNspInvoiceChargesTb> getShpNspInvoiceChargesTbs() {
		return this.shpNspInvoiceChargesTbs;
	}

	public void setShpNspInvoiceChargesTbs(List<ShpNspInvoiceChargesTb> shpNspInvoiceChargesTbs) {
		this.shpNspInvoiceChargesTbs = shpNspInvoiceChargesTbs;
	}

	public ShpNspInvoiceChargesTb addShpNspInvoiceChargesTb(ShpNspInvoiceChargesTb shpNspInvoiceChargesTb) {
		getShpNspInvoiceChargesTbs().add(shpNspInvoiceChargesTb);
		shpNspInvoiceChargesTb.setShpNspCodeValuesTb(this);

		return shpNspInvoiceChargesTb;
	}

	public ShpNspInvoiceChargesTb removeShpNspInvoiceChargesTb(ShpNspInvoiceChargesTb shpNspInvoiceChargesTb) {
		getShpNspInvoiceChargesTbs().remove(shpNspInvoiceChargesTb);
		shpNspInvoiceChargesTb.setShpNspCodeValuesTb(null);

		return shpNspInvoiceChargesTb;
	}

	public List<ShpNspInvoiceDetailsTb> getShpNspInvoiceDetailsTbs1() {
		return this.shpNspInvoiceDetailsTbs1;
	}

	public void setShpNspInvoiceDetailsTbs1(List<ShpNspInvoiceDetailsTb> shpNspInvoiceDetailsTbs1) {
		this.shpNspInvoiceDetailsTbs1 = shpNspInvoiceDetailsTbs1;
	}

	public ShpNspInvoiceDetailsTb addShpNspInvoiceDetailsTbs1(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTbs1) {
		getShpNspInvoiceDetailsTbs1().add(shpNspInvoiceDetailsTbs1);
		shpNspInvoiceDetailsTbs1.setShpNspCodeValuesTb1(this);

		return shpNspInvoiceDetailsTbs1;
	}

	public ShpNspInvoiceDetailsTb removeShpNspInvoiceDetailsTbs1(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTbs1) {
		getShpNspInvoiceDetailsTbs1().remove(shpNspInvoiceDetailsTbs1);
		shpNspInvoiceDetailsTbs1.setShpNspCodeValuesTb1(null);

		return shpNspInvoiceDetailsTbs1;
	}

	public List<ShpNspInvoiceDetailsTb> getShpNspInvoiceDetailsTbs2() {
		return this.shpNspInvoiceDetailsTbs2;
	}

	public void setShpNspInvoiceDetailsTbs2(List<ShpNspInvoiceDetailsTb> shpNspInvoiceDetailsTbs2) {
		this.shpNspInvoiceDetailsTbs2 = shpNspInvoiceDetailsTbs2;
	}

	public ShpNspInvoiceDetailsTb addShpNspInvoiceDetailsTbs2(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTbs2) {
		getShpNspInvoiceDetailsTbs2().add(shpNspInvoiceDetailsTbs2);
		shpNspInvoiceDetailsTbs2.setShpNspCodeValuesTb2(this);

		return shpNspInvoiceDetailsTbs2;
	}

	public ShpNspInvoiceDetailsTb removeShpNspInvoiceDetailsTbs2(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTbs2) {
		getShpNspInvoiceDetailsTbs2().remove(shpNspInvoiceDetailsTbs2);
		shpNspInvoiceDetailsTbs2.setShpNspCodeValuesTb2(null);

		return shpNspInvoiceDetailsTbs2;
	}

	public List<ShpNspInvoiceDetailsTb> getShpNspInvoiceDetailsTbs3() {
		return this.shpNspInvoiceDetailsTbs3;
	}

	public void setShpNspInvoiceDetailsTbs3(List<ShpNspInvoiceDetailsTb> shpNspInvoiceDetailsTbs3) {
		this.shpNspInvoiceDetailsTbs3 = shpNspInvoiceDetailsTbs3;
	}

	public ShpNspInvoiceDetailsTb addShpNspInvoiceDetailsTbs3(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTbs3) {
		getShpNspInvoiceDetailsTbs3().add(shpNspInvoiceDetailsTbs3);
		shpNspInvoiceDetailsTbs3.setShpNspCodeValuesTb3(this);

		return shpNspInvoiceDetailsTbs3;
	}

	public ShpNspInvoiceDetailsTb removeShpNspInvoiceDetailsTbs3(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTbs3) {
		getShpNspInvoiceDetailsTbs3().remove(shpNspInvoiceDetailsTbs3);
		shpNspInvoiceDetailsTbs3.setShpNspCodeValuesTb3(null);

		return shpNspInvoiceDetailsTbs3;
	}

	public List<ShpNspInvoiceDetailsTb> getShpNspInvoiceDetailsTbs4() {
		return this.shpNspInvoiceDetailsTbs4;
	}

	public void setShpNspInvoiceDetailsTbs4(List<ShpNspInvoiceDetailsTb> shpNspInvoiceDetailsTbs4) {
		this.shpNspInvoiceDetailsTbs4 = shpNspInvoiceDetailsTbs4;
	}

	public ShpNspInvoiceDetailsTb addShpNspInvoiceDetailsTbs4(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTbs4) {
		getShpNspInvoiceDetailsTbs4().add(shpNspInvoiceDetailsTbs4);
		shpNspInvoiceDetailsTbs4.setShpNspCodeValuesTb4(this);

		return shpNspInvoiceDetailsTbs4;
	}

	public ShpNspInvoiceDetailsTb removeShpNspInvoiceDetailsTbs4(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTbs4) {
		getShpNspInvoiceDetailsTbs4().remove(shpNspInvoiceDetailsTbs4);
		shpNspInvoiceDetailsTbs4.setShpNspCodeValuesTb4(null);

		return shpNspInvoiceDetailsTbs4;
	}

	public List<ShpNspInvFilterDetailsTb> getShpNspInvFilterDetailsTbs() {
		return this.shpNspInvFilterDetailsTbs;
	}

	public void setShpNspInvFilterDetailsTbs(List<ShpNspInvFilterDetailsTb> shpNspInvFilterDetailsTbs) {
		this.shpNspInvFilterDetailsTbs = shpNspInvFilterDetailsTbs;
	}

	public ShpNspInvFilterDetailsTb addShpNspInvFilterDetailsTb(ShpNspInvFilterDetailsTb shpNspInvFilterDetailsTb) {
		getShpNspInvFilterDetailsTbs().add(shpNspInvFilterDetailsTb);
		shpNspInvFilterDetailsTb.setShpNspCodeValuesTb(this);

		return shpNspInvFilterDetailsTb;
	}

	public ShpNspInvFilterDetailsTb removeShpNspInvFilterDetailsTb(ShpNspInvFilterDetailsTb shpNspInvFilterDetailsTb) {
		getShpNspInvFilterDetailsTbs().remove(shpNspInvFilterDetailsTb);
		shpNspInvFilterDetailsTb.setShpNspCodeValuesTb(null);

		return shpNspInvFilterDetailsTb;
	}

	public List<ShpNspLocationTb> getShpNspLocationTbs() {
		return this.shpNspLocationTbs;
	}

	public void setShpNspLocationTbs(List<ShpNspLocationTb> shpNspLocationTbs) {
		this.shpNspLocationTbs = shpNspLocationTbs;
	}

	public ShpNspLocationTb addShpNspLocationTb(ShpNspLocationTb shpNspLocationTb) {
		getShpNspLocationTbs().add(shpNspLocationTb);
		shpNspLocationTb.setShpNspCodeValuesTb(this);

		return shpNspLocationTb;
	}

	public ShpNspLocationTb removeShpNspLocationTb(ShpNspLocationTb shpNspLocationTb) {
		getShpNspLocationTbs().remove(shpNspLocationTb);
		shpNspLocationTb.setShpNspCodeValuesTb(null);

		return shpNspLocationTb;
	}

	public List<ShpNspScannedInvoiceTb> getShpNspScannedInvoiceTbs() {
		return this.shpNspScannedInvoiceTbs;
	}

	public void setShpNspScannedInvoiceTbs(List<ShpNspScannedInvoiceTb> shpNspScannedInvoiceTbs) {
		this.shpNspScannedInvoiceTbs = shpNspScannedInvoiceTbs;
	}

	public ShpNspScannedInvoiceTb addShpNspScannedInvoiceTb(ShpNspScannedInvoiceTb shpNspScannedInvoiceTb) {
		getShpNspScannedInvoiceTbs().add(shpNspScannedInvoiceTb);
		shpNspScannedInvoiceTb.setShpNspCodeValuesTb(this);

		return shpNspScannedInvoiceTb;
	}

	public ShpNspScannedInvoiceTb removeShpNspScannedInvoiceTb(ShpNspScannedInvoiceTb shpNspScannedInvoiceTb) {
		getShpNspScannedInvoiceTbs().remove(shpNspScannedInvoiceTb);
		shpNspScannedInvoiceTb.setShpNspCodeValuesTb(null);

		return shpNspScannedInvoiceTb;
	}

	public List<ShpNspStandardFeeTb> getShpNspStandardFeeTbs() {
		return this.shpNspStandardFeeTbs;
	}

	public void setShpNspStandardFeeTbs(List<ShpNspStandardFeeTb> shpNspStandardFeeTbs) {
		this.shpNspStandardFeeTbs = shpNspStandardFeeTbs;
	}

	public ShpNspStandardFeeTb addShpNspStandardFeeTb(ShpNspStandardFeeTb shpNspStandardFeeTb) {
		getShpNspStandardFeeTbs().add(shpNspStandardFeeTb);
		shpNspStandardFeeTb.setShpNspCodeValuesTb(this);

		return shpNspStandardFeeTb;
	}

	public ShpNspStandardFeeTb removeShpNspStandardFeeTb(ShpNspStandardFeeTb shpNspStandardFeeTb) {
		getShpNspStandardFeeTbs().remove(shpNspStandardFeeTb);
		shpNspStandardFeeTb.setShpNspCodeValuesTb(null);

		return shpNspStandardFeeTb;
	}
	
	@Override
	public String toString() {
		
		return "ShpNspCodeValuesTb [nspCodeValueId=" + nspCodeValueId + ", "
				+ (active != null ? "active=" + active + ", " : "")
				+ (codeValue != null ? "codeValue=" + codeValue + ", " : "")
				+ (createDate != null ? "createDate=" + createDate + ", " : "")
				+ (createUser != null ? "createUser=" + createUser + ", " : "")
				+ (defaultValue != null ? "defaultValue=" + defaultValue + ", " : "")
				+ (lastUpdateDate != null ? "lastUpdateDate=" + lastUpdateDate + ", " : "")
				+ (lastUpdateUser != null ? "lastUpdateUser=" + lastUpdateUser + ", " : "")
				+ (productId != null ? "productId=" + productId + ", " : "")
				+ (property1 != null ? "property1=" + property1 + ", " : "")
				+ (property2 != null ? "property2=" + property2 + ", " : "")
				+ (property3 != null ? "property3=" + property3 + ", " : "")
				+ (property4 != null ? "property4=" + property4 + ", " : "")
				+ (property5 != null ? "property5=" + property5 + ", " : "")
				+ (property6 != null ? "property6=" + property6 + ", " : "")
				+ (property7 != null ? "property7=" + property7 + ", " : "")
				+ (protect != null ? "protect=" + protect + ", " : "")
				+ (taxable != null ? "taxable=" + taxable + ", " : "")
				+ "]";
	}

}