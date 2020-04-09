/***************************************************************************f******************u************zz*******y**
 * File: EmployeeSystemTestSuite.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 *
 * @date 2020 03
 *
 * TODO - add to this class for Assignment 4
 */
package com.algonquincollege.cst8277;

import static com.algonquincollege.cst8277.utils.MyConstants.APPLICATION_API_VERSION;
import static com.algonquincollege.cst8277.utils.MyConstants.EMPLOYEE_RESOURCE_NAME;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.CoreMatchers.not;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.ObjectInputStream;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.algonquincollege.cst8277.models.EmployeePojo;
import com.fasterxml.jackson.databind.ObjectMapper;


public class EmployeeSystemTestSuite {
    private static final Class<?> _thisClaz = MethodHandles.lookup().lookupClass();
    private static final Logger logger = LoggerFactory.getLogger(_thisClaz);

    static final String APPLICATION_CONTEXT_ROOT = "make-progress";
    static final String HTTP_SCHEMA = "http";
    static final String HOST = "localhost";
    static final int PORT = 9090; //TODO - use your actual Payara port number
    
    static final String DEFAULT_ADMIN_USER = "admin";
    static final String DEFAULT_ADMIN_USER_PW = "admin";
    static final String DEFAULT_USER = "user1";
    static final String DEFAULT_USER_PW = "user1";
    
    static final String SOME_RESOURCE =
        //some JAX-RS resource the 'admin' user has security privileges to invokd
        "employees";

    // test fixture(s)
    static HttpAuthenticationFeature feature;
    
    /**
     * Moved uri to here and initilize in <em>BeforeClass</em>. Seems unnecessary to keep making it.
     * -- Josh
     */
    static URI uri;
    
    static Client client;
    
    static ObjectMapper map;


    @BeforeClass
    public static void oneTimeSetUp() throws Exception {
        logger.debug("oneTimeSetUp");
        feature = HttpAuthenticationFeature.basic("admin", "admin");
        
        //Josh Defined
        uri = UriBuilder
            .fromUri(APPLICATION_CONTEXT_ROOT + APPLICATION_API_VERSION)
            .scheme(HTTP_SCHEMA)
            .host(HOST)
            .port(PORT)
            .build();
        
        map = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @AfterClass
    public static void oneTimeTearDown() {
        logger.debug("oneTimeTearDown");
        
        
    }
    
    @Before
    public void beforeEach() {
        client = ClientBuilder.newClient();
    }
    
    
    
    // TODO - create 40 test-cases that send GET/PUT/POST/DELETE messages
    // to REST'ful endpoints for the EmployeeSystem entities using the JAX-RS
    // ClientBuilder APIs
    
    @Test
    public void test00_test_admin() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(SOME_RESOURCE);
        Response response = webTarget
            .request(APPLICATION_JSON)
            .get();
        assertThat(response.getStatus(), is(200));
    }
    
    /**
     * Testing inserting a new employee
     */
    @Ignore
    public void test01_persist_employee() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(SOME_RESOURCE);
        
        EmployeePojo newEmp = new EmployeePojo();
        newEmp.setFirstName("John-O");
        newEmp.setLastName("Pono");
        
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .post(Entity.json(newEmp), Response.class);
       
        
        assertEquals(200, response.getStatus());
    }
    
    @Test
    public void test15_update_employee_by_id() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(SOME_RESOURCE)
            .path("1");
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .get();
        
        String emp1String = response.readEntity(String.class);
        EmployeePojo emp1 = new EmployeePojo();
        
        try {
            emp1 = map.readValue(emp1String, EmployeePojo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        emp1.setFirstName("Testy");
 
        
        response = webTarget
            .request(APPLICATION_JSON)
            .put(Entity.json(emp1), Response.class);

            
    }
}