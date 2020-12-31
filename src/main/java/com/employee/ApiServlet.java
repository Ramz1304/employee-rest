package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(name = "Api Servelet", description = "api servlet", urlPatterns = { "/api"})

public class ApiServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 4112660863637769085L;
	
	private static EmployeeHibernateApi api;
	
	static {
		
		HibernateUtil.configure();
		api = new EmployeeHibernateApi();
	}
	
	
	@Override
	//Retrieving
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		
		String actionParam = req.getParameter("action");
		String idParam = req.getParameter("id");
		
		if(idParam == null)
		{
			try {
				List<employeePojo> list = api.SelectAll();
				String json = JsonUtil.writeJson(list);
				out.write(json);
				}
			 catch (SQLException e) {
				throw new ServletException("error retriving employee list",e);
			}
			return;
		}
		
		if(actionParam!=null && actionParam.contentEquals("delete")) {
			doDelete(req, resp);
            return;		
		}
		int id = Integer.valueOf(idParam);
		
		try {
			employeePojo p = api.Select(id);
			String json = JsonUtil.writeJson(p);
			out.write(json);
		} catch (SQLException e) {
			throw new ServletException("error retriving single employee",e);
		}
		
	
	}
     
    	
		
		
	
	//Creating
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	     employeePojo p = JsonUtil.readJson(req);
	    
		try {
			api.Insert(p);
		} catch (SQLException e) {
			
			throw new ServletException("error saving object",e);
		}
		
	}
	
	
	// Updating
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String idStr=req.getParameter("id");
	
	     employeePojo p = JsonUtil.readJson(req);
	     
	     int id =Integer.valueOf(idStr)	;    
		try {
			api.Update(id,p);
		} catch (SQLException e) {
			
			throw new ServletException("error updating object",e);
		}
	}
	// Deleting
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int id = Integer.valueOf(req.getParameter("id"));
		
		try {
			api.Delete(id);;
		} catch (SQLException e) {
			throw new ServletException("error retriving object",e);
		}
	}
	
	
	       
	     		
	     		
	     	
}
