package org.com.xsx.Dao;

import java.util.ArrayList;
import java.util.List;

import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.PersonBean;
import org.com.xsx.Tools.HibernateDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

public class DeviceInfoDao {
	
	public static List<Object[]> QueryDeviceS(List<String> deviceidlist){
		
		List<Object[]> result = new ArrayList<>();
		
        StringBuffer hql = new StringBuffer("select m,p from DeviceBean as m, PersonBean as p WHERE m.id IN (:parm0) AND m.p_id=p.id");
        
        List<Object[]> devicelist = HibernateDao.GetInstance().query(hql.toString(), new Object[]{ deviceidlist}, null, null);

        for (Object[] device : devicelist) {
        	Object[] deviceinfo = new Object[3];
        	deviceinfo[0] = device[0];
        	deviceinfo[1] = device[1];
        	
        	hql.setLength(0);
        	JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(((DeviceBean)deviceinfo[0]).getP_list());
			List<String> personid_list = (List<String>) JSONSerializer.toJava(jsonArray);
        	hql.append("select p from PersonBean as p WHERE p.id IN (:parm0)");
        	
        	List<PersonBean> person_list = HibernateDao.GetInstance().query(hql.toString(), new Object[]{personid_list}, null, null);

        	deviceinfo[2] = person_list;
        	
        	result.add(deviceinfo);
		}

		return result;
	}
}
