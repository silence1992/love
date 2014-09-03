package com.lhx.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lhx.action.base.BaseAction;
import com.lhx.dao.UserDAO;
import com.lhx.dao.UserFriendDAO;
import com.lhx.entity.User;

public class ListFriendAction extends BaseAction{
	private UserFriendDAO ufd = new UserFriendDAO();
	private UserDAO ud = new UserDAO();
	private String username;
	private int categroy;
	public String listByCtaegroy(){
		if(categroy == 0) return null;
		User user = (User)session.get("user");
		if(user == null) return null;
		out.write(JSONArray.fromObject(ufd.findByCategroy(categroy,user.getUsername())).toString());
		return null;
	}
	
	public String getUserMsg(){
		if(username == null || username.equals("")){
			return null;
		}
		out.write(JSONObject.fromObject(ud.findById(username)).toString());
		return null;
	}
	public int getCategroy() {
		return categroy;
	}
	public void setCategroy(int categroy) {
		this.categroy = categroy;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
