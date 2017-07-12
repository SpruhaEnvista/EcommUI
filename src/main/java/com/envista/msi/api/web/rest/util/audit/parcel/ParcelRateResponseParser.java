package com.envista.msi.api.web.rest.util.audit.parcel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;

/**
 * Created by Sujit kumar on 21/06/2017.
 */
public class ParcelRateResponseParser {
    public static ParcelRateResponse parse(String xmlString) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ParcelRateResponse.class);
        return (ParcelRateResponse) jaxbContext.createUnmarshaller().unmarshal(new StringReader(xmlString));
    }

    public static ParcelRateResponse.Charge findChargeByType(String chargeType, ParcelRateResponse.PriceSheet priceSheet){
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && chargeType.equalsIgnoreCase(charge.getType())){
                    return charge;
                }
            }
        }
        return null;
    }

    public static ParcelRateResponse.Charge findChargeByEDICodeInResponse(String chargeType, ParcelRateResponse.PriceSheet priceSheet){
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && chargeType.equalsIgnoreCase(charge.getEdiCode())){
                    return charge;
                }
            }
        }
        return null;
    }
}
