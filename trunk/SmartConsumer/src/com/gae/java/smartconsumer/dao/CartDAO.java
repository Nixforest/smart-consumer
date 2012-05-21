package com.gae.java.smartconsumer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.Bill;

public enum CartDAO {
	INSTANCE;
	
	/**
	 * Insert method.
	 * @param cartInfo CartInfo object to insert
	 */
	 public void insert(List<Bill> cartInfo) {
        synchronized (this) {	        	
            EntityManager em = EMFService.get().createEntityManager();
            for(Bill c : cartInfo) {
            	em.persist(c);	   
            }
            em.close();	 	            
        }
	  }
	 
	 /**
	  * Get Cart Information by customer Id.
	  * @param customerId Customer's Id
	  * @return List Cart info
	  */
	 public List<Bill> getByCustomerId(Long customerId) {		 
		 EntityManager em = EMFService.get().createEntityManager();	
		 Query q = em.createQuery("select from " + Bill.class.getName() + " where customer_id=" + customerId);
		 List<Bill> list = ( List<Bill>) q.getResultList();
		 return list;
	 }
}