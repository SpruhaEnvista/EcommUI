package com.envista.msi.api.web.rest.util.pac;

import java.io.Serializable;

import org.json.JSONObject;

/**
 * <p>
 * 
 * @author sreedhar mallepaddi
 * @Version : 1.0 Sreedhar Mallepaddi 10-Mar-2003 Created
 *          </p>
 */
public interface IBean extends Serializable {

	// /////////////////////////////////////
	// operations

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 * 
	 * @return a String with ...
	 *         </p>
	 */
	public String convertToXML();

	public JSONObject getJSONObject(String[] dataField);

} // end IBean

