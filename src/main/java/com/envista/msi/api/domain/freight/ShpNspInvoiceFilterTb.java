package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SHP_NSP_INVOICE_FILTER_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_INVOICE_FILTER_TB")
@NamedQuery(name="ShpNspInvoiceFilterTb.findAll", query="SELECT s FROM ShpNspInvoiceFilterTb s")
public class ShpNspInvoiceFilterTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INVOICE_FILTER_ID", unique=true, precision=22)
	private long invoiceFilterId;

	@Column(name="AUDIT_ACTION", precision=22)
	private BigDecimal auditAction;

	@Column(name="BOL_PO_FORMAT", length=100)
	private String bolPoFormat;

	@Column(name="CARRIER_ID", precision=22)
	private BigDecimal carrierId;

	@Column(length=100)
	private String comments;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="CREATED_USER", length=100)
	private String createdUser;

	@Column(name="CUSTOMER_ID", precision=22)
	private BigDecimal customerId;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_DATE")
	private Date effectiveDate;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DATE")
	private Date expiryDate;

	@Column(name="FILTER_GROUP", length=100)
	private String filterGroup;

	@Column(name="FILTER_NAME", length=100)
	private String filterName;

	@Column(name="GAIN_SHARE", precision=22)
	private BigDecimal gainShare;

	@Column(name="IS_AUTO_CLOSE_RULE", precision=1)
	private BigDecimal isAutoCloseRule;

	@Column(name="IS_DYNAMIC_QUEUE", precision=1)
	private BigDecimal isDynamicQueue;

	@Column(name="IS_EIFOC", precision=1)
	private BigDecimal isEifoc;

	@Column(name="IS_OD_VALID", precision=1)
	private BigDecimal isOdValid;

	@Column(name="IS_PUBLIC", precision=1)
	private BigDecimal isPublic;

	@Column(precision=22)
	private BigDecimal reason;

	@Column(name="RULES_PRIORITY", precision=22)
	private BigDecimal rulesPriority;

	@Column(precision=1)
	private BigDecimal status;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="UPDATED_USER", length=100)
	private String updatedUser;

	@Column(name="WHERE_CLAUSE", length=1000)
	private String whereClause;

	//bi-directional many-to-one association to ShpNspInvFilterDetailsTb
	@OneToMany(mappedBy="shpNspInvoiceFilterTb")
	private List<ShpNspInvFilterDetailsTb> shpNspInvFilterDetailsTbs;

	public ShpNspInvoiceFilterTb() {
	}

	public long getInvoiceFilterId() {
		return this.invoiceFilterId;
	}

	public void setInvoiceFilterId(long invoiceFilterId) {
		this.invoiceFilterId = invoiceFilterId;
	}

	public BigDecimal getAuditAction() {
		return this.auditAction;
	}

	public void setAuditAction(BigDecimal auditAction) {
		this.auditAction = auditAction;
	}

	public String getBolPoFormat() {
		return this.bolPoFormat;
	}

	public void setBolPoFormat(String bolPoFormat) {
		this.bolPoFormat = bolPoFormat;
	}

	public BigDecimal getCarrierId() {
		return this.carrierId;
	}

	public void setCarrierId(BigDecimal carrierId) {
		this.carrierId = carrierId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public BigDecimal getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getFilterGroup() {
		return this.filterGroup;
	}

	public void setFilterGroup(String filterGroup) {
		this.filterGroup = filterGroup;
	}

	public String getFilterName() {
		return this.filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public BigDecimal getGainShare() {
		return this.gainShare;
	}

	public void setGainShare(BigDecimal gainShare) {
		this.gainShare = gainShare;
	}

	public BigDecimal getIsAutoCloseRule() {
		return this.isAutoCloseRule;
	}

	public void setIsAutoCloseRule(BigDecimal isAutoCloseRule) {
		this.isAutoCloseRule = isAutoCloseRule;
	}

	public BigDecimal getIsDynamicQueue() {
		return this.isDynamicQueue;
	}

	public void setIsDynamicQueue(BigDecimal isDynamicQueue) {
		this.isDynamicQueue = isDynamicQueue;
	}

	public BigDecimal getIsEifoc() {
		return this.isEifoc;
	}

	public void setIsEifoc(BigDecimal isEifoc) {
		this.isEifoc = isEifoc;
	}

	public BigDecimal getIsOdValid() {
		return this.isOdValid;
	}

	public void setIsOdValid(BigDecimal isOdValid) {
		this.isOdValid = isOdValid;
	}

	public BigDecimal getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(BigDecimal isPublic) {
		this.isPublic = isPublic;
	}

	public BigDecimal getReason() {
		return this.reason;
	}

	public void setReason(BigDecimal reason) {
		this.reason = reason;
	}

	public BigDecimal getRulesPriority() {
		return this.rulesPriority;
	}

	public void setRulesPriority(BigDecimal rulesPriority) {
		this.rulesPriority = rulesPriority;
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

	public List<ShpNspInvFilterDetailsTb> getShpNspInvFilterDetailsTbs() {
		return this.shpNspInvFilterDetailsTbs;
	}

	public void setShpNspInvFilterDetailsTbs(List<ShpNspInvFilterDetailsTb> shpNspInvFilterDetailsTbs) {
		this.shpNspInvFilterDetailsTbs = shpNspInvFilterDetailsTbs;
	}

	public ShpNspInvFilterDetailsTb addShpNspInvFilterDetailsTb(ShpNspInvFilterDetailsTb shpNspInvFilterDetailsTb) {
		getShpNspInvFilterDetailsTbs().add(shpNspInvFilterDetailsTb);
		shpNspInvFilterDetailsTb.setShpNspInvoiceFilterTb(this);

		return shpNspInvFilterDetailsTb;
	}

	public ShpNspInvFilterDetailsTb removeShpNspInvFilterDetailsTb(ShpNspInvFilterDetailsTb shpNspInvFilterDetailsTb) {
		getShpNspInvFilterDetailsTbs().remove(shpNspInvFilterDetailsTb);
		shpNspInvFilterDetailsTb.setShpNspInvoiceFilterTb(null);

		return shpNspInvFilterDetailsTb;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		return "ShpNspInvoiceFilterTb [invoiceFilterId=" + invoiceFilterId + ", "
				+ (auditAction != null ? "auditAction=" + auditAction + ", " : "")
				+ (bolPoFormat != null ? "bolPoFormat=" + bolPoFormat + ", " : "")
				+ (carrierId != null ? "carrierId=" + carrierId + ", " : "")
				+ (comments != null ? "comments=" + comments + ", " : "")
				+ (createdDate != null ? "createdDate=" + createdDate + ", " : "")
				+ (createdUser != null ? "createdUser=" + createdUser + ", " : "")
				+ (customerId != null ? "customerId=" + customerId + ", " : "")
				+ (effectiveDate != null ? "effectiveDate=" + effectiveDate + ", " : "")
				+ (expiryDate != null ? "expiryDate=" + expiryDate + ", " : "")
				+ (filterGroup != null ? "filterGroup=" + filterGroup + ", " : "")
				+ (filterName != null ? "filterName=" + filterName + ", " : "")
				+ (gainShare != null ? "gainShare=" + gainShare + ", " : "")
				+ (isAutoCloseRule != null ? "isAutoCloseRule=" + isAutoCloseRule + ", " : "")
				+ (isDynamicQueue != null ? "isDynamicQueue=" + isDynamicQueue + ", " : "")
				+ (isEifoc != null ? "isEifoc=" + isEifoc + ", " : "")
				+ (isOdValid != null ? "isOdValid=" + isOdValid + ", " : "")
				+ (isPublic != null ? "isPublic=" + isPublic + ", " : "")
				+ (reason != null ? "reason=" + reason + ", "
						: "")
				+ (rulesPriority != null ? "rulesPriority=" + rulesPriority + ", "
						: "")
				+ (status != null
						? "status=" + status
								+ ", "
						: "")
				+ (updatedDate != null ? "updatedDate=" + updatedDate + ", " : "")
				+ (updatedUser != null ? "updatedUser=" + updatedUser + ", " : "")
				+ (whereClause != null ? "whereClause=" + whereClause + ", " : "")
				+ (shpNspInvFilterDetailsTbs != null ? "shpNspInvFilterDetailsTbs="
						+ shpNspInvFilterDetailsTbs.subList(0, Math.min(shpNspInvFilterDetailsTbs.size(), maxLen)) : "")
				+ "]";
	}
	
}