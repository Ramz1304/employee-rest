package com.employee;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	 public static String writeJson(employeePojo p) throws ServletException {
	    	
	    	ObjectMapper mapper = new ObjectMapper(); 
	    	try {
				return mapper.writeValueAsString(p);
			} catch (JsonProcessingException e) {
				throw new ServletException("error converting to jason");
			}
	 }
	
	
	 public static String writeJson(List<employeePojo> p) throws ServletException {
	    	
	    	ObjectMapper mapper = new ObjectMapper(); 
	    	try {
				return mapper.writeValueAsString(p);
			} catch (JsonProcessingException e) {
				throw new ServletException("error converting to jason");
			}
	 }
	    	
	    	
	    	 public static employeePojo readJson(HttpServletRequest request) throws ServletException, IOException {
	    	    	
	    	    	ObjectMapper mapper = new ObjectMapper(); 
	    	    	try {
	    				return mapper.readValue(request.getInputStream(), employeePojo.class);
	    			} catch (JsonProcessingException e) {
	    				throw new ServletException("error jason to object");
	    			}
}
	    	 

}
