/**
 * 
 */
package com.envista.msi.api.domain;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

/**
 * @author SANKER
 *
 */
@Component
public class PersistentDaoImpl {
	 @PersistenceContext
	 private EntityManager em;
	 public Collection loadCarriers() {
	      //  Query query = em.createQuery("from ShpCarrierTb WHERE CARRIER_ID < 1000");
		 String sqlQuery = "SELECT CARRIER_ID from SHP_CARRIER_TB WHERE CARRIER_ID < 1000";
		 Query query = em.createNativeQuery(sqlQuery);
	       // query.setParameter("category", category);
	        return query.getResultList();
	    }
}
