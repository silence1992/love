package com.lhx.action;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.lhx.action.base.BaseAction;
import com.lhx.dao.BlogDAO;
import com.lhx.dao.UserBlogDAO;
import com.lhx.entity.Blog;
import com.lhx.entity.Msg;
import com.lhx.entity.User;
import com.lhx.entity.UserBlog;
import com.lhx.utils.timeutils.MyDate;

public class BlogAction extends BaseAction{
	private BlogDAO bd = new BlogDAO();
	private UserBlogDAO ubd = new UserBlogDAO();
	private List<Blog> blist;
	private int id;
	private String text;
	private String topic;
	public String getRecentBlog(){
		User user = (User) session.get("user");
		if(user != null){
			this.blist =  bd.getRecentBlogs(user.getUsername());
		}
		return "success";
	}
	
	public String priseBlog(){
		bd.updatePriseNum(id);
		Msg m = new Msg("success","点赞成功！");		
		out.write(JSONObject.fromObject(m).toString());
		return null;
	}
	
	public String responseBlog(){
		User user = (User)session.get("user");
		if(user != null){
			UserBlog ub = new UserBlog();
			ub.setBlogId(id);
			ub.setTalks(text.trim());
			ub.setUser(user);
			ub.setTalkTime(MyDate.formatToMinute(new Date()));
			ubd.save(ub);
			bd.updateComments(id);
		}
		Msg m = new Msg("success","评论成功！");		
		out.write(JSONObject.fromObject(m).toString());
		return null;
	}
	
	
	public String addNewBlog(){
		User user = (User)session.get("user");
		Blog b = new Blog();
		b.setUser(user);
		b.setComments(0);
		b.setContent(text);
		b.setCreateTime(MyDate.formatToMinute(new Date()));
		b.setZan(0);
		b.setTopic(topic);
		bd.save(b);
		Msg m = new Msg("success","发表成功！");		
		out.write(JSONObject.fromObject(m).toString());
		return null;
	}
	
	public List<Blog> getBlist() {
		return blist;
	}
	public void setBlist(List<Blog> blist) {
		this.blist = blist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}
