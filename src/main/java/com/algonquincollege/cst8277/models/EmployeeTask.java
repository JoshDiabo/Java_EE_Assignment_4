/***************************************************************************f******************u************zz*******y**
 * File: EmployeeTask.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 * @date 2020 02
 *
 *
 * Daria Ponomareva
 * Student ID: 040648913
 *
 *
 *
 */
package com.algonquincollege.cst8277.models;

import javax.persistence.Column;

import javax.persistence.Embeddable;



@Embeddable
public class EmployeeTask {

    /**
     * task description
     */
    protected String description;
    /**
     * task done or not
     */
    protected boolean done;

    /**
     * constructor
     */
    public EmployeeTask() {
    }

    /**
     * 
     * @return task description
     */
    @Column(name = "TASK_DESCRIPTION")
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
     * @return done or not
     */
    public boolean isDone() {
        return done;
    }

    /**
     * 
     * @param done
     */
    public void setDone(boolean done) {
        this.done = done;
    }

}