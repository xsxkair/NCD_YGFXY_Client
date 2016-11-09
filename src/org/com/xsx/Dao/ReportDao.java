package org.com.xsx.Dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.com.xsx.Data.ReportFilterData;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Domain.CardBean;
import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Domain.PersonBean;
import org.com.xsx.Domain.TestDataBean;
import org.com.xsx.Tools.HibernateDao;

public class ReportDao {
	
	public static Object[] QueryTestDataS(boolean isquerytotalnum){

		BigInteger totalnum = null;
		
		StringBuffer hql = new StringBuffer("from TESTDATABEAN");

		ArrayList<Object> parms = new ArrayList<>();
		String tempstr;
		java.sql.Date tempdate;
		
		
		// 测试项目,如果过滤条件为null，则搜索所有项目
		tempstr = ReportFilterData.GetInstance().getTestitem();
		if(tempstr != null){
			hql.append(" AND CITEM like '%"+tempstr+"%'");
		}
		
 		// 测试时间, 如果时间条件为null，则搜索所有时间
		tempdate = ReportFilterData.GetInstance().getTesttime();
		if(tempdate != null){
			hql.append(" AND DATE(TESTTIME) ='"+tempdate+"'");
		}
		
		//测试人, 如果测试人条件为null，则搜索所有人
		tempstr = ReportFilterData.GetInstance().getTestername();
		if(tempstr != null){
			hql.append(" AND T_NAME like '%"+tempstr+"%'");
		}
		
		//测试设备,如果为null，则搜索所有设备
		tempstr = ReportFilterData.GetInstance().getDeviceid();
		if(tempstr != null){
			hql.append(" AND DID='"+tempstr+"'");
		}
		else{
			List<String> deviceid = ManagerDao.QueryDeviceList(SignedManager.GetInstance().getGB_SignedAccount());
			if(deviceid.size() == 0)
				return null;
			
			hql.append(" AND DID in (");
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
			hql.append(" AND SAMPLEID like '%"+tempstr+"%'");
		}

		//报告结果
		//如果为null，则显示所有
		//如果为合格，则显示所有合格的数据
		 //如果为不合格，则显示所有不合格的数据
		//如果为未处理，则显示所有未处理的数据
		tempstr = ReportFilterData.GetInstance().getReportresult();
		if(tempstr != null){
			if(tempstr.equals("未审核"))
				hql.append(" AND RESULT is null");
			else{
				hql.append(" AND RESULT='"+tempstr+"'");
			}	
		}
		hql.replace(hql.indexOf("AND"), hql.indexOf("AND")+3, "WHERE");
		
		StringBuffer hql1 = new StringBuffer("select CID ");
		hql1.append(hql);
		hql1.append(" limit " 
				+ ReportFilterData.GetInstance().getFirstindex() 
				+ "," 
				+ReportFilterData.GetInstance().getPagesize());

		StringBuffer hql3 = new StringBuffer("select {testbean1.*}, {cardbean.*}, {device.*}, {tester.*}, {managerbean.*}, {sample.*}, {devicer.*} from CARDBEAN cardbean , DEVICEBEAN device, PERSONBEAN tester, MANAGERBEAN managerbean, PERSONBEAN sample, DEVICERBEAN devicer, TESTDATABEAN testbean1  " 
				+ " INNER JOIN ( " + hql1.toString() + ")testbean2 on testbean1.CID=testbean2.CID WHERE cardbean.ID=testbean1.CID and device.ID=testbean1.DID and devicer.ID=device.P_ID and tester.ID=testbean1.T_ID and managerbean.ACCOUNT=testbean1.M_ACCOUNT and sample.ID=testbean1.S_ID");
		
		List<Object[]> list = HibernateDao.GetInstance().querysql(hql3.toString(), 
				new Object[][]{{"testbean1", TestDataBean.class}, {"cardbean", CardBean.class}, {"device", DeviceBean.class}, {"tester", PersonBean.class}
				,{"managerbean", ManagerBean.class}, {"sample", PersonBean.class}, {"devicer", PersonBean.class}}
				);

		if(isquerytotalnum){			
			
			StringBuffer hql2 = new StringBuffer("select count(CID) ");
			hql2.append(hql);
			
			totalnum = (BigInteger) HibernateDao.GetInstance().queryonesql(hql2.toString());
		}

		return new Object[]{list, totalnum};
	}
	
