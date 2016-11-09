package org.com.xsx.Dao;

import java.util.List;

import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Tools.HibernateDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

public class ManagerDao {
	
	public static ManagerBean QueryReportManager(String account, String password){
		StringBuffer hql = new StringBuffer("select t from ManagerBean as t where t.account=:parm0");
		if(password != null){
			hql.append(" and t.password=:parm1");
		}
		
		if(account == null)
			return null;
				
		return (ManagerBean) HibernateDao.GetInstance().queryOne(hql.toString(), new String[]{ account, password});
	}
	
	public static List<Object[]> QueryChildManagerNameList(String fatheraccount){
		String hql = "select t.account,t.name from ManagerBean as t where t.fatheraccount=:parm0 ";
		
		return HibernateDao.GetInstance().query(hql.toString(), new String[]{fatheraccount}, null, null);
	}
	
	public static Boolean ModifyManagerInfo(ManagerBean managerBean){
		return HibernateDao.GetInstance().save(managerBean);
	}
	
	public static List<String> QueryDeviceList(String account){
		StringBuffer hql = new StringBuffer("select t.devicelist from ManagerBean as t where t.account=:parm0");
		
		String jsonstr = (String) HibernateDao.GetInstance().queryOne(hql.toString(), new String[]{ account});
		
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(jsonstr);
		return (List<String>) JSONSerializer.toJava(jsonArray);
	}
	
	public static Boolean DeleteManager(String account){
		ManagerBean manager = QueryReportManager(account, null);
		
		if(manager != null)
			return HibernateDao.GetInstance().delete(manager);
		else
			return false;
	}
	
	public static Boolean DeleteManager(ManagerBean manager){

		return HibernateDao.GetInstance().delete(manager);
	}
	
	public static List<ManagerBean> QueryManagerByDeviceid(String deviceid){
		
		StringBuffer hql = new StringBuffer("select t from ManagerBean as t where t.devicelist like :parm0)");
		
		return HibernateDao.GetInstance().query(hql.toString(), new Object[]{"%"+deviceid+"%"}, null, null);
	}
}
