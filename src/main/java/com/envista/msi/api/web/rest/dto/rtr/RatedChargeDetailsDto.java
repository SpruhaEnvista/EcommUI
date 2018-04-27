package com.envista.msi.api.web.rest.dto.rtr;

import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Sujit kumar on 27/04/2018.
 */
@NamedStoredProcedureQuery(
        name = "RatedChargeDetailsDto.getRatedChargeAmount",
        procedureName = "SHP_GET_RATED_AMOUNT_PROC",
        resultClasses = {RatedChargeDetailsDto.class},
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_parent_id", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_rated_charges", type = Void.class)
        }
)

//@Entity
public class RatedChargeDetailsDto implements Serializable {
    private Long id;
    private BigDecimal freightCharge;
    private BigDecimal fuelSurcharge;
    private String chargeClassificationCode;
    private String chargeDescriptionCode;
    private BigDecimal billedAmount;
    private BigDecimal ratedAmount;
    private BigDecimal baseDiscount;
    private BigDecimal accessorial1;
    private BigDecimal accessorial2;
    private BigDecimal accessorial3;
    private BigDecimal accessorial4;
    private String accessorial1Code;
    private String accessorial2Code;
    private String accessorial3Code;
    private String accessorial4Code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getChargeClassificationCode() {
        return chargeClassificationCode;
    }

    public void setChargeClassificationCode(String chargeClassificationCode) {
        this.chargeClassificationCode = chargeClassificationCode;
    }

    public String getChargeDescriptionCode() {
        return chargeDescriptionCode;
    }

    public void setChargeDescriptionCode(String chargeDescriptionCode) {
        this.chargeDescriptionCode = chargeDescriptionCode;
    }

    public BigDecimal getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(BigDecimal billedAmount) {
        this.billedAmount = billedAmount;
    }

    public BigDecimal getRatedAmount() {
        return ratedAmount;
    }

    public void setRatedAmount(BigDecimal ratedAmount) {
        this.ratedAmount = ratedAmount;
    }

    public BigDecimal getBaseDiscount() {
        return baseDiscount;
    }

    public void setBaseDiscount(BigDecimal baseDiscount) {
        this.baseDiscount = baseDiscount;
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
}
