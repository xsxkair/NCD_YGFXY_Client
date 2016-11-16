package org.com.xsx.Dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.com.xsx.Data.SignedManager;
import org.com.xsx.Domain.CardRecordBean;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Tools.HibernateDao;

public class CardRecordDao {
	
	public static Boolean InOutBound(CardRecordBean cardRecordBean, ManagerBean admin) {
	
		cardRecordBean.setDotime(new java.sql.Timestamp(System.currentTimeMillis()));
		cardRecordBean.setHandler(admin.getName());
		
		if(admin.getFatheraccount() == null)
			cardRecordBean.setAdmin_a(admin.getAccount());
		else {
			cardRecordBean.setAdmin_a(admin.getFatheraccount());
		}
		
		HibernateDao.GetInstance().save(cardRecordBean);
		
		return true;
	}
	
	//查询每个品种的库存
	public static Map<String, Integer> QueryCardRepertoryNumByItem() {
		
		Map<String, Integer> result = new HashMap<>();
		
		//获取当前医院管理员账号
		ManagerBean admin = ManagerDao.QueryReportManager(SignedManager.GetInstance().getGB_SignedAccount(), null);
		
		StringBuffer hql = new StringBuffer();
		
		hql.append("SELECT ITEM,SUM(NUM) FROM CARDRECORDBEAN where ADMIN_A='");
		
		if(admin.getFatheraccount() == null)
			hql.append(admin.getAccount());
		else 
			hql.append(admin.getFatheraccount());
		
		hql.append("' GROUP BY ITEM");
		
		List<Object[]> queryresult = HibernateDao.GetInstance().querysql(hql.toString(), null);
		
		for (Object[] objects : queryresult) {
			BigDecimal num = (BigDecimal) objects[1];
			result.put((String)objects[0], num.intValue());
		}
		
		return result;
	}
	
	//查询某台设备的的某一个品种的出入库记录
	public static List<CardRecordBean> QueryCardRecordList(String item, String deviceid) {
			
			Map<String, Integer> result = new HashMap<>();
			
			//获取当前医院管理员账号
			ManagerBean admin = ManagerDao.QueryReportManager(SignedManager.GetInstance().getGB_SignedAccount(), null);
			
			StringBuffer hql = new StringBuffer();
			
			hql.append("SELECT ITEM,SUM(NUM) FROM CARDRECORDBEAN where ADMIN_A='");
			
			if(admin.getFatheraccount() == null)
				hql.append(admin.getAccount());
			else 
				hql.append(admin.getFatheraccount());
			
			hql.append("' GROUP BY ITEM");
			
			List<Object[]> queryresult = HibernateDao.GetInstance().querysql(hql.toString(), null);
			
			for (Object[] objects : queryresult) {
				BigDecimal num = (BigDecimal) objects[1];
				result.put((String)objects[0], num.intValue());
			}
			
			return result;
		}
}
