package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.utils.MyConstants.ADMIN_ROLE;
import static com.algonquincollege.cst8277.utils.MyConstants.PHONE_RESOURCE_NAME;

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

import com.algonquincollege.cst8277.ejb.AddressBean;
import com.algonquincollege.cst8277.ejb.PhoneBean;
import com.algonquincollege.cst8277.models.AddressPojo;
import com.algonquincollege.cst8277.models.PhonePojo;

/**
 * Controls GET, POST, PUT, DELETE REST API Methods for the
 * Phone entity
 */
@Path(PHONE_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PhoneResource {
    
    /**Handles all business logic*/
    @EJB
    protected PhoneBean pBean;
    
    /**
     * GET route for retrieving all Phones
     */
    @GET
    @RolesAllowed(ADMIN_ROLE)
    public Response getAllPhones() {
        return Response.ok(pBean.findAllPhones()).build();
    }
    
    /**
     * Posts a new phone
     * @param address the address to post
     */
    @POST
    @RolesAllowed(ADMIN_ROLE)
    public Response createNewPhone(PhonePojo phone) {
        try {
            pBean.persistPhone(phone);
            return Response.ok(phone).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
    
    /**
     * Retrieves a phone by an id
     * @param id the associated phone id
     */
    @GET
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public Response getPhoneById(@PathParam("id") int id) {
        return Response.ok(pBean.getPhoneById(id)).build();
    }
    
    /**
     * Updates a phone with the given id
     * @param id the id of the phone to update
     * @param phone the address containing the updated fields
     */
    @PUT
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public Response updatePhoneById(@PathParam("id") int id, PhonePojo phone) {
        
        try {
            pBean.updatePhone(phone);
            return Response.ok(phone).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
    
    /**
     * Deletes a phone with the given id
     * @param id the id of the associated phone
     */
    @DELETE
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public Response deletePhoneById(@PathParam("id") int id) {
        try {
            pBean.deletePhoneById(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
    
    /**
     * Restarts the Phone id sequence
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
