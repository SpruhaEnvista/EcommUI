package com.envista.msi.api.web.rest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

}
