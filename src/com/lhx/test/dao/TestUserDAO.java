package com.lhx.test.dao;

import java.util.List;

import com.lhx.dao.UserDAO;
import com.lhx.entity.User;

public class TestUserDAO {
	private static UserDAO ud = new UserDAO();
	public static void main(String[] args) {
		List<User> ulist = ud.findByLessId("111","111114");
		for(User user : ulist){
			System.out.println(user.getNickName()+":"+user.getSignature());
		}
	}
}
