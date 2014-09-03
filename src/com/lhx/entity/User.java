package com.lhx.entity;

public class User implements java.io.Serializable {

	// Fields

	private String username;
	private String nickName;
	private String password;
	private String sex;
	private Integer age;
	private String signature;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String nickName, String password, Integer age) {
		this.nickName = nickName;
		this.password = password;
		this.age = age;
	}

	/** full constructor */
	public User(String nickName, String password, String sex, Integer age,
			String signature) {
		this.nickName = nickName;
		this.password = password;
		this.sex = sex;
		this.age = age;
		this.signature = signature;
	}

	// Property accessors

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}