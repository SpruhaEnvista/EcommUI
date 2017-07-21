/**
 * 
 */
package com.envista.msi.api.domain.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ParameterMode;

/**
 * @author SANKER
 *
 */
public class QueryParameter {

	protected QueryParameter(String name, Object value) {
		this.parameters = new HashMap<String, Object>();
		this.positionParameters = new ArrayList<StoredParameter>();
		this.parameters.put(name, checkForNull(value));
	}

	public static QueryParameter with(String name, Object value) {
		return new QueryParameter(name, checkForNull(value));
	}

	public QueryParameter and(String name, Object value) {
		this.parameters.put(name, checkForNull(value));
		return this;
	}

	public Map<String, Object> parameters() {
		return this.parameters;
	}

	protected <T> QueryParameter(int position, ParameterMode mode, Class<T> type, Object value) {
		this.positionParameters = new ArrayList<StoredParameter>();
		this.parameters = new HashMap<String, Object>();
		this.positionParameters.add(new StoredParameter(position, mode, type, checkForNull(value)));
	}

	public static <T> QueryParameter withPosition(int position, ParameterMode mode, Class<T> type, Object value) {
		return new QueryParameter(position, mode, type, checkForNull(value));
	}

	public <T> QueryParameter andPosition(int position, ParameterMode mode, Class<T> type, Object value) {
		this.positionParameters.add(new StoredParameter(position, mode, type, checkForNull(value)));
		return this;
	}

	public List<StoredParameter> positionParameters() {
		return this.positionParameters;
	}

	private static Object  checkForNull(Object value){
		if(value != null){
			return value;
		} else {
			return "";
		}
	}

	private Map<String, Object> parameters = null;
	// private Map<Integer, Object> positionParameters = null;
	private List<StoredParameter> positionParameters = null;
}

class StoredParameter {
	int position;
	ParameterMode mode;
	Class type;
	Object value;

	public <T> StoredParameter(int position, ParameterMode mode, Class<T> type, Object value) {
		super();
		this.position = position;
		this.mode = mode;
		this.type = type;

		if(value == null){
			this.value  = "";
		} else {
			this.value = value;
		}
	}

}
