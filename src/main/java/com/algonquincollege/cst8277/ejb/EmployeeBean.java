/***************************************************************************f******************u************zz*******y**
 * File: SomeBean.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 * (Modified) @date 2020 03
 * 
 * Group Members:
 * Sam Heaton
 * Michael Norris
 * Josh Diabo
 * Daria Ponomareva
 *
 */
package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.models.EmployeePojo.ALL_EMPLOYEES_QUERY_NAME;
import static com.algonquincollege.cst8277.models.EmployeePojo.SINGLE_EMPLOYEE_QUERY_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.algonquincollege.cst8277.models.EmployeePojo;

@Stateless
public class EmployeeBean {

    @PersistenceContext(unitName = "assignment4-PU")
    protected EntityManager em;
    
    public List<EmployeePojo> findAllEmployees() {
        return em.createNamedQuery(ALL_EMPLOYEES_QUERY_NAME, EmployeePojo.class).getResultList();
    }

    public EmployeePojo getEmployeeById(int id) {
        EmployeePojo emp = em.createNamedQuery(SINGLE_EMPLOYEE_QUERY_NAME, EmployeePojo.class).setParameter("id", id).getSingleResult();
        //em.detach(emp);
        return emp;
    }
    
    /**
     * Persist an employee
     * @param emp The Employee to persist
     */
    public void persistEmployee(EmployeePojo emp) {
        em.persist(emp);
    }
    
    /**
     * Update an employee
     * @param emp The Employee with updated fields
     */
    public void updateEmployee(EmployeePojo emp) {
        em.detach(emp);
        em.merge(emp);
    }
    
    /**
     * Delete an employee
     * @param id The id of the employee to delete
     */
    public void deleteEmployeeById(int id) {
        EmployeePojo emp = em.createNamedQuery(SINGLE_EMPLOYEE_QUERY_NAME,
            EmployeePojo.class).setParameter("id", id).getSingleResult();
        
        em.remove(emp);
    }
    
    public void restartSequence() {
        em.createNativeQuery("ALTER TABLE Employee ALTER COLUMN EMP_ID RESTART WITH 3").executeUpdate();
    }
}