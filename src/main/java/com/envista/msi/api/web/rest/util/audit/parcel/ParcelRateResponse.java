package com.envista.msi.api.web.rest.util.audit.parcel;

import org.apache.commons.lang.StringUtils;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sujit kumar on 23/06/2017.
 */

@XmlRootElement(name="RateResults")
@XmlAccessorType(XmlAccessType.NONE)
public class ParcelRateResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum RateError {
        INVALID_LICENSE("Invalid license key."),
        INVALID_CUSTOMER("Invalid customer code.");

        private final String value;
        private RateError(String value) { this.value = value; }
        public String getValue() { return value; }
    }
    public void setStatus(RateError err) {
        setStatusCode(err.ordinal());
        setStatusMessage(err.getValue());
    }

    @XmlElement(name="StatusCode")
    private Integer statusCode = 0;

    @XmlElement(name="StatusMessage")
    private String statusMessage = "Rating process completed without errors.";

    @XmlElementWrapper(name="StatusMessages")
    @XmlElement(name="StatusMessage")
    private List<StatusMessage> statusMessages = new ArrayList<StatusMessage>();

    @XmlElementWrapper(name="PriceSheets")
    @XmlElement(name="PriceSheet")
    private List<PriceSheet> priceSheets = new ArrayList<PriceSheet>();

    @XmlAccessorType(XmlAccessType.NONE)
    public static class PriceSheet implements Serializable {
        private static final long serialVersionUID = 1L;

        // Attributes
        @XmlAttribute
        private String id; // Copy of the id passed from the rate request

        @XmlAttribute
        private String type; // enum

        @XmlAttribute
        private String chargeModel; // vc?

        @XmlAttribute(name="isSelected")
        private boolean selected = false;

        @XmlAttribute(name="isAllocated")
        private boolean allocated = false;

        @XmlAttribute
        private String currencyCode; // enum

        @XmlAttribute
        private String createDate; // mm/dd/yyyy hh:mm

        @XmlAttribute
        private String internalId;

        // Elements
        @XmlElement(name="AccessorialTotal")
        @XmlJavaTypeAdapter(type=BigDecimal.class, value=BigDecimalCurrencyAdapter.class)
        private BigDecimal accessorialTotal;

        @XmlElement(name="SubTotal")
        @XmlJavaTypeAdapter(type=BigDecimal.class, value=BigDecimalCurrencyAdapter.class)
        private BigDecimal subTotal;

        @XmlElement(name="Total")
        @XmlJavaTypeAdapter(type=BigDecimal.class, value=BigDecimalCurrencyAdapter.class)
        private BigDecimal total;

        @XmlElement(name="ContractId")
        private String contractId;

        @XmlElement(name="ContractName")
        private String contractName;

        @XmlElement(name="CarrierId")
        private String carrierId;

        @XmlElement(name="CarrierName")
        private String carrierName;

        @XmlElement(name="SCAC")
        private String scac;

        //@XmlElement(name="Mode")
        //private TransModeVO mode;

        @XmlElement(name="Service")
        private String service; // enum

        @XmlElement(name="Category")
        private String category;

        @XmlElement(name = "RateSet")
        private String rateSet;

        @XmlElement(name="Distance")
        private BigDecimal distance; // ####.#

        @XmlElement(name="TransitDays")
        private Integer transitDays;

        //@XmlElement(name="OriginServiceType")
        //private DirectionalityVO originServiceType;

        //@XmlElement(name="DestServiceType")
        //private DirectionalityVO destServiceType;

        // TODO: Distance Unit

        //private List<InsuranceType> insuranceTypes;

        // Address

        @XmlElementWrapper(name="Charges")
        @XmlElement(name="Charge")
        private List<Charge> charges = new ArrayList<Charge>();

        @XmlElement(name="Comments")
        private String comments;

        public String getCleanComments() {
            if (comments == null) return null;

            String cleanComment = "";
            for (String commentLine : comments.split("\n")) {
                cleanComment += StringUtils.substringAfterLast(commentLine, "00:") + "\n";
            }

            return cleanComment;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getChargeModel() {
            return chargeModel;
        }
        public void setChargeModel(String chargeModel) {
            this.chargeModel = chargeModel;
        }
        public boolean isSelected() {
            return selected;
        }
        public void setSelected(boolean selected) {
            this.selected = selected;
        }
        public boolean isAllocated() {
            return allocated;
        }
        public void setAllocated(boolean allocated) {
            this.allocated = allocated;
        }
        public String getCurrencyCode() {
            return currencyCode;
        }
        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }
        public String getCreateDate() {
            return createDate;
        }
        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
        public String getInternalId() {
            return internalId;
        }
        public void setInternalId(String internalId) {
            this.internalId = internalId;
        }
        public BigDecimal getAccessorialTotal() {
            return accessorialTotal;
        }
        public void setAccessorialTotal(BigDecimal accessorialTotal) {
            this.accessorialTotal = accessorialTotal;
        }
        public BigDecimal getSubTotal() {
            return subTotal;
        }
        public void setSubTotal(BigDecimal subTotal) {
            this.subTotal = subTotal;
        }
        public BigDecimal getTotal() {
            return total;
        }
        public void setTotal(BigDecimal total) {
            this.total = total;
        }
        public String getContractId() {
            return contractId;
        }
        public void setContractId(String contractId) {
            this.contractId = contractId;
        }
        public String getContractName() {
            return contractName;
        }
        public void setContractName(String contractName) {
            this.contractName = contractName;
        }
        public String getCarrierId() {
            return carrierId;
        }
        public void setCarrierId(String carrierId) {
            this.carrierId = carrierId;
        }
        public String getCarrierName() {
            return carrierName;
        }
        public void setCarrierName(String carrierName) {
            this.carrierName = carrierName;
        }
        public String getScac() {
            return scac;
        }
        public void setScac(String scac) {
            this.scac = scac;
        }
       /* public TransModeVO getMode() {
            return mode;
        }
        public void setMode(TransModeVO mode) {
            this.mode = mode;
        }*/
        public String getService() {
            return service;
        }
        public void setService(String service) {
            this.service = service;
        }

        public String getCategory() {
            return category;
        }

        public String getRateSet() {
            return rateSet;
        }

        public void setRateSet(String rateSet) {
            this.rateSet = rateSet;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public BigDecimal getDistance() {
            return distance;
        }
        public void setDistance(BigDecimal distance) {
            this.distance = distance;
        }
        public List<Charge> getCharges() {
            return charges;
        }
        public void setCharges(List<Charge> charges) {
            this.charges = charges;
        }
        public String getComments() {
            return comments;
        }
        public void setComments(String comments) {
            this.comments = comments;
        }
        public Integer getTransitDays() {
            return transitDays;
        }
        public void setTransitDays(Integer transitDays) {
            this.transitDays = transitDays;
        }
        /*public DirectionalityVO getOriginServiceType() {
            return originServiceType;
        }
        public void setOriginServiceType(DirectionalityVO originServiceType) {
            this.originServiceType = originServiceType;
        }
        public DirectionalityVO getDestServiceType() {
            return destServiceType;
        }
        public void setDestServiceType(DirectionalityVO destServiceType) {
            this.destServiceType = destServiceType;
        }*/
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class StatusMessage implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlElement(name="Severity")
        private StatusSeverity severity = StatusSeverity.INFO;

        @XmlElement(name="Message")
        private String message = "";

        public StatusMessage() { }

        public StatusMessage(StatusSeverity severity, String message) {
            super();
            this.severity = severity;
            this.message = message;
        }

        public StatusSeverity getSeverity() {
            return severity;
        }
        public void setSeverity(StatusSeverity severity) {
            this.severity = severity;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Charge implements Serializable {
        private static final long serialVersionUID = 1L;

        // Attributes
        @XmlAttribute
        private String sequenceNum="";

        @XmlAttribute
        private String type; // enum

        @XmlAttribute
        private String itemGroupId;

        @XmlElement(name="Name")
        private String name = "";

        // MSI uses description for some arbitrary custom logic, do not put anything in it
        @XmlElement(name="Description")
        private String description = "";

        @XmlElement(name="EdiCode")
        private String ediCode = ""; // enum

        @XmlElement(name="Amount")
        @XmlJavaTypeAdapter(type=BigDecimal.class, value=BigDecimalCurrencyAdapter.class)
        private BigDecimal amount = new BigDecimal("0.0");

        @XmlElement(name="Rate")
        @XmlJavaTypeAdapter(type=BigDecimal.class, value=BigDecimalCurrencyAdapter.class)
        private BigDecimal rate = new BigDecimal("0.0");

        @XmlElement(name="RateQualifier")
        private String rateQualifier = ""; // enum

        @XmlElement(name="Quantity")
        private BigDecimal quantity = new BigDecimal("0.0");

        @XmlElement(name="Weight")
        private BigDecimal weight = new BigDecimal("0.0"); // ###.##

        @XmlElement(name="DimWeight")
        private BigDecimal dimWeight = new BigDecimal("0.0"); // ###.#

        @XmlElement(name="DimDivisor")
        private BigDecimal dimDivisor = new BigDecimal("0.000"); // ###.#

        @XmlElement(name="FreightClass")
        private String freightClass = "0.0"; // ###.#

        @XmlElement(name="FakFreightClass")
        private BigDecimal fakFreightClass = new BigDecimal("0.0"); // ###.#

        //@XmlElement(name="IsMin")
        //private boolean min;

        //@XmlElement(name="IsMax")
        //private boolean max;

        //@XmlElement(name="IsNonTaxable")
        //private boolean nontaxable;

        public String getSequenceNum() {
            return sequenceNum;
        }
        public void setSequenceNum(String sequenceNum) {
            this.sequenceNum = sequenceNum;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getItemGroupId() {
            return itemGroupId;
        }
        public void setItemGroupId(String itemGroupId) {
            this.itemGroupId = itemGroupId;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getEdiCode() {
            return ediCode;
        }
        public void setEdiCode(String ediCode) {
            this.ediCode = ediCode;
        }
        public BigDecimal getAmount() {
            return amount;
        }
        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
        public BigDecimal getRate() {
            return rate;
        }
        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }
        public String getRateQualifier() {
            return rateQualifier;
        }
        public void setRateQualifier(String rateQualifier) {
            this.rateQualifier = rateQualifier;
        }
        public BigDecimal getWeight() {
            return weight;
        }
        public void setWeight(BigDecimal weight) {
            this.weight = weight;
        }
        public BigDecimal getDimWeight() {
            return dimWeight;
        }
        public void setDimWeight(BigDecimal dimWeight) {
            this.dimWeight = dimWeight;
        }
        public String getFreightClass() {
            return freightClass;
        }
        public void setFreightClass(String freightClass) {
            this.freightClass = freightClass;
        }
        public BigDecimal getFakFreightClass() {
            return fakFreightClass;
        }
        public void setFakFreightClass(BigDecimal fakFreightClass) {
            this.fakFreightClass = fakFreightClass;
        }
        public BigDecimal getQuantity() {
            return quantity;
        }
        public void setQuantity(BigDecimal quantity) {
            this.quantity = quantity;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getDimDivisor() {
            return dimDivisor;
        }

        public void setDimDivisor(BigDecimal dimDivisor) {
            this.dimDivisor = dimDivisor;
        }
    }

    public static enum ChargeType {
        ITEM("Freight"),
        DISCOUNT("Discount"),
        ACCESSORIAL_FUEL("Fuel Surcharge"),
        MG_MINMAX_ADJ("Min/Max Adjustment"),
        SMC_MIN_ADJ("Min/Max Adjustment"),
        ACCESSORIAL("ACCESSORIAL");

        private final String value;
        private ChargeType(String value) { this.value = value; }
        public String getValue() { return value; }

        public static ChargeType fromString(String unitStr) {
            if (unitStr != null) {
                for (ChargeType unit : ChargeType.values()) {
                    if (unitStr.equalsIgnoreCase(unit.value) || unitStr.equalsIgnoreCase(unit.getValue())) {
                        return unit;
                    }
                }
            }
            return null;
        }
    }

    public static enum StatusSeverity {
        //DEBUG("Debug"),
        INFO("Info"),
        WARN("Warning"),
        ERROR("Error");

        private final String value;
        private StatusSeverity(String value) { this.value = value; }
        public String getValue() { return value; }

        public static StatusSeverity fromString(String statusStr) {
            if (statusStr != null) {
                for (StatusSeverity status : StatusSeverity.values()) {
                    if (statusStr.equalsIgnoreCase(status.value) || statusStr.equalsIgnoreCase(status.getValue())) {
                        return status;
                    }
                }
            }
            return null;
        }
    }

	/*
	@XmlRootElement(name = "Insurance")
	public static class InsuranceType {
		@XmlAttribute
		private String type; // enum, Cargo, General, Liability

		@XmlAttribute
		private BigDecimal amount; // ###.##

		@XmlAttribute
		private String company;

		@XmlAttribute
		private String expirationDate;

		@XmlAttribute
		private String contactName;

		@XmlAttribute
		private String contactPhone;
	}
	*/

    public void addMessage(StatusSeverity severity, String message) {
        statusMessages.add( new StatusMessage(severity, message));
    }

    public Integer getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public List<PriceSheet> getPriceSheets() {
        return priceSheets;
    }
    public void setPriceSheets(List<PriceSheet> priceSheets) {
        this.priceSheets = priceSheets;
    }

    public List<StatusMessage> getStatusMessages() {
        return statusMessages;
    }
    public void setStatusMessages(List<StatusMessage> statusMessages) {
        this.statusMessages = statusMessages;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("FreightRateResults");
        res.append("\n  status: ").append(statusCode).append(" - ").append(statusMessage);

        for (PriceSheet p : priceSheets) {
            res.append("\n  Pricesheet "+p.getId());

            //res.append("\n    mode: "+p.getMode()).append(", service: ").append(p.getService()).append(", distance: ").append(p.getDistance()).append(", transitDays: ").append(p.getTransitDays());
            res.append("\n    contractId: ").append(p.getContractId()).append(", contractName: ").append(p.getContractName());
            res.append("\n    carrierId: ").append(p.getCarrierId()).append(", carrierName: ").append(p.getCarrierName()).append(", scac: ").append(p.getScac());

            res.append("\n    subtotal: ").append(p.getSubTotal());
            res.append("\n    total: ").append(p.getTotal());

            res.append("\n    Charges");
            for (Charge c : p.getCharges()) {
                res.append("\n      seq: ").append(c.getSequenceNum()).append(", type: ").append(p.getType());
                res.append("\n        code: ").append(c.getEdiCode()).append(", name: ").append(c.getName()).append(", desc: ").append(c.getDescription());
                res.append("\n        amount: ").append(c.getAmount()).append(", rate: ").append(c.getRate()).append(", rateQualifier: ").append(c.getRateQualifier());
                res.append("\n        quantity: ").append(c.getQuantity()).append(", weight: ").append(c.getWeight()).append(" fakFreightClass: ").append(c.getFakFreightClass());
            }

            res.append("\n    Comments\n").append(p.getComments());
        }

        res.append("\n  Messages");
        for (StatusMessage m : statusMessages) {
            res.append("\n    ").append(m.getSeverity().getValue()).append(" - ").append(m.getMessage());
        }

        return res.toString();
    }
}
