package org.com.xsx.Dao;

import java.util.ArrayList;
import java.util.List;

import org.com.xsx.Data.ReportFilterData;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Tools.HibernateDao;

public class ReportDao {
	
	public static Object[] QueryTestDataS(boolean isquerytotalnum){

		Long totalnum = null;
		
		StringBuffer hql = new StringBuffer("from TESTDATABEAN t");

		ArrayList<Object> parms = new ArrayList<>();
		String tempstr;
		java.sql.Date tempdate;
		
		// 测试项目,如果过滤条件为null，则搜索所有项目
		tempstr = ReportFilterData.GetInstance().getTestitem();
		if(tempstr != null){
			hql.append(" AND t.ITEM like '%"+tempstr+"%'");
		}
		
 		// 测试时间, 如果时间条件为null，则搜索所有时间
		tempdate = ReportFilterData.GetInstance().getTesttime();
		if(tempdate != null){
			hql.append(" AND t.TESTTIME ='"+tempdate+"'");
		}
		
		//测试人, 如果测试人条件为null，则搜索所有人
		tempstr = ReportFilterData.GetInstance().getTestername();
		if(tempstr != null){
			hql.append(" AND t.T_NAME like '%"+tempstr+"%'");
		}
		
		//测试设备,如果为null，则搜索所有设备
		tempstr = ReportFilterData.GetInstance().getDeviceid();
		if(tempstr != null){
			hql.append(" AND t.DID='"+tempstr+"'");
		}
		else{
			List<String> deviceid = SignedManager.GetInstance().GetManagerDeviceIdList();
			if(deviceid.size() == 0)
				return null;
			
			hql.append(" AND t.DID in (");
			for(int i=0; i<deviceid.size(); i++){
				if(i == 0)
					hql.append("'" + deviceid.get(i) + "'");
				else
					hql.append(", '" + deviceid.get(i) + "'");
			}

			hql.append(")");
		}
			
 		
		//测试样本id,如果为null，则显示所有样本id的测试数据
		tempstr = ReportFilterData.GetInstance().getSimpleid();
		if(tempstr != null){
			hql.append(" AND t.SAMPLEID like '%"+tempstr+"%'");
		}

		//报告结果
		//如果为null，则显示所有
		//如果为合格，则显示所有合格的数据
		 //如果为不合格，则显示所有不合格的数据
		//如果为未处理，则显示所有未处理的数据
		tempstr = ReportFilterData.GetInstance().getReportresult();
		if(tempstr != null){
			if(tempstr.equals("未审核"))
				hql.append(" AND t.RESULT is null");
			else{
				hql.append(" AND t.RESULT='"+tempstr+"'");
			}	
		}
		hql.replace(hql.indexOf("AND"), hql.indexOf("AND")+3, "WHERE");
		
		StringBuffer hql1 = new StringBuffer("select t.CID ");
		hql1.append(hql);
		hql1.append(" limit " + ReportFilterData.GetInstance().getFirstindex() + "," +ReportFilterData.GetInstance().getPagesize());
		
		StringBuffer hql2 = new StringBuffer("select t1.* from TESTDATABEAN t1 inner join (" + hql1.toString() + ")t2 on t1.CID=t2.CID");
		StringBuffer hql3 = new StringBuffer("select t3.*, c.*, d.*, p1.*, m.*, p2.*, p3.* from (CARDBEAN c , DEVICEBEAN d, PERSONBEAN p1, MANAGERBEAN m, PERSONBEAN p2, PERSONBEAN p3)" 
				+ " INNER JOIN ( " + hql2.toString() + ")t3 on c.ID=t3.CID and d.ID=t3.DID and p1.ID=t3.T_ID and m.ACCOUNT=t3.M_ACCOUNT and p2.ID=m.P_ID and p3.ID=t3.S_ID");
		
		List<Object[]> list = HibernateDao.GetInstance().querysql(hql3.toString());
		
		if(isquerytotalnum){
			System.out.println("查询数目");
			
			hql1.setLength(0);
			hql1.append("select count(t.CID)");
			hql1.append(hql);
			
			totalnum = (Long) HibernateDao.GetInstance().queryonesql(hql1.toString());
			System.out.println(totalnum);
		}

		return new Object[]{list, totalnum};
	}
}
