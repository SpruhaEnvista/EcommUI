package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_CUST_CARRIER_CONT_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_CUST_CARRIER_CONT_TB")
@NamedQuery(name="ShpNspCustCarrierContTb.findAll", query="SELECT s FROM ShpNspCustCarrierContTb s")
public class ShpNspCustCarrierContTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_CUST_CARRIER_CONT_ID", unique=true, precision=22)
	private long nspCustCarrierContId;

	@Column(name="CONTRACT_NAME", length=100)
	private String contractName;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=100)
	private String createUser;

	@Column(name="IS_ACTIVE", precision=1)
	private BigDecimal isActive;

	//bi-directional many-to-one association to ShpNspCustCarrierRelnTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NSP_CUST_CARRIER_RELN_ID")
	private ShpNspCustCarrierRelnTb shpNspCustCarrierRelnTb;

	public ShpNspCustCarrierContTb() {
	}

	public long getNspCustCarrierContId() {
		return this.nspCustCarrierContId;
	}

	public void setNspCustCarrierContId(long nspCustCarrierContId) {
		this.nspCustCarrierContId = nspCustCarrierContId;
	}

	public String getContractName() {
		return this.contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
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

	public BigDecimal getIsActive() {
		return this.isActive;
	}

	public void setIsActive(BigDecimal isActive) {
		this.isActive = isActive;
	}

	public ShpNspCustCarrierRelnTb getShpNspCustCarrierRelnTb() {
		return this.shpNspCustCarrierRelnTb;
	}

	public void setShpNspCustCarrierRelnTb(ShpNspCustCarrierRelnTb shpNspCustCarrierRelnTb) {
		this.shpNspCustCarrierRelnTb = shpNspCustCarrierRelnTb;
	}

}