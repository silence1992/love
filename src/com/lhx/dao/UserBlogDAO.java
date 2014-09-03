package com.lhx.dao;

import com.lhx.entity.UserBlog;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserBlog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.lhx.entity.UserBlog
 * @author MyEclipse Persistence Tools
 */

public class UserBlogDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UserBlogDAO.class);
	// property constants
	public static final String CUSTOM = "custom";
	public static final String TALKS = "talks";
	public static final String TALK_TIME = "talkTime";

	public void save(UserBlog transientInstance) {
		log.debug("saving UserBlog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserBlog persistentInstance) {
		log.debug("deleting UserBlog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserBlog findById(java.lang.Integer id) {
		log.debug("getting UserBlog instance with id: " + id);
		try {
			UserBlog instance = (UserBlog) getSession().get(
					"com.lhx.entity.UserBlog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserBlog instance) {
		log.debug("finding UserBlog instance by example");
		try {
			List results = getSession()
					.createCriteria("com.lhx.entity.UserBlog")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding UserBlog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserBlog as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCustom(Object custom) {
		return findByProperty(CUSTOM, custom);
	}

	public List findByTalks(Object talks) {
		return findByProperty(TALKS, talks);
	}

	public List findByTalkTime(Object talkTime) {
		return findByProperty(TALK_TIME, talkTime);
	}

	public List findAll() {
		log.debug("finding all UserBlog instances");
		try {
			String queryString = "from UserBlog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserBlog merge(UserBlog detachedInstance) {
		log.debug("merging UserBlog instance");
		try {
			UserBlog result = (UserBlog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserBlog instance) {
		log.debug("attaching dirty UserBlog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserBlog instance) {
		log.debug("attaching clean UserBlog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}