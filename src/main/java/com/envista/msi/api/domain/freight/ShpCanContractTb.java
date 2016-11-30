package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_CAN_CONTRACT_TB database table.
 * 
 */
@Entity
@Table(name="SHP_CAN_CONTRACT_TB")
@NamedQuery(name="ShpCanContractTb.findAll", query="SELECT s FROM ShpCanContractTb s")
public class ShpCanContractTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CAN_CONT_ID", unique=true, precision=22)
	private long canContId;

	@Column(name="CONTRACT_NAME", length=500)
	private String contractName;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=100)
	private String createUser;

	@Column(name="CUSTOMER_ID", precision=22)
	private BigDecimal customerId;

	@Column(name="IS_ACTIVE", length=1)
	private String isActive;

	public ShpCanContractTb() {
	}

	public long getCanContId() {
		return this.canContId;
	}

	public void setCanContId(long canContId) {
		this.canContId = canContId;
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

	public BigDecimal getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}