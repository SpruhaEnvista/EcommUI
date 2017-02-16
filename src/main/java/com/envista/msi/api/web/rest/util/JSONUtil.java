package com.envista.msi.api.web.rest.util;

import com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.auditactivity.*;
import com.envista.msi.api.web.rest.dto.dashboard.common.CommonValuesForChartDto;
import com.envista.msi.api.web.rest.dto.dashboard.common.NetSpendCommonDto;
import com.envista.msi.api.web.rest.dto.dashboard.common.NetSpendMonthlyChartDto;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendByModeDto;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendOverTimeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * @author SANKER
 *
 */
public class JSONUtil {
	static ArrayList<String> colorsList = new ArrayList<String>();
	static{
		if (colorsList.isEmpty()) {

			colorsList.add("#673FB4");
			colorsList.add("Green");
			colorsList.add("Red");
			colorsList.add("Magenta");// Pink
			colorsList.add("CornflowerBlue");
			colorsList.add("#1d3549");
			colorsList.add("SlateGray");
			colorsList.add("Brown");
			colorsList.add("Aqua");
			colorsList.add("Gold");
			colorsList.add("Maroon");
			colorsList.add("LawnGreen");
			colorsList.add("MediumOrchid");
			colorsList.add("BurlyWood");
			colorsList.add("Orange");
			colorsList.add("DarkOliveGreen");
			colorsList.add("DimGray");
			colorsList.add("Yellow");
			colorsList.add("Black");
			colorsList.add("#000080");
			colorsList.add("#C21D55");// Rose color
		}
	}

	/**
	 * @param obj
	 * @return JSONString
	 */
	final public static String ConvertObject2JSON(Object obj){
		String jsonInString = "{}";
		ObjectMapper mapper = new ObjectMapper();
		//Object to JSON in String
		try {
			jsonInString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// ignore exception
			e.printStackTrace();
		}
		return jsonInString;
	}
	
