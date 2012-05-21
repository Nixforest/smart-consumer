package com.gae.java.smartconsumer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gae.java.smartconsumer.model.BillDetail;
import com.gae.java.smartconsumer.model.Deal;

public enum CustomerDAO {
	INSTANCE;
	
	/**
	 * Insert a customer to data store.
	 * @param customer customer to insert
	 * 
	 */
	public void insert(BillDetail customer) {
	        synchronized (this) {
	            EntityManager em = EMFService.get().createEntityManager();	            
	            em.persist(customer);	           
	            em.close();
	        }
	 }
	 
	 /**
	 * List all customers in data store.
	 * @return List customers
	 */
	 public List<BillDetail> listCustomers() {
	        EntityManager em = EMFService.get().createEntityManager();
	        // Read the existing entries
	        Query q = em.createQuery("select m from Customer m");
	        @SuppressWarnings("unchecked")
	        List<BillDetail> cus = q.getResultList();	        
	        return cus;
	 }
	 
	 /**
	  * Delete customer method
	  * @param id id of customer
	  */
	 public void delete(Long id)
	 {		 
		 EntityManager em = EMFService.get().createEntityManager();
	        try {
	            BillDetail cus = em.find(BillDetail.class, id);
	            em.remove(cus);
	        } finally {
	            em.close();
	        }
	 }
	 
	 /**
	  * Get Customer by id
	  * @param id id of customer
	  * @return Customer object
	  */
	 public BillDetail getCustomerById(Long id){
	        EntityManager em = EMFService.get().createEntityManager();
        try {
        	BillDetail customer = em.find(BillDetail.class, id);
        	return customer;
		} catch (Exception e) {
			return null;
		} finally {
        	em.close();
        }
	}
}
