/***************************************************************************f******************u************zz*******y**
 * File: AddressBean.java
 * Course materials (20W) CST 8277
 * 
 * Group Members:
 * Sam Heaton
 * Michael Norris
 * Josh Diabo
 * Daria Ponomareva
 */


package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.models.PhonePojo.ALL_PHONES_QUERY_NAME;
import static com.algonquincollege.cst8277.models.PhonePojo.SINGLE_PHONES_QUERY_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algonquincollege.cst8277.models.PhonePojo;

/**
 * Handles the business logic for phones
 */
@Stateless
public class PhoneBean {
    
    /**EntityManager for persistence context*/
    @PersistenceContext(unitName = "assignment4-PU")
    protected EntityManager em;
    
    /**
     * Find all phones
     * @return List<PhonePojo> a list of all phones
     */
    public List<PhonePojo> findAllPhones() {
        return em.createNamedQuery(ALL_PHONES_QUERY_NAME, PhonePojo.class).getResultList();
    }
    
    /**
     * Find a phone by id
     * @param id the id of the phone
     * @return PhonePojo the phone with the associated id
     */
    public PhonePojo getPhoneById(int id) {
        PhonePojo phone = em.createNamedQuery(SINGLE_PHONES_QUERY_NAME, PhonePojo.class).setParameter("id", id).getSingleResult();

        return phone;
    }
    
    /**
     * Persist a phone
     * @param phone The phone to persist
     */
    public void persistPhone(PhonePojo phone) {
        em.persist(phone);
    }
    
    /**
     * Update a phone
     * @param phone The phone with updated fields
     */
    public void updatePhone(PhonePojo phone) {
        em.detach(phone);
        em.merge(phone);
    }
    
    /**
     * Delete a phone
     * @param id The id of the phone to delete
     */
    public void deletePhoneById(int id) {
        PhonePojo phone = em.createNamedQuery(SINGLE_PHONES_QUERY_NAME,
            PhonePojo.class).setParameter("id", id).getSingleResult();
        
        em.remove(phone);
    }
    
    /**
     * Restarts PHONE id sequence
     */
    public void restartSequence() {
        em.createNativeQuery("ALTER TABLE Phone ALTER COLUMN PHONE_ID RESTART WITH 1").executeUpdate();
    }
}
