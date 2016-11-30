package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_CAN_INVOICE_FILTER_TB database table.
 * 
 */
@Entity
@Table(name="SHP_CAN_INVOICE_FILTER_TB")
@NamedQuery(name="ShpCanInvoiceFilterTb.findAll", query="SELECT s FROM ShpCanInvoiceFilterTb s")
public class ShpCanInvoiceFilterTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INVOICE_FILTER_ID", unique=true, precision=22)
	private long invoiceFilterId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="CREATED_USER", length=100)
	private String createdUser;

	@Column(name="FILTER_NAME", length=100)
	private String filterName;

	@Column(name="IS_PUBLIC", precision=1)
	private BigDecimal isPublic;

	@Column(precision=1)
	private BigDecimal status;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="UPDATED_USER", length=100)
	private String updatedUser;

	@Column(name="WHERE_CLAUSE", length=1000)
	private String whereClause;

	public ShpCanInvoiceFilterTb() {
	}

	public long getInvoiceFilterId() {
		return this.invoiceFilterId;
	}

	public void setInvoiceFilterId(long invoiceFilterId) {
		this.invoiceFilterId = invoiceFilterId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedUser() {
		return this.createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getFilterName() {
		return this.filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public BigDecimal getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(BigDecimal isPublic) {
		this.isPublic = isPublic;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedUser() {
		return this.updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public String getWhereClause() {
		return this.whereClause;
	}

	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}

}