package com.lhx.service;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.hibernate.Transaction;

import com.lhx.dao.UserFriendDAO;
import com.lhx.entity.User;
import com.lhx.utils.dbutils.HibernateSessionFactory;

public class UserSessionListener implements HttpSessionListener{

	public void sessionCreated(HttpSessionEvent arg0) {
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		User user = (User)session.getAttribute("user");
		if(user != null){
			Transaction txt = HibernateSessionFactory.getSession().beginTransaction();
			UserFriendDAO ufd = new UserFriendDAO();
			ufd.setUserState(user.getUsername(),0);
			session.removeAttribute("user");
			txt.commit();
		}
	}
}
