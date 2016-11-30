package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SHP_NSP_PARSERS_DEF_VALUES_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_PARSERS_DEF_VALUES_TB")
@NamedQuery(name="ShpNspParsersDefValuesTb.findAll", query="SELECT s FROM ShpNspParsersDefValuesTb s")
public class ShpNspParsersDefValuesTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_DEFAULT_VALUE_ID", unique=true, precision=22)
	private long nspDefaultValueId;

	@Column(length=1)
	private String active;

	@Column(name="CARRIER_SCAC_CODE", length=50)
	private String carrierScacCode;

	@Column(name="CUSTOMER_CODE", length=50)
	private String customerCode;

	@Column(name="DEFAULT_VALUE", length=50)
	private String defaultValue;

	@Column(name="EDI_TYPE", length=50)
	private String ediType;

	@Column(name="FIELD_NAME", length=50)
	private String fieldName;

	@Column(name="PROPERTY_1", length=50)
	private String property1;

	@Column(name="PROPERTY_2", length=50)
	private String property2;

	@Column(name="PROPERTY_3", length=50)
	private String property3;

	public ShpNspParsersDefValuesTb() {
	}

	public long getNspDefaultValueId() {
		return this.nspDefaultValueId;
	}

	public void setNspDefaultValueId(long nspDefaultValueId) {
		this.nspDefaultValueId = nspDefaultValueId;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCarrierScacCode() {
		return this.carrierScacCode;
	}

	public void setCarrierScacCode(String carrierScacCode) {
		this.carrierScacCode = carrierScacCode;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getEdiType() {
		return this.ediType;
	}

	public void setEdiType(String ediType) {
		this.ediType = ediType;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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

}