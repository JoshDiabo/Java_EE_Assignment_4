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

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algonquincollege.cst8277.models.AddressPojo;

import static com.algonquincollege.cst8277.models.AddressPojo.ALL_ADDRESSES_QUERY_NAME;
import static com.algonquincollege.cst8277.models.AddressPojo.SINGLE_ADDRESS_QUERY_NAME;

import java.util.List;

/**
 * Contains business logic for Address entity
 */
@Stateless
public class AddressBean {
    
    /**EntityManager for persistence context*/
    @PersistenceContext(unitName = "assignment4-PU")
    protected EntityManager em;

    /**
     * Find all addresses
     * @return List<AddressPojo> a list of all addresses
     */
    public List<AddressPojo> findAllAddresses() {
        return em.createNamedQuery(ALL_ADDRESSES_QUERY_NAME, AddressPojo.class).getResultList();
    }

    /**
     * Find an address by id
     * @param id the id of the address
     * @return AddressPojo the address with the associated id
     */
    public AddressPojo getAddressById(int id) {
        AddressPojo address = em.createNamedQuery(SINGLE_ADDRESS_QUERY_NAME, AddressPojo.class).setParameter("id", id).getSingleResult();

        return address;
    }
    
    /**
     * Persist an address
     * @param address The Address to persist
     */
    public void persistAddress(AddressPojo address) {
        em.persist(address);
    }
    
    /**
     * Update an address
     * @param address The Address with updated fields
     */
    public void updateAddress(AddressPojo address) {
        em.detach(address);
        em.merge(address);
    }
    
    /**
     * Delete an address
     * @param id The id of the address to delete
     */
    public void deleteAddressById(int id) {
        AddressPojo address = em.createNamedQuery(SINGLE_ADDRESS_QUERY_NAME,
            AddressPojo.class).setParameter("id", id).getSingleResult();
        
        em.remove(address);
    }
    
    /**
     * Restarts ADDRESS id sequence
     */
    public void restartSequence() {
        em.createNativeQuery("ALTER TABLE Address ALTER COLUMN ADDR_ID RESTART WITH 1").executeUpdate();
    }
}
