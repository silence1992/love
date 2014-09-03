package com.lhx.entity;

import java.util.HashSet;
import java.util.Set;

public class Blog implements java.io.Serializable {
	
	private Integer id;
	private String topic;
	private String content;
	private Integer zan;
	private Integer comments;
	private String createTime;
	private User user;
	private Set<UserBlog> set = new HashSet<UserBlog>();

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getZan() {
		return this.zan;
	}

	public void setZan(Integer zan) {
		this.zan = zan;
	}

	public Integer getComments() {
		return this.comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<UserBlog> getSet() {
		return set;
	}

	public void setSet(Set<UserBlog> set) {
		this.set = set;
	}
	
}