package org.com.xsx.Dao;

import java.util.List;

import org.com.xsx.Domain.DeviceInfoBean;
import org.com.xsx.Tools.HibernateDao;

public class DeviceInfoDao {
	
	public static List<DeviceInfoBean> QueryDeviceS(List<String> deviceidlist){

        String hql = "from DeviceInfoBean as m WHERE m.id IN (:parm0)";

		return HibernateDao.GetInstance().query(hql, new Object[]{ deviceidlist}, null, null);
	}
}
