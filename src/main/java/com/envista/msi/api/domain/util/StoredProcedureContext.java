/**
 * 
 */
package com.envista.msi.api.domain.util;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.EntityManagerImpl;
import com.envista.msi.api.domain.PersistentContext;

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

}
