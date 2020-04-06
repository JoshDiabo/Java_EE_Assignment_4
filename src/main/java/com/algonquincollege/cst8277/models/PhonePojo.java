/***************************************************************************f******************u************zz*******y**
 * File: PhonePojo.java
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

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Phone class
 * 
 */
@Entity(name = "Phone")
@Table(name = "PHONE")
@AttributeOverride(name = "id", column = @Column(name="PHONE_ID"))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PHONE_TYPE", length = 1)
public abstract class PhonePojo extends PojoBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String areacode;
    protected String phoneNumber;
    protected EmployeePojo owningEmployee;

    // JPA requires each @Entity class have a default constructor
    public PhonePojo() {
        super();
    }

    public String getAreacode() {
        return areacode;
    }
    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phonenumber) {
        this.phoneNumber = phonenumber;
    }

    @ManyToOne
    @JoinColumn(name = "OWNING_EMP_ID")
    public EmployeePojo getOwningEmployee() {
        return owningEmployee;
    }
    public void setOwningEmployee(EmployeePojo owningEmployee) {
        this.owningEmployee = owningEmployee;
    }

}