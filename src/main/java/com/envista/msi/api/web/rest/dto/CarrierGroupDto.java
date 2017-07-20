package com.envista.msi.api.web.rest.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by Sujit kumar on 17/07/2017.
 */

public class CarrierGroupDto {
    private Long carrierGroupId;

    private String getCarrierGroupName;

    private String carrierGroupDescription;

    private List<CarrierDto> carriers;

    private String carrierId;

    public CarrierGroupDto() {
    }

    public CarrierGroupDto(Long carrierGroupId) {
        this.carrierGroupId = carrierGroupId;
    }

    public CarrierGroupDto(Long carrierGroupId, String getCarrierGroupName, String carrierGroupDescription, List<CarrierDto> carriers, String carrierId) {
        this.carrierGroupId = carrierGroupId;
        this.getCarrierGroupName = getCarrierGroupName;
        this.carrierGroupDescription = carrierGroupDescription;
        this.carriers = carriers;
        this.carrierId = carrierId;
    }

    public Long getCarrierGroupId() {
        return carrierGroupId;
    }

    public void setCarrierGroupId(Long carrierGroupId) {
        this.carrierGroupId = carrierGroupId;
    }

    public String getGetCarrierGroupName() {
        return getCarrierGroupName;
    }

    public void setGetCarrierGroupName(String getCarrierGroupName) {
        this.getCarrierGroupName = getCarrierGroupName;
    }

    public String getCarrierGroupDescription() {
        return carrierGroupDescription;
    }

    public void setCarrierGroupDescription(String carrierGroupDescription) {
        this.carrierGroupDescription = carrierGroupDescription;
    }

    public List<CarrierDto> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<CarrierDto> carriers) {
        this.carriers = carriers;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CarrierGroupDto){
            CarrierGroupDto carrierGroupDto = (CarrierGroupDto) obj;
            return carrierGroupDto.carrierGroupId != null && this.carrierGroupId != null
                    && carrierGroupDto.carrierGroupId == this.carrierGroupId;
        }
        return false;
    }
}
