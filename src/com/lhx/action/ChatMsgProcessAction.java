package com.lhx.action;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lhx.action.base.BaseAction;
import com.lhx.dao.ChatHisDAO;
import com.lhx.dao.UserDAO;
import com.lhx.entity.ChatHis;
import com.lhx.entity.User;
import com.lhx.utils.timeutils.MyDate;

public class ChatMsgProcessAction extends BaseAction{
	private ChatHis ch;
	private ChatHisDAO chd = new ChatHisDAO();
	private UserDAO ud = new UserDAO();
	
	public String saveChatHis(){
		User user = (User)session.get("user");
		ch.setFromUser(user.getUsername());
		ch.setIsAccepted("n");
		String msg = ch.getMsg();
		ch.setMsg(msg.trim());
		ch.setTime(MyDate.formatToSecond(new Date()));
		chd.save(ch);
		out.write("okokook");
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String getChatMsg(){
		User user = (User)session.get("user");
		String toUser = ch.getToUser();
		List<ChatHis> list = chd.getMsg(toUser,user.getUsername());
		out.write(JSONArray.fromObject(list).toString());
		if(list.size() != 0){			
			chd.setIsAccepted(toUser, user.getUsername());
			out.close();
			return null;
		}
		out.close();
		return null;
	}
	
	public String getLatestMsg(){
		User user = (User)session.get("user");		
		if(user != null ){
			User user2 = ud.findById(chd.getLatestComm(user.getUsername()));
			out.write(JSONObject.fromObject
					(user2).toString());
		}else{
			out.write("[]");
		}
		return null;
	}
	
	public String getAllUnMessage(){
		User user = (User)session.get("user");
		out.write(JSONArray.fromObject(chd.getAllUnMessage(user.getUsername())).toString());
		return null;
	}
	public ChatHis getCh() {
		return ch;
	}

	public void setCh(ChatHis ch) {
		this.ch = ch;
	}	
}
