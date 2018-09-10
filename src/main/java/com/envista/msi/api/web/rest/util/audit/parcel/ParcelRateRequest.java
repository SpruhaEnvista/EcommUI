package com.envista.msi.api.web.rest.util.audit.parcel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sujit kumar on 21/06/2017.
 */
@XmlRootElement(name="RateRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class ParcelRateRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "LicenseKey")
    private String licenseKey;

    @XmlElement(name = "RequestId")
    private String requestId;

    @XmlElement(name="Features")
    private Features features = null;

    // Batch
    @XmlElementWrapper(name="Shipments")
    @XmlElement(name="Shipment", required=true)
    private List<ParcelRateRequest.BatchShipment> shipments = new ArrayList<ParcelRateRequest.BatchShipment>();

    @XmlElement(name="Constraints", required=true)
    private ParcelRateRequest.Constraints constraints = null;

    @XmlElementWrapper(name="Items")
    @XmlElement(name="Item", required=true)
    private List<ParcelRateRequest.Item> items = null;

    @XmlElementWrapper(name="Events")
    @XmlElement(name="Event", required=true)
    private List<ParcelRateRequest.Event> events = null;

    @XmlElementWrapper(name="ReferenceNumbers")
    @XmlElement(name="ReferenceNumber", required=true)
    private List<ParcelRateRequest.ReferenceNumber> referenceNumbers = null;

    @XmlElement(name="BilledMiles")
    private String billedMiles;

    @XmlAccessorType(XmlAccessType.NONE)
    public static class BatchShipment implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute(name="id")
        private String id = "";

        @XmlElement(name="Constraints", required=true)
        private Constraints constraints = new Constraints();

        @XmlElement(name="RevenueTier")
        private RevenueTier revenueTier = new RevenueTier();

        @XmlElement(name = "Shipper")
        private Shipper shipper = new Shipper();

        @XmlElement(name="Features")
        private Features features = new Features();

        @XmlElement(name="Weight")
        private Weight weight = new Weight();

        @XmlElement(name="ActualWeight")
        private Weight actualWeight = new Weight();

        @XmlElement(name="Quantity")
        private Quantity quantity = new Quantity();

        @XmlElement(name="BilledMiles")
        private String billedMiles;

        @XmlElementWrapper(name="Items")
        @XmlElement(name="Item", required=true)
        private List<Item> items = new ArrayList<Item>();

        @XmlElementWrapper(name="Events")
        @XmlElement(name="Event", required=true)
        private List<Event> events = new ArrayList<Event>();

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public Constraints getConstraints() {
            return constraints;
        }
        public void setConstraints(Constraints constraints) {
            this.constraints = constraints;
        }
        public Weight getWeight() {
            return weight;
        }
        public void setWeight(Weight weight) {
            this.weight = weight;
        }
        public Weight getActualWeight() {
            return actualWeight;
        }
        public void setActualWeight(Weight actualWeight) {
            this.actualWeight = actualWeight;
        }
        public Quantity getQuantity() {
            return quantity;
        }
        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }
        public List<Item> getItems() {
            return items;
        }
        public void setItems(List<Item> items) {
            this.items = items;
        }
        public List<Event> getEvents() {
            return events;
        }
        public void setEvents(List<Event> events) {
            this.events = events;
        }
        public String getBilledMiles() {
            return billedMiles;
        }
        public void setBilledMiles(String billedMiles) {
            this.billedMiles = billedMiles;
        }

        public Shipper getShipper() {
            return shipper;
        }

        public void setShipper(Shipper shipper) {
            this.shipper = shipper;
        }

        public RevenueTier getRevenueTier() {
            return revenueTier;
        }
        public void setRevenueTier(RevenueTier revenueTier) {
            this.revenueTier = revenueTier;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();

            res.append("Shipment: "+getId());
            res.append("\n  customer: ").append(getConstraints().getCustomerCode());
            if (getConstraints().getCarrier() != null)
                res.append("\n  carrier: ").append(getConstraints().getCarrier().getScac());
            res.append("\n  mode: ").append(getConstraints().getMode());
            res.append("\n  service: ").append(getConstraints().getService());
            res.append("\n  billOption: ").append(getConstraints().getBillOption());
            res.append("\n  currency: ").append(getConstraints().getCurrency());
            res.append("\n  rateSet: ").append(getConstraints().getRateSet());

            res.append("\n  serviceFlags: ");
            for (ServiceFlag flag :getConstraints().getServiceFlags())
                res.append(flag.getCode()).append(", ");

            res.append("\n  items:");
            for (Item item :getItems()) {
                res.append("\n    ").append(item.getSequence()).append(": class ").append(item.getFreightClass());
                if (item.getWeight() != null)
                    res.append(", bill weight ").append(item.getWeight().getWeight()).append(item.getWeight().getUnits());
                if (item.getActualWeight() != null)
                    res.append(", actual weight ").append(item.getActualWeight().getWeight()).append(item.getActualWeight().getUnits());
                if (item.getQuantity() != null)
                    res.append(", qty ").append(item.getQuantity().getQuantity()).append(item.getQuantity().getUnits());
                if (item.getContainer() != null)
                    res.append(", equip ").append(item.getContainer());
                if (item.getCommodity() != null)
                    res.append(", commodity ").append(item.getCommodity());
                if (item.getDimensions() != null)
                    res.append(", dimensions ").append(item.getDimensions());
            }

            res.append("\n  events:");
            for (Event event :getEvents()) {
                Location l = event.getLocation();
                res.append("\n    ").append(event.getSequence()).append(": ").append(event.getType()).append(", ").append(event.getDate());
                res.append("\n      ").append(l.getLocationCode()).append(", ").append(l.getCompany()).append(", ").append(l.getCity()).append(", ").append(l.getState()).append(", ").append(l.getZip()).append(", ").append(l.getCountry()).append(l.getLaneID());
            }

            return res.toString();
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Features implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute(name="transitTime")
        private boolean transitTime = false;

        @XmlAttribute(name="verbose")
        private boolean verbose = false;

        public boolean isTransitTime() {
            return transitTime;
        }
        public void setTransitTime(boolean transitTime) {
            this.transitTime = transitTime;
        }
        public boolean isVerbose() {
            return verbose;
        }
        public void setVerbose(boolean verbose) {
            this.verbose = verbose;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Constraints implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlElement(name = "Customer")
        private String customerCode;

        @XmlElement(name = "Contract", required=true)
        private Contract contract = new Contract();

        @XmlElement(name = "Carrier", required=true)
        private Carrier carrier = new Carrier();

        @XmlElement(name = "Mode", required=true)
        private String mode;

        @XmlElement(name = "Service")
        private String service;

        @XmlElement(name = "ContractService")
        private String contractService; // PD / DP / PP / DD

        @XmlElementWrapper(name = "ServiceFlags")
        @XmlElement(name = "ServiceFlag", required=true)
        private List<ServiceFlag> serviceFlags = new ArrayList<ServiceFlag>();

        @XmlElement(name = "BillOption")
        private String billOption;

        @XmlElement(name = "Currency")
        private String currency;

        @XmlElement(name = "RateSet")
        private String rateSet;

        @XmlElement(name = "Zone")
        private String zone;

        @XmlElement(name = "ZoneOverride")
        private String zoneOverride = "";

        @XmlElement(name = "Return")
        private String returnFlag = "";

        @XmlElement(name = "Residential")
        private String resiFlag = "";

        @XmlElement(name = "WorldEaseNumber")
        private String worldEaseNum = "";

        @XmlElement(name = "ComToRes")
        private String comToRes = "";

        @XmlElement(name = "ReturnProgram")
        private String prpFlag = "";

        public Contract getContract() {
            return contract;
        }
        public void setContract(Contract contract) {
            this.contract = contract;
        }
        public Carrier getCarrier() {
            return carrier;
        }
        public void setCarrier(Carrier carrier) {
            this.carrier = carrier;
        }
        public String getMode() {
            return mode;
        }
        public void setMode(String mode) {
            this.mode = mode;
        }
        public List<ServiceFlag> getServiceFlags() {
            return serviceFlags;
        }
        public void setServiceFlags(List<ServiceFlag> serviceFlags) {
            this.serviceFlags = serviceFlags;
        }
        public String getCustomerCode() {
            return customerCode;
        }
        public void setCustomerCode(String customerCode) {
            this.customerCode = customerCode;
        }
        public String getService() {
            return service;
        }
        public void setService(String service) {
            this.service = service;
        }
        public String getContractService() {
            return contractService;
        }
        public void setContractService(String contractService) {
            this.contractService = contractService;
        }
        public String getBillOption() {
            return billOption;
        }
        public void setBillOption(String billOption) {
            this.billOption = billOption;
        }
        public String getCurrency() {
            return currency;
        }
        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getRateSet() {
            return rateSet;
        }

        public void setRateSet(String rateSet) {
            this.rateSet = rateSet;
        }

        public String getZone() {
            return zone;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }

        public String getZoneOverride() {
            return zoneOverride;
        }

        public void setZoneOverride(String zoneOverride) {
            this.zoneOverride = zoneOverride;
        }

        public String getReturnFlag() {
            return returnFlag;
        }

        public void setReturnFlag(String returnFlag) {
            this.returnFlag = returnFlag;
        }

        public String getResiFlag() {
            return resiFlag;
        }

        public void setResiFlag(String resiFlag) {
            this.resiFlag = resiFlag;
        }

        public String getWorldEaseNum() {
            return worldEaseNum;
        }

        public void setWorldEaseNum(String worldEaseNum) {
            this.worldEaseNum = worldEaseNum;
        }

        public String getComToRes() {
            return comToRes;
        }

        public void setComToRes(String comToRes) {
            this.comToRes = comToRes;
        }

        public String getPrpFlag() {
            return prpFlag;
        }

        public void setPrpFlag(String prpFlag) {
            this.prpFlag = prpFlag;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Contract implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute
        private String name = "";

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Carrier implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute
        private String scac = "";

        public String getScac() {
            return scac;
        }
        public void setScac(String scac) {
            this.scac = scac;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class ServiceFlag implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute
        private String code = "";

        @XmlAttribute
        private String netAmount;

        @XmlAttribute
        private BigDecimal weight;

        @XmlAttribute
        private String weightUnit;

        @XmlAttribute
        private String quantity;

        @XmlAttribute
        private String quantityUnit;

        @XmlAttribute
        private Long sequence;


        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getNetAmount() {
            return netAmount;
        }
        public void setNetAmount(String netAmount) {
            this.netAmount = netAmount;
        }
        public BigDecimal getWeight() {
            return weight;
        }
        public void setWeight(BigDecimal weight) {
            this.weight = weight;
        }
        public String getWeightUnit() {
            return weightUnit;
        }
        public void setWeightUnit(String weightUnit) {
            this.weightUnit = weightUnit;
        }
        public String getQuantity() {
            return quantity;
        }
        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
        public String getQuantityUnit() {
            return quantityUnit;
        }
        public void setQuantityUnit(String quantityUnit) {
            this.quantityUnit = quantityUnit;
        }

        public Long getSequence() {
            return sequence;
        }

        public void setSequence(Long sequence) {
            this.sequence = sequence;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Item implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute
        private Long sequence;

        @XmlAttribute
        private String freightClass; // #.#

        @XmlAttribute
        private String nmfcCode; // #####-#, deprecated by categoryCode

        @XmlAttribute
        private String categoryCode;

        /**
         * Unique id for this shipment. If the shipment is re-rated, the same id would be provided
         */
        @XmlElement(name="ItemId")
        private String itemId;

        @XmlElement(name="Container")
        private String container;

        @XmlElement(name="Commodity")
        private String commodity;

        @XmlElement(name="Weight")
        private Weight weight = new Weight();

        @XmlElement(name="ActualWeight")
        private Weight actualWeight = new Weight();

        @XmlElement(name="Quantity")
        private Quantity quantity = new Quantity();

        @XmlElement(name="Dimensions")
        private Dimensions dimensions = new Dimensions();

        public Long getSequence() {
            return sequence;
        }

        public void setSequence(Long sequence) {
            this.sequence = sequence;
        }
        public String getFreightClass() {
            return freightClass;
        }
        public void setFreightClass(String freightClass) {
            this.freightClass = freightClass;
        }
        public String getContainer() {
            return container;
        }
        public void setContainer(String container) {
            this.container = container;
        }
        public String getCommodity() {
            return commodity;
        }
        public void setCommodity(String commodity) {
            this.commodity = commodity;
        }
        public Weight getWeight() {
            return weight;
        }
        public void setWeight(Weight weight) {
            this.weight = weight;
        }
        public Quantity getQuantity() {
            return quantity;
        }
        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }
        public String getItemId() {
            return itemId;
        }
        public void setItemId(String itemId) {
            this.itemId = itemId;
        }
        public Weight getActualWeight() {
            return actualWeight;
        }
        public void setActualWeight(Weight actualWeight) {
            this.actualWeight = actualWeight;
        }
        public String getNmfcCode() {
            return nmfcCode;
        }
        public void setNmfcCode(String nmfcCode) {
            this.nmfcCode = nmfcCode;
        }
        public Dimensions getDimensions() {
            return dimensions;
        }
        public void setDimensions(Dimensions dimensions) {
            this.dimensions = dimensions;
        }
        public String getCategoryCode() {
            return categoryCode;
        }
        public void setCategoryCode(String categoryCode) {
            this.categoryCode = categoryCode;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Weight implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute
        private String units;

        @XmlValue
        private BigDecimal weight; // ###.##

        public Weight() {}

        public Weight(BigDecimal weight, String units) {
            this.units = units;
            this.weight = weight;
        }

        public String getUnits() {
            return units;
        }
        public void setUnits(String units) {
            this.units = units;
        }
        public BigDecimal getWeight() {
            return weight;
        }
        public void setWeight(BigDecimal weight) {
            this.weight = weight;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Quantity implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute
        private String units; // enum

        @XmlValue
        private BigDecimal quantity; // ###.0

        public Quantity() {}

        public Quantity(BigDecimal quantity, String units) {
            this.units = units;
            this.quantity = quantity;
        }

        public String getUnits() {
            return units;
        }
        public void setUnits(String units) {
            this.units = units;
        }
        public BigDecimal getQuantity() {
            return quantity;
        }
        public void setQuantity(BigDecimal quantity) {
            this.quantity = quantity;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Dimensions implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute
        private BigDecimal length;

        @XmlAttribute
        private BigDecimal width;

        @XmlAttribute
        private BigDecimal height;

        @XmlAttribute
        private String units; // enum

        public BigDecimal getLength() {
            return length;
        }

        public void setLength(BigDecimal length) {
            this.length = length;
        }

        public BigDecimal getWidth() {
            return width;
        }

        public void setWidth(BigDecimal width) {
            this.width = width;
        }

        public BigDecimal getHeight() {
            return height;
        }

        public void setHeight(BigDecimal height) {
            this.height = height;
        }

        public String getUnits() {
            return units;
        }

        public void setUnits(String units) {
            this.units = units;
        }

        @Override
        public String toString() {
            return "[length=" + length + ", width=" + width
                    + ", height=" + height + ", units=" + units + "]";
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Event implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute
        private Integer sequence;

        @XmlAttribute
        private String type;

        @XmlAttribute
        private String date; // mm/dd/yyyy hh:mm

        @XmlElement(name="Location")
        private Location location = new Location();

        public Integer getSequence() {
            return sequence;
        }
        public void setSequence(Integer sequence) {
            this.sequence = sequence;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getDate() {
            return date;
        }
        public void setDate(String date) {
            this.date = date;
        }
        public Location getLocation() {
            return location;
        }
        public void setLocation(Location location) {
            this.location = location;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Location implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlElement(name="LocationCode")
        private String locationCode;

        @XmlElement(name="Company")
        private String company;

        @XmlElement(name="City")
        private String city;

        @XmlElement(name="State")
        private String state;

        @XmlElement(name="Zip")
        private String zip;

        @XmlElement(name="Country")
        private String country; // 3-char code

        @XmlElement(name="LaneID")
        private String laneID;

        public Location() {}

        public Location(String locationCode, String company, String city, String state,
                        String zip, String country, String laneID) {
            super();
            this.locationCode = locationCode;
            this.company = company;
            this.city = city;
            this.state = state;
            this.zip = zip;
            this.country = country;
            this.laneID = laneID;
        }

        public String getLocationCode() {
            return locationCode;
        }
        public void setLocationCode(String locationCode) {
            this.locationCode = locationCode;
        }
        public String getCompany() {
            return company;
        }
        public void setCompany(String company) {
            this.company = company;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city = city;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state = state;
        }
        public String getZip() {
            return zip;
        }
        public void setZip(String zip) {
            this.zip = zip;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }

        public String getLaneID() {
            return laneID;
        }

        public void setLaneID(String laneID) {
            this.laneID = laneID;
        }


    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class ReferenceNumber implements Serializable {
        @XmlAttribute(name="type")
        private String type;

        @XmlAttribute(name="isPrimary")
        private String isPrimary;

        @XmlValue
        private String referenceNumber;

        public ReferenceNumber() {}

        public ReferenceNumber(String type, String isPrimary, String referenceNumber) {
            this.type = type;
            this.isPrimary = isPrimary;
            this.referenceNumber = referenceNumber;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIsPrimary() {
            return isPrimary;
        }

        public void setIsPrimary(String isPrimary) {
            this.isPrimary = isPrimary;
        }

        public String getReferenceNumber() {
            return referenceNumber;
        }

        public void setReferenceNumber(String referenceNumber) {
            this.referenceNumber = referenceNumber;
        }
    }

    public enum EventType {
        Pickup("Pickup"),
        Export("Export"),
        Import("Import"),
        Drop("Drop");

        private final String value;
        private EventType(String value) { this.value = value; }
        public String getValue() { return value; }
    }

    public Features getFeatures() {
        return features;
    }
    public void setFeatures(Features features) {
        this.features = features;
    }
    public String getLicenseKey() {
        return licenseKey;
    }
    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }
    public ParcelRateRequest.Constraints getConstraints() {
        return constraints;
    }
    public void setConstraints(ParcelRateRequest.Constraints constraints) {
        this.constraints = constraints;
    }
    public List<ParcelRateRequest.Item> getItems() {
        return items;
    }
    public void setItems(List<ParcelRateRequest.Item> items) {
        this.items = items;
    }
    public List<ParcelRateRequest.Event> getEvents() {
        return events;
    }
    public void setEvents(List<ParcelRateRequest.Event> events) {
        this.events = events;
    }
    public List<ParcelRateRequest.BatchShipment> getShipments() {
        return shipments;
    }
    public void setShipments(List<ParcelRateRequest.BatchShipment> shipments) {
        this.shipments = shipments;
    }
    public List<ParcelRateRequest.ReferenceNumber> getReferenceNumbers() {
        return referenceNumbers;
    }
    public void setReferenceNumbers(List<ParcelRateRequest.ReferenceNumber> referenceNumbers) {
        this.referenceNumbers = referenceNumbers;
    }
    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    public String getBilledMiles() {
        return billedMiles;
    }
    public void setBilledMiles(String billedMiles) {
        this.billedMiles = billedMiles;
    }
    // Warning: This should be run after any single-shipment rate requests are converted to batch form (inserted into the Shipments container)
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("FreightRateRequest");
        res.append("\n  licenseKey: ").append(getLicenseKey()).append(", transitTime: ").append(getFeatures().isTransitTime()).append(", verbose: ").append(getFeatures().isVerbose());

        // If non-batch request is made, convert it to a batch request
        if (getShipments() == null || getShipments().size() == 0) {
            ParcelRateRequest.BatchShipment s = new ParcelRateRequest.BatchShipment();
            s.setConstraints(getConstraints());
            s.setItems(getItems());
            s.setEvents(getEvents());

            res.append("\n"+s.toString());
        }
        else {
            for (ParcelRateRequest.BatchShipment s : getShipments()) {
                res.append("\n"+s.toString());
            }
        }

        return res.toString();
    }

    public String toXmlString(){
        String xmlString = null;
        try {
            JAXBContext context = JAXBContext.newInstance(ParcelRateRequest.class);
            StringWriter sw = new StringWriter();
            context.createMarshaller().marshal(this, sw);
            xmlString = sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Shipper implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute
        private String number = "";

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class RevenueTier implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlValue
        private String revenueTier = "";

        public String getRevenueTier() {
            return revenueTier;
        }

        public void setRevenueTier(String revenueTier) {
            this.revenueTier = revenueTier;
        }
    }
}
