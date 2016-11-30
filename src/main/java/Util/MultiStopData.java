/**
 * 
 */
package Util;

import java.util.ArrayList;
import java.util.List;

import com.envista.msi.api.freight.ui.pojo.InBound;
import com.envista.msi.api.freight.ui.pojo.OutBound;
import com.envista.msi.api.freight.ui.pojo.PickupAddress;

/**
 * @author SANKER
 *
 */
public class MultiStopData {
	

	public static List<PickupAddress> getMultiStopData() {
		List<PickupAddress> pickupAddresses = new ArrayList<PickupAddress>();
		InBound inboundBegin = new InBound();
		inboundBegin.setDescription("Indescription ");
		inboundBegin.setEquipmentType("1");
		inboundBegin.setId(2);
		inboundBegin.setQty(4);
		inboundBegin.setServiceCode("1");
		inboundBegin.setUom(804);
		inboundBegin.setWeight(6);

		List<InBound> inboundBeginds = new ArrayList<InBound>();
		inboundBeginds.add(inboundBegin);
		PickupAddress pickupAddressB = new PickupAddress("name " , "line1 " , "line2 " , "city " ,
				"state ", "postal ", "country ", "airportCode " , "bol " , "po ", false,
				false, inboundBeginds, null, null);
		pickupAddressB.setStopNumber(0);
		pickupAddressB.setStartPoint(true);
		pickupAddresses.add(pickupAddressB);
		for (int i = 1; i < 3; i++) {

			InBound inbound = new InBound();
			inbound.setDescription("Indescription " + i);
			inbound.setEquipmentType("1");
			inbound.setId(i + 2);
			inbound.setQty(i);
			inbound.setServiceCode("1");
			inbound.setUom(0);
			inbound.setWeight(6 + i);

			OutBound outbound = new OutBound();
			outbound.setDescription("Indescription " + i);
			outbound.setEquipmentType("1");
			outbound.setId(i + 2);
			outbound.setQty(i);
			outbound.setServiceCode("1");
			outbound.setUom(0);
			outbound.setWeight(6 + i);

			List<InBound> inBounds = new ArrayList<InBound>();
			inBounds.add(inbound);
			List<OutBound> outBounds = new ArrayList<OutBound>();
			outBounds.add(outbound);
			PickupAddress pickupAddress = new PickupAddress("name " + i, "line1 " + i, "line2 " + i, "city " + i,
					"state " + i, "postal " + i, "country " + i, "airportCode " + i, "bol " + i, "po " + i, false,
					false, inBounds, outBounds, null);
			pickupAddress.setStopNumber(i);
			pickupAddresses.add(pickupAddress);
		}
		OutBound outbound = new OutBound();
		outbound.setDescription("Indescription ");
		outbound.setEquipmentType("1");
		outbound.setId(2);
		outbound.setQty(4);
		outbound.setServiceCode("1");
		outbound.setUom(0);
		outbound.setWeight(6);

		List<OutBound> outBounds = new ArrayList<OutBound>();
		outBounds.add(outbound);
		PickupAddress pickupAddress = new PickupAddress("name " , "line1 " , "line2 " , "city " ,
				"state ", "postal ", "country ", "airportCode " , "bol " , "po ", false,
				false, null, outBounds, null);
		pickupAddress.setStopNumber(pickupAddresses.size()+1);
		pickupAddress.setEndPoint(true);
		pickupAddresses.add(pickupAddress);
		return pickupAddresses;
	}
}
