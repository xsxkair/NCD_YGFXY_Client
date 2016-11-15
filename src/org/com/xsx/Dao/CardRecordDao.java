package org.com.xsx.Dao;

import org.com.xsx.Domain.CardRecordBean;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Tools.HibernateDao;

public class CardRecordDao {
	
	public static Boolean InOutBound(CardRecordBean cardRecordBean, String account) {
		
		ManagerBean admin = ManagerDao.QueryReportManager(account, null);
		
		cardRecordBean.setDotime(new java.sql.Timestamp(System.currentTimeMillis()));
		cardRecordBean.setHandler(admin.getName());
		
		HibernateDao.GetInstance().save(cardRecordBean);
		
		return true;
	}
	
	//³ö¿â
	public static Boolean InOutBound(CardRecordBean cardRecordBean, ManagerBean admin) {
			
		cardRecordBean.setDotime(new java.sql.Timestamp(System.currentTimeMillis()));
		cardRecordBean.setHandler(admin.getName());
				
		HibernateDao.GetInstance().save(cardRecordBean);
			
		return true;
	}
}
