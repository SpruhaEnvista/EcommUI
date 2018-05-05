package com.envista.msi.rating.util;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.MsiARChargeCodesDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.util.DateUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateRequest;
import com.envista.msi.rating.bean.RatingQueueBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Sujit kumar on 02/05/2018.
 */
public class ParcelRateRequestBuilder {
    private static final String RATE_REQUEST_EVENT_DATE_FORMAT = "MM/dd/yyyy hh:mm";

    public static ParcelRateRequest buildParcelRateRequest(RatingQueueBean ratingQueueBean, String licenseKey){
        ParcelRateRequest parcelRateRequest = new ParcelRateRequest();
        parcelRateRequest.setLicenseKey(licenseKey);

        if(ratingQueueBean != null){
            ParcelRateRequest.BatchShipment batchShipment = new ParcelRateRequest.BatchShipment();
            batchShipment.setId("1");
            String mode = "PCL";

            batchShipment.setBilledMiles("0.0");

            //ServiceFlags section
            List<ParcelRateRequest.ServiceFlag> serviceFlagList = new ArrayList<>();
            if(ratingQueueBean.getAccessorialArray() != null) {
                for(String acc : ratingQueueBean.getAccessorialArray()){
                    if(acc != null && !acc.isEmpty()){
                        ParcelRateRequest.ServiceFlag serviceFlag = new ParcelRateRequest.ServiceFlag();
                        serviceFlag.setCode(acc);
                        serviceFlagList.add(serviceFlag);
                    }
                }
            }

            //Constraints section
            ParcelRateRequest.Constraints constraints = new ParcelRateRequest.Constraints();
            ParcelRateRequest.Carrier carrier = new ParcelRateRequest.Carrier();
            carrier.setScac(ratingQueueBean.getScacCode());
            constraints.setCarrier(carrier);

            constraints.setBillOption(ratingQueueBean.getBillOption());
            constraints.setCurrency(ratingQueueBean.getCurrencyCode());
            constraints.setMode(mode);

            String serviceLevel = ratingQueueBean.getService();
            if(serviceLevel == null || serviceLevel.trim().isEmpty())
                throw new RuntimeException("Invalid Service Level for " + ratingQueueBean.getTrackingNumber());

            constraints.setService(serviceLevel);
            constraints.setCustomerCode(ratingQueueBean.getCustomerCode());
            constraints.setServiceFlags(serviceFlagList);
            batchShipment.setConstraints(constraints);

            ParcelRateRequest.RevenueTier revenueTier = new ParcelRateRequest.RevenueTier();
            String revenueValue = ratingQueueBean.getRevenueTier();
            if(revenueValue==null || revenueValue.equals("0")) revenueValue = "";
            revenueTier.setRevenueTier(revenueValue);
            batchShipment.setRevenueTier(revenueTier);

            ParcelRateRequest.Shipper shipper = new ParcelRateRequest.Shipper();
            shipper.setNumber(ratingQueueBean.getShipperNumber());
            batchShipment.setShipper(shipper);

            List<ParcelRateRequest.Item> items = new ArrayList<>();
            ParcelRateRequest.Weight weightObj = new ParcelRateRequest.Weight();
            if(ratingQueueBean.getFrtWeight() != null){
                weightObj.setWeight(BigDecimal.valueOf(ratingQueueBean.getFrtWeight()));
            }
            weightObj.setUnits(ratingQueueBean.getFrtWeightUnits());

            ParcelRateRequest.Weight actualWeightElement = new ParcelRateRequest.Weight();
            if(ratingQueueBean.getFrtActualWeight() != null) {
                actualWeightElement.setWeight(new BigDecimal(ratingQueueBean.getFrtActualWeight().toString()));
            }
            actualWeightElement.setUnits(ratingQueueBean.getFrtActualWeightUnits());

            ParcelRateRequest.Quantity quantityObj = new ParcelRateRequest.Quantity();
            if(ratingQueueBean.getFrtQyantity() != null){
                quantityObj.setQuantity(new BigDecimal(ratingQueueBean.getFrtQyantity().toString()));
            }
            quantityObj.setUnits(ratingQueueBean.getFrtQuantityUnits());

            ParcelRateRequest.Dimensions dimensionsObj = new ParcelRateRequest.Dimensions();
            try{ if(ratingQueueBean.getDimLength() != null) dimensionsObj.setLength(new BigDecimal(ratingQueueBean.getDimLength().toString())); }catch (Exception e){}
            try{ if(ratingQueueBean.getDimWidth() != null) dimensionsObj.setWidth(new BigDecimal(ratingQueueBean.getDimWidth().toString())); }catch (Exception e){}
            try{ if(ratingQueueBean.getDimHeight() != null) dimensionsObj.setHeight(new BigDecimal(ratingQueueBean.getDimHeight().toString())); }catch (Exception e){}
            dimensionsObj.setUnits(ratingQueueBean.getDimUnits());

            ParcelRateRequest.Item item = new ParcelRateRequest.Item();
            //removed parentid here in the sequence, because while making int value for sequence, it is generating some negating value.
            item.setSequence(1);
            item.setWeight(weightObj);
            item.setActualWeight(actualWeightElement);
            item.setQuantity(quantityObj);
            item.setDimensions(dimensionsObj);
            item.setContainer(ratingQueueBean.getPackageType());
            items.add(item);
            batchShipment.setItems(items);

            //Events section
            String pickupDate = "", dropDate = "", locationCode = "";
            if(ratingQueueBean.getShipDate() != null){
                pickupDate = DateUtil.format(ratingQueueBean.getShipDate(), RATE_REQUEST_EVENT_DATE_FORMAT);
            }
            String senderCountry =  (null == ratingQueueBean.getShipperCountry() || ratingQueueBean.getShipperCountry().isEmpty() ? "US" :  ratingQueueBean.getShipperCountry());
            String senderState =  (null == ratingQueueBean.getShipperState() ? "" :  ratingQueueBean.getShipperState());
            String senderCity =  (null == ratingQueueBean.getShipperCity() ? "" :  ratingQueueBean.getShipperCity());
            String senderZipCode =  (null == ratingQueueBean.getShipperZip() ? "" :  ratingQueueBean.getShipperZip());

            ParcelRateRequest.Event pickupDateEvent = new ParcelRateRequest.Event();
            pickupDateEvent.setSequence(1);
            pickupDateEvent.setType(ParcelRateRequest.EventType.Pickup.getValue());
            pickupDateEvent.setDate(pickupDate);

            ParcelRateRequest.Location senderLocation = new ParcelRateRequest.Location();
            senderLocation.setCity(senderCity);
            senderLocation.setState(senderState);
            senderLocation.setCountry(senderCountry);
            senderLocation.setZip(senderZipCode);
            senderLocation.setLocationCode(locationCode);
            pickupDateEvent.setLocation(senderLocation);

            if(ratingQueueBean.getDeliveryDate() != null){
                dropDate = DateUtil.format(ratingQueueBean.getDeliveryDate(), RATE_REQUEST_EVENT_DATE_FORMAT);
            }else{
                dropDate = pickupDate;
            }
            String receiverCountry =  (null == ratingQueueBean.getReceiverCountry() || ratingQueueBean.getReceiverCountry().isEmpty() ? "US" :  ratingQueueBean.getReceiverCountry());
            String receiverState =  (null == ratingQueueBean.getReceiverState() ? "" :  ratingQueueBean.getReceiverState());
            String receiverCity =  (null == ratingQueueBean.getReceiverCity() ? "" :  ratingQueueBean.getReceiverCity());
            String receiverZipCode = (null == ratingQueueBean.getReceiverZip() ? "" :  ratingQueueBean.getReceiverZip());

            ParcelRateRequest.Event dropDateEvent = new ParcelRateRequest.Event();
            dropDateEvent.setSequence(2);
            dropDateEvent.setType(ParcelRateRequest.EventType.Drop.getValue());
            dropDateEvent.setDate(dropDate);

            ParcelRateRequest.Location receiverLocation = new ParcelRateRequest.Location();
            receiverLocation.setCity(receiverCity);
            receiverLocation.setState(receiverState);
            receiverLocation.setCountry(receiverCountry);
            receiverLocation.setZip(receiverZipCode);
            receiverLocation.setLocationCode(locationCode);
            dropDateEvent.setLocation(receiverLocation);

            batchShipment.setEvents(Arrays.asList(pickupDateEvent, dropDateEvent));
            parcelRateRequest.getShipments().add(batchShipment);
        }
        return parcelRateRequest;
    }
}
