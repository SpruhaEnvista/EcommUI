/**
 * 
 */
package com.envista.msi.api.domain;

import java.io.Serializable;
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
	 * @param storedProcedureName
	 * @param parameters
	 * @return
	 */
	public <T> T findEntity(String StoredProcedureQueryName, QueryParameter parameters);

	/**
	 * @param storedProcedureName
	 * @param parameters
	 * @return
	 */
	public <T> List<T> findEntities(String StoredProcedureQueryName, QueryParameter parameters);

	/**
	 * @param storedProcedureName
	 * @param parameters
	 * @param maxCount
	 * @return
	 */
	public <T> List<T> findEntities(String StoredProcedureQueryName, QueryParameter parameters, int maxCount);

	/**
	 * @param storedProcedureName
	 * @param parameters
	 * @return
	 */
	public <T> T findEntityAndMapFields(String StoredProcedureQueryName, QueryParameter parameters);

	/**
	 * @param storedProcedureName
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

	/**
	 * @return hibernate session
	 */
	public Session getHibernateSession();
}
