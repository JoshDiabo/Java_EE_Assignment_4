/***************************************************************************f******************u************zz*******y**
 * File: SomeBean.java
 * Course materials (20W) CST 8277
 *
 * @author (original) Mike Norman
 * (Modified) @date 2020 03
 */
package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.models.EmployeePojo.ALL_EMPLOYEES_QUERY_NAME;
import static com.algonquincollege.cst8277.models.EmployeePojo.SINGLE_EMPLOYEE_QUERY_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}