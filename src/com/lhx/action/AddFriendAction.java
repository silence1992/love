package com.lhx.action;

import java.util.Date;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lhx.action.base.BaseAction;
import com.lhx.dao.UserDAO;
import com.lhx.dao.UserFriendDAO;
import com.lhx.entity.Msg;
import com.lhx.entity.User;
import com.lhx.entity.UserFriend;
import com.lhx.utils.timeutils.MyDate;

public class AddFriendAction extends BaseAction{
	private String q;
	private UserDAO ud = new UserDAO();
	private UserFriendDAO ufd = new UserFriendDAO();
	private UserFriend uf;
	private String minAge;
	private String maxAge;
	private String sex;
	private String sort;
	private String order;

	public String getUserByLessUsername() throws Exception{
		User user = ((User)session.get("user")==null)? new User():(User)session.get("user");
		if(user == null || q==null || q.equals("")){
			out.write("[]");
			return null;
		}
		if(q.trim().length() != 0){
			 out.write(JSONArray.fromObject(ud.findByLessId(q,user.getUsername())).toString());
		}
		return null;
	}
	
	public String addFriendById(){
		User user = (User)session.get("user");
		uf.setUsername(user.getUsername());
		uf.setBuildTime(MyDate.format(new Date()));
		uf.setStatus(0);
		ufd.save(uf);
		Msg m = new Msg();
		m.setStatus("success");
		m.setMsg("Ìí¼Ó³É¹¦£¡");
		out.write(JSONObject.fromObject(m).toString());
		return null;
	}
	
	public String findByCondition(){
		HashMap<String,String> map = new HashMap<String,String>();
		User user = ((User)session.get("user")==null)? new User():(User)session.get("user");
		if(user == null) return null;
		String username = user.getUsername();
		if(minAge != null && !minAge.equals("")){
			map.put("minAge",minAge);
		}
		if(maxAge != null && !maxAge.equals("")){
			map.put("maxAge",maxAge);
		}
		if(sex != null && !sex.equals("")){
			map.put("sex",sex);
		}
		if(sort != null && !sort.equals("")){
			map.put("sort", sort);
		}
		if(order != null && !order.equals("")){
			map.put("order", order);
		}
		out.write(JSONArray.fromObject(ud.findByCondition(username,map)).toString());
		return null;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public UserFriend getUf() {
		return uf;
	}

	public void setUf(UserFriend uf) {
		this.uf = uf;
	}

	public String getMinAge() {
		return minAge;
	}

	public void setMinAge(String minAge) {
		this.minAge = minAge;
	}

	public String getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
