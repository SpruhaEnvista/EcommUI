package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SHP_NSP_WISO_ADDRESS_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_WISO_ADDRESS_TB")
@NamedQuery(name="ShpNspWisoAddressTb.findAll", query="SELECT s FROM ShpNspWisoAddressTb s")
public class ShpNspWisoAddressTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_WISO_ADDRESS_ID", unique=true, precision=22)
	private long nspWisoAddressId;

	@Column(length=250)
	private String address;

	@Column(length=100)
	private String city;

	@Column(length=250)
	private String facility;

	@Column(name="\"STATE\"", length=50)
	private String state;

	@Column(length=10)
	private String whse;

	@Column(name="ZIP_CODE", length=50)
	private String zipCode;

	public ShpNspWisoAddressTb() {
	}

	public long getNspWisoAddressId() {
		return this.nspWisoAddressId;
	}

	public void setNspWisoAddressId(long nspWisoAddressId) {
		this.nspWisoAddressId = nspWisoAddressId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFacility() {
		return this.facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWhse() {
		return this.whse;
	}

	public void setWhse(String whse) {
		this.whse = whse;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}