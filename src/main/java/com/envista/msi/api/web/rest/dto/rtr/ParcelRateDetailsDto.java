package com.envista.msi.api.web.rest.dto.rtr;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Sujit kumar on 11/04/2018.
 */
@NamedStoredProcedureQuery(
        name = "ParcelRateDetailsDto.getRateDetails",
        procedureName = "SHP_GET_RATE_DETAILS_PROC",
        resultClasses = {ParcelRateDetailsDto.class},
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_entity_name", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_entity_ids", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_rated_charges", type = Void.class)
        }
)

@Entity
public class ParcelRateDetailsDto implements Serializable {
    @Id
    @Column(name = "AUDIT_RATE_DETAILS_ID")
    private Long id;

    @Column(name = "CREATE_USER")
    private String createUser;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "LAST_UPDATE_USER")
    private String lastUpdateUser;

    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    @Column(name = "DIM_DIVISOR")
    private BigDecimal dimDivisor;

    @Column(name = "SHIPPER_CATEGORY")
    private String shipperCategory;

    @Column(name = "RATED_WEIGHT")
    private BigDecimal ratedWeight;

    @Column(name = "CONTRACT_NAME")
    private String contractName;

    @Column(name = "FUEL_TABLE_PERC")
    private BigDecimal fuelTablePercentage;

    @Column(name = "RATED_BASE_DISCOUNT")
    private BigDecimal ratedBaseDiscount;

    @Column(name = "RATED_EARNED_DISCOUNT")
    private BigDecimal ratedEarnedDiscount;

    @Column(name = "RATED_MIN_MAX_ADJ")
    private BigDecimal ratedMinMaxAdjustment;

    @Column(name = "RATED_FUEL_SURCHARGE_DISC")
    private BigDecimal ratedFuelSurchargeDiscount;

    @Column(name = "RATED_CUST_FUEL_SURCHARGE_DISC")
    private BigDecimal ratedCustomFuelSurchargeDiscount;

    @Column(name = "RATED_GROSS_FUEL")
    private BigDecimal ratedGrossFuel;

    @Column(name = "RES_SURCHARGE_DSC")
    private BigDecimal residentialSurchargeDiscount;

    @Column(name = "RES_SURCHARGE_DSC_PERC")
    private BigDecimal residentialSurchargeDiscountPercentage;

    @Column(name = "OTHER_DSC_1")
    private BigDecimal otherDiscount1;

    @Column(name = "OTHER_DSC_2")
    private BigDecimal otherDiscount2;

    @Column(name = "OTHER_DSC_3")
    private BigDecimal otherDiscount3;

    @Column(name = "ACCESSORIAL_1")
    private BigDecimal accessorial1;

    @Column(name = "ACCESSORIAL_2")
    private BigDecimal accessorial2;

    @Column(name = "ACCESSORIAL_3")
    private BigDecimal accessorial3;

    @Column(name = "ACCESSORIAL_4")
    private BigDecimal accessorial4;

    @Column(name = "RATED_DAS_DSC")
    private BigDecimal deliveryAreaSurchargeDiscount;

    @Column(name = "ACCESSORIAL_1_CODE")
    private String accessorial1Code;

    @Column(name = "ACCESSORIAL_2_CODE")
    private String accessorial2Code;

    @Column(name = "ACCESSORIAL_3_CODE")
    private String accessorial3Code;

    @Column(name = "ACCESSORIAL_4_CODE")
    private String accessorial4Code;

    @Column(name = "FRT_CHARGE")
    private BigDecimal freightCharge;

    @Column(name = "FSC_CHARGE")
    private BigDecimal fuelSurcharge;

    @Column(name = "RTR_AMOUNT")
    private BigDecimal rtrAmount;

    @Column(name = "RTR_STATUS")
    private String rtrStatus;

    @Column(name = "HWT_IDENTIFIER")
    private String HwtIdentifier;

    @Column(name = "RATE_SET_NAME")
    private String rateSetName;

    @Transient
    private String flagged;


    public static ParcelRateDetailsDto getInstance(){
        return new ParcelRateDetailsDto();
    }

    public BigDecimal getDimDivisor() {
        return dimDivisor;
    }

    public void setDimDivisor(BigDecimal dimDivisor) {
        this.dimDivisor = dimDivisor;
    }

    public String getShipperCategory() {
        return shipperCategory;
    }

    public void setShipperCategory(String shipperCategory) {
        this.shipperCategory = shipperCategory;
    }

    public BigDecimal getRatedWeight() {
        return ratedWeight;
    }

    public void setRatedWeight(BigDecimal ratedWeight) {
        this.ratedWeight = ratedWeight;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public BigDecimal getFuelTablePercentage() {
        return fuelTablePercentage;
    }

    public void setFuelTablePercentage(BigDecimal fuelTablePercentage) {
        this.fuelTablePercentage = fuelTablePercentage;
    }

    public BigDecimal getRatedBaseDiscount() {
        return ratedBaseDiscount;
    }

    public void setRatedBaseDiscount(BigDecimal ratedBaseDiscount) {
        this.ratedBaseDiscount = ratedBaseDiscount;
    }

    public BigDecimal getRatedEarnedDiscount() {
        return ratedEarnedDiscount;
    }

    public void setRatedEarnedDiscount(BigDecimal ratedEarnedDiscount) {
        this.ratedEarnedDiscount = ratedEarnedDiscount;
    }

    public BigDecimal getRatedFuelSurchargeDiscount() {
        return ratedFuelSurchargeDiscount;
    }

    public void setRatedFuelSurchargeDiscount(BigDecimal ratedFuelSurchargeDiscount) {
        this.ratedFuelSurchargeDiscount = ratedFuelSurchargeDiscount;
    }

    public BigDecimal getRatedCustomFuelSurchargeDiscount() {
        return ratedCustomFuelSurchargeDiscount;
    }

    public void setRatedCustomFuelSurchargeDiscount(BigDecimal ratedCustomFuelSurchargeDiscount) {
        this.ratedCustomFuelSurchargeDiscount = ratedCustomFuelSurchargeDiscount;
    }

    public BigDecimal getRatedMinMaxAdjustment() {
        return ratedMinMaxAdjustment;
    }

    public void setRatedMinMaxAdjustment(BigDecimal ratedMinMaxAdjustment) {
        this.ratedMinMaxAdjustment = ratedMinMaxAdjustment;
    }

    public BigDecimal getRatedGrossFuel() {
        return ratedGrossFuel;
    }

    public void setRatedGrossFuel(BigDecimal ratedGrossFuel) {
        this.ratedGrossFuel = ratedGrossFuel;
    }

    public BigDecimal getResidentialSurchargeDiscount() {
        return residentialSurchargeDiscount;
    }

    public void setResidentialSurchargeDiscount(BigDecimal residentialSurchargeDiscount) {
        this.residentialSurchargeDiscount = residentialSurchargeDiscount;
    }

    public BigDecimal getResidentialSurchargeDiscountPercentage() {
        return residentialSurchargeDiscountPercentage;
    }

    public void setResidentialSurchargeDiscountPercentage(BigDecimal residentialSurchargeDiscountPercentage) {
        this.residentialSurchargeDiscountPercentage = residentialSurchargeDiscountPercentage;
    }

    public BigDecimal getOtherDiscount1() {
        return otherDiscount1;
    }

    public void setOtherDiscount1(BigDecimal otherDiscount1) {
        this.otherDiscount1 = otherDiscount1;
    }

    public BigDecimal getOtherDiscount2() {
        return otherDiscount2;
    }

    public void setOtherDiscount2(BigDecimal otherDiscount2) {
        this.otherDiscount2 = otherDiscount2;
    }

    public BigDecimal getOtherDiscount3() {
        return otherDiscount3;
    }

    public void setOtherDiscount3(BigDecimal otherDiscount3) {
        this.otherDiscount3 = otherDiscount3;
    }

    public BigDecimal getAccessorial1() {
        return accessorial1;
    }

    public void setAccessorial1(BigDecimal accessorial1) {
        this.accessorial1 = accessorial1;
    }

    public BigDecimal getAccessorial2() {
        return accessorial2;
    }

    public void setAccessorial2(BigDecimal accessorial2) {
        this.accessorial2 = accessorial2;
    }

    public BigDecimal getAccessorial3() {
        return accessorial3;
    }

    public void setAccessorial3(BigDecimal accessorial3) {
        this.accessorial3 = accessorial3;
    }

    public BigDecimal getAccessorial4() {
        return accessorial4;
    }

    public void setAccessorial4(BigDecimal accessorial4) {
        this.accessorial4 = accessorial4;
    }

    public BigDecimal getDeliveryAreaSurchargeDiscount() {
        return deliveryAreaSurchargeDiscount;
    }

    public void setDeliveryAreaSurchargeDiscount(BigDecimal deliveryAreaSurchargeDiscount) {
        this.deliveryAreaSurchargeDiscount = deliveryAreaSurchargeDiscount;
    }

    public String getAccessorial1Code() {
        return accessorial1Code;
    }

    public void setAccessorial1Code(String accessorial1Code) {
        this.accessorial1Code = accessorial1Code;
    }

    public String getAccessorial2Code() {
        return accessorial2Code;
    }

    public void setAccessorial2Code(String accessorial2Code) {
        this.accessorial2Code = accessorial2Code;
    }

    public String getAccessorial3Code() {
        return accessorial3Code;
    }

    public void setAccessorial3Code(String accessorial3Code) {
        this.accessorial3Code = accessorial3Code;
    }

    public String getAccessorial4Code() {
        return accessorial4Code;
    }

    public void setAccessorial4Code(String accessorial4Code) {
        this.accessorial4Code = accessorial4Code;
    }

    public BigDecimal getFreightCharge() {
        return freightCharge;
    }

    public void setFreightCharge(BigDecimal freightCharge) {
        this.freightCharge = freightCharge;
    }

    public BigDecimal getFuelSurcharge() {
        return fuelSurcharge;
    }

    public void setFuelSurcharge(BigDecimal fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public BigDecimal getRtrAmount() {
        return rtrAmount;
    }

    public void setRtrAmount(BigDecimal rtrAmount) {
        this.rtrAmount = rtrAmount;
    }

    public String getRtrStatus() {
        return rtrStatus;
    }

    public void setRtrStatus(String rtrStatus) {
        this.rtrStatus = rtrStatus;
    }

    public String getHwtIdentifier() {
        return HwtIdentifier;
    }

    public void setHwtIdentifier(String hwtIdentifier) {
        HwtIdentifier = hwtIdentifier;
    }

    public String getRateSetName() { return rateSetName; }

    public void setRateSetName(String rateSetName) { this.rateSetName = rateSetName; }

    public String getFlagged() {
        return flagged;
    }

    public void setFlagged(String flagged) {
        this.flagged = flagged;
    }
}
