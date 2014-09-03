package com.lhx.entity;

public class Msg {
	private String status;
	private String msg;
	
	public Msg(String status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	
	public Msg(){
		super();
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
