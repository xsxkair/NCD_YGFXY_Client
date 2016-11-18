package org.com.xsx.Dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.com.xsx.Data.SignedManager;
import org.com.xsx.Domain.CardRecordBean;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Domain.TestDataBean;
import org.com.xsx.Tools.HibernateDao;
import org.hibernate.hql.internal.ast.HqlASTFactory;

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
	
	//查询某个品种的出入库记录
	public static Object[] QueryCardRecordList(String item, String deviceid, Integer firstindex, Integer size, Boolean isgetnum){
		BigInteger num = null;
		
		//获取当前医院管理员账号
		ManagerBean admin = ManagerDao.QueryReportManager(SignedManager.GetInstance().getGB_SignedAccount(), null);
		
		StringBuffer hql = new StringBuffer();
		StringBuffer hql1 = new StringBuffer();
		StringBuffer hql2 = new StringBuffer();
		StringBuffer hql3 = new StringBuffer();
		
		hql.append("FROM CARDRECORDBEAN c WHERE ITEM='");
		hql.append(item);
		
		hql.append("' AND ADMIN_A='");
		if(admin.getFatheraccount() == null)
			hql.append(admin.getAccount());
		else 
			hql.append(admin.getFatheraccount());
		hql.append("'");
		
		if(deviceid != null){
			hql.append(" AND DEVICEID='" + deviceid + "'");
		}
		
		hql1.append("SELECT c.* "+hql.toString()+" limit "+firstindex+","+size);
		
		List<CardRecordBean> queryresult = HibernateDao.GetInstance().querysql(hql1.toString(), new Object[][]{{"c", CardRecordBean.class}});
		
		if(isgetnum){
			hql2.append("SELECT COUNT(c.ID) "+hql.toString());
			num = (BigInteger) HibernateDao.GetInstance().queryonesql(hql2.toString());
		}

		return new Object[]{queryresult, num};
	}
	
	
	//获取所有出库的项目名称
	public static List<String> QueryOutBoundItemList(){
		//获取当前医院管理员账号
		ManagerBean admin = ManagerDao.QueryReportManager(SignedManager.GetInstance().getGB_SignedAccount(), null);
				
		StringBuffer hql = new StringBuffer();
				
		hql.append("SELECT ITEM FROM CARDRECORDBEAN c WHERE NUM<0 AND ADMIN_A='");

		if(admin.getFatheraccount() == null)
			hql.append(admin.getAccount());
		else 
			hql.append(admin.getFatheraccount());
				
		hql.append("' GROUP BY ITEM");
				
		List<String> queryresult = HibernateDao.GetInstance().querysql(hql.toString(), null);

		return queryresult;
	}
	
	//获取所有出库的设备id
	public static List<String> QueryOutBoundDeviceList(){
		//获取当前医院管理员账号
		ManagerBean admin = ManagerDao.QueryReportManager(SignedManager.GetInstance().getGB_SignedAccount(), null);
				
		StringBuffer hql = new StringBuffer();
				
		hql.append("SELECT DEVICEID FROM CARDRECORDBEAN c WHERE NUM<0 AND ADMIN_A='");

		if(admin.getFatheraccount() == null)
			hql.append(admin.getAccount());
		else 
			hql.append(admin.getFatheraccount());
				
		hql.append("' GROUP BY DEVICEID");
				
		List<String> queryresult = HibernateDao.GetInstance().querysql(hql.toString(), null);

		return queryresult;
	}
	
	//获取一个设备的所有项目的库存量
	public static Map<String, Integer> QueryDeviceRepertory(String deviceid){
		Map<String, Integer> result = new HashMap<>();
		
		StringBuffer hql = new StringBuffer();
		
		hql.append("SELECT B.ITEM, B.SUMNUM-IFNULL(A.USEDNUM,0) FROM"
				+" (SELECT ABS(SUM(NUM)) AS SUMNUM, ITEM FROM CARDRECORDBEAN WHERE num<0 AND DEVICEID='"
				+deviceid
				+"' GROUP BY ITEM) B"
				+" LEFT JOIN"
				+"(SELECT COUNT(CID) as  USEDNUM, CITEM FROM TESTDATABEAN WHERE DID='"
				+deviceid
				+"' GROUP BY CITEM ) A ON B.ITEM=A.CITEM ");
	
		List<Object[]> queryresult = HibernateDao.GetInstance().querysql(hql.toString(), null);
		
		for (Object[] objects : queryresult) {
			BigDecimal num = (BigDecimal) objects[1];
			result.put((String)objects[0], num.intValue());
		}
		
		return result;
	}
/*	
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
		}*/
}
