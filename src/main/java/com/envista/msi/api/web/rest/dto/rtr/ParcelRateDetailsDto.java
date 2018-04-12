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
}
