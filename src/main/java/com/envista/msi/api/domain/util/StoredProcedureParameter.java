/**
 * 
 */
package com.envista.msi.api.domain.util;

import javax.persistence.ParameterMode;

/**
 * 
 * 
 * Container implementation of both the positional and named parameter Stored
 * Procedure contracts.
 *
 * 
 * @author SANKER
 *
 */
public class StoredProcedureParameter extends QueryParameter {

	protected <T> StoredProcedureParameter(int position, ParameterMode mode, Class<T> type, Object value) {
		super(position, mode, type, value);
	}

	/*protected StoredProcedureParameter(String name, Object value) {
		super(name, value);
	}*/

}
