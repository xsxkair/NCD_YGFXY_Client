package org.com.xsx.Dao;

import org.com.xsx.Domain.ReportManagerBean;
import org.com.xsx.Tools.HibernateDao;

public class ReportManageDao {

	public static ReportManagerBean QueryReportManager(String account, String password){
		
		String hql = "select t from ReportManagerBean as t where t.account=:parm0 and t.password=:parm1";
				
		return (ReportManagerBean) HibernateDao.GetInstance().queryOne(hql, new String[]{ account, password});
	}
}
