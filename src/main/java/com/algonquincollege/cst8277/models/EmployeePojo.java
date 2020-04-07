/***************************************************************************f******************u************zz*******y**
 * File: EmployeePojo.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 * (Modified) @date 2020 02
 *
 * Copyright (c) 1998, 2009 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Original @authors dclarke, mbraeuer
 */
package com.algonquincollege.cst8277.models;

import static com.algonquincollege.cst8277.models.EmployeePojo.ALL_EMPLOYEES_QUERY_NAME;
import static com.algonquincollege.cst8277.models.EmployeePojo.SINGLE_EMPLOYEE_QUERY_NAME;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The Employee class demonstrates several JPA features:
 * <ul>
 * <li>OneToOne relationship
 * <li>OneToMany relationship
 * <li>ManyToMany relationship
 * </ul>
 */
@Entity(name = "Employee")
@Table(name = "EMPLOYEE")
@AttributeOverride(name = "id", column = @Column(name="EMP_ID"))
@NamedQueries({
    @NamedQuery(name=ALL_EMPLOYEES_QUERY_NAME, query = "select e from Employee e"),
    @NamedQuery(name=SINGLE_EMPLOYEE_QUERY_NAME, query = "select e from Employee e where e.emp_id = :id")
})
public class EmployeePojo extends PojoBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    public static final String ALL_EMPLOYEES_QUERY_NAME =
        "allEmployees";
    public static final String SINGLE_EMPLOYEE_QUERY_NAME =
        "singleEmployees";

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String title;
    protected Double salary;
    protected List<PhonePojo> phones = new ArrayList<>();

    // JPA requires each @Entity class have a default constructor
    public EmployeePojo() {
        super();
    }
    /**
     * @return the value for firstName
     */
    @Column(name = "FNAME")
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName new value for firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the value for lastName
     */
    @Column(name = "LNAME")
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName new value for lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the value for email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email new value for email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the value for title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title new value for title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the value for salary
     */
    public Double getSalary() {
        return salary;
    }
    /**
     * @param salary new value for salary
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "owningEmployee", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<PhonePojo> getPhones() {
        return phones;
    }
    public void setPhones(List<PhonePojo> phones) {
        this.phones = phones;
    }
    public void addPhone(PhonePojo phone) {
        getPhones().add(phone);
        phone.setOwningEmployee(this);
    }

}