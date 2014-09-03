package com.lhx.test.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lhx.dao.BlogDAO;
import com.lhx.entity.Blog;
import com.lhx.entity.User;
import com.lhx.entity.UserBlog;
import com.lhx.utils.dbutils.HibernateSessionFactory;
import com.lhx.utils.timeutils.MyDate;

public class TestUserBlogDAO {

	public static void main(String[] args) {
		Session session = HibernateSessionFactory.getSession();
		Transaction txt = session.beginTransaction();
		BlogDAO bd = new BlogDAO();
		bd.updatePriseNum(1);		
		txt.commit();
	}
	
	public void test1(){
		
	}

}
