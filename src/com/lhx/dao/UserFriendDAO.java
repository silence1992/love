package com.lhx.dao;

import com.lhx.entity.UserFriend;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserFriendDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UserFriendDAO.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String _FUSERNAME = "FUsername";
	public static final String BUILD_TIME = "buildTime";
	public static final String CATEGROY = "categroy";
	public static final String STATUS = "status";

	public void save(UserFriend transientInstance) {
		log.debug("saving UserFriend instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserFriend persistentInstance) {
		log.debug("deleting UserFriend instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserFriend findById(java.lang.Integer id) {
		log.debug("getting UserFriend instance with id: " + id);
		try {
			UserFriend instance = (UserFriend) getSession().get(
					"com.lhx.entity.UserFriend", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
		
	public void setUserState(String username,int status){
		String hql = "update UserFriend uf set uf.status=? where uf.FUsername=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0,status);
		query.setString(1,username);
		query.executeUpdate();
	}
	public List findByExample(UserFriend instance) {
		log.debug("finding UserFriend instance by example");
		try {
			List results = getSession()
					.createCriteria("com.lhx.entity.UserFriend")
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
		log.debug("finding UserFriend instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserFriend as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findByFUsername(Object FUsername) {
		return findByProperty(_FUSERNAME, FUsername);
	}

	public List findByBuildTime(Object buildTime) {
		return findByProperty(BUILD_TIME, buildTime);
	}

	public List findByCategroy(Object categroy) {
		return findByProperty(CATEGROY, categroy);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all UserFriend instances");
		try {
			String queryString = "from UserFriend";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserFriend merge(UserFriend detachedInstance) {
		log.debug("merging UserFriend instance");
		try {
			UserFriend result = (UserFriend) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserFriend instance) {
		log.debug("attaching dirty UserFriend instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserFriend instance) {
		log.debug("attaching clean UserFriend instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByCategroy(int categroy, String username) {
		String query = "from UserFriend uf where uf.username=? and uf.categroy=?";
		Query qobj = getSession().createQuery(query);
		qobj.setString(0,username);
		qobj.setInteger(1,categroy);
		return qobj.list();
	}
	
	public void setAllUserOffLine(){
		String hql = "update UserFriend uf set uf.status=0";
		Query qobj = getSession().createQuery(hql);
		qobj.executeUpdate();
	}
}