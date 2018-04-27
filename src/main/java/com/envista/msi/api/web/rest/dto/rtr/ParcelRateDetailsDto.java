package com.envista.msi.api.web.rest.dto.rtr;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Sujit kumar on 11/04/2018.
 */
public class ParcelRateDetailsDto implements Serializable {
    private BigDecimal dimDivisor;
    private String shipperCategory;
    private BigDecimal ratedWeight;
    private String contractName;
    private BigDecimal fuelTablePercentage;
    private BigDecimal ratedBaseDiscount;
    private BigDecimal ratedEarnedDiscount;
    private BigDecimal ratedMinMaxAdjustment;
    private BigDecimal ratedFuelSurchargeDiscount;
    private BigDecimal ratedCustomFuelSurchargeDiscount;
    private BigDecimal ratedGrossFuel;
    private BigDecimal residentialSurchargeDiscount;
    private BigDecimal residentialSurchargeDiscountPercentage;
    private BigDecimal otherDiscount1;
    private BigDecimal otherDiscount2;
    private BigDecimal otherDiscount3;
    private BigDecimal accessorial1;
    private BigDecimal accessorial2;
    private BigDecimal accessorial3;
    private BigDecimal accessorial4;
    private BigDecimal deliveryAreaSurchargeDiscount;
    private String accessorial1Code;
    private String accessorial2Code;
    private String accessorial3Code;
    private String accessorial4Code;
    private BigDecimal freightCharge;
    private BigDecimal fuelSurcharge;

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
}
