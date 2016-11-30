package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SHP_CR_RPT_TB database table.
 * 
 */
@Entity
@Table(name="SHP_CR_RPT_TB")
@NamedQuery(name="ShpCrRptTb.findAll", query="SELECT s FROM ShpCrRptTb s")
public class ShpCrRptTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RPT_ID", unique=true, precision=22)
	private long rptId;

	@Column(name="ARCHIVE_USE_UNION_FOR_WITH", precision=22)
	private BigDecimal archiveUseUnionForWith;

	@Column(name="CARRIERS_SUPPORTED", length=200)
	private String carriersSupported;

	@Column(precision=22)
	private BigDecimal category;

	@Column(length=10)
	private String comments;

	@Column(name="CUSTOMERS_SUPPORTED", length=100)
	private String customersSupported;

	@Column(name="DEFAULT_REPORT_TYPE", precision=22)
	private BigDecimal defaultReportType;

	@Column(name="IS_ALL_CARRIERS_SUPPORTED", precision=22)
	private BigDecimal isAllCarriersSupported;

	@Column(name="IS_EXCEL_DATA_ONLY", precision=22)
	private BigDecimal isExcelDataOnly;

	@Column(name="IS_HEADERS_REQUIRED", precision=22)
	private BigDecimal isHeadersRequired;

	@Column(name="IS_INTERNAL", precision=22)
	private BigDecimal isInternal;

	@Column(name="IS_MULTISHEET_REPORT", precision=22)
	private BigDecimal isMultisheetReport;

	@Column(name="IS_RELEASED_TO_USER", precision=22)
	private BigDecimal isReleasedToUser;

	@Column(name="IS_SINGLE_RPT", precision=22)
	private BigDecimal isSingleRpt;

	@Column(name="IS_TESTING_ON", precision=22)
	private BigDecimal isTestingOn;

	@Column(name="MACRO_FILE", length=200)
	private String macroFile;

	@Column(length=150)
	private String queries;

	@Column(name="REPORT_NAME", length=100)
	private String reportName;

	@Column(name="REPORT_TYPES_SUPPORTED", length=30)
	private String reportTypesSupported;

	@Column(name="RPT_COMBINATION_TYPE", precision=22)
	private BigDecimal rptCombinationType;

	@Column(name="RPT_GRP_ID", precision=22)
	private BigDecimal rptGrpId;

	@Column(name="RPT_SPECIFIER", length=50)
	private String rptSpecifier;

	@Column(name="RUN_IN_MSI", precision=22)
	private BigDecimal runInMsi;

	@Column(name="RUN_IN_NEW_SERVER", precision=22)
	private BigDecimal runInNewServer;

	@Column(name="TOOL_TIP", length=2000)
	private String toolTip;

	//bi-directional many-to-one association to ShpCrRptDetailsTb
	@OneToMany(mappedBy="shpCrRptTb")
	private List<ShpCrRptDetailsTb> shpCrRptDetailsTbs;

	//bi-directional many-to-one association to ShpCrRptTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PARENT_RPT_ID")
	private ShpCrRptTb shpCrRptTb;

	//bi-directional many-to-one association to ShpCrRptTb
	@OneToMany(mappedBy="shpCrRptTb")
	private List<ShpCrRptTb> shpCrRptTbs;

	public ShpCrRptTb() {
	}

	public long getRptId() {
		return this.rptId;
	}

	public void setRptId(long rptId) {
		this.rptId = rptId;
	}

	public BigDecimal getArchiveUseUnionForWith() {
		return this.archiveUseUnionForWith;
	}

	public void setArchiveUseUnionForWith(BigDecimal archiveUseUnionForWith) {
		this.archiveUseUnionForWith = archiveUseUnionForWith;
	}

	public String getCarriersSupported() {
		return this.carriersSupported;
	}

	public void setCarriersSupported(String carriersSupported) {
		this.carriersSupported = carriersSupported;
	}

	public BigDecimal getCategory() {
		return this.category;
	}

	public void setCategory(BigDecimal category) {
		this.category = category;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCustomersSupported() {
		return this.customersSupported;
	}

	public void setCustomersSupported(String customersSupported) {
		this.customersSupported = customersSupported;
	}

	public BigDecimal getDefaultReportType() {
		return this.defaultReportType;
	}

	public void setDefaultReportType(BigDecimal defaultReportType) {
		this.defaultReportType = defaultReportType;
	}

	public BigDecimal getIsAllCarriersSupported() {
		return this.isAllCarriersSupported;
	}

	public void setIsAllCarriersSupported(BigDecimal isAllCarriersSupported) {
		this.isAllCarriersSupported = isAllCarriersSupported;
	}

	public BigDecimal getIsExcelDataOnly() {
		return this.isExcelDataOnly;
	}

	public void setIsExcelDataOnly(BigDecimal isExcelDataOnly) {
		this.isExcelDataOnly = isExcelDataOnly;
	}

	public BigDecimal getIsHeadersRequired() {
		return this.isHeadersRequired;
	}

	public void setIsHeadersRequired(BigDecimal isHeadersRequired) {
		this.isHeadersRequired = isHeadersRequired;
	}

	public BigDecimal getIsInternal() {
		return this.isInternal;
	}

	public void setIsInternal(BigDecimal isInternal) {
		this.isInternal = isInternal;
	}

	public BigDecimal getIsMultisheetReport() {
		return this.isMultisheetReport;
	}

	public void setIsMultisheetReport(BigDecimal isMultisheetReport) {
		this.isMultisheetReport = isMultisheetReport;
	}

	public BigDecimal getIsReleasedToUser() {
		return this.isReleasedToUser;
	}

	public void setIsReleasedToUser(BigDecimal isReleasedToUser) {
		this.isReleasedToUser = isReleasedToUser;
	}

	public BigDecimal getIsSingleRpt() {
		return this.isSingleRpt;
	}

	public void setIsSingleRpt(BigDecimal isSingleRpt) {
		this.isSingleRpt = isSingleRpt;
	}

	public BigDecimal getIsTestingOn() {
		return this.isTestingOn;
	}

	public void setIsTestingOn(BigDecimal isTestingOn) {
		this.isTestingOn = isTestingOn;
	}

	public String getMacroFile() {
		return this.macroFile;
	}

	public void setMacroFile(String macroFile) {
		this.macroFile = macroFile;
	}

	public String getQueries() {
		return this.queries;
	}

	public void setQueries(String queries) {
		this.queries = queries;
	}

	public String getReportName() {
		return this.reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportTypesSupported() {
		return this.reportTypesSupported;
	}

	public void setReportTypesSupported(String reportTypesSupported) {
		this.reportTypesSupported = reportTypesSupported;
	}

	public BigDecimal getRptCombinationType() {
		return this.rptCombinationType;
	}

	public void setRptCombinationType(BigDecimal rptCombinationType) {
		this.rptCombinationType = rptCombinationType;
	}

	public BigDecimal getRptGrpId() {
		return this.rptGrpId;
	}

	public void setRptGrpId(BigDecimal rptGrpId) {
		this.rptGrpId = rptGrpId;
	}

	public String getRptSpecifier() {
		return this.rptSpecifier;
	}

	public void setRptSpecifier(String rptSpecifier) {
		this.rptSpecifier = rptSpecifier;
	}

	public BigDecimal getRunInMsi() {
		return this.runInMsi;
	}

	public void setRunInMsi(BigDecimal runInMsi) {
		this.runInMsi = runInMsi;
	}

	public BigDecimal getRunInNewServer() {
		return this.runInNewServer;
	}

	public void setRunInNewServer(BigDecimal runInNewServer) {
		this.runInNewServer = runInNewServer;
	}

	public String getToolTip() {
		return this.toolTip;
	}

	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}

	public List<ShpCrRptDetailsTb> getShpCrRptDetailsTbs() {
		return this.shpCrRptDetailsTbs;
	}

	public void setShpCrRptDetailsTbs(List<ShpCrRptDetailsTb> shpCrRptDetailsTbs) {
		this.shpCrRptDetailsTbs = shpCrRptDetailsTbs;
	}

	public ShpCrRptDetailsTb addShpCrRptDetailsTb(ShpCrRptDetailsTb shpCrRptDetailsTb) {
		getShpCrRptDetailsTbs().add(shpCrRptDetailsTb);
		shpCrRptDetailsTb.setShpCrRptTb(this);

		return shpCrRptDetailsTb;
	}

	public ShpCrRptDetailsTb removeShpCrRptDetailsTb(ShpCrRptDetailsTb shpCrRptDetailsTb) {
		getShpCrRptDetailsTbs().remove(shpCrRptDetailsTb);
		shpCrRptDetailsTb.setShpCrRptTb(null);

		return shpCrRptDetailsTb;
	}

	public ShpCrRptTb getShpCrRptTb() {
		return this.shpCrRptTb;
	}

	public void setShpCrRptTb(ShpCrRptTb shpCrRptTb) {
		this.shpCrRptTb = shpCrRptTb;
	}

	public List<ShpCrRptTb> getShpCrRptTbs() {
		return this.shpCrRptTbs;
	}

	public void setShpCrRptTbs(List<ShpCrRptTb> shpCrRptTbs) {
		this.shpCrRptTbs = shpCrRptTbs;
	}

	public ShpCrRptTb addShpCrRptTb(ShpCrRptTb shpCrRptTb) {
		getShpCrRptTbs().add(shpCrRptTb);
		shpCrRptTb.setShpCrRptTb(this);

		return shpCrRptTb;
	}

	public ShpCrRptTb removeShpCrRptTb(ShpCrRptTb shpCrRptTb) {
		getShpCrRptTbs().remove(shpCrRptTb);
		shpCrRptTb.setShpCrRptTb(null);

		return shpCrRptTb;
	}

}