/**
 * 
 */
package com.envista.msi.api.domain;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.envista.msi.api.domain.util.QueryParameter;

/**
 * Interface used to control stored procedure query execution.
 * 
 * @author SANKER
 *
 */
public interface PersistentContext extends EntityManager {

	/**
	 * @param id
	 * @return
	 */
	public <T> T findById(Serializable id);

	/**
	 * @return
	 */
	public <T> List<T> findAll();

	/**
	 * @param StoredProcedureQueryName
	 * @param parameters
	 * @return
	 */
	public <T> T findEntity(String StoredProcedureQueryName, QueryParameter parameters);

	/**
	 * @param StoredProcedureQueryName
	 * @param parameters
	 * @return
	 */
	public <T> List<T> findEntities(String StoredProcedureQueryName, QueryParameter parameters);

	/**
	 * @param StoredProcedureQueryName
	 * @param parameters
	 * @param maxCount
	 * @return
	 */
	public <T> List<T> findEntities(String StoredProcedureQueryName, QueryParameter parameters, int maxCount);

	/**
	 * @param StoredProcedureQueryName
	 * @param parameters
	 * @return
	 */
	public <T> T findEntityAndMapFields(String StoredProcedureQueryName, QueryParameter parameters);

	/**
	 * @param StoredProcedureQueryName
	 * @param parameters
	 * @return
	 */
	public <T> List<T> findEntitiesAndMapFields(String StoredProcedureQueryName, QueryParameter parameters);
	
	/**
	 * @param StoredProcedureQueryName
	 * @param parameters
	 * @param maxCount
	 * @return
	 */
	public <T> List<T> findEntitiesAndMapFields(String StoredProcedureQueryName, QueryParameter parameters, int maxCount);

	public <T> List<T> executeStoredProcedure(String storedProcedureName, QueryParameter parameters)
			throws SQLException;

	/**
	 * @return hibernate session
	 */
	public Session getHibernateSession();

	public <T> List<T> executeStoredProcedureListType(String storedProcedureName, QueryParameter parameters, String object, String objectArray)
			throws SQLException;
}
