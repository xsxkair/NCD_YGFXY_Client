package org.com.xsx.Dao;

import org.com.xsx.Data.SoftwareInfo;
import org.com.xsx.Tools.HibernateDao;

public class SoftwareDao {
	
	public static Boolean CheckUpdate() {
		String hql = "select s.version from SoftVersion as s where s.softname='YGFXY_Client_Patch'";
		
		Integer version = (Integer) HibernateDao.GetInstance().queryOne(hql, null);
		
		if(version == null)
			return null;
		else if (version.intValue() > SoftwareInfo.version) {
			return true;
		}
		else {
			return false;
		}
	}
}
