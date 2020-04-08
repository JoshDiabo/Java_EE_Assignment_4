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
import static org.junit.Assert.assertThat;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.algonquincollege.cst8277.ejb.EmployeeBean;
import com.algonquincollege.cst8277.models.EmployeePojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

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


    @BeforeClass
    public static void oneTimeSetUp() throws Exception {
        logger.debug("oneTimeSetUp");
        feature = HttpAuthenticationFeature.basic("admin", "admin");
    }

    @AfterClass
    public static void oneTimeTearDown() {
        logger.debug("oneTimeTearDown");
        
        
    }
    
    // TODO - create 40 test-cases that send GET/PUT/POST/DELETE messages
    // to REST'ful endpoints for the EmployeeSystem entities using the JAX-RS
    // ClientBuilder APIs
    
    @Test
    public void test00_test_admin() {
        Client client = ClientBuilder.newClient();
        URI uri = UriBuilder
            .fromUri(APPLICATION_CONTEXT_ROOT + APPLICATION_API_VERSION)
            .scheme(HTTP_SCHEMA)
            .host(HOST)
            .port(PORT)
            .build();
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(SOME_RESOURCE);
        Response response = webTarget
            .request(APPLICATION_JSON)
            .get();
        assertThat(response.getStatus(), is(200));
    }
    
    
  
}