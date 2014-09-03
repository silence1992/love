package com.lhx.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lhx.entity.User;

public class UserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	// property constants
	public static final String NICK_NAME = "nickName";
	public static final String PASSWORD = "password";
	public static final String SEX = "sex";
	public static final String AGE = "age";
	public static final String SIGNATURE = "signature";

	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.String id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getSession().get(User.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List results = getSession().createCriteria(User.class)
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
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLessId(String username ,String real){
		String query = "from User u where u.username like" +
				" ? and u.username !=? and u.username not in(select FUsername from UserFriend uf where uf.username=?)";
		Query queryObj = getSession().createQuery(query);
		queryObj.setString(0,username+"%");
		queryObj.setString(1,real);
		queryObj.setString(2,real);
		queryObj.setFirstResult(0);
		queryObj.setMaxResults(10);
		return queryObj.list();
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByCondition(String username,Map<String,String> m){
		
		String sql = "from User u where u.username !=? and u.username not " +
				"in(select FUsername from UserFriend uf where uf.username=?) ";
		Set<String> set = m.keySet();
		Iterator<String> sit = set.iterator();
		while(sit.hasNext()){
			String str = sit.next();
			if(str != null && str.equals("minAge")){
				sql = sql + " and u.age >= "+m.get(str);
			}
			if(str != null && str.equals("maxAge")){
				sql = sql + " and u.age <= "+m.get(str);
			}
			if(str != null && str.equals("sex")){
				sql = sql + " and u.sex = '"+m.get(str)+"'";
			}
		}
		String sort = m.get("sort");
		if(sort != null && !sort.equals("")){
			sql = sql + " order by "+sort+" "+m.get("order");
		}
		Query query = getSession().createQuery(sql);
		query.setString(0,username);
		query.setString(1,username);
		query.setFirstResult(0);
		query.setMaxResults(10);
		return query.list();
	}
	
	public List findCount(){
		String hql = "from User u";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(8);
		return query.list();
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public void update(User user){
		log.debug("updating User instance");
		try {
			getSession().update(user);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}