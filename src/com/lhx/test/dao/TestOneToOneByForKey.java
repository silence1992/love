package com.lhx.test.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lhx.entity.Blog;
import com.lhx.entity.User;
import com.lhx.utils.dbutils.HibernateSessionFactory;

public class TestOneToOneByForKey {

	public static void main(String[] args) {
		Session session = HibernateSessionFactory.getSession();		
		Blog b = (Blog) session.get(Blog.class,1);
		System.out.println(b.getUser().getNickName()+" "+b.getUser().getSignature());
	}

}
