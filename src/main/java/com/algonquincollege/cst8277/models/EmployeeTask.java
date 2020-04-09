/***************************************************************************f******************u************zz*******y**
 * File: EmployeeTask.java
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
 *
 *
 */
package com.algonquincollege.cst8277.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;

import javax.persistence.Embeddable;

@Embeddable
public class EmployeeTask implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String description;
    protected Boolean done;
    protected LocalDateTime taskStart;
    protected LocalDateTime taskEndDate;

    /**
     * Default constructor
     */
    public EmployeeTask() {
    }

    /**
     * 
     * @return
     */
    @Column(name="TASK_DESCRIPTION")
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
     * @return
     */
    @Column(name="TASK_DONE")
    public Boolean isDone() {
        return done;
    }

    /**
     * 
     * @param done
     */
    public void setDone(Boolean done) {
        this.done = done;
    }

    /**
     * 
     * @return
     */
    @Column(name="TASK_START")
    public LocalDateTime getTaskStart() {
        return taskStart;
    }

    /**
     * 
     * @param taskStart
     */
    public void setTaskStart(LocalDateTime taskStart) {
        this.taskStart = taskStart;
    }

    /**
     * 
     * @return
     */
    @Column(name="TASK_END_DATE")
    public LocalDateTime getTaskEndDate() {
        return taskEndDate;
    }

    /**
     * 
     * @param taskDone
     */
    public void setTaskEndDate(LocalDateTime taskDone) {
        this.taskEndDate = taskDone;
    }
}