	/**
	 * @param <T>
	 * @param jsonInString
	 * @return Object
	 */
	final public static <T> T ConvertJSON2Object(String jsonInString, Class<T> type){
		ObjectMapper mapper = new ObjectMapper();
		T ret = null;
		try {
			ret = (T) mapper.readValue(jsonInString, type);
		} catch (Exception e) {
			// ignore exception
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 *
	 * @param netSpendDtoList
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject prepareNetSpendByModesJson(List<NetSpendByModeDto> netSpendDtoList) throws JSONException {
		JSONObject netSpendJsonData = new JSONObject();
		JSONArray valuesArray = null;
		JSONArray seriesArray = null;

		if(netSpendDtoList != null && netSpendDtoList.size() > 0){
			valuesArray = new JSONArray();
			seriesArray = new JSONArray();

			Map<String, HashMap<String, Double>> modesValuesMap = new LinkedHashMap<String, HashMap<String, Double>>();
			List<String> scortypeList = new ArrayList<String>();

			for(NetSpendByModeDto netSpendDto: netSpendDtoList){
				if(netSpendDto != null && netSpendDto.getSpend() != 0){
					String mode = netSpendDto.getModes();
					String scoreType = netSpendDto.getScoreType();
					Double spend = netSpendDto.getSpend();

					if (!scortypeList.contains(scoreType)) {
						scortypeList.add(scoreType);
					}

					if (modesValuesMap.containsKey(mode)) {
						modesValuesMap.get(mode).put(scoreType, spend);
					} else {
						HashMap<String, Double> tempHashMap = new HashMap<String, Double>();
						tempHashMap.put(scoreType, spend);
						modesValuesMap.put(mode, tempHashMap);
					}
				}
			}

			int counter = 1;
			Iterator<String> modesIterator = modesValuesMap.keySet().iterator();

			while (modesIterator.hasNext()) {
				JSONObject jsonObject = new JSONObject();
				String mode = modesIterator.next();
				HashMap<String, Double> scoreTypeMap = modesValuesMap.get(mode);
				Iterator<String> scoreTypesIterator = scoreTypeMap.keySet().iterator();

				jsonObject.put("name", mode);
				jsonObject.put("counter", counter);

				while (scoreTypesIterator.hasNext()) {
					String scoreType = scoreTypesIterator.next();
					double spend = scoreTypeMap.get(scoreType);
					jsonObject.put(scoreType, spend);
				}

				valuesArray.put(jsonObject);
				counter++;
			}

			String append = "\"";
			counter = 1;

			for (String scoreType : scortypeList) {
				scoreType = append + scoreType + append;
				String seriesId = append + "S" + counter + append;
				String object = "{\"id\":" + seriesId + ",\"name\":" + scoreType + ", \"data\": {\"field\":" + scoreType + "},style: { depth: 4, gradient: 0.9,fillColor: \"#65ABE8\" }}";
				seriesArray.put(new JSONObject(object));
				counter++;
			}

			netSpendJsonData.put("values", valuesArray);
			netSpendJsonData.put("series", seriesArray);
		}
		return netSpendJsonData;
	}

	public static JSONObject prepareMonthlyChartJson(List<NetSpendMonthlyChartDto> netSpendMonthlyChartDtos) throws JSONException {
		JSONObject returnJson = new JSONObject();
		JSONArray returnArray = null;
		int count = 0;
		long fromDate = 0;
		long toDate = 0;

		if(netSpendMonthlyChartDtos != null && netSpendMonthlyChartDtos.size() > 0){
			returnArray = new JSONArray();
			for(NetSpendMonthlyChartDto monthlyChartDto : netSpendMonthlyChartDtos){
				if(monthlyChartDto != null){
					JSONArray dataArray = new JSONArray();
					long dateInMilliSecs = 0L;
					if(monthlyChartDto.getBillDate() != null){
						dateInMilliSecs = monthlyChartDto.getBillDate().getTime();
					}

					dataArray.put(dateInMilliSecs);
					dataArray.put(monthlyChartDto.getAmount());
					returnArray.put(dataArray);
					if (count == 0) {
						fromDate = dateInMilliSecs;
					}
					toDate = dateInMilliSecs;
					dataArray = null;

					count++;
				}
			}
			if (fromDate == toDate) {
				toDate = toDate + 1;
			}

			returnJson.put("values", returnArray);
			returnJson.put("fromDate", fromDate);
			returnJson.put("toDate", toDate);
		}
		return returnJson;
	}

	public static JSONObject prepareNetSpendOverTimeJson(List<NetSpendOverTimeDto> netSpendOverTimeDtos) throws JSONException {
		JSONObject returnObject = new JSONObject();
		JSONArray valuesArray = null;
		JSONArray seriesArray = null;
		LinkedHashMap<String, HashMap<String, Double>> datesValuesMap = null;
		ArrayList<String> carriersList = null;

		if(netSpendOverTimeDtos != null && netSpendOverTimeDtos.size() > 0){
			valuesArray = new JSONArray();
			seriesArray = new JSONArray();
			datesValuesMap = new LinkedHashMap<String, HashMap<String, Double>>();
			carriersList = new ArrayList<String>();

			for(NetSpendOverTimeDto overTimeDto : netSpendOverTimeDtos){
				if(overTimeDto != null){
					String billDate = overTimeDto.getBillDate();
					String carrierName = overTimeDto.getCarrierName();
					long carrierId = overTimeDto.getCarrierId();
					Double spend = overTimeDto.getNetCharges();

					if (spend != 0) {
						String concatCarrier = carrierId+"#@#"+carrierName;
						if (!carriersList.contains(concatCarrier)) {
							carriersList.add(concatCarrier);
						}

						if (datesValuesMap.containsKey(billDate)) {
							datesValuesMap.get(billDate).put(carrierName, spend);
						} else {
							HashMap<String, Double> tempHashMap = new HashMap<String, Double>();
							tempHashMap.put(carrierName, spend);
							datesValuesMap.put(billDate, tempHashMap);
						}
					}
				}
			}

			// Bar Chart
			int counter = 1;
			Iterator<String> datesIterator = datesValuesMap.keySet().iterator();

			while (datesIterator.hasNext()) {
				JSONObject jsonObject = new JSONObject();

				String date = datesIterator.next();
				HashMap<String, Double> carrierFlagMap = datesValuesMap.get(date);

				Iterator<String> carrierFlagIterator = carrierFlagMap.keySet().iterator();

				jsonObject.put("name", date);
				jsonObject.put("counter", counter);

				while (carrierFlagIterator.hasNext()) {
					String carrierFlag = carrierFlagIterator.next();
					double spend = carrierFlagMap.get(carrierFlag);
					jsonObject.put(carrierFlag, spend);
				}

				valuesArray.put(jsonObject);
				counter++;
			}

			String append = "\"";
			counter = 1;

			for (String carrierDetails : carriersList) {
				String carrierId = carrierDetails.split("#@#")[0];
				String carrierName = carrierDetails.split("#@#")[1];

				carrierId = append + carrierId + append;
				carrierName = append + carrierName + append;
				String seriesId = append + "S" + counter + append;
				String object = "{\"id\":" + seriesId + ",\"name\":" + carrierName + ", \"carrierId\":" + carrierId + ",\"data\": {\"field\":" + carrierName
						+ "},\"type\":\"line\",\"style\":{\"lineWidth\": 2,smoothing: true, marker: {shape: \"circle\", width: 5},";

				object = object + "lineColor: \"" + colorsList.get(counter - 1) + "\"";
				object = object + "}}";

				seriesArray.put(new JSONObject(object));
				counter++;
				if(counter == colorsList.size()){
					counter = 1;
				}
			}

			returnObject.put("values", valuesArray);
			returnObject.put("series", seriesArray);
			returnObject.put("carrierDetails", new JSONArray().put(carriersList));
		}
		return returnObject;
	}

	public static JSONObject prepareCommonSpendJson(List<NetSpendCommonDto> spendList) throws JSONException {
		JSONObject returnObject = new JSONObject();
		JSONArray spendArray = null;
		HashMap<String, Double> spendMap = null;

		if(spendList != null && spendList.size() > 0){
			spendArray = new JSONArray();
			spendMap = new HashMap<>();

			for(NetSpendCommonDto taxSpend : spendList){
				if(taxSpend != null){
					String spendTypeName = taxSpend.getSpendTypeName();
					Double spend = taxSpend.getSpend();
					if (spend > 0) {
						if (spendMap.containsKey(spendTypeName)) {
							double spendAmount = spendMap.get(spendTypeName);
							spendAmount += spend;
							spendMap.put(spendTypeName, spendAmount);
						} else {
							spendMap.put(spendTypeName, spend);
						}
					}
				}
			}

			// Donut Chart
			Iterator<String> taxIterator = spendMap.keySet().iterator();
			while (taxIterator.hasNext()) {
				String tax = taxIterator.next();
				double spend = spendMap.get(tax);

				JSONObject taxesJson = new JSONObject();
				taxesJson.put("name", tax);
				taxesJson.put("value", spend);

				spendArray.put(taxesJson);
				taxesJson = null;
			}
			returnObject.put("donutChartvalues", spendArray);
		}
		return returnObject;
	}

	public static JSONObject prepareCommonJsonForChart(List<CommonValuesForChartDto> dataList) throws JSONException {
		JSONObject returnJson = new JSONObject();
		JSONArray returnArray = null;
		JSONObject statusJson = null;

		if(dataList != null && dataList.size() > 0){
			returnArray = new JSONArray();
			for(CommonValuesForChartDto chartData : dataList){
				if(chartData != null){
					statusJson = new JSONObject();
					statusJson.put("name", chartData.getName());
					statusJson.put("value", chartData.getValue());
					statusJson.put("id", chartData.getId());

					returnArray.put(statusJson);
					statusJson = null;
				}
			}
			returnJson.put("values", returnArray);
		}
		return returnJson;
	}

	public static JSONObject prepareInvoiceMethodScoreJson(List<InvoiceMethodScoreDto> dataList) throws JSONException {
		JSONObject returnJson = new JSONObject();
		JSONArray returnArray = null;
		JSONObject statusJson = null;

		if(dataList != null && dataList.size() > 0){
			returnArray = new JSONArray();
			for(InvoiceMethodScoreDto chartData : dataList){
				if(chartData != null){
					statusJson = new JSONObject();
					statusJson.put("name", chartData.getName());
					statusJson.put("value", chartData.getValue());
					statusJson.put("id", chartData.getInvoiceMethodId());

					returnArray.put(statusJson);
					statusJson = null;
				}
			}
			returnJson.put("values", returnArray);
		}
		return returnJson;
	}

	public static JSONObject prepareTopAccessorialSpendJson(List<AccessorialSpendDto> accessorialSpendList) throws JSONException {
		JSONObject returnObject = new JSONObject();
		JSONArray valuesArray = null;
		JSONArray seriesArray = null;
		LinkedHashMap<String, HashMap<String, Double>> datesValuesMap = null;
		ArrayList<String> serviceFlagList = null;

		if(accessorialSpendList != null){
			valuesArray = new JSONArray();
			seriesArray = new JSONArray();
			datesValuesMap = new LinkedHashMap<String, HashMap<String, Double>>();
			serviceFlagList = new ArrayList<String>();

			for(AccessorialSpendDto accSpend : accessorialSpendList){
				if(accSpend != null){
					String billDate = accSpend.getBillDate();
					String service = accSpend.getAccessorialName();
					Double spend = accSpend.getSpend();

					if (spend != 0) {

						if (!serviceFlagList.contains(service)) {
							serviceFlagList.add(service);
						}

						if (datesValuesMap.containsKey(billDate)) {
							datesValuesMap.get(billDate).put(service, spend);
						} else {
							HashMap<String, Double> tempHashMap = new HashMap<String, Double>();
							tempHashMap.put(service, spend);
							datesValuesMap.put(billDate, tempHashMap);
						}

					}
				}
			}

			// Bar Chart
			int counter = 1;
			Iterator<String> datesIterator = datesValuesMap.keySet().iterator();

			while (datesIterator.hasNext()) {
				JSONObject jsonObject = new JSONObject();

				String date = datesIterator.next();
				HashMap<String, Double> serviceFlagMap = datesValuesMap.get(date);

				Iterator<String> serviceFlagIterator = serviceFlagMap.keySet().iterator();

				jsonObject.put("name", date);
				jsonObject.put("counter", counter);

				while (serviceFlagIterator.hasNext()) {
					String serviceFlag = serviceFlagIterator.next();
					double spend = serviceFlagMap.get(serviceFlag);
					jsonObject.put(serviceFlag, spend);
				}

				valuesArray.put(jsonObject);
				counter++;
			}

			String append = "\"";
			counter = 1;

			for (String serviceFlag : serviceFlagList) {
				serviceFlag = append + serviceFlag + append;
				String seriesId = append + "S" + counter + append;
				String object = "{\"id\":" + seriesId + ",\"name\":" + serviceFlag + ", \"data\": {\"field\":" + serviceFlag
						+ "},\"type\":\"line\",\"style\":{\"lineWidth\": 2,smoothing: true, marker: {shape: \"circle\", width: 5},";
				object = object + "lineColor: \"" + colorsList.get(counter - 1) + append;
				object = object + "}}";
				seriesArray.put(new JSONObject(object));
				counter++;
			}

			returnObject.put("values", valuesArray);
			returnObject.put("series", seriesArray);
		}
		return returnObject;
	}

	public static JSONObject prepareOrderMatchJson(List<OrderMatchDto> orderMatchList) throws JSONException {
		JSONObject returnJson = new JSONObject();
		JSONArray returnArray = null;
		JSONObject statusJson = null;
		if(orderMatchList != null && orderMatchList.size() > 0){
			returnArray = new JSONArray();
			statusJson = new JSONObject();
			statusJson.put("name", "Status");
			statusJson.put("id", "1");

			for(OrderMatchDto orderMatch : orderMatchList){
				if(orderMatch != null){
					if ("Matched".equals(orderMatch.getStatus())) {
						statusJson.put("Matched", orderMatch.getValue());
					} else {
						statusJson.put("UnMatched", orderMatch.getValue());
					}
				}
			}

			returnArray.put(statusJson);
			returnJson.put("values", returnArray);
		}
		return returnJson;
	}

	public static JSONObject prepareBilledVsApprovedJson(List<BilledVsApprovedDto> billedVsApprovedList) throws JSONException {
		JSONObject returnJson = new JSONObject();
		JSONArray returnArray = new JSONArray();;
		JSONObject statusJson = null;
		if(billedVsApprovedList != null && billedVsApprovedList.size() > 0){
			for(BilledVsApprovedDto billedVsApproved : billedVsApprovedList){
				if(billedVsApproved != null){
					statusJson = new JSONObject();
					statusJson.put("id", billedVsApproved.getCarrierId());
					statusJson.put("name", billedVsApproved.getCarrierName());
					statusJson.put("Billed", billedVsApproved.getBilledAmount());
					statusJson.put("Approved", billedVsApproved.getApprovedAmount());
					statusJson.put("Recovered", billedVsApproved.getRecoveredAmount());

					returnArray.put(statusJson);
					statusJson = null;
				}
			}
			returnJson.put("values", returnArray);
		}
		return returnJson;
	}

	public static JSONObject prepareRecoveryAdjustmentJson(List<RecoveryAdjustmentDto> recoveryAdjustmentList) throws JSONException {
		JSONObject returnObject = new JSONObject();
		JSONArray valuesArray = null;
		JSONArray seriesArray = null;

		if(recoveryAdjustmentList != null && !recoveryAdjustmentList.isEmpty()){
			valuesArray = new JSONArray();
			seriesArray = new JSONArray();
			LinkedHashMap<String, HashMap<String, Double>> monthsMap = new LinkedHashMap<String, HashMap<String, Double>>();
			ArrayList<String> servicesList = new ArrayList<String>();
			for(RecoveryAdjustmentDto recoveryAdjustment : recoveryAdjustmentList){
				if(recoveryAdjustment != null){
					String month = recoveryAdjustment.getMonth();
					String service = recoveryAdjustment.getService();
					Double spend = recoveryAdjustment.getSpend();

					if (spend != 0) {
						if (!servicesList.contains(service)) {
							servicesList.add(service);
						}

						if (monthsMap.containsKey(month)) {
							monthsMap.get(month).put(service, spend);
						} else {
							HashMap<String, Double> tempHashMap = new HashMap<String, Double>();
							tempHashMap.put(service, spend);
							monthsMap.put(month, tempHashMap);
						}

					}
				}
			}

			// Bar Chart
			int counter = 1;
			Iterator<String> monthsIterator = monthsMap.keySet().iterator();

			while (monthsIterator.hasNext()) {
				JSONObject jsonObject = new JSONObject();
				String month = monthsIterator.next();
				HashMap<String, Double> servicesMap = monthsMap.get(month);
				Iterator<String> serivcesIterator = servicesMap.keySet().iterator();

				jsonObject.put("name", month);
				jsonObject.put("counter", counter);

				while (serivcesIterator.hasNext()) {
					String service = serivcesIterator.next();
					double spend = servicesMap.get(service);
					jsonObject.put(service, spend);
				}

				valuesArray.put(jsonObject);
				counter++;
			}

			String append = "\"";
			counter = 1;

			for (String service : servicesList) {
				service = append + service + append;
				String seriesId = append + "S" + counter + append;

				String object = "{\"id\":" + seriesId + ",\"name\":" + service + ", \"data\": {\"field\":" + service
						+ "},\"type\":\"line\",\"style\":{\"lineWidth\": 2,smoothing: true, marker: {shape: \"circle\", width: 5},";

				object = object + "lineColor: \"" + colorsList.get(counter - 1) + "\"";
				object = object + "}}";
				seriesArray.put(new JSONObject(object));
				counter++;
			}

			returnObject.put("values", valuesArray);
			returnObject.put("series", seriesArray);
		}else{
			returnObject.put("values", new JSONArray());
			returnObject.put("series", new JSONArray());
		}
		return returnObject;
	}

	public static JSONObject prepareTotalCreditRecoveryByServiceLevelJson(List<RecoveryServiceDto> recoveryServiceList) throws JSONException {
		JSONObject returnObject = new JSONObject();
		JSONArray valuesArray = null;
		JSONArray seriesArray = null;

		if(recoveryServiceList != null && !recoveryServiceList.isEmpty()){
			valuesArray = new JSONArray();
			seriesArray = new JSONArray();
			Map<String, HashMap<String, Double>> servicesMap = new LinkedHashMap<String, HashMap<String, Double>>();
			Map<String, Long> carrierMap = new HashMap<String, Long>();
			ArrayList<String> carriersList = new ArrayList<String>();
			ArrayList<String> concatCarriersList = new ArrayList<String>();

			for(RecoveryServiceDto recoveryService : recoveryServiceList){
				if(recoveryService != null){
					String service = recoveryService.getBucketType();
					String carrierName = recoveryService.getCarrierName();
					Long carrierId = recoveryService.getCarrierId();
					Double spend = recoveryService.getCreditAmount();
					carrierMap.put(carrierName, carrierId);
					if (spend != null && spend != 0) {
						String concatCarrier = carrierId + "#@#" + carrierName;
						if (!concatCarriersList.contains(concatCarrier)) {
							concatCarriersList.add(concatCarrier);
						}

						if (!carriersList.contains(carrierName)) {
							carriersList.add(carrierName);
						}

						if (servicesMap.containsKey(service)) {
							servicesMap.get(service).put(carrierName, spend);
						} else {
							HashMap<String, Double> tempHashMap = new HashMap<String, Double>();
							tempHashMap.put(carrierName, spend);
							servicesMap.put(service, tempHashMap);
						}

					}
				}
			}

			// Bar Chart
			int counter = 1;
			Iterator<String> servicesIterator = servicesMap.keySet().iterator();

			while (servicesIterator.hasNext()) {
				JSONObject jsonObject = new JSONObject();

				String service = servicesIterator.next();
				HashMap<String, Double> carriersMap = servicesMap.get(service);

				Iterator<String> carriersIterator = carriersMap.keySet().iterator();

				jsonObject.put("name", service);
				jsonObject.put("counter", counter);

				while (carriersIterator.hasNext()) {
					String carrierName = carriersIterator.next();
					double spend = carriersMap.get(carrierName);
					jsonObject.put(carrierName, spend);
				}

				valuesArray.put(jsonObject);
				counter++;
			}

			String append = "\"";
			counter = 1;

			for (String carrier : carriersList) {
				String carrierName = carrier;
				carrier = append + carrier + append;
				String seriesId = append + "S" + counter + append;

				String object = "{\"id\":" + seriesId + ",\"name\":" + carrier + ", \"data\": {\"field\":" + carrier + ",\"carrierId\" : "+ carrierMap.get(carrierName) +"},style: { depth: 4, gradient: 0.9 }}";
				seriesArray.put(new JSONObject(object));
				counter++;
			}

			returnObject.put("values", valuesArray);
			returnObject.put("series", seriesArray);
			returnObject.put("carrierDetails", new JSONArray().put(concatCarriersList));
		} else{
			returnObject.put("values", new JSONArray());
			returnObject.put("series", new JSONArray());
			returnObject.put("carrierDetails", new JSONArray());
		}
		return returnObject;
	}

	public static JSONObject preparePackageExceptionJson(List<PackageExceptionDto> packageExceptionList) throws JSONException {
		JSONObject returnObject = new JSONObject();

		if(packageExceptionList != null && !packageExceptionList.isEmpty()){
			JSONArray valuesArray = new JSONArray();
			JSONArray seriesArray = new JSONArray();
			LinkedHashMap<String, HashMap<String, Integer>> datesValuesMap = new LinkedHashMap<String, HashMap<String, Integer>>();
			ArrayList<String> deliveryFlagList = new ArrayList<String>();

			for(PackageExceptionDto packageException : packageExceptionList){
				if(packageException != null){
					String billDate = packageException.getBillingDate();
					String deliveryFlag = packageException.getDeliveryFlag();
					Integer spend = packageException.getDeliveryFlagCount();

					if (spend != 0) {

						if ("NoPods".equalsIgnoreCase(deliveryFlag)) {
							deliveryFlag = "No POD";
						}

						if (!deliveryFlagList.contains(deliveryFlag)) {
							deliveryFlagList.add(deliveryFlag);
						}

						if (datesValuesMap.containsKey(billDate)) {
							datesValuesMap.get(billDate).put(deliveryFlag, spend);
						} else {
							HashMap<String, Integer> tempHashMap = new HashMap<String, Integer>();
							tempHashMap.put(deliveryFlag, spend);
							datesValuesMap.put(billDate, tempHashMap);
						}

					}
				}
			}
			// Bar Chart
			int counter = 1;
			Iterator<String> datesIterator = datesValuesMap.keySet().iterator();

			while (datesIterator.hasNext()) {
				JSONObject jsonObject = new JSONObject();

				String date = datesIterator.next();
				HashMap<String, Integer> deliveryFlagMap = datesValuesMap.get(date);

				Iterator<String> deliveryFlagIterator = deliveryFlagMap.keySet().iterator();

				jsonObject.put("name", date);
				jsonObject.put("counter", counter);

				while (deliveryFlagIterator.hasNext()) {
					String deliveryFlag = deliveryFlagIterator.next();
					double spend = deliveryFlagMap.get(deliveryFlag);
					jsonObject.put(deliveryFlag, spend);
				}

				valuesArray.put(jsonObject);
				counter++;
			}

			String append = "\"";
			counter = 1;

			for (String deliveryFlag : deliveryFlagList) {
				deliveryFlag = append + deliveryFlag + append;
				String seriesId = append + "S" + counter + append;
				String object = "{\"id\":" + seriesId + ",\"name\":" + deliveryFlag + ", \"data\": {\"field\":" + deliveryFlag + "},";

				if ("\"LATE\"".equalsIgnoreCase(deliveryFlag)) {
					object = object + " style: { depth: 4, gradient: 0.9 ,fillColor: \"#673FB4\" }}";
				}
				if ("\"MNS\"".equalsIgnoreCase(deliveryFlag)) {
					object = object + " style: { depth: 4, gradient: 0.9 ,fillColor: \"#2B98F0\" }}";
				}
				if ("\"No POD\"".equalsIgnoreCase(deliveryFlag)) {
					object = object + " style: { depth: 4, gradient: 0.9 ,fillColor: \"#FF9801\" }}";
				}
				seriesArray.put(new JSONObject(object));
				counter++;
			}

			returnObject.put("values", valuesArray);
			returnObject.put("series", seriesArray);
		}else{
			returnObject.put("values", new JSONArray());
			returnObject.put("series", new JSONArray());
		}
		return returnObject;
	}
}
