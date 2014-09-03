package com.lhx.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lhx.entity.Blog;

public class BlogDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(BlogDAO.class);
	// property constants
	public static final String HOST = "host";
	public static final String TOPIC = "topic";
	public static final String CONTENT = "content";
	public static final String ZAN = "zan";
	public static final String COMMENTS = "comments";
	public static final String CREATE_TIME = "createTime";

	public void save(Blog transientInstance) {
		log.debug("saving Blog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Blog persistentInstance) {
		log.debug("deleting Blog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Blog findById(java.lang.Integer id) {
		log.debug("getting Blog instance with id: " + id);
		try {
			Blog instance = (Blog) getSession().get(Blog.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Blog> getRecentBlogs(String username){
		String hql = "From Blog b where b.user.username=? or b.user.username in " +
				"(select uf.FUsername from UserFriend uf where uf.username=?) order by b.createTime desc";
		Query query = getSession().createQuery(hql);
		query.setString(0,username);
		query.setString(1,username);
		query.setFirstResult(0);
		query.setMaxResults(6);
		return query.list();
	}
	public Blog merge(Blog detachedInstance) {
		log.debug("merging Blog instance");
		try {
			Blog result = (Blog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public void updatePriseNum(int id){
		String hql = "update Blog b set b.zan=b.zan+1 where b.id=?";
			Query query = getSession().createQuery(hql);
			try{
			query.setInteger(0,id);
			query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void attachDirty(Blog instance) {
		log.debug("attaching dirty Blog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Blog instance) {
		log.debug("attaching clean Blog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void updateComments(int id) {
		
		String hql = "update Blog b set b.comments=b.comments+1 where b.id=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0,id);
		query.executeUpdate();
	}

	public List<Blog> getMoreBlogs(String username) {
		String hql = "From Blog b where b.user.username=? or b.user.username in " +
				"(select uf.FUsername from UserFriend uf where uf.username=?) order by b.createTime desc";
		Query query = getSession().createQuery(hql);
		query.setString(0,username);
		query.setString(1,username);
		query.setFirstResult(0);
		query.setMaxResults(5);
		return query.list();
	}
}