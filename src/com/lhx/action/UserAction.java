package com.lhx.action;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.lhx.action.base.BaseAction;
import com.lhx.dao.UserDAO;
import com.lhx.dao.UserFriendDAO;
import com.lhx.entity.Msg;
import com.lhx.entity.User;
import com.lhx.utils.timeutils.MyDate;

public class UserAction extends BaseAction{
	private User user;
	private String checkcode;
	private UserDAO ud = new UserDAO();
	private UserFriendDAO ufd = new UserFriendDAO();
	@SuppressWarnings("unchecked")
	public String regist() throws Exception{
		List<User> ulist = ud.findByProperty("username",user.getUsername());
		Msg m = new Msg();
		if(((String)session.get("checkcode")).equals(checkcode)){
			if(ulist.size() == 0){
				ud.save(user);
				m.setStatus("success");
				m.setMsg("恭喜您,注册成功！！");
				out.write(JSONObject.fromObject(m).toString());
			}else{
				m.setStatus("error");
				m.setMsg("该用户名已存在！");
				out.write(JSONObject.fromObject(m).toString());
			}
		}else{
			m.setStatus("error");
			m.setMsg("验证码错误！！");
			out.write(JSONObject.fromObject(m).toString());
		}
		out.close();
		return null;
	}
	
	public String checkUsername() throws Exception{
		if(ud.findByProperty("username", user.getUsername()).size() != 0){
			out.write("have");
		}else{
			out.write("you can use");
		}
		out.close();
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String userLogin() throws Exception{
		List<User> ulist = ud.findByProperty("username",user.getUsername());
		User u = new User();
		if(ulist.size() != 0){
			u = (User)ulist.get(0);
		}
		Msg m = new Msg();
		if(user.getPassword().equals(u.getPassword())){
			session.put("user",u);
			ufd.setUserState(u.getUsername(), 1);
			String str = "您的账户在"+MyDate.formatCn(new Date())+"登录成功!";
			m.setStatus("success");
			m.setMsg(str);
			out.write(JSONObject.fromObject(m).toString());
		}else{
			if(ulist.size() == 0){
				m.setStatus("error");
				m.setMsg("该用户不存在！");
				out.write(JSONObject.fromObject(m).toString());
			}else{
				m.setStatus("error");
				m.setMsg("用户名或者密码错误！");
				out.write(JSONObject.fromObject(m).toString());
			}
		}
		out.close();
		return null;
	}
	
	public String userLogout() throws Exception{
		User user = (User) session.get("user");
		ufd.setUserState(user.getUsername(),0);
		session.remove("user");
		out.write("ok");
		out.close();
		return null;
	}

	public String changeUserMsg() throws Exception{
		User u = (User) session.get("user");		
		Msg msg = new Msg();
		if(u.getPassword().equals(user.getPassword())){
			ud.update(user);
			msg.setStatus("success");
			msg.setMsg("恭喜您修改成功！");
			session.put("user",user);
			out.write(JSONObject.fromObject(msg).toString());			
		}else{
			msg.setStatus("wrong");
			msg.setMsg("密码错误！不能修改！");
			out.write(JSONObject.fromObject(msg).toString());	
		}
		out.close();
		return null;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

}
