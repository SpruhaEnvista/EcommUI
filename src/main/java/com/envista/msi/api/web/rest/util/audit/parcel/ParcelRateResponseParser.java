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
                        && !"Fuel Surcharge Discount".equalsIgnoreCase(charge.getName()) && !"Custom Fuel Surcharge Discount".equalsIgnoreCase(charge.getName())){
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
                if(charge != null && ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType()) && ("Fuel Surcharge Discount".equalsIgnoreCase(charge.getName()))){
                    ratedSurchargeDiscount = ratedSurchargeDiscount.add(charge.getAmount());
                }
            }
        }
        return ratedSurchargeDiscount;
    }

    public static BigDecimal getRatedCustomSurchargeDiscount(ParcelRateResponse.PriceSheet priceSheet){
        BigDecimal ratedSurchargeDiscount =new BigDecimal("0.000");
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType()) && "Custom Fuel Surcharge Discount".equalsIgnoreCase(charge.getName())){
                    ratedSurchargeDiscount = ratedSurchargeDiscount.add(charge.getAmount());
                }
            }
        }
        return ratedSurchargeDiscount;
    }


    public static BigDecimal getFuelTablePercentage(ParcelRateResponse.PriceSheet priceSheet){
        BigDecimal fuelTablePerc = new BigDecimal("0.000");
        try{
            if(priceSheet != null && priceSheet.getComments() != null){
                String comments = priceSheet.getComments();
                if(comments != null && comments != null){
                    if(comments.contains("Gross fuel surcharge is")){
                        comments = comments.substring(comments.indexOf("Gross fuel surcharge is"));
                        if(comments.contains("at")){
                            comments = comments.substring(comments.indexOf("at") + 2, comments.indexOf("%"));
                            fuelTablePerc = new BigDecimal(comments.trim());
                        }

                    }
                }
            }
        }catch (Exception e){}
        return fuelTablePerc;
    }

    public static BigDecimal getRatedGrossFuel(ParcelRateResponse.PriceSheet priceSheet){
        BigDecimal ratedGrossFuel = new BigDecimal("0.000");
        try{
            if(priceSheet != null && priceSheet.getComments() != null){
                String comments = priceSheet.getComments();
                if(comments != null && comments != null){
                    if(comments.contains("Gross fuel surcharge is")){
                        comments = comments.substring(comments.indexOf("Gross fuel surcharge is"));
                        if(comments.contains("at")){
                            comments = comments.substring(comments.indexOf("$"), comments.indexOf("at"));
                            comments = comments.replace("$", "");
                            ratedGrossFuel = new BigDecimal(comments.trim());
                        }
                    }
                }
            }
        }catch (Exception e){}
        return ratedGrossFuel;
    }

    public static BigDecimal getSumOfFreightDiscount(ParcelRateResponse.PriceSheet priceSheet){
        BigDecimal freightDiscount = new BigDecimal("0.000");
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType()) && charge.getName() != null && !"Spend Discount".equalsIgnoreCase(charge.getName())
                        && charge.getName() != null && charge.getName().contains("Base")){
                    freightDiscount = freightDiscount.add(charge.getAmount());
                }
            }
        }
        return freightDiscount;
    }

    public static BigDecimal getSpendDiscount(ParcelRateResponse.PriceSheet priceSheet){
        BigDecimal spendDiscount = new BigDecimal("0.000");
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType()) && charge.getName() != null && "Spend Discount".equalsIgnoreCase(charge.getName())){
                    spendDiscount = spendDiscount.add(charge.getAmount());
                }
            }
        }
        return spendDiscount;
    }

    public static BigDecimal getMinMaxAdjustment(ParcelRateResponse.PriceSheet priceSheet){
        BigDecimal minMaxAjd = new BigDecimal("0.000");
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && "Min/Max Adjustment".equalsIgnoreCase(charge.getName())){
                    minMaxAjd = minMaxAjd.add(charge.getAmount());
                }
            }
        }
        return minMaxAjd;
    }
}
