package com.lhx.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lhx.entity.ChatHis;
import com.lhx.entity.CountChatMsg;

public class ChatHisDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ChatHisDAO.class);
	// property constants
	public static final String FROM_USER = "fromUser";
	public static final String TO_USER = "toUser";
	public static final String TIME = "time";

	public void save(ChatHis transientInstance) {
		log.debug("saving ChatHis instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public List getMsg(String from,String to){
		String hql = "from ChatHis ch where ch.fromUser=? and ch.toUser=? and ch.isAccepted='n'";
		Query query = getSession().createQuery(hql);
		query.setString(0,from);
		query.setString(1,to);
		return query.list();
	}
	
	public void setIsAccepted(String from,String to){
		String hql = "update ChatHis ch set ch.isAccepted='y' where ch.fromUser=? and ch.toUser=?";
		Query query = getSession().createQuery(hql);
		query.setString(0,from);
		query.setString(1,to);
		query.executeUpdate();
	}

	public void delete(ChatHis persistentInstance) {
		log.debug("deleting ChatHis instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ChatHis findById(java.lang.Integer id) {
		log.debug("getting ChatHis instance with id: " + id);
		try {
			ChatHis instance = (ChatHis) getSession().get(ChatHis.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ChatHis instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ChatHis as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public ChatHis merge(ChatHis detachedInstance) {
		log.debug("merging ChatHis instance");
		try {
			ChatHis result = (ChatHis) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ChatHis instance) {
		log.debug("attaching dirty ChatHis instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ChatHis instance) {
		log.debug("attaching clean ChatHis instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public String getLatestComm(String username){
		String hql = "select ch.fromUser from ChatHis ch where ch.toUser=? and ch.isAccepted='n' order by time desc";
		Query query = getSession().createQuery(hql);
		query.setString(0,username);
		query.setFirstResult(0);
		query.setMaxResults(1);
		String str = "";
		List ulist = query.list();
		if(ulist.size() != 0){
			str = (String) ulist.get(0);
		}else{
			str = "***";
		}		
		return str;
	}
	
	public List getAllUnMessage(String username) {
		String hql = "select u.nick_name,u.username,count(ch.id) from chat_his ch join user u on(ch.from_user=u.username) where " +
				"ch.to_user=? and ch.is_accepted='n' group by ch.from_user";
		SQLQuery query = getSession().createSQLQuery(hql);
		query.setString(0, username);
		List<CountChatMsg> clist = new ArrayList<CountChatMsg>();
		List<Object[]> list = query.list();
		for(Object[] obj:list){
			CountChatMsg ccm = new CountChatMsg();
			ccm.setUsername((String)obj[1]);
			ccm.setNickName((String)obj[0]);
			ccm.setCount((BigInteger)obj[2]);
			clist.add(ccm);
		}
		return clist;
	}
}