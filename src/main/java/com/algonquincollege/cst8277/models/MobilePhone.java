/***************************************************************************f******************u************zz*******y**
 * File: MobilePhone.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 * @date 2020 02
 * 
 * Group Members:
 * Sam Heaton
 * Michael Norris
 * Josh Diabo
 * Daria Ponomareva
 *
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
//import javax.persistence.PrimaryKeyJoinColumn;

@Entity
//@PrimaryKeyJoinColumn(name = "m_phone_id")
@DiscriminatorValue("M")
public class MobilePhone extends PhonePojo implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String provider;

    public MobilePhone() {
        super();
    }

    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }
}