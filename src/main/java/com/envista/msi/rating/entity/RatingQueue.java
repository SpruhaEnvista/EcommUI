package com.envista.msi.rating.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.Date;

@Entity
@Table(name = "SHP_RATING_QUEUE_TB")
public class RatingQueue {

    @Id
    @Column(name="SHP_RATING_QUEUE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ratingQueueId;

    @Column(name="MANIFEST_ID")
    private Long maniestId;

    @Column(name="GFF_ID")
    private Long gffId;

    @Column(name="CUSTOMER_CODE")
    private String customerCode;

    @Column(name="SCAC_CODE")
    private String scacCode;

    @Column(name="CONTRACT_NAME")
    private String contractName;

    @Column(name="SERVICE")
    private String service;

    @Column(name="ACCESORIAL1")
    private String accesorial1;

    @Column(name="ACCESORIAL2")
    private String accesorial2;

    @Column(name="ACCESORIAL3")
    private String accesorial3;

    @Column(name="ACCESORIAL4")
    private String accesorial4;

    @Column(name="ACCESORIAL5")
    private String accesorial5;

    @Column(name="BILL_OPTION")
    private String billOption;

    @Column(name="CURRENCY_CODE")
    private String currencyCode;

    @Column(name="SHIPPER_NUMBER")
    private String shipperNumber;

    @Column(name="TRANSIT_TIME")
    private int transitTime;

    @Column(name="VERBOSE")
    private int verbose;

    @Column(name="TOTAL_WEIGHT")
    private float totalWeight;

    @Column(name="TOTAL_ACTUAL_WEIGHT")
    private float totalActualWeight;

    @Column(name="TOTAL_QUANTITY")
    private float totalQuantity;

    @Column(name="BILLED_MILES")
    private Long billedMiles;

    @Column(name="FRT_WEIGHT")
    private float frtWeight;

    @Column(name="FRT_WEIGHT_UNITS")
    private String frtWeightUnits;

    @Column(name="FRT_ACTUAL_WEIGHT")
    private Long frtActualWeight;

    @Column(name="FRT_ACTUAL_WEIGHT_UNITS")
    private String frtActualWeightUnits;

    @Column(name="FRT_QUANTITY")
    private Long frtQyantity;

    @Column(name="FRT_QUANTITY_UNITS")
    private String frtQuantityUnits;

    @Column(name="DIM_UNITS")
    private String dimUnits;

    @Column(name="DIM_LENGTH")
    private Long dimLength;

    @Column(name="DIM_WIDTH")
    private Long dimWidth;

    @Column(name="DIM_HEIGHT")
    private Long dimHeight;

    @Column(name="SHIP_DATE")
    private Date shipDate;

    @Column(name="SHIPPER_LOCATION_CODE")
    private String shipperLocationCode;

    @Column(name="SHIPPER_CITY")
    private String shipperCity;

    @Column(name="SHIPPER_STATE")
    private String shipperState;

    @Column(name="SHIPPER_ZIP")
    private String shipperZip;

    @Column(name="SHIPPER_COUNTRY")
    private String shipperCountry;

    @Column(name = "RECEIVER_LOCATION_CODE")
    private String receiverLocationCode;

    @Column(name="RECEIVER_CITY")
    private String receiverCity;

    @Column(name="RECEIVER_STATE")
    private String receiverState;

    @Column(name="RECEIVER_ZIP")
    private String receiverZip;

    @Column(name="RECEIVER_COUNTRY")
    private String receiverCountry;

    @Column(name="IS_HUNDRED_WEIGHT")
    private Boolean hundredWeight;

    @Column(name="JOB_ID")
    private Long jobId;

    @Column(name="RATING_STATUS")
    private String ratingStatus;

    @Column(name="CREATE_USER")
    private String createUser;

    @Column(name="CREATE_DATE")
    private Date createDate;

    @Column(name="CARRIER_ID")
    private Long carrierId;

    @Column(name="PARENT_ID")
    private Long parentId;

    public Long getRatingQueueId() {
        return ratingQueueId;
    }

    public void setRatingQueueId(Long ratingQueueId) {
        this.ratingQueueId = ratingQueueId;
    }

    public Long getManiestId() {
        return maniestId;
    }

    public void setManiestId(Long maniestId) {
        this.maniestId = maniestId;
    }

    public Long getGffId() {
        return gffId;
    }

    public void setGffId(Long gffId) {
        this.gffId = gffId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getScacCode() {
        return scacCode;
    }

    public void setScacCode(String scacCode) {
        this.scacCode = scacCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAccesorial1() {
        return accesorial1;
    }

    public void setAccesorial1(String accesorial1) {
        this.accesorial1 = accesorial1;
    }

    public String getAccesorial2() {
        return accesorial2;
    }

    public void setAccesorial2(String accesorial2) {
        this.accesorial2 = accesorial2;
    }

    public String getAccesorial3() {
        return accesorial3;
    }

    public void setAccesorial3(String accesorial3) {
        this.accesorial3 = accesorial3;
    }

    public String getAccesorial4() {
        return accesorial4;
    }

    public void setAccesorial4(String accesorial4) {
        this.accesorial4 = accesorial4;
    }

    public String getAccesorial5() {
        return accesorial5;
    }

    public void setAccesorial5(String accesorial5) {
        this.accesorial5 = accesorial5;
    }

    public String getBillOption() {
        return billOption;
    }

    public void setBillOption(String billOption) {
        this.billOption = billOption;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getShipperNumber() {
        return shipperNumber;
    }

    public void setShipperNumber(String shipperNumber) {
        this.shipperNumber = shipperNumber;
    }

    public int getTransitTime() {
        return transitTime;
    }

    public void setTransitTime(int transitTime) {
        this.transitTime = transitTime;
    }

    public int getVerbose() {
        return verbose;
    }

    public void setVerbose(int verbose) {
        this.verbose = verbose;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public float getTotalActualWeight() {
        return totalActualWeight;
    }

    public void setTotalActualWeight(float totalActualWeight) {
        this.totalActualWeight = totalActualWeight;
    }

    public float getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(float totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Long getBilledMiles() {
        return billedMiles;
    }

    public void setBilledMiles(Long billedMiles) {
        this.billedMiles = billedMiles;
    }

    public float getFrtWeight() {
        return frtWeight;
    }

    public void setFrtWeight(float frtWeight) {
        this.frtWeight = frtWeight;
    }

    public String getFrtWeightUnits() {
        return frtWeightUnits;
    }

    public void setFrtWeightUnits(String frtWeightUnits) {
        this.frtWeightUnits = frtWeightUnits;
    }

    public Long getFrtActualWeight() {
        return frtActualWeight;
    }

    public void setFrtActualWeight(Long frtActualWeight) {
        this.frtActualWeight = frtActualWeight;
    }

    public String getFrtActualWeightUnits() {
        return frtActualWeightUnits;
    }

    public void setFrtActualWeightUnits(String frtActualWeightUnits) {
        this.frtActualWeightUnits = frtActualWeightUnits;
    }

    public Long getFrtQyantity() {
        return frtQyantity;
    }

    public void setFrtQyantity(Long frtQyantity) {
        this.frtQyantity = frtQyantity;
    }

    public String getFrtQuantityUnits() {
        return frtQuantityUnits;
    }

    public void setFrtQuantityUnits(String frtQuantityUnits) {
        this.frtQuantityUnits = frtQuantityUnits;
    }

    public String getDimUnits() {
        return dimUnits;
    }

    public void setDimUnits(String dimUnits) {
        this.dimUnits = dimUnits;
    }

    public Long getDimLength() {
        return dimLength;
    }

    public void setDimLength(Long dimLength) {
        this.dimLength = dimLength;
    }

    public Long getDimWidth() {
        return dimWidth;
    }

    public void setDimWidth(Long dimWidth) {
        this.dimWidth = dimWidth;
    }

    public Long getDimHeight() {
        return dimHeight;
    }

    public void setDimHeight(Long dimHeight) {
        this.dimHeight = dimHeight;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public String getShipperLocationCode() {
        return shipperLocationCode;
    }

    public void setShipperLocationCode(String shipperLocationCode) {
        this.shipperLocationCode = shipperLocationCode;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    public String getShipperState() {
        return shipperState;
    }

    public void setShipperState(String shipperState) {
        this.shipperState = shipperState;
    }

    public String getShipperZip() {
        return shipperZip;
    }

    public void setShipperZip(String shipperZip) {
        this.shipperZip = shipperZip;
    }

    public String getShipperCountry() {
        return shipperCountry;
    }

    public void setShipperCountry(String shipperCountry) {
        this.shipperCountry = shipperCountry;
    }

    public String getReceiverLocationCode() {
        return receiverLocationCode;
    }

    public void setReceiverLocationCode(String receiverLocationCode) {
        this.receiverLocationCode = receiverLocationCode;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    public Boolean getHundredWeight() {
        return hundredWeight;
    }

    public void setHundredWeight(Boolean hundredWeight) {
        this.hundredWeight = hundredWeight;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getRatingStatus() {
        return ratingStatus;
    }

    public void setRatingStatus(String ratingStatus) {
        this.ratingStatus = ratingStatus;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
