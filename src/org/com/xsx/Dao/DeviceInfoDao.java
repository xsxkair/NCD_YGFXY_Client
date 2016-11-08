package org.com.xsx.Dao;

import java.util.ArrayList;
import java.util.List;

import org.com.xsx.Domain.CardBean;
import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.DevicerBean;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Domain.PersonBean;
import org.com.xsx.Tools.HibernateDao;
import org.com.xsx.UI.MainScene.DevicePage.DeviceDataPackage;

import javafx.collections.FXCollections;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

public class DeviceInfoDao {
	
	public static List<DeviceDataPackage> QueryDeviceList(String account){
		
		List<DeviceDataPackage> result = new ArrayList<>();
		
		List<String> deviceidlist = ManagerDao.QueryDeviceList(account);
		
        StringBuffer hql = new StringBuffer("select m,p from DeviceBean as m left join DevicerBean as p on p.id=m.p_id WHERE m.id IN (:parm0) ");
        
        List<Object[]> devicelist = HibernateDao.GetInstance().query(hql.toString(), new Object[]{ deviceidlist}, null, null);
       
        for (Object[] objects : devicelist) {
        	result.add(new DeviceDataPackage(objects));
		}
       
		return result;
	}
	
	public static DeviceDataPackage QueryDevice(String deviceid){
		
        StringBuffer hql = new StringBuffer("select m,p from DeviceBean m left join DevicerBean p on m.p_id=p.id WHERE m.id=:parm0 ");
        
        Object[] hqldata = (Object[]) HibernateDao.GetInstance().queryOne(hql.toString(), new Object[]{deviceid});
        
        DeviceDataPackage result = new DeviceDataPackage(hqldata);

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
		
		return (DeviceBean) HibernateDao.GetInstance().queryOne(hql.toString(), new String[]{deviceid});
	}
	
	public static DevicerBean ReadDevicerByID(int id) {
		String hql = "select c from DevicerBean as c where c.id=:parm0 ";
		
		return (DevicerBean) HibernateDao.GetInstance().queryOne(hql.toString(), new Object[]{id});
	}
}
