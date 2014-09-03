package com.lhx.entity;

/**
 * UserFriend entity. @author MyEclipse Persistence Tools
 */

public class UserFriend implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String FUsername;
	private String buildTime;
	private Integer categroy;
	private Integer status;

	// Constructors

	/** default constructor */
	public UserFriend() {
	}

	/** full constructor */
	public UserFriend(String username, String FUsername, String buildTime,
			Integer categroy, Integer status) {
		this.username = username;
		this.FUsername = FUsername;
		this.buildTime = buildTime;
		this.categroy = categroy;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFUsername() {
		return this.FUsername;
	}

	public void setFUsername(String FUsername) {
		this.FUsername = FUsername;
	}

	public String getBuildTime() {
		return this.buildTime;
	}

	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}

	public Integer getCategroy() {
		return this.categroy;
	}

	public void setCategroy(Integer categroy) {
		this.categroy = categroy;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}