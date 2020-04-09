/**
 * File: AddressResurce.java
 * Group Members:
 * Sam Heaton
 * Michael Norris
 * Josh Diabo
 * Daria Ponomareva
 */

package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.utils.MyConstants.ADMIN_ROLE;
import static com.algonquincollege.cst8277.utils.MyConstants.ADDRESS_RESOURCE_NAME;

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

import com.algonquincollege.cst8277.ejb.AddressBean;
import com.algonquincollege.cst8277.models.AddressPojo;

/**
 * Controls GET, POST, PUT, DELETE REST API Methods for the
 * Address entity
 */
@Path(ADDRESS_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {
    
    /**Handles all business logic*/
    @EJB
    protected AddressBean aBean;
    
    /**
     * GET route for retrieving all Address
     */
    @GET
    @RolesAllowed(ADMIN_ROLE)
    public Response getAllEmployees() {
        return Response.ok(aBean.findAllAddresses()).build();
    }
    
    /**
     * Posts a new address
     * @param address the address to post
     */
    @POST
    @RolesAllowed(ADMIN_ROLE)
    public Response createNewAddress(AddressPojo address) {
        try {
            aBean.persistAddress(address);
            return Response.ok(address).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
    
    /**
     * Retrieves an address by an id
     * @param id the associated address id
     */
    @GET
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public Response getAddressById(@PathParam("id") int id) {
        return Response.ok(aBean.getAddressById(id)).build();
    }
    
    /**
     * Updates an address with the given id
     * @param id the id of the address to update
     * @param address the address containing the updated fields
     */
    @PUT
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public Response updateEmployeeById(@PathParam("id") int id, AddressPojo address) {
        
        try {
            aBean.updateAddress(address);
            return Response.ok(address).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
    
    /**
     * Deletes an address with the given id
     * @param id the id of the associated address
     */
    @DELETE
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public Response deleteAddressById(@PathParam("id") int id) {
        try {
            aBean.deleteAddressById(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
    
    /**
     * Restarts the Address id sequence
     */
    @GET
    @RolesAllowed(ADMIN_ROLE)
    @Path("RestartSequence")
    public Response restartSequence() {
        try {
            aBean.restartSequence();
            return Response.status(200).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

}
