package com.envista.msi.api.web.rest.util.audit.parcel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to parse parcel audit response and having utility method to find charges etc.
 *
 * Created by Sujit kumar on 21/06/2017.
 */
public class ParcelRateResponseParser {

    /**
     * To marshal XML response string into ParcelRateResponse object type.
     * @param xmlString
     * @return
     * @throws JAXBException
     */
    public static ParcelRateResponse parse(String xmlString) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ParcelRateResponse.class);
        return (ParcelRateResponse) jaxbContext.createUnmarshaller().unmarshal(new StringReader(xmlString));
    }

    /**
     * To find applied charge based on given chargeType in a price sheet.
     *
     * @param chargeType
     * @param priceSheet
     * @return
     */
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

    /**
     * To find applied charge based on given chargeType by comparing with EDI code of each charge in a price sheet.
     *
     * @param chargeType
     * @param priceSheet
     * @return
     */
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

    public static int getRatedDiscountCount(ParcelRateResponse.PriceSheet priceSheet){
        int ratedDiscountCount = 0;
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType())){
                    ratedDiscountCount++;
                }
            }
        }
        return ratedDiscountCount;
    }

    public static List<ParcelRateResponse.Charge> getAllRatedDiscountForFedEx(ParcelRateResponse.PriceSheet priceSheet){
        List<ParcelRateResponse.Charge> ratedDiscounts = null;
        if(priceSheet != null && priceSheet.getCharges() != null){
            ratedDiscounts = new ArrayList<>();
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType())){
                    ratedDiscounts.add(charge);
                }
            }
        }
        return ratedDiscounts;
    }

    public static List<ParcelRateResponse.Charge> getRatedDiscountForFedEx(ParcelRateResponse.PriceSheet priceSheet){
        List<ParcelRateResponse.Charge> ratedDiscounts = null;
        if(priceSheet != null && priceSheet.getCharges() != null){
            ratedDiscounts = new ArrayList<>();
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType())
                        && !"Fuel Surcharge Discount".equalsIgnoreCase(charge.getName())){
                    ratedDiscounts.add(charge);
                }
            }
        }
        return ratedDiscounts;
    }

    public static BigDecimal getRatedSurchargeDiscount(ParcelRateResponse.PriceSheet priceSheet){
        BigDecimal ratedSurchargeDiscount =new BigDecimal("0.000");
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType()) && "Fuel Surcharge Discount".equalsIgnoreCase(charge.getName())){
                    ratedSurchargeDiscount=charge.getAmount();
                    break;
                }
            }
        }
        return ratedSurchargeDiscount;
    }


    public static BigDecimal getFuelTablePercentage(ParcelRateResponse.PriceSheet priceSheet){
        BigDecimal fuelTablePerc = new BigDecimal("0.000");

        if(priceSheet != null && priceSheet.getComments() != null){
            String comments = priceSheet.getComments();
            if(comments != null && comments != null){
                if(comments.contains("Gross fuel surcharge is")){
                    comments = comments.substring(comments.indexOf("Gross fuel surcharge is"));
                    if(comments.contains("at")){
                        comments = comments.substring(comments.indexOf("at")+2, comments.indexOf("%"));
                    }
                    fuelTablePerc=new BigDecimal(comments.trim());
                }
            }
        }
        return fuelTablePerc;
    }

}
