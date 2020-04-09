package com.algonquincollege.cst8277.ejb;

import static com.algonquincollege.cst8277.models.ProjectPojo.SINGLE_PROJECT_QUERY_NAME;
import static com.algonquincollege.cst8277.models.ProjectPojo.ALL_PROJECTS_QUERY_NAME;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algonquincollege.cst8277.models.ProjectPojo;

@Stateless
public class ProjectBean {
    
    @PersistenceContext(unitName = "assignment4-PU")
    protected EntityManager em;
    
    /**
     * 
     * @return List<EmployeePojo>
     */
    public List<ProjectPojo> findAllProjects() {
        return em.createNamedQuery(ALL_PROJECTS_QUERY_NAME, ProjectPojo.class).getResultList();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public ProjectPojo getProjectById(int id) {
        ProjectPojo proj = em.createNamedQuery(SINGLE_PROJECT_QUERY_NAME, ProjectPojo.class)
            .setParameter("id", id).getSingleResult();
        return proj;
    }

    /**
     * Persist a project
     * 
     * @param emp The Project to persist
     */
    public void persistProject(ProjectPojo proj) {
        em.persist(proj);
    }

    /**
     * Update a project
     * 
     * @param emp The Project with updated fields
     */
    public void updateProject(ProjectPojo proj) {
        em.detach(proj);
        em.merge(proj);
    }

    /**
     * Delete a project
     * 
     * @param id The id of the project to delete
     */
    public void deleteProjectById(int id) {
        ProjectPojo proj = em.createNamedQuery(SINGLE_PROJECT_QUERY_NAME, ProjectPojo.class)
            .setParameter("id", id).getSingleResult();
        em.remove(proj);
    }

    /**
     * Restarts PROJECT id sequence
     */
    public void restartSequence() {
        em.createNativeQuery("ALTER TABLE Project ALTER COLUMN PROJ_ID RESTART WITH 1").executeUpdate();
    }
}
