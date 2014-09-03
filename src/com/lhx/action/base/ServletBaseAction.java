package com.lhx.action.base;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

public class ServletBaseAction implements ServletRequestAware{
	protected HttpServletRequest request ;
	protected HttpSession session;
	protected ServletContext application;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		session = arg0.getSession();
		application = session.getServletContext();
	}

}
