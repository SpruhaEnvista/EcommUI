package com.envista.msi.api.web.rest.util;

import com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.common.CommonValuesForChartDto;
import com.envista.msi.api.web.rest.dto.dashboard.common.NetSpendCommonDto;
import com.envista.msi.api.web.rest.dto.dashboard.common.NetSpendMonthlyChartDto;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendByModeDto;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendOverTimeDto;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.AverageSpendPerShipmentDto;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.AverageWeightModeShipmtDto;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.ServiceLevelUsageAndPerformanceDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.ws.Service;
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
				String object = "{\"id\":" + seriesId + ",\"name\":" + scoreType + ", \"data\": {\"field\":" + scoreType + "},style: { depth: 4, gradient: 0.9,fillColor: \"#00E5FF\" }}";
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

	public static JSONObject prepareAverageWeightOrSpendJson(List<AverageSpendPerShipmentDto> avgPerShipmentList) throws JSONException {
		JSONObject returnObject = new JSONObject();
		JSONArray valuesArray = new JSONArray();
		JSONArray seriesArray = new JSONArray();
		LinkedHashMap<String, HashMap<String, Double>> datesValuesMap = new LinkedHashMap<String, HashMap<String, Double>>();
		ArrayList<String> modeFlagList = new ArrayList<String>();

			for (AverageSpendPerShipmentDto perShipmentDto:avgPerShipmentList){
				String billDate = perShipmentDto.getBillDate();
				String mode = perShipmentDto.getModes();
				Double spend = perShipmentDto.getNetWeight();

				if (spend != 0) {

					if (!modeFlagList.contains(mode)) {
						modeFlagList.add(mode);
					}

					if (datesValuesMap.containsKey(billDate)) {
						datesValuesMap.get(billDate).put(mode, spend);
					} else {
						HashMap<String, Double> tempHashMap = new HashMap<String, Double>();
						tempHashMap.put(mode, spend);
						datesValuesMap.put(billDate, tempHashMap);
					}

				}

			}
			// Bar Chart
			int counter = 1;
			Iterator<String> datesIterator = datesValuesMap.keySet().iterator();

			while (datesIterator.hasNext()) {
				JSONObject jsonObject = new JSONObject();

				String date = datesIterator.next();
				HashMap<String, Double> modeFlagMap = datesValuesMap.get(date);
				Iterator<String> modeFlagIterator = modeFlagMap.keySet().iterator();

				jsonObject.put("name", date);
				jsonObject.put("counter", counter);

				while (modeFlagIterator.hasNext()) {
					String modeFlag = modeFlagIterator.next();
					double spend = modeFlagMap.get(modeFlag);
					jsonObject.put(modeFlag, spend);
				}
				valuesArray.put(jsonObject);
				counter++;
			}

			String append = "\"";
			counter = 1;
			for (String modeFlag : modeFlagList) {
				modeFlag = append + modeFlag + append;
				String seriesId = append + "S" + counter + append;
				String object = "{\"id\":" + seriesId + ",\"name\":" + modeFlag + ", \"data\": {\"field\":" + modeFlag
						+ "},\"type\":\"line\",\"style\":{\"lineWidth\": 2,smoothing: true, marker: {shape: \"circle\", width: 5},";
				object = object + "lineColor: \"" + colorsList.get(counter - 1) + "\"";
				object = object + "}}";
				seriesArray.put(new JSONObject(object));
				counter++;
			}
			returnObject.put("values", valuesArray);
			returnObject.put("series", seriesArray);

		return returnObject;
	}


	public static JSONObject prepareAverageWeightJson(   List<AverageWeightModeShipmtDto> avgWeigthModeShpmtList) throws JSONException {
		JSONObject returnObject = new JSONObject();
		JSONArray valuesArray = new JSONArray();
		JSONArray seriesArray = new JSONArray();
		LinkedHashMap<String, HashMap<String, Double>> datesValuesMap = new LinkedHashMap<String, HashMap<String, Double>>();
		ArrayList<String> modeFlagList = new ArrayList<String>();

		for (AverageWeightModeShipmtDto perWeightShipmentDto:avgWeigthModeShpmtList){
			String billDate = perWeightShipmentDto.getBillDate();
			String mode = perWeightShipmentDto.getModes();
			Double spend = perWeightShipmentDto.getNetWeight();

			if (spend != 0) {

				if (!modeFlagList.contains(mode)) {
					modeFlagList.add(mode);
				}

				if (datesValuesMap.containsKey(billDate)) {
					datesValuesMap.get(billDate).put(mode, spend);
				} else {
					HashMap<String, Double> tempHashMap = new HashMap<String, Double>();
					tempHashMap.put(mode, spend);
					datesValuesMap.put(billDate, tempHashMap);
				}

			}

		}
		// Bar Chart
		int counter = 1;
		Iterator<String> datesIterator = datesValuesMap.keySet().iterator();

		while (datesIterator.hasNext()) {
			JSONObject jsonObject = new JSONObject();

			String date = datesIterator.next();
			HashMap<String, Double> modeFlagMap = datesValuesMap.get(date);
			Iterator<String> modeFlagIterator = modeFlagMap.keySet().iterator();

			jsonObject.put("name", date);
			jsonObject.put("counter", counter);

			while (modeFlagIterator.hasNext()) {
				String modeFlag = modeFlagIterator.next();
				double spend = modeFlagMap.get(modeFlag);
				jsonObject.put(modeFlag, spend);
			}
			valuesArray.put(jsonObject);
			counter++;
		}

		String append = "\"";
		counter = 1;
		for (String modeFlag : modeFlagList) {
			modeFlag = append + modeFlag + append;
			String seriesId = append + "S" + counter + append;
			String object = "{\"id\":" + seriesId + ",\"name\":" + modeFlag + ", \"data\": {\"field\":" + modeFlag
					+ "},\"type\":\"line\",\"style\":{\"lineWidth\": 2,smoothing: true, marker: {shape: \"circle\", width: 5},";
			object = object + "lineColor: \"" + colorsList.get(counter - 1) + "\"";
			object = object + "}}";
			seriesArray.put(new JSONObject(object));
			counter++;
		}
		returnObject.put("values", valuesArray);
		returnObject.put("series", seriesArray);

		return returnObject;
	}

	public static JSONObject prepareServiceLevelUsageAndPerfromanceJson(List<ServiceLevelUsageAndPerformanceDto> performanceList) throws JSONException {
		JSONObject finalJsonObj = new JSONObject();
		JSONArray valuesArray = null;
		JSONArray seriesArray = null;

		if(performanceList != null && !performanceList.isEmpty()){
			valuesArray = new JSONArray();
			seriesArray = new JSONArray();
			HashMap<String, Double> carriersValuesMap = new HashMap<String, Double>();
			Set<String> categories = new TreeSet<String>(Arrays.asList("DAY2", "DAY3", "GROUND", "INTL", "NDA", "POSTALINTG"));
			Set<String> attributes = new TreeSet<String>();

			for(ServiceLevelUsageAndPerformanceDto serviceLevelUsage : performanceList){
				if(serviceLevelUsage != null){
					int c = 0;
					for(String category : categories){
						String carrierName = serviceLevelUsage.getCarrierName();
						if(c == 0){
							attributes.add(carrierName + "COUNT");
							attributes.add(carrierName + "PERC");
							attributes.add(carrierName + "LATEPERC");
							c++;
						}
						String key = carrierName + "#@#" + category;
						switch (category){
							case "DAY2":
								carriersValuesMap.put(key + "#@#DAY2COUNT", serviceLevelUsage.getDay2Count());
								carriersValuesMap.put(key + "#@#DAY2PERC", serviceLevelUsage.getDay2Percentage());
								carriersValuesMap.put(key + "#@#DAY2LATEPERC", serviceLevelUsage.getDay2LatePercentage());
								break;
							case "DAY3":
								carriersValuesMap.put(key + "#@#DAY3COUNT", serviceLevelUsage.getDay2Count());
								carriersValuesMap.put(key + "#@#DAY3PERC", serviceLevelUsage.getDay2Percentage());
								carriersValuesMap.put(key + "#@#DAY3LATEPERC", serviceLevelUsage.getDay2LatePercentage());
								break;
							case "GROUND":
								carriersValuesMap.put(key + "#@#GROUNDCOUNT", serviceLevelUsage.getGroundCount());
								carriersValuesMap.put(key + "#@#GROUNDPERC", serviceLevelUsage.getGroundPercentage());
								carriersValuesMap.put(key + "G#@#ROUNDLATEPERC", serviceLevelUsage.getGroundLatePercentage());
								break;
							case "INTL":
								carriersValuesMap.put(key + "I#@#NTLCOUNT", serviceLevelUsage.getInternationalCount());
								carriersValuesMap.put(key + "#@#INTLPERC", serviceLevelUsage.getInternationalPercentage());
								carriersValuesMap.put(key + "I#@#NTLLATEPERC", serviceLevelUsage.getInternationalLatePercentage());
								break;
							case "NDA":
								carriersValuesMap.put(key + "#@#NDACOUNT", serviceLevelUsage.getNdaCount());
								carriersValuesMap.put(key + "#@#NDAPERC", serviceLevelUsage.getNdaPercentage());
								carriersValuesMap.put(key + "#@#NDALATEPERC", serviceLevelUsage.getNdaLatePercentage());
								break;
							case "POSTALINTG":
								carriersValuesMap.put(key + "#@#POSTALINTGCOUNT", serviceLevelUsage.getPostalIntgCount());
								carriersValuesMap.put(key + "#@#POSTALINTGPERC", serviceLevelUsage.getPostalIntgPercentage());
								carriersValuesMap.put(key + "#@#POSTALINTGLATEPERC", serviceLevelUsage.getPostalIntgLatePercentage());
								break;
						}
					}
				}
			}

			Iterator<String> categoriesIterator = categories.iterator();

			while (categoriesIterator.hasNext()) {
				String service = categoriesIterator.next();
				JSONObject finalValues = new JSONObject();
				finalValues.put("name", service);
				Iterator<String> carrierIterator = carriersValuesMap.keySet().iterator();
				while (carrierIterator.hasNext()) {
					String carrierMapKey = carrierIterator.next();
					double amount = carriersValuesMap.get(carrierMapKey);
					String carrierName = carrierMapKey.split("#@#")[0];
					String serviceName = carrierMapKey.split("#@#")[1];
					String columnName = carrierMapKey.split("#@#")[2];

					if (service.equalsIgnoreCase(serviceName)) {
						finalValues.put(carrierName + columnName, amount);
					}
				}
				valuesArray.put(finalValues);
			}

			String append = "\"";
			int counter = 1;
			for (String seriesName : attributes) {
				String seriesId = append + "S" + counter + append;
				StringBuffer seriesObject = new StringBuffer();

				seriesObject.append("{\"id\":" + seriesId + ",\"name\":" + append + seriesName + append + ", \"data\": {\"field\":" + seriesName + "}");
				if (seriesName.contains("COUNT")) {
					seriesObject.append(",\"type\":\"line\",\"style\":{\"lineWidth\": 2,depth: 4, gradient: 0.9 ,smoothing: true, marker: {shape: \"circle\", width: 5},");
					seriesObject.append("lineColor: \"" + colorsList.get(counter - 1) + "\"");
					seriesObject.append("}");
				}else {
					seriesObject.append(",style: { depth: 4, gradient: 0.9 }");
				}

				seriesObject.append("}");
				seriesArray.put(new JSONObject(seriesObject.toString()));
				counter++;
			}
			finalJsonObj.put("values", valuesArray);
			finalJsonObj.put("series", seriesArray);
		} else {
			finalJsonObj.put("values", new JSONArray());
			finalJsonObj.put("series", new JSONArray());
		}
		return finalJsonObj;
	}

	public static JSONObject prepareInAndOutBuondJson(List<NetSpendCommonDto> spendList) throws JSONException {
		JSONObject returnObject = new JSONObject();
		JSONArray valuesArray = null;
		JSONArray seriesArray = null;

		if(spendList != null && !spendList.isEmpty()){
			valuesArray = new JSONArray();
			seriesArray = new JSONArray();
			LinkedHashMap<String, HashMap<String, Double>> datesValuesMap = new LinkedHashMap<String, HashMap<String, Double>>();
			ArrayList<String> modeFlagList = new ArrayList<String>();
			ArrayList<String> carriersList = new ArrayList<String>();
			for(NetSpendCommonDto spendDto : spendList){
				if(spendDto != null){
					String billDate = spendDto.getBillingDate();
					String carrierScacCode = spendDto.getCarrierName();
					Double spend = spendDto.getNetDueAmount();
					Long carrierId = spendDto.getCarrierId();
					String carrierIaAndName = carrierId + "#@#" + carrierScacCode;
					if(!carriersList.contains(carrierIaAndName)){
						carriersList.add(carrierIaAndName);
					}

					if (spend != 0) {
						if (!modeFlagList.contains(carrierScacCode)) {
							modeFlagList.add(carrierScacCode);
						}
						if (datesValuesMap.containsKey(billDate)) {
							datesValuesMap.get(billDate).put(carrierScacCode, spend);
						} else {
							HashMap<String, Double> tempHashMap = new HashMap<String, Double>();
							tempHashMap.put(carrierScacCode, spend);
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
				HashMap<String, Double> modeFlagMap = datesValuesMap.get(date);

				Iterator<String> modeFlagIterator = modeFlagMap.keySet().iterator();

				jsonObject.put("name", date);
				jsonObject.put("counter", counter);

				while (modeFlagIterator.hasNext()) {
					String modeFlag = modeFlagIterator.next();
					double spend = modeFlagMap.get(modeFlag);
					jsonObject.put(modeFlag, spend);
				}

				valuesArray.put(jsonObject);
				counter++;
			}

			String append = "\"";
			counter = 1;

			for (String modeFlag : modeFlagList) {
				modeFlag = append + modeFlag + append;
				String seriesId = append + "S" + counter + append;
				String object = "{\"id\":" + seriesId + ",\"name\":" + modeFlag + ", \"data\": {\"field\":" + modeFlag
						+ "},\"type\":\"line\",\"style\":{\"lineWidth\": 2,smoothing: true, marker: {shape: \"circle\", width: 5},";
				object = object + "lineColor: \"" + colorsList.get(counter - 1) + "\"";
				object = object + "}}";
				seriesArray.put(new JSONObject(object));
				counter++;
			}

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
			}

			returnObject.put("values", valuesArray);
			returnObject.put("series", seriesArray);
			returnObject.put("carrierDetails", new JSONArray().put(carriersList));
		}
		return returnObject;
	}
}
