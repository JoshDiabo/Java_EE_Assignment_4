/**
 * Group Members:
 * Sam Heaton
 * Michael Norris
 * Josh Diabo
 * Daria Ponomareva
 */
package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.utils.MyConstants.ADMIN_ROLE;
import static com.algonquincollege.cst8277.utils.MyConstants.EMPLOYEE_RESOURCE_NAME;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.algonquincollege.cst8277.ejb.EmployeeBean;
import com.algonquincollege.cst8277.models.EmployeePojo;

@Path(EMPLOYEE_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {
    
    @EJB
    protected EmployeeBean eBean;
    
    @GET
    @RolesAllowed(ADMIN_ROLE)
    public Response getAllEmployees() {
        return Response.ok(eBean.findAllEmployees()).build();
    }
    
    @POST
    @RolesAllowed(ADMIN_ROLE)
    public Response createNewEmployee(EmployeePojo emp) {
        try {
            eBean.persistEmployee(emp);
            return Response.ok(emp).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
    
    @GET
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public Response getEmployeeById(@PathParam("id") int id) {
        return Response.ok(eBean.getEmployeeById(id)).build();
    }
    
    @PUT
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public Response updateEmployeeById(@PathParam("id") int id, EmployeePojo emp) {
        
        try {
            System.out.println(emp.getFirstName());
            eBean.updateEmployee(emp);
            return Response.ok(emp).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
    
    @DELETE
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public Response deleteEmployeeById(@PathParam("id") int id) {
        try {
            eBean.deleteEmployeeById(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
    
    @GET
    @RolesAllowed(ADMIN_ROLE)
    @Path("RestartSequence")
    public Response restartSequence() {
        try {
            eBean.restartSequence();
            return Response.status(200).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
    
}
