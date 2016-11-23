package org.com.xsx.Dao;


import java.util.ArrayList;
import java.util.List;

import org.com.xsx.Domain.CardBean;
import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.DevicerBean;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Domain.PersonBean;
import org.com.xsx.Domain.TestDataBean;
import org.com.xsx.Tools.HibernateDao;
import org.com.xsx.UI.MainScene.DevicePage.DeviceDataPackage;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

public class DeviceInfoDao {
	
	public static List<DeviceBean> QueryDeviceList(String account){

		List<String> deviceidlist = ManagerDao.QueryDeviceList(account);
		
        StringBuffer hql = new StringBuffer("select m from DeviceBean as m WHERE m.id IN (:parm0) ");
        
        List<DeviceBean> devicelist = HibernateDao.GetInstance().query(hql.toString(), new Object[]{ deviceidlist}, null, null);
       
		return devicelist;
	}
	
	public static DeviceDataPackage QueryDevice(String deviceid){
		
        StringBuffer hql = new StringBuffer("select m from DeviceBean m WHERE m.id=:parm0 ");
        
        DeviceBean deviceBean = (DeviceBean) HibernateDao.GetInstance().queryOne(hql.toString(), new Object[]{deviceid});

        hql.setLength(0);
        hql.append("select p from DevicerBean p WHERE p.did=(:parm0)");
        List<DevicerBean> devicerlist = HibernateDao.GetInstance().query(hql.toString(), new Object[]{deviceBean.getId()}, null, null);
        
        DeviceDataPackage result = new DeviceDataPackage(deviceBean, devicerlist);
        
		return result;
	}
	
	public static Boolean DeleteDevice(String deviceid) {
		
		List<ManagerBean> managerlist = ManagerDao.QueryManagerByDeviceid(deviceid);
		
		for (ManagerBean managerBean : managerlist) {
			JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(managerBean.getDevicelist());
			List<String> devicelist = (List<String>) JSONSerializer.toJava(jsonArray);
			devicelist.remove(deviceid);
			
			jsonArray = JSONArray.fromObject(devicelist);
			managerBean.setDevicelist(jsonArray.toString());
			
			ManagerDao.ModifyManagerInfo(managerBean);
		}
		 
		return true;
	}
	
	public static DeviceBean ReadDeviceByDID(String deviceid) {
		String hql = "select c from DeviceBean as c where c.id=:parm0 ";
		
		if(deviceid == null)
			return null;
		
		return (DeviceBean) HibernateDao.GetInstance().queryOne(hql.toString(), new String[]{deviceid});
	}
	
	public static DevicerBean ReadDevicerByID(Integer id) {
		String hql = "select c from DevicerBean as c where c.id=:parm0 ";
		
		if(id == null)
			return null;
		
		return (DevicerBean) HibernateDao.GetInstance().queryOne(hql.toString(), new Object[]{id});
	}
	
	public static List<Object[]> QueryDeviceTestCounyGroupByDate(String deviceid){
		StringBuffer sql = new StringBuffer();
		
		if(deviceid == null)
			return null;
		
		sql.append("SELECT DATE_FORMAT(TESTTIME,'%YÄê%mÔÂ'),COUNT(DATE_FORMAT(TESTTIME,'%Y%m')) FROM TESTDATABEAN WHERE DID='");
		sql.append(deviceid);
		sql.append("' GROUP BY DATE_FORMAT(TESTTIME,'%Y%m')");
		
		List<Object[]> list = HibernateDao.GetInstance().querysql(sql.toString(), 
				null
				);

		return list;
	}
}
