/***************************************************************************f******************u************zz*******y**
 * File: EmployeeSystemTestSuite.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 *
 * @date 2020 03
 *
 * 
 * Group Members:
 * Sam Heaton
 * Michael Norris
 * Josh Diabo
 * Daria Ponomareva
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.ObjectInputStream;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.json.JsonArray;
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
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.algonquincollege.cst8277.models.AddressPojo;
import com.algonquincollege.cst8277.models.EmployeePojo;
import com.algonquincollege.cst8277.models.HomePhone;
import com.algonquincollege.cst8277.models.MobilePhone;
import com.algonquincollege.cst8277.models.PhonePojo;
import com.algonquincollege.cst8277.models.ProjectPojo;
import com.algonquincollege.cst8277.models.WorkPhone;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Unit tests to test API GET, PUT, POST, DELETE
 * for the EmployeeSystem
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeSystemTestSuite {
    
    /**
     * Handles class lookups
     */
    private static final Class<?> _thisClaz = MethodHandles.lookup().lookupClass();
    
    /**
     * Logging API
     */
    private static final Logger logger = LoggerFactory.getLogger(_thisClaz);

    /**
     * Defines root of the api URL
     */
    static final String APPLICATION_CONTEXT_ROOT = "make-progress";
    
    /**
     * Defines the HTTP type
     */
    static final String HTTP_SCHEMA = "http";
    
    /**
     * The server hosting the API
     */
    static final String HOST = "localhost";

    /**
     * Port hosting the API
     */
    static final int PORT = 9090; //TODO - use your actual Payara port number

    
    /**
     * Defines ADMIN_USER user name
     */
    static final String DEFAULT_ADMIN_USER = "admin";
    
    /**
     * Defines ADMIN_USER password
     */
    static final String DEFAULT_ADMIN_USER_PW = "admin";
    
    /**
     * Defines DEFAULT_USER user name
     */
    static final String DEFAULT_USER = "user1";
    
    /**
     * Defines DEFAULT_USER password
     */
    static final String DEFAULT_USER_PW = "user1";
    
    /**
     * Employee resource endpoint
     */
    static final String EMPLOYEE_RESOURCE =
        //some JAX-RS resource the 'admin' user has security privileges to invokd
        "employees";
    
    /**
     * Address resource endpoint
     */
    static final String ADDRESS_RESOURCE =
        "addresses";
    
    static final String PHONE_RESOURCE =
        "phones";
    

    /**
     * Project resource endpoint
     */

    static final String PROJECT_RESOURCE =
        "projects";

    /**
     * Defines authentication parameters
     */
    static HttpAuthenticationFeature feature;
    
    /**
     * Moved uri to here and initilize in <em>BeforeClass</em>. Seems unnecessary to keep making it.
     * -- Josh
     */
    static URI uri;

    /**
     * Defines the client
     */
    static Client client;
    
    /**
     * Jackson API for handling JSON
     */
    static ObjectMapper map;

    /**
     * Executes before all tests
     * @throws Exception
     */
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
        
        client = ClientBuilder.newClient();
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(EMPLOYEE_RESOURCE)
            .path("RestartSequence");
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .get();
        
        webTarget = client
            .register(feature)
            .target(uri)
            .path(ADDRESS_RESOURCE)
            .path("RestartSequence");

        response = webTarget
            .request(APPLICATION_JSON)
            .get();
        
        webTarget = client
            .register(feature)
            .target(uri)
            .path(PROJECT_RESOURCE)
            .path("RestartSequence");

        response = webTarget
            .request(APPLICATION_JSON)
            .get();
        
        webTarget = client
            .register(feature)
            .target(uri)
            .path(PHONE_RESOURCE)
            .path("RestartSequence");

        response = webTarget
            .request(APPLICATION_JSON)
            .get();
    }
  
    /**
     * Executes after all tests
     */
    @AfterClass
    public static void oneTimeTearDown() {
        
        logger.debug("oneTimeTearDown");
        
        
    }
    
    /**
     * Executes before each test
     */
    @Before
    public void beforeEach() {
        client = ClientBuilder.newClient();
    }
    
    
    
    // TODO - create 40 test-cases that send GET/PUT/POST/DELETE messages
    // to REST'ful endpoints for the EmployeeSystem entities using the JAX-RS
    // ClientBuilder APIs
   
    /**
     * Tests admin credentials against api
     */
    @Test
    public void test00_test_admin() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(EMPLOYEE_RESOURCE);

        Response response = webTarget
            .request(APPLICATION_JSON)
            .get();
        assertThat(response.getStatus(), is(200));
    }
    
    /**
     * Testing inserting a new employee
     */
    @Test
    public void test01_persist_employee() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(EMPLOYEE_RESOURCE);
        
        EmployeePojo newEmp = new EmployeePojo();
        newEmp.setFirstName("John-O");
        newEmp.setLastName("Pono");
        
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .post(Entity.json(newEmp), Response.class);
       
        
        assertEquals(200, response.getStatus());
    }

    /**
     * Testing inserting a new project
     */
    @Test
    public void test02_persist_project() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PROJECT_RESOURCE);
        
        ProjectPojo newProj = new ProjectPojo();
        newProj.setName("First Project");
        newProj.setDescription("This is our first project!");
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .post(Entity.json(newProj), Response.class);
        
        assertEquals(200, response.getStatus());
    }

    /**
     * Testing inserting a new address
     */
    @Test
    public void test03_persist_address() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(ADDRESS_RESOURCE);
        
        AddressPojo newAddress = new AddressPojo();
        newAddress.setStreet("Blueberry");
        newAddress.setCity("Ottawa");
        newAddress.setCountry("France");
    
        Response response = webTarget
            .request(APPLICATION_JSON)
            .post(Entity.json(newAddress), Response.class);
        
        assertEquals(200, response.getStatus());
    }
    
    /**
     * Testing inserts a mobile phone with a new employee
     */
    @Test
    public void test04_persist_mobile() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PHONE_RESOURCE);
        
        EmployeePojo e = new EmployeePojo();
        e.setFirstName("Niko");
        e.setLastName("Oli");
        
        
        MobilePhone newPhone = new MobilePhone();
        newPhone.setPhoneType("M");
        newPhone.setAreacode("613");
        newPhone.setPhoneNumber("919-0101");
        newPhone.setProvider("Rogers");
        e.addPhone(newPhone);
        
        String newPhoneString = "";
        try {
            newPhoneString = map.writeValueAsString(newPhone);
        } catch (Exception e1) {
            e1.getStackTrace();
        }
        
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .post(Entity.json(newPhoneString), Response.class);
        
        assertEquals(200, response.getStatus());
        
    }
    
    /**
     * Testing inserts a home phone with a new employee
     */
    @Test
    public void test05_persist_home() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PHONE_RESOURCE);
        
        EmployeePojo e = new EmployeePojo();
        e.setFirstName("Rio");
        e.setLastName("Ugo");
        
        
        HomePhone newHomePhone = new HomePhone();
        newHomePhone.setAreacode("647");
        newHomePhone.setPhoneNumber("877-5555");
        newHomePhone.setPhoneType("H");
        
        e.addPhone(newHomePhone);
        
        String newPhoneString = "";
        try {
            newPhoneString = map.writeValueAsString(newHomePhone);
        } catch (Exception e1) {
            e1.getStackTrace();
        }
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .post(Entity.json(newPhoneString), Response.class);
        
        assertEquals(200, response.getStatus());
        
    }
    
    /**
     * Testing inserts a work phone with a new employee
     */
    @Test
    public void test06_persist_work() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PHONE_RESOURCE);
        
        EmployeePojo e = new EmployeePojo();
        e.setFirstName("May");
        e.setLastName("Pearson");
        
        
        WorkPhone newWorkPhone = new WorkPhone();
        newWorkPhone.setAreacode("901");
        newWorkPhone.setPhoneNumber("800-2222");
        newWorkPhone.setPhoneType("W");
        
        e.addPhone(newWorkPhone);
        
        String newPhoneString = "";
        try {
            newPhoneString = map.writeValueAsString(newWorkPhone);
        } catch (Exception e1) {
            e1.getStackTrace();
        }
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .post(Entity.json(newPhoneString), Response.class);
        
        assertEquals(200, response.getStatus());
    }
  
   /**
    * Reading all employees from the api
    */
    @Test
    public void test07_read_employees()  {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(EMPLOYEE_RESOURCE);
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .get();
        
        String ex = response.readEntity(String.class);
    
        EmployeePojo[] e1 = {};
        
        try {
            e1 = map.readValue(ex, EmployeePojo[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
  
        assertEquals(200, response.getStatus());
    }
    
    /**
     * Read all projects from the api
     */
    @Test
    public void test08_read_projects() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PROJECT_RESOURCE);
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .get();
        
        
        String p = response.readEntity(String.class);
        
        ProjectPojo[] p1 = {};
        try {
            p1 = map.readValue(p, ProjectPojo[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
       
        assertEquals(200, response.getStatus());
    }
    
    /**
     * Reading all addresses from the api
     */
    @Test
    public void test09_read_addresses() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(ADDRESS_RESOURCE);
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .get();
        
        
        String a = response.readEntity(String.class);
        
        AddressPojo[] a1 = {};
        try {
            a1 = map.readValue(a, AddressPojo[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
       
        assertEquals(200, response.getStatus());
    }
    
    /**
     * Reading all phones from the api
     */
    @Test
    public void test10_read_phones() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PHONE_RESOURCE);

        Response response = webTarget
            .request(APPLICATION_JSON)
            .get();


        String p = response.readEntity(String.class);

        PhonePojo[] p1 = {};
        try {
            p1 = map.readValue(p, PhonePojo[].class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(200, response.getStatus());
    }

    @Test
    public void test15_update_employee_by_id() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(EMPLOYEE_RESOURCE)
            .path("3");


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


        emp1.setFirstName("Bono");


        String newEmp = "";
        try {
            newEmp = map.writeValueAsString(emp1);
        } catch (Exception e) {
            e.getStackTrace();
        }

        response = webTarget
            .request(APPLICATION_JSON)
            .put(Entity.json(newEmp), Response.class);
        
        assertEquals(200, response.getStatus());
    }
    
    /**
     * Updates a Project with the specified id
     */
    @Test
    public void test16_update_project_by_id() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PROJECT_RESOURCE)
            .path("1");
      
        
         Response response = webTarget
           .request(APPLICATION_JSON)
           .get();
            
         String project1String = response.readEntity(String.class);
         ProjectPojo project1 = new ProjectPojo();
              

         try {
             project1 = map.readValue(project1String, ProjectPojo.class);
         } catch (Exception e) {
             e.printStackTrace();
         }

         project1.setName("Firs Updated Project");

         String newProject = "";
         try {
             newProject = map.writeValueAsString(project1);
         } catch (Exception e) {
             e.getStackTrace();
         }

         response = webTarget
             .request(APPLICATION_JSON)
             .put(Entity.json(newProject), Response.class);

         assertEquals(200, response.getStatus());
    }
    
    /**
     * Updates address with the specified id
     */
    @Test
    public void test17_update_address_by_id() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(ADDRESS_RESOURCE)
            .path("1");
      
        
         Response response = webTarget
           .request(APPLICATION_JSON)
           .get();
            
         String address1String = response.readEntity(String.class);
         AddressPojo address1 = new AddressPojo();
              

         try {
             address1 = map.readValue(address1String, AddressPojo.class);
         } catch (Exception e) {
             e.printStackTrace();
         }


         address1.setCountry("Spain");

         String newAddress = "";
         try {
             newAddress = map.writeValueAsString(address1);
         } catch (Exception e) {
             e.getStackTrace();
         }

         response = webTarget
             .request(APPLICATION_JSON)
             .put(Entity.json(newAddress), Response.class);

         assertEquals(200, response.getStatus());
    }
    
    /**
     * Updates mobile phone with the specified id
     */
    @Test
    public void test18_update_mobile_phone_by_id() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PHONE_RESOURCE)
            .path("1");


         Response response = webTarget
           .request(APPLICATION_JSON)
           .get();

         String phone1String = response.readEntity(String.class);
         PhonePojo phone1 = new MobilePhone();


         try {
             phone1 = map.readValue(phone1String, PhonePojo.class);
         } catch (Exception e) {
             e.printStackTrace();
         }

         phone1.setAreacode("666");

         String newPhone = "";
         try {
             newPhone = map.writeValueAsString(phone1);
         } catch (Exception e) {
             e.getStackTrace();
         }

         response = webTarget
             .request(APPLICATION_JSON)
             .put(Entity.json(newPhone), Response.class);

         assertEquals(200, response.getStatus());
    }
   
    /**
     * Deletes employee with the specified id
     */
    @Test
    public void test19_delete_employee_by_id() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(EMPLOYEE_RESOURCE)
            .path("3");
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .delete();
        
        assertEquals(200, response.getStatus());
    }
    
    /**
     * Deletes project with the specified id
     */
    @Test
    public void test20_delete_project_by_id() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PROJECT_RESOURCE)
            .path("1");
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .delete();
        
        assertEquals(200, response.getStatus());
    }
    
    /**
     * Deletes address with the specified id
     */
    @Test
    public void test22_delete_address_by_id() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(ADDRESS_RESOURCE)
            .path("1");
        
        Response response = webTarget
            .request(APPLICATION_JSON)
            .delete();
        
        
        assertEquals(200, response.getStatus());
    }
    
    /**
     * Updates home phone with the specified id
     */
    @Test
    public void test23_update_home_phone_by_id() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PHONE_RESOURCE)
            .path("2");


         Response response = webTarget
           .request(APPLICATION_JSON)
           .get();

         String phone1String = response.readEntity(String.class);
         PhonePojo phone1 = new HomePhone();


         try {
             phone1 = map.readValue(phone1String, PhonePojo.class);
         } catch (Exception e) {
             e.printStackTrace();
         }

         phone1.setAreacode("777");

         String newPhone = "";
         try {
             newPhone = map.writeValueAsString(phone1);
         } catch (Exception e) {
             e.getStackTrace();
         }

         response = webTarget
             .request(APPLICATION_JSON)
             .put(Entity.json(newPhone), Response.class);

         assertEquals(200, response.getStatus());
    }
    
    /**
     * Delete HomePhone with specified id
     */
    @Test
    public void test26_delete_home_phone_by_id() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PHONE_RESOURCE)
            .path("2");

        Response response = webTarget
            .request(APPLICATION_JSON)
            .delete();


        assertEquals(200, response.getStatus());
    }
    
    /**
     * Updates home phone with the specified id
     */
    @Test
    public void test27_update_work_phone_by_id() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PHONE_RESOURCE)
            .path("3");


         Response response = webTarget
           .request(APPLICATION_JSON)
           .get();

         String phone1String = response.readEntity(String.class);
         PhonePojo phone1 = new WorkPhone();


         try {
             phone1 = map.readValue(phone1String, PhonePojo.class);
         } catch (Exception e) {
             e.printStackTrace();
         }

         phone1.setAreacode("888");

         String newPhone = "";
         try {
             newPhone = map.writeValueAsString(phone1);
         } catch (Exception e) {
             e.getStackTrace();
         }

         response = webTarget
             .request(APPLICATION_JSON)
             .put(Entity.json(newPhone), Response.class);

         assertEquals(200, response.getStatus());
    }
    
    /**
     * Deletes MobilePhone with the specified id
     */
    @Test
    public void test28_delete_mobile_phone_by_id() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PHONE_RESOURCE)
            .path("1");

        Response response = webTarget
            .request(APPLICATION_JSON)
            .delete();


        assertEquals(200, response.getStatus());
    }
    
    /**
     * Delete WorkPhone with specified id
     */
    @Test
    public void test29_delete_work_phone_by_id() {
        WebTarget webTarget = client
            .register(feature)
            .target(uri)
            .path(PHONE_RESOURCE)
            .path("3");

        Response response = webTarget
            .request(APPLICATION_JSON)
            .delete();


        assertEquals(200, response.getStatus());
    }
}