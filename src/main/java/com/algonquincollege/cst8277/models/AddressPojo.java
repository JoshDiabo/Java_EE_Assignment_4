/***************************************************************************f******************u************zz*******y**
 * File: AddressPojo.java
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
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple Address class
 */

//JPA Annotations here
@Access(AccessType.PROPERTY)
@Entity(name = "Address")
@Table(name = "ADDRESS")

@AttributeOverride(name = "id", column = @Column(name="ADDR_ID"))
public class AddressPojo extends PojoBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /**
     * Employees city
     */
    protected String city;
    /**
     * Employees country
     */
    protected String country;
    /**
     * Employees postal code
     */
    protected String postal;
    /**
     * Employees state
     */
    protected String state;
    /**
     * Employees street
     */
    protected String street;

    

    /**
     * JPA requires each @Entity class have a default constructor
     */
    public AddressPojo() {
        super();
    }
    /**
     * 
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return postal
     */
    public String getPostal() {
        return postal;
    }

    /**
     * 
     * @param postal
     */
    public void setPostal(String postal) {
        this.postal = postal;
    }

    /**
     * 
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }
    
    /**
     * 
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * 
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }


}