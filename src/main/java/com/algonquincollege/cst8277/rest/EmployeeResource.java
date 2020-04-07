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
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.algonquincollege.cst8277.ejb.EmployeeBean;

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
    
    @GET
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public Response getEmployeeById(@PathParam("id") int id) {
        return Response.ok(eBean.getEmployeeById(id)).build();
    }

}
