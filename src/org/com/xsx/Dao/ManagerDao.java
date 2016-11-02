package org.com.xsx.Dao;

import org.com.xsx.Tools.HibernateDao;

public class ManagerDao {
	
	public static Object[] QueryReportManager(String account, String password){
		
		String hql = "select t,p from ManagerBean as t, PersonBean as p where t.account=:parm0 and t.password=:parm1 and p.id=t.p_id";
				
		return (Object[]) HibernateDao.GetInstance().queryOne(hql, new String[]{ account, password});
	}
}