	public static void DeleteReport(TestDataBean reportdata){

		//删除检测卡
		CardBean cardBean = ReportDao.ReadCardByCID(reportdata.getCid());
		HibernateDao.GetInstance().delete(cardBean);
		
		//删除样品
		PersonBean personBean = PersonDao.QueryPerson(reportdata.getS_id());
		HibernateDao.GetInstance().delete(personBean);
		
		//删除测试数据
		HibernateDao.GetInstance().delete(reportdata);
	}
	
	public static Object[] QueryTableDataList(boolean isquerytotalnum){

		BigInteger totalnum = null;
		
		StringBuffer hql = new StringBuffer("from TESTDATABEAN");

		ArrayList<Object> parms = new ArrayList<>();
		String tempstr;
		java.sql.Date tempdate;
		
		
		// 测试项目,如果过滤条件为null，则搜索所有项目
		tempstr = ReportFilterData.GetInstance().getTestitem();
		if(tempstr != null){
			hql.append(" AND CITEM like '%"+tempstr+"%'");
		}
		
 		// 测试时间, 如果时间条件为null，则搜索所有时间
		tempdate = ReportFilterData.GetInstance().getTesttime();
		if(tempdate != null){
			hql.append(" AND DATE(TESTTIME) ='"+tempdate+"'");
		}
		
		//测试人, 如果测试人条件为null，则搜索所有人
		tempstr = ReportFilterData.GetInstance().getTestername();
		if(tempstr != null){
			hql.append(" AND T_NAME like '%"+tempstr+"%'");
		}
		
		//测试设备,如果为null，则搜索所有设备
		tempstr = ReportFilterData.GetInstance().getDeviceid();
		if(tempstr != null){
			hql.append(" AND DID='"+tempstr+"'");
		}
		else{
			List<String> deviceid = ManagerDao.QueryDeviceList(SignedManager.GetInstance().getGB_SignedAccount());
			if(deviceid.size() == 0)
				return null;
			
			hql.append(" AND DID in (");
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
			hql.append(" AND SAMPLEID like '%"+tempstr+"%'");
		}

		//报告结果
		//如果为null，则显示所有
		//如果为合格，则显示所有合格的数据
		 //如果为不合格，则显示所有不合格的数据
		//如果为未处理，则显示所有未处理的数据
		tempstr = ReportFilterData.GetInstance().getReportresult();
		if(tempstr != null){
			if(tempstr.equals("未审核"))
				hql.append(" AND RESULT is null");
			else{
				hql.append(" AND RESULT='"+tempstr+"'");
			}	
		}
		hql.replace(hql.indexOf("AND"), hql.indexOf("AND")+3, "WHERE");
		
		StringBuffer hql1 = new StringBuffer("select CID ");
		hql1.append(hql);
		hql1.append(" limit " 
				+ ReportFilterData.GetInstance().getFirstindex() 
				+ "," 
				+ReportFilterData.GetInstance().getPagesize());

		StringBuffer hql3 = new StringBuffer("select {testbean1.*} from TESTDATABEAN testbean1  " 
				+ " INNER JOIN ( " + hql1.toString() + ")testbean2 on testbean1.CID=testbean2.CID ");
		
		List<TestDataBean> list = HibernateDao.GetInstance().querysql(hql3.toString(),
				new Object[][]{{"testbean1", TestDataBean.class}});

		if(isquerytotalnum){			
			
			StringBuffer hql2 = new StringBuffer("select count(CID) ");
			hql2.append(hql);
			
			totalnum = (BigInteger) HibernateDao.GetInstance().queryonesql(hql2.toString());
		}

		return new Object[]{list, totalnum};
	}
	
	public static CardBean ReadCardByCID(String cardid) {
		String hql = "select c from CardBean as c where c.id=:parm0 ";
		
		if(cardid == null)
			return null;
		
		return (CardBean) HibernateDao.GetInstance().queryOne(hql.toString(), new String[]{cardid});
	}
	
	public static Boolean UpdateReport(TestDataBean reportdata) {
		HibernateDao.GetInstance().update(reportdata);
		return true;
	}
}
