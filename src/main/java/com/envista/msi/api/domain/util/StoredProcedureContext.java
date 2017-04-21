/**
 *
 */
package com.envista.msi.api.domain.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import com.envista.msi.api.dao.type.GenericObject;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.EntityManagerImpl;
import com.envista.msi.api.domain.PersistentContext;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

/**
 * @author SANKER
 *
 */
@Repository("persistentContext")
@SuppressWarnings("unchecked")
@Transactional
public class StoredProcedureContext extends EntityManagerImpl implements PersistentContext {

	@SuppressWarnings("rawtypes")
	private Class entityBeanType;

	public StoredProcedureContext() {
		super();
		this.entityBeanType = GenericTypeResolver.resolveTypeArgument(getClass(), StoredProcedureContext.class);
	}

	public <T> T findById(Serializable id) {
		return (T) entityManager.find(getEntityBeanType(), id);
	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		return entityManager.createQuery("from " + getEntityBeanType().getName()).getResultList();
	}

	@SuppressWarnings("rawtypes")
	protected Class getEntityBeanType() {
		return entityBeanType;
	}

	private Query setParams(StoredProcedureQuery query, QueryParameter qp, int maxCount) {
		if (query != null) {
			query.setMaxResults(maxCount);
		}
		return setParams(query, qp);
	}

	private Query setParams(Query query, QueryParameter parameters) {
		if (parameters != null && parameters.parameters() != null && query != null) {

			parameters.parameters().forEach((key, value) -> {
				query.setParameter(key, value);
			});
		}
		if (parameters != null && parameters.positionParameters() != null && query != null) {
			List<StoredParameter> a = parameters.positionParameters();
			for (StoredParameter storedParameter : a) {
				query.setParameter(storedParameter.position, storedParameter.value);
			}
		}
		return query;
	}

	@Override
	public <T> T findEntity(String StoredProcedureQueryName, QueryParameter parameters) {
		return (T) setParams(entityManager.createNamedStoredProcedureQuery(StoredProcedureQueryName), parameters)
				.getSingleResult();
	}

	@Override
	public <T> List<T> findEntities(String StoredProcedureQueryName, QueryParameter parameters) {
		return setParams(entityManager.createNamedStoredProcedureQuery(StoredProcedureQueryName), parameters)
				.getResultList();
	}

	@Override
	public <T> T findEntityAndMapFields(String StoredProcedureQueryName, QueryParameter parameters) {
		return findEntity(StoredProcedureQueryName, parameters);
	}

	@Override
	public <T> List<T> findEntitiesAndMapFields(String StoredProcedureQueryName, QueryParameter parameters) {
		return findEntities(StoredProcedureQueryName, parameters);
	}

	@Override
	public <T> List<T> findEntities(String StoredProcedureQueryName, QueryParameter parameters, int maxCount) {
		return setParams(entityManager.createNamedStoredProcedureQuery(StoredProcedureQueryName), parameters, maxCount)
				.getResultList();
	}

	@Override
	public Session getHibernateSession() {
		Session session = entityManager.unwrap(Session.class);
		return session;
	}

	@Override
	public <T> List<T> findEntitiesAndMapFields(String StoredProcedureQueryName, QueryParameter parameters,
												int maxCount) {
		return findEntities(StoredProcedureQueryName, parameters, maxCount);
	}

	/**
	 * Get the database connection currently in use.
	 * <p>
	 *
	 * @return Connection jdbc connection to the database
	 */
	// @SuppressWarnings("deprecation")
	private OracleConnection getNativeConnection() {

		return getHibernateSession().doReturningWork(new ReturningWork<OracleConnection>() {

			@Override
			public OracleConnection execute(Connection connection) throws SQLException {
				return connection.isWrapperFor(OracleConnection.class) ? connection.unwrap(OracleConnection.class)
						: null;
			}
		});
	}

	private ARRAY createOracleArray(final OracleConnection connection, final String[] data) throws SQLException {
		return new ARRAY(ArrayDescriptor.createDescriptor("STRING_LIST", connection), connection, data);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T> List<T> executeStoredProcedure(String storedProcedureName, QueryParameter parameters)
			throws SQLException {
		if (null == storedProcedureName) {
			throw new SQLException("Unknown Procedure name");
		}

		if (null == parameters) {
			throw new SQLException("Unknown Procedure name");
		}

		String params = "";

		List<StoredParameter> a = parameters.positionParameters();
		for (int i = 0; i < a.size(); i++) {
			params = params + "?,";
		}

		params = storedProcedureName + "(" + (params.endsWith(",") ? params.substring(0, params.length() - 1) : params)
				+ ")";
		CallableStatement st = null;
		List returnList = new ArrayList();
		ResultSet rs = null;
		OracleConnection connection = null;
		try {
			connection = getNativeConnection();
			st = connection.prepareCall("call " + params);

			int outCount = 0;
			int outPos = 0;
			if (parameters != null && parameters.positionParameters() != null) {
				for (StoredParameter storedParameter : a) {
					switch (storedParameter.mode) {
						case IN:
							if (storedParameter.type.equals(String[].class)) {
								st.setArray(storedParameter.position,
										createOracleArray(connection, (String[]) storedParameter.value));
							} else if(storedParameter.type.equals(GenericObject[].class)) {
								STRUCT[] structs = null;
								if(null != storedParameter.value){
									List<GenericObject> paramsList = (List<GenericObject>) storedParameter.value;
									StructDescriptor structDescriptor = StructDescriptor.createDescriptor("genericobject".toUpperCase(), st.getConnection());
									structs = new STRUCT[paramsList.size()];
									Field[] objParams = GenericObject.class.getDeclaredFields();
									for (int index = 0; index < paramsList.size(); index++) {
										GenericObject genericObject = paramsList.get(index);
										int i = 0;
										Object[] sqlParams = new Object[objParams.length];
										for(Field field : objParams){
											field.setAccessible(true);
											sqlParams[i++] = field.get(genericObject);
										}

										STRUCT struct = new STRUCT(structDescriptor, st.getConnection(), sqlParams);
										structs[index] = struct;
									}
								}

								ArrayDescriptor desc = ArrayDescriptor.createDescriptor("genericobject_array".toUpperCase(), st.getConnection());
								ARRAY oracleArray = new ARRAY(desc, st.getConnection(), structs);
								st.setArray(storedParameter.position, oracleArray);
							} else {
								st.setObject(storedParameter.position, storedParameter.value);
							}
							break;
						case INOUT:
							throw new SQLException("Only IN and REF_CURSOR modes are supported");
						case OUT:
							throw new SQLException("Only IN and REF_CURSOR modes are supported");
						case REF_CURSOR:
							outCount++;
							st.registerOutParameter(storedParameter.position, OracleTypes.CURSOR);
							if (outCount > 1) {
								throw new SQLException("Unknown parameter name");
							}
							outPos = storedParameter.position;
							break;

					}
				}
			}

			st.execute();
			rs = (ResultSet) st.getObject(outPos);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				List record = new ArrayList();
				;
				for (int i = 1; i <= columnCount; i++) {
					record.add(rs.getObject(i));
				}
				if (record != null && !record.isEmpty())
					returnList.add(record);
			}
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					;
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					;
				}
			}
			if(connection != null){
				connection.close();
			}
		}

		return returnList;
	}
}
