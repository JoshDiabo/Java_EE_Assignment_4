/***************************************************************************f******************u************zz*******y**
 * File: ProjectPojo.java
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
 * Daria Ponomareva
 * Student ID: 040648913
 *
 */
package com.algonquincollege.cst8277.models;

import static com.algonquincollege.cst8277.models.ProjectPojo.ALL_PROJECTS_QUERY_NAME;
import static com.algonquincollege.cst8277.models.ProjectPojo.SINGLE_PROJECT_QUERY_NAME;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.AttributeOverride;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name = "Project")
@Table(name = "PROJECT")
@AttributeOverride(name = "id", column = @Column(name="PROJ_ID"))
@NamedQueries({
    @NamedQuery(name=ALL_PROJECTS_QUERY_NAME, query = "select e from Project e"),
    @NamedQuery(name=SINGLE_PROJECT_QUERY_NAME, query = "select e from Project e where e.id = :id")
})
public class ProjectPojo extends PojoBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    public static final String ALL_PROJECTS_QUERY_NAME =
        "allEmployees";
    public static final String SINGLE_PROJECT_QUERY_NAME =
        "singleEmployees";
    public static final String RESET_SEQUENCE =
        "resetSequence";
    
    /**
     * project description
     */
    protected String description;
    /**
     * project name
     */
    protected String name;
    /**
     * set of employees
     */
    protected Set<EmployeePojo> employees;
    

    // JPA requires each @Entity class have a default constructor
    public ProjectPojo() {
    }
    
    /**
     * 
     * @return employees
     */
    @ManyToMany(mappedBy="projects")
    public Set<EmployeePojo> getEmployees(){
        return employees;
    }
    
    /**
     * 
     * @param employees
     */
    public void setEmployees(Set<EmployeePojo> employees) {
        this.employees = employees;
    }
    
    /**
     * 
     * @return project description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }



}