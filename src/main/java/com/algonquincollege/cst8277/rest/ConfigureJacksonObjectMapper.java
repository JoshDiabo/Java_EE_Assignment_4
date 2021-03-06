/**************************************************************************************************
 * File: ConfigureJacksonObjectMapper.java
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
package com.algonquincollege.cst8277.rest;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Provider
public class ConfigureJacksonObjectMapper implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper;

    public ConfigureJacksonObjectMapper() {
        this.objectMapper = createObjectMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }

    //configure JDK 8's new DateTime objects to use proper ISO-8601 timeformat
    protected ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            // lenient parsing of JSON - if a field is not known, don't fall to pieces!
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            ;
        return mapper;
    }
}