package com.envista.msi.api.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.ParameterMode;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.rest.WebappTestEnvironment;

/**
 * Test class for the input list to stored proc.
 *
 * 
 */

public class InParamArrayTest extends WebappTestEnvironment {

	@Inject
	private PersistentContext persistentContext;

	@Test
	@Transactional
	public void testSmallListProc() {
		List<String> input = new ArrayList<String>();
		input.add("D Caruso");
		input.add("J Hamil");
		input.add("D Piro");
		input.add("R Singh");

		final String[] data = input.toArray(new String[input.size()]);

		try {
			List<String> results = persistentContext.executeStoredProcedure("procarrayinout",
					StoredProcedureParameter.withPosition(1, ParameterMode.IN, String[].class, data)
							.andPosition(2, ParameterMode.IN, String.class, "Sample ")
							.andPosition(3, ParameterMode.REF_CURSOR, void.class, null));
			System.out.println("results: " + results);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	public void testStringList() {
		List<String> input = new ArrayList<String>();
		input.add("SPOKANE VALLEY,WA,US");
		input.add("SOUTH DEERFIELD,MA,US");
		input.add("BYRAM,MS,US");

		final String[] data = input.toArray(new String[input.size()]);

		try {
			List<List<Object>> results = persistentContext.executeStoredProcedure("SHP_PARCEL_GEO_COORDINATE_PROC",
					StoredProcedureParameter.withPosition(1, ParameterMode.IN, String[].class, data)
							.andPosition(2, ParameterMode.REF_CURSOR, void.class, null));
			System.out.println("results: " + results);
			System.out.println("results: " +  persistentContext.executeStoredProcedure("SHP_PARCEL_GEO_COORDINATE_PROC",
					StoredProcedureParameter.withPosition(1, ParameterMode.IN, String[].class, data)
							.andPosition(2, ParameterMode.REF_CURSOR, void.class, null)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
