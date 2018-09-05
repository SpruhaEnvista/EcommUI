package com.envista.msi.rating.bean;

import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.*;

public class RatingQueueBean {

    private Long ratingQueueId;
    private Long maniestId;
    private Long gffId;
    private String customerCode;
    private String scacCode;
    private String contractName;
    private String service;
    private String accesorial1;
    private String accesorial2;
    private String accesorial3;
    private String accesorial4;
    private String accesorial5;
    private String billOption;
    private String currencyCode;
    private String shipperNumber;
    private int transitTime;
    private int verbose;
    private float totalWeight;
    private float totalActualWeight;
    private float totalQuantity;
    private Float billedMiles;
    private Float frtWeight;
    private String frtWeightUnits;
    private Float frtActualWeight;
    private String frtActualWeightUnits;
    private Long frtQyantity;
    private String frtQuantityUnits;
    private String dimUnits;
    private Float dimLength;
    private Float dimWidth;
    private Float dimHeight;
    private Date shipDate;
    private Date deliveryDate;
    private String shipperLocationCode;
    private String shipperCity;
    private String shipperState;
    private String shipperZip;
    private String shipperCountry;
    private String receiverLocationCode;
    private String receiverCity;
    private String receiverState;
    private String receiverZip;
    private String receiverCountry;
    private Boolean hundredWeight;
    private Long jobId;
    private String ratingStatus;
    private String createUser;
    private Date createDate;
    private Long carrierId;
    private Long parentId;
    private Integer rateStatus = 0;
    private String accessorialInfo;
    private Set<ParcelRateRequest.ServiceFlag> accessorials;
    private String revenueTier;
    private String trackingNumber;
    private String packageType;
    private String hwtIdentifier;
    private BigDecimal netAmount;
    private Long taskId;
    private String rateSetName;
    private String thresholdValue;
    private String thresholdType;
    private String serviceLevel;
    private String zone;
    private String senderBilledZipCode;
    private String receiverBilledZipCode;
    private String returnFlag;
    private String resiFlag;

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

    public Float getBilledMiles() {
        return billedMiles;
    }

    public void setBilledMiles(Float billedMiles) {
        this.billedMiles = billedMiles;
    }

    public Float getFrtWeight() {
        return frtWeight;
    }

    public void setFrtWeight(Float frtWeight) {
        this.frtWeight = frtWeight;
    }

    public String getFrtWeightUnits() {
        return frtWeightUnits;
    }

    public void setFrtWeightUnits(String frtWeightUnits) {
        this.frtWeightUnits = frtWeightUnits;
    }

    public Float getFrtActualWeight() {
        return frtActualWeight;
    }

    public void setFrtActualWeight(Float frtActualWeight) {
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

    public Float getDimLength() {
        return dimLength;
    }

    public void setDimLength(Float dimLength) {
        this.dimLength = dimLength;
    }

    public Float getDimWidth() {
        return dimWidth;
    }

    public void setDimWidth(Float dimWidth) {
        this.dimWidth = dimWidth;
    }

    public Float getDimHeight() {
        return dimHeight;
    }

    public void setDimHeight(Float dimHeight) {
        this.dimHeight = dimHeight;
    }

    public void setAccessorialArray(Set<ParcelRateRequest.ServiceFlag> serviceFlag) {
        this.accessorials = accessorials;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public Integer getRateStatus() {
        return rateStatus;
    }

    public void setRateStatus(Integer rateStatus) {
        this.rateStatus = rateStatus;
    }

    public String getAccessorialInfo() {
        return accessorialInfo;
    }

    public void setAccessorialInfo(String accessorialInfo) {
        this.accessorialInfo = accessorialInfo;
    }

    public String getSenderBilledZipCode() {
        return senderBilledZipCode;
    }

    public void setSenderBilledZipCode(String senderBilledZipCode) {
        this.senderBilledZipCode = senderBilledZipCode;
    }

    public String getReceiverBilledZipCode() {
        return receiverBilledZipCode;
    }

    public void setReceiverBilledZipCode(String receiverBilledZipCode) {
        this.receiverBilledZipCode = receiverBilledZipCode;
    }

    public Set<ParcelRateRequest.ServiceFlag> getAccessorials(){
        Set<ParcelRateRequest.ServiceFlag> serviceFlags = null;
        try{
            if(this.accessorials == null){
                if(this.accessorialInfo!=null && this.accessorialInfo.length()>0){
                    serviceFlags = new HashSet<>();
                    JSONArray accJsonArr = new JSONArray(this.accessorialInfo);
                    if(accJsonArr != null && accJsonArr.length() > 0){
                        for(int n = 0; n < accJsonArr.length(); n++) {
                            JSONObject accjson = accJsonArr.getJSONObject(n);
                            if(accjson != null) {
                                ParcelRateRequest.ServiceFlag serviceFlag = new ParcelRateRequest.ServiceFlag();
                                if(accjson.has("code")){
                                    serviceFlag.setCode(accjson.getString("code"));
                                }
                                if(accjson.has("netAmount")){
                                    serviceFlag.setNetAmount(accjson.getString("netAmount"));
                                }
                                if(accjson.has("weight")){
                                    serviceFlag.setWeight(accjson.getString("weight") != null ? new BigDecimal(accjson.getString("weight")) : new BigDecimal("0.0"));
                                }
                                if(accjson.has("weightUnit")){
                                    serviceFlag.setWeightUnit(accjson.getString("weightUnit"));
                                }
                                if(accjson.has("quantity")){
                                    serviceFlag.setQuantity("" + accjson.getInt("quantity") + "");
                                }
                                if(accjson.has("quantityUnit")){
                                    serviceFlag.setQuantityUnit(accjson.getString("quantityUnit"));
                                }
                                serviceFlags.add(serviceFlag);
                            }
                        }
                    }
                    this.accessorials = new HashSet<>(serviceFlags);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.accessorials;
    }

    public String getRevenueTier() {
        return revenueTier;
    }

    public void setRevenueTier(String revenueTier) {
        this.revenueTier = revenueTier;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getHwtIdentifier() {
        return hwtIdentifier;
    }

    public void setHwtIdentifier(String hwtIdentifier) {
        this.hwtIdentifier = hwtIdentifier;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getRateSetName() {
        return rateSetName;
    }

    public void setRateSetName(String rateSetName) {
        this.rateSetName = rateSetName;
    }

    public String getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(String thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public String getThresholdType() {
        return thresholdType;
    }

    public void setThresholdType(String thresholdType) {
        this.thresholdType = thresholdType;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
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
}
