package com.lhx.action.base;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

public class BaseAction implements RequestAware, SessionAware,
		ApplicationAware, ServletResponseAware {
	protected Map<String, Object> session;
	protected Map<String, Object> request;
	protected Map<String, Object> application;
	protected HttpServletResponse response;
	protected PrintWriter out;

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;

	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
