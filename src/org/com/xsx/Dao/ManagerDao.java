package org.com.xsx.Dao;

import java.util.List;

import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Tools.HibernateDao;

public class ManagerDao {
	
	public static ManagerBean QueryReportManager(String account, String password){
		
		String hql = "select t from ManagerBean as t where t.account=:parm0 and t.password=:parm1";
				
		return (ManagerBean) HibernateDao.GetInstance().queryOne(hql, new String[]{ account, password});
	}
	
	public static List<String> QueryChildManagerName(String fatheraccount){
		String hql = "select t.name from ManagerBean as t where t.fatheraccount=:parm0";
		
		return HibernateDao.GetInstance().query(hql.toString(), new String[]{fatheraccount}, null, null);
	}
}
