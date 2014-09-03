package com.lhx.entity;

public class UserBlog implements java.io.Serializable {

	private Integer id;
	private String talks;
	private String talkTime;
	private User user;
	private Integer blogId;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTalks() {
		return this.talks;
	}

	public void setTalks(String talks) {
		this.talks = talks;
	}

	public String getTalkTime() {
		return this.talkTime;
	}

	public void setTalkTime(String talkTime) {
		this.talkTime = talkTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
}