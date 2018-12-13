package com.envista.msi.api.web.rest.util.audit.parcel;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.ParcelRateDetailsDto;
import com.envista.msi.rating.bean.AccessorialDto;

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

        if (xmlString != null && xmlString.length() > 0) {
            JAXBContext jaxbContext = JAXBContext.newInstance(ParcelRateResponse.class);
            return (ParcelRateResponse) jaxbContext.createUnmarshaller().unmarshal(new StringReader(xmlString));
        }
        return null;
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

    /**
     * This method is used to get the base discount applied for a shipment.
     * @param priceSheet
     * @return
     */
    public static BigDecimal getSumOfFreightDiscount(ParcelRateResponse.PriceSheet priceSheet){
        BigDecimal freightDiscount = new BigDecimal("0.000");
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType()) && charge.getName() != null && charge.getName() != null
                        && !"Spend Discount".equalsIgnoreCase(charge.getName()) && (charge.getName().contains("Base")|| "Custom Net Rate Discount".equalsIgnoreCase(charge.getName()))){
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

    public static ParcelRateResponse.Charge getResidentialSurcharge(ParcelRateResponse.PriceSheet priceSheet){
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && "ACCESSORIAL".equalsIgnoreCase(charge.getType())
                    && charge.getName() != null && "Residential Surcharge".equalsIgnoreCase(charge.getName())){
                    return charge;
                }
            }
        }
        return null;
    }

    public static ParcelRateResponse.Charge getResidentialSurchargeDiscount(ParcelRateResponse.PriceSheet priceSheet){
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && "DISCOUNT".equalsIgnoreCase(charge.getType())
                        && charge.getName() != null && "Residential Surcharge Discount".equalsIgnoreCase(charge.getName())){
                    return charge;
                }
            }
        }
        return null;
    }

    public static List<ParcelRateResponse.Charge> getAllOtherDiscounts(ParcelRateResponse.PriceSheet priceSheet) {
        List<ParcelRateResponse.Charge> discountCharges = null;
        if(priceSheet != null && priceSheet.getCharges() != null){
            discountCharges = new ArrayList<>();
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && "DISCOUNT".equalsIgnoreCase(charge.getType()) && charge.getName() != null
                        && !"Residential Surcharge Discount".equalsIgnoreCase(charge.getName())
                        && !"Fuel Surcharge Discount".equalsIgnoreCase(charge.getName())
                        && !"Custom Fuel Surcharge Discount".equalsIgnoreCase(charge.getName())
                        && !"Spend Discount".equalsIgnoreCase(charge.getName())
                        && !"Custom Net Rate Discount".equalsIgnoreCase(charge.getName())
                        && !charge.getName().contains("Base")
                        ){
                    discountCharges.add(charge);
                }
            }
        }
        return discountCharges;
    }

    public static ParcelRateResponse.Charge getDeliveryAreaSurcharge(ParcelRateResponse.PriceSheet priceSheet){
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && "ACCESSORIAL".equalsIgnoreCase(charge.getType())
                        && charge.getName() != null && "Delivery Area Surcharge".equalsIgnoreCase(charge.getName())){
                    return charge;
                }
            }
        }
        return null;
    }

    public static List<ParcelRateResponse.Charge> getAccessorialCharges(ParcelRateResponse.PriceSheet priceSheet) {
        List<ParcelRateResponse.Charge> accessorialCharges = null;
        if(priceSheet != null && priceSheet.getCharges() != null) {
            accessorialCharges = new ArrayList<>();
            for (ParcelRateResponse.Charge charge : priceSheet.getCharges()) {
                if(charge != null && charge.getType() != null && ParcelRateResponse.ChargeType.ACCESSORIAL.name().equalsIgnoreCase(charge.getType())){
                    accessorialCharges.add(charge);
                }
            }
        }
        return accessorialCharges;
    }


    public static void  getRatedDasDiscount(ParcelRateResponse.PriceSheet priceSheet, List<ParcelRateResponse.Charge> charges){
        if(priceSheet != null && priceSheet.getCharges() != null) {
            for (ParcelRateResponse.Charge charge : priceSheet.getCharges()) {
                if (charge != null && charge.getType() != null && ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType())
                        && charge.getName() != null && "Delivery Area Surcharge Discount".equalsIgnoreCase(charge.getName())){
                    charges.add(charge);
                }
            }
        }

    }

    public static ParcelRateResponse.Charge getLargePachageSurcharge(ParcelRateResponse.PriceSheet priceSheet){
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && "ACCESSORIAL".equalsIgnoreCase(charge.getType())
                        && charge.getName() != null && "Large Package Surcharge".equalsIgnoreCase(charge.getName())){
                    return charge;
                }
            }
        }
        return null;
    }

    public static ParcelRateResponse.Charge getRatedExtendedDasDiscount(ParcelRateResponse.PriceSheet priceSheet) {
        if (priceSheet != null && priceSheet.getCharges() != null) {
            for (ParcelRateResponse.Charge charge : priceSheet.getCharges()) {
                if (charge != null && charge.getType() != null && ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType())
                        && charge.getName() != null && "Delivery Area Surcharge - Extended Discount".equalsIgnoreCase(charge.getName())) {
                    return charge;
                }
            }
        }
        return null;
    }

    public static ParcelRateResponse.Charge getRatedDeclaredValue(ParcelRateResponse.PriceSheet priceSheet) {
        if (priceSheet != null && priceSheet.getCharges() != null) {
            for (ParcelRateResponse.Charge charge : priceSheet.getCharges()) {
                if (charge != null && charge.getType() != null && ParcelRateResponse.ChargeType.ACCESSORIAL.name().equalsIgnoreCase(charge.getType())
                        && charge.getName() != null && "Declared Value".equalsIgnoreCase(charge.getName())) {
                    return charge;
                }
            }
        }
        return null;
    }

    public static void mapPercentageAndDis(ParcelRateDetailsDto rateDetails, ParcelRateResponse.PriceSheet priceSheet,
                                           List<ParcelRateResponse.Charge> mappedDscChanges, List<AccessorialDto> prevParentsRatesDtos) {


        AccessorialDto prevRatesdto = ParcelRatingUtil.findPrevRatesForSpecColumns(prevParentsRatesDtos);

        BigDecimal fuelTablePerc = ParcelRateResponseParser.getFuelTablePercentage(priceSheet);
        BigDecimal ratedGrossFuel = ParcelRateResponseParser.getRatedGrossFuel(priceSheet);

        rateDetails.setFuelTablePercentage(fuelTablePerc);
        rateDetails.setRatedGrossFuel(ratedGrossFuel.subtract(prevRatesdto.getRatedGrossFuel()));

        rateDetails.setRatedBaseDiscount(ParcelRateResponseParser.getSumOfFreightDiscount(priceSheet).subtract(prevRatesdto.getBaseDis()));
        rateDetails.setRatedEarnedDiscount(ParcelRateResponseParser.getSpendDiscount(priceSheet).subtract(prevRatesdto.getEarnedDis()));
        rateDetails.setRatedMinMaxAdjustment(ParcelRateResponseParser.getMinMaxAdjustment(priceSheet).subtract(prevRatesdto.getMinMaxDis()));

        List<ParcelRateResponse.Charge> resDiscounts = new ArrayList<>();
        getResidentialSurchargeDiscount(priceSheet, resDiscounts);
        for(ParcelRateResponse.Charge charge: resDiscounts  ){
            mappedDscChanges.add(charge);
            if(rateDetails.getResidentialSurchargeDiscount() == null)
                rateDetails.setResidentialSurchargeDiscount(charge.getAmount());
            else
                rateDetails.setResidentialSurchargeDiscount(rateDetails.getResidentialSurchargeDiscount().add(charge.getAmount()));

            rateDetails.setResidentialSurchargeDiscountPercentage(charge.getRate());
        }
        if(rateDetails.getResidentialSurchargeDiscount() != null)
            rateDetails.setResidentialSurchargeDiscount(rateDetails.getResidentialSurchargeDiscount().subtract(prevRatesdto.getResDis()));

        List<ParcelRateResponse.Charge> dasDiscounts = new ArrayList<>();
       getRatedDasDiscount(priceSheet, dasDiscounts);
        for(ParcelRateResponse.Charge charge: dasDiscounts  ){
            mappedDscChanges.add(charge);
            if(rateDetails.getDeliveryAreaSurchargeDiscount() == null)
                rateDetails.setDeliveryAreaSurchargeDiscount(charge.getAmount());
            else
                rateDetails.setDeliveryAreaSurchargeDiscount(rateDetails.getDeliveryAreaSurchargeDiscount().add(charge.getAmount()));
        }
        if(rateDetails.getDeliveryAreaSurchargeDiscount() != null)
            rateDetails.setDeliveryAreaSurchargeDiscount(rateDetails.getDeliveryAreaSurchargeDiscount().subtract(prevRatesdto.getDasDis()));


        rateDetails.setRatedFuelSurchargeDiscount(ParcelRateResponseParser.getRatedSurchargeDiscount(priceSheet).subtract(prevRatesdto.getFuleSurDis()));
        rateDetails.setRatedCustomFuelSurchargeDiscount(ParcelRateResponseParser.getRatedCustomSurchargeDiscount(priceSheet).subtract(prevRatesdto.getCustFuleSurDis()));

    }

    public static void getResidentialSurchargeDiscount(ParcelRateResponse.PriceSheet priceSheet, List<ParcelRateResponse.Charge> resDiscounts){
        if(priceSheet != null && priceSheet.getCharges() != null){
            for(ParcelRateResponse.Charge charge : priceSheet.getCharges()){
                if(charge != null && "DISCOUNT".equalsIgnoreCase(charge.getType())
                        && charge.getName() != null && "Residential Surcharge Discount".equalsIgnoreCase(charge.getName())){
                    resDiscounts.add(charge);
                }
            }
        }
    }

    public static ParcelRateResponse.Charge  getRatedDasDiscount(ParcelRateResponse.PriceSheet priceSheet){
        if(priceSheet != null && priceSheet.getCharges() != null) {
            for (ParcelRateResponse.Charge charge : priceSheet.getCharges()) {
                if (charge != null && charge.getType() != null && ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType())
                        && charge.getName() != null && "Delivery Area Surcharge Discount".equalsIgnoreCase(charge.getName())){
                    return  charge;
                }
            }
        }
        return null;
    }

    public static ParcelRateResponse.Charge findChargeByEDICodeInResForHwt(String chargeType, ParcelRateResponse.PriceSheet priceSheet, List<ParcelRateResponse.Charge> mappedAccChanges) {
        ParcelRateResponse.Charge finalCharge = null;
        BigDecimal netAmount = null;
        if (priceSheet != null && priceSheet.getCharges() != null) {
            for (ParcelRateResponse.Charge charge : priceSheet.getCharges()) {
                if (charge != null && chargeType.equalsIgnoreCase(charge.getEdiCode())) {
                    finalCharge = charge;
                    if (netAmount == null)
                        netAmount = charge.getAmount();
                    else
                        netAmount = netAmount.add(charge.getAmount());

                    mappedAccChanges.add(charge);

                }
            }

            if (finalCharge != null && netAmount != null)
                finalCharge.setAmount(netAmount);

        }

        return finalCharge;
    }
}
