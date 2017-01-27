package com.envista.msi.api.web.rest.util;

import com.envista.msi.api.web.rest.dto.NetSpendDto;
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

	public static JSONObject prepareNetSpendByModesJson(List<NetSpendDto> netSpendDtoList) throws JSONException {
		JSONObject netSpendJsonData = new JSONObject();
		JSONArray valuesArray = new JSONArray();
		JSONArray seriesArray = new JSONArray();

		Map<String, HashMap<String, Double>> modesValuesMap = new LinkedHashMap<String, HashMap<String, Double>>();
		List<String> scortypeList = new ArrayList<String>();

		for(NetSpendDto netSpendDto: netSpendDtoList){
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

	public static JSONObject prepareJsonForMonthlyChart(List<NetSpendDto> netSpendDtoList){
		JSONObject netSpenfChatJson = new JSONObject();
        /*JSONArray jsonArr = new JSONArray();
        int count = 0;
        long fromDate = 0;
        long toDate = 0;

        try{
            if(netSpendDtoList != null && netSpendDtoList.size() > 0){
                for(NetSpendDto netSpendDto : netSpendDtoList){
                    JSONArray dataArray = new JSONArray();
                    long dateInMilliSecs = netSpendDto.getBillDate().getTime();
                    dataArray.put(dateInMilliSecs);
                    dataArray.put(netSpendDto.getAmount());

                    jsonArr.put(dataArray);

                    if(count == 0){
                        fromDate = dateInMilliSecs;
                    }
                    toDate = dateInMilliSecs;
                    dataArray = null;
                    count++;
                }

                if (fromDate == toDate) {
                    toDate = toDate + 1;
                }
                netSpenfChatJson.put("values", jsonArr);
                netSpenfChatJson.put("fromDate", fromDate);
                netSpenfChatJson.put("toDate", toDate);
            }
        }catch(Exception e){
            //handle here
        }*/
		return netSpenfChatJson;
	}
}
