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

    public Object getEmployeeById(int id) {
        return em.createNamedQuery(SINGLE_EMPLOYEE_QUERY_NAME, EmployeePojo.class).setParameter("id", id).getSingleResult();
    }
    
    /**
     * Persist an employee
     * @param emp The Employee to persist
     */
    public void persistEmployee(EmployeePojo emp) {
        em.persist(emp);
    }
}