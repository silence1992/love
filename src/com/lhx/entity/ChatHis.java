package com.lhx.entity;

/**
 * ChatHis entity. @author MyEclipse Persistence Tools
 */

public class ChatHis implements java.io.Serializable {

	// Fields

	private Integer id;
	private String fromUser;
	private String toUser;
	private String time;
	private String msg;
	private String isAccepted;

	// Constructors

	/** default constructor */
	public ChatHis() {
	}

	/** full constructor */
	public ChatHis(String fromUser, String toUser, String time) {
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFromUser() {
		return this.fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getToUser() {
		return this.toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(String isAccepted) {
		this.isAccepted = isAccepted;
	}
	
}