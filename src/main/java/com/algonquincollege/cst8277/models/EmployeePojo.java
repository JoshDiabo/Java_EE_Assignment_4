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
 * 
 * Group Members:
 * Sam Heaton
 * Michael Norris
 * Josh Diabo
 * Daria Ponomareva
 */
package com.algonquincollege.cst8277.models;

import static com.algonquincollege.cst8277.models.EmployeePojo.ALL_EMPLOYEES_QUERY_NAME;
import static com.algonquincollege.cst8277.models.EmployeePojo.SINGLE_EMPLOYEE_QUERY_NAME;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @NamedQuery(name=SINGLE_EMPLOYEE_QUERY_NAME, query = "select e from Employee e where e.id = :id")
})
public class EmployeePojo extends PojoBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    public static final String ALL_EMPLOYEES_QUERY_NAME =
        "allEmployees";
    public static final String SINGLE_EMPLOYEE_QUERY_NAME =
        "singleEmployees";
    public static final String RESET_SEQUENCE =
        "resetSequence";

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String title;
    protected Double salary;
    protected List<PhonePojo> phones = new ArrayList<>();
    protected Set<ProjectPojo> projects = new HashSet<>();
    protected List<EmployeeTask> tasks = new ArrayList<>();
    protected SecurityUser sUser;

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
     * 
     * @param ep
     */
    public void addProject(ProjectPojo ep) {
        getProjects().add(ep);
    }
    
    /**
     * 
     * @return projects
     */
    @ManyToMany
    @JoinTable(name = "EMP_PROJ", joinColumns = @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID"), inverseJoinColumns = @JoinColumn(name = "PROJ_ID", referencedColumnName = "PROJ_ID"))
    public Set<ProjectPojo> getProjects() {
        return projects;
    }
    @OneToOne
    @JoinColumn(name="USER_ID")
    public SecurityUser getsUser() {
       return sUser;
   }
   
   public void setsUser(SecurityUser sUser) {
       this.sUser = sUser;
   }

    /**
     * 
     * @param projects
     */
    public void setProjects(Set<ProjectPojo> projects) {
        this.projects = projects;
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
    /**
     * 
     * @return tasks
     */
    @ElementCollection
    @CollectionTable(name = "EMPLOYEE_TASKS", joinColumns = @JoinColumn(name = "OWNING_EMP_ID"))
    public List<EmployeeTask> getTasks() {
        return tasks;
    }
    
    /**
     * 
     * @param tasks
     */
    public void setTasks(List<EmployeeTask> tasks) {
        this.tasks = tasks;
    }
    /**
     * 
     * @param t
     */
    public void addTask(EmployeeTask t) {
        getTasks().add(t);
        
    }
    

    /**
     * 
     * @return
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "owningEmployee", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<PhonePojo> getPhones() {
        return phones;
    }
    
    /**
     * 
     * @param phones
     */
    public void setPhones(List<PhonePojo> phones) {
        this.phones = phones;
    }
    
    /**
     * 
     * @param phone
     */
    public void addPhone(PhonePojo phone) {
        getPhones().add(phone);
        phone.setOwningEmployee(this);
    }
    
    /**
     * 
     * @return
     */
    @ElementCollection
    @CollectionTable(name="EMPLOYEE_TASKS", joinColumns = @JoinColumn(name="OWNING_EMP_ID"))
    public List<EmployeeTask> getTasks() {
        return tasks;
    }
    
    /**
     * 
     * @param tasks
     */
    public void setTasks(List<EmployeeTask> tasks) {
        this.tasks = tasks;
    }
    
    /**
     * 
     * @param employeeTask
     */
    public void addTask(EmployeeTask employeeTask) {
        getTasks().add(employeeTask);
    }
}