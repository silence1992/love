package com.lhx.intecepter;

import org.hibernate.Transaction;

import com.lhx.utils.dbutils.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@SuppressWarnings("serial")
public class TransactionInt extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		String resultCode = "";
		Transaction txt = HibernateSessionFactory.getSession().beginTransaction();
		try{
			resultCode = arg0.invoke();
			txt.commit();
		}catch (Exception e){
			e.printStackTrace();
			txt.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return resultCode;
	}
	
}
