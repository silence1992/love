package com.lhx.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Transaction;

import com.lhx.utils.dbutils.HibernateSessionFactory;
import com.lhx.dao.UserFriendDAO;
public class UserCotext implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		Transaction txt = HibernateSessionFactory.getSession().beginTransaction();
		(new UserFriendDAO()).setAllUserOffLine();
		txt.commit();
	}

	public void contextInitialized(ServletContextEvent arg0) {		
	}

}
