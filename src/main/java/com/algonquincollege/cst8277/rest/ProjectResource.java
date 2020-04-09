package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.utils.MyConstants.ADMIN_ROLE;
import static com.algonquincollege.cst8277.utils.MyConstants.PROJECT_RESOURCE_NAME;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.algonquincollege.cst8277.ejb.ProjectBean;
import com.algonquincollege.cst8277.models.ProjectPojo;

@Path(PROJECT_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {
    
    @EJB
    protected ProjectBean pBean;
    
    /**
     * 
     * @return
     */
    @GET
    @RolesAllowed(ADMIN_ROLE)
    public Response getAllProjects() {
        return Response.ok(pBean.findAllProjects()).build();
    }
    
    /**
     * 
     * @param emp
     * @return
     */
    @POST
    @RolesAllowed(ADMIN_ROLE)
    public Response createNewEmployee(ProjectPojo proj) {
        try {
            pBean.persistProject(proj);
            return Response.ok(proj).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
    
    /**
     * 
     * @param id
     * @return
     */
    @GET
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public Response getProjectById(@PathParam("id") int id) {
        return Response.ok(pBean.getProjectById(id)).build();
    }
    
    /**
     * 
     * @param id
     * @param emp
     * @return
     */
    @PUT
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public Response updateProjectById(@PathParam("id") int id, ProjectPojo emp) {
        try {
            pBean.updateProject(emp);
            return Response.ok(emp).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
    
    /**
     * 
     * @param id
     * @return
     */
    @DELETE
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public Response deleteProjectById(@PathParam("id") int id) {
        try {
            pBean.deleteProjectById(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
    
    /**
     * 
     * @return
     */
    @GET
    @RolesAllowed(ADMIN_ROLE)
    @Path("RestartSequence")
    public Response restartSequence() {
        try {
            pBean.restartSequence();
            return Response.status(200).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
}