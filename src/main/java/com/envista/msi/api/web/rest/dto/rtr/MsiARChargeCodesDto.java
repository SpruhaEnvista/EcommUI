package com.envista.msi.api.web.rest.dto.rtr;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Sujit kumar on 28/04/2018.
 */
public class MsiARChargeCodesDto implements Serializable {
    private Map<String, String> dasChargeCodes;
    private Map<String, String> lpsChargeCodes;

    public static MsiARChargeCodesDto getInstance(){
        return new MsiARChargeCodesDto();
    }

    public Map<String, String> getDasChargeCodes() {
        return dasChargeCodes;
    }

    public void setDasChargeCodes(Map<String, String> dasChargeCodes) {
        this.dasChargeCodes = dasChargeCodes;
    }

    public Map<String, String> getLpsChargeCodes() {
        return lpsChargeCodes;
    }

    public void setLpsChargeCodes(Map<String, String> lpsChargeCodes) {
        this.lpsChargeCodes = lpsChargeCodes;
    }
}
