/**************************************************************************************************
 * File: MyConstants.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 * 
 * Group Members:
 * Sam Heaton
 * Michael Norris
 * Josh Diabo
 * Daria Ponomareva
 *
 */
package com.algonquincollege.cst8277.utils;

/**
 * <p>
 * This class holds various constants used by this App's artifacts
 * <p>
 * The key idea here is that often an annotation contains String-based parameters that <b><u>must be an exact match</u></b> <br/>
 * to a string used elsewhere. Use of this type of 'Contants' Interface class prevents errors such as:
<blockquote><pre>
{@literal @}GET
{@literal @}Path("{<b><u>emailID</u></b>}/project")  //accidently capitalized <b><u>ID</u></b>, instead of camel-case <b><u>Id</u></b>
public List<Project> getProjects({@literal @}PathParam("<b><u>emailId</u></b>") String emailId) ...  // path parameter does not match Annotation
</pre></blockquote>
 *
 * @author mwnorman
 */
public interface MyConstants {
    
    // Shared REST constants
    public static final String SLASH = "/";
    public static final String APPLICATION_API_VERSION = SLASH + "api" + SLASH + "v1";
    public static final String RESOURCE_PATH_ID_ELEMENT =  "id";
    public static final String RESOURCE_PATH_ID_PATH =  "/{" + RESOURCE_PATH_ID_ELEMENT + "}";
    
    /** Endpoint associated with Employee entities */
    public static final String EMPLOYEE_RESOURCE_NAME =  "employees";
    
    /** Endpoint associated with Address entities */
    public static final String ADDRESS_RESOURCE_NAME = "addresses";
    
    /** Endpoint associated with Project entities */
    public static final String PROJECT_RESOURCE_NAME = "projects";
    
    /** Endpoint associated with Phone entities */
    public static final String PHONE_RESOURCE_NAME = "phones";

    //Security constants
    public static final String USER_ROLE = "USER_ROLE";
    public static final String ADMIN_ROLE = "ADMIN_ROLE";

    //JPA constants
    public static final String PU_NAME = "assignment4-PU";

}