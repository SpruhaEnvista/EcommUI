package com.envista.msi.api.web.rest.util;

import com.envista.msi.api.web.rest.dto.common.CommonValuesForChartDto;
import com.envista.msi.api.web.rest.dto.netspend.*;
import com.envista.msi.api.web.rest.dto.taxspend.TaxSpendDto;
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

	public static JSONObject prepareNetSpendByModesJson(List<NetSpendByModeDto> netSpendDtoList) throws JSONException {
		JSONObject netSpendJsonData = new JSONObject();
		JSONArray valuesArray = new JSONArray();
		JSONArray seriesArray = new JSONArray();

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
		JSONArray valuesArray = new JSONArray();
		JSONArray seriesArray = new JSONArray();
		LinkedHashMap<String, HashMap<String, Double>> datesValuesMap = new LinkedHashMap<String, HashMap<String, Double>>();
		ArrayList<String> carriersList = new ArrayList<String>();

		if(netSpendOverTimeDtos != null && netSpendOverTimeDtos.size() > 0){
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

	public static JSONObject prepareTaxSpendJson(List<TaxSpendDto> taxSpendList) throws JSONException {
		JSONObject returnObject = new JSONObject();
		JSONArray taxesArray = new JSONArray();
		HashMap<String, Double> taxMap = new HashMap<>();
		ArrayList<String> taxesList = new ArrayList<String>();

		if(taxSpendList != null && taxSpendList.size() > 0){
			for(TaxSpendDto taxSpend : taxSpendList){
				if(taxSpend != null){
					String tax = taxSpend.getTax();
					Double spend = taxSpend.getSpend();

					if (spend > 0) {
						if (!taxesList.contains(tax)) {
							taxesList.add(tax);
						}

						if (taxMap.containsKey(tax)) {
							double spendAmount = taxMap.get(tax);
							spendAmount += spend;
							taxMap.put(tax, spendAmount);
						} else {
							taxMap.put(tax, spend);
						}
					}
				}
			}

			// Donut Chart
			Iterator<String> taxIterator = taxMap.keySet().iterator();
			while (taxIterator.hasNext()) {
				String tax = taxIterator.next();
				double spend = taxMap.get(tax);

				JSONObject taxesJson = new JSONObject();
				taxesJson.put("name", tax);
				taxesJson.put("value", spend);

				taxesArray.put(taxesJson);
				taxesJson = null;
			}
			returnObject.put("donutChartvalues", taxesArray);
		}
		return returnObject;
	}

	public static JSONObject prepareCommonJsonForChart(List<CommonValuesForChartDto> dataList) throws JSONException {
		JSONObject returnJson = new JSONObject();
		JSONArray returnArray = new JSONArray();
		JSONObject statusJson = null;

		if(dataList != null && dataList.size() > 0){
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
}
