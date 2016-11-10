package org.com.xsx.Dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.com.xsx.Data.ReportFilterData;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Domain.CardBean;
import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Domain.PersonBean;
import org.com.xsx.Domain.TestDataBean;
import org.com.xsx.Tools.HibernateDao;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class ReportDao {
	
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
	
	public static List<String> QueryAllItem(List<String> deviceid) {
		StringBuffer hql = new StringBuffer();
		
		hql.append("SELECT CITEM FROM TESTDATABEAN where DID IN (");
		for (String string : deviceid) {
			hql.append("'"+string+"'");
			if(deviceid.size() != (deviceid.indexOf(string)+1))
				hql.append(",");
		}
		hql.append(")");
		hql.append(" GROUP BY (CITEM)");
		
		List<String> queryresult = HibernateDao.GetInstance().querysql(hql.toString(), null);
		return queryresult;
		
	}
	
	public static Map<String, Integer> QueryReportCountGroupByResult(java.sql.Date date){
		Map<String, Integer> result = new HashMap<>();
		
		StringBuffer hql = new StringBuffer();
		
		List<String> deviceidlist = ManagerDao.QueryDeviceList(SignedManager.GetInstance().getGB_SignedAccount());
		
		hql.append("SELECT RESULT,COUNT(CID) FROM TESTDATABEAN where DATE(TESTTIME)='");
		hql.append(date);
		hql.append("'");
		hql.append(" AND DID IN (");
		for (String string : deviceidlist) {
			hql.append("'"+string+"'");
			if(deviceidlist.size() != (deviceidlist.indexOf(string)+1))
				hql.append(",");
		}
		hql.append(")");
		hql.append(" GROUP BY (RESULT)");
		
		List<Object[]> queryresult = HibernateDao.GetInstance().querysql(hql.toString(), null);
		
		for (Object[] objects : queryresult) {
			BigInteger num = (BigInteger) objects[1];
			result.put((String)objects[0], num.intValue());
		}
		
		return result;
	}
	
	public static Map<String, Integer> QueryReportCountGroupByItem(java.sql.Date date) {
		Map<String, Integer> result = new HashMap<>();
		
		StringBuffer hql = new StringBuffer();
		
		List<String> deviceidlist = ManagerDao.QueryDeviceList(SignedManager.GetInstance().getGB_SignedAccount());
		
		hql.append("SELECT CITEM,COUNT(CID) FROM TESTDATABEAN where DATE(TESTTIME)='");
		hql.append(date);
		hql.append("'");
		hql.append(" AND DID IN (");
		for (String string : deviceidlist) {
			hql.append("'"+string+"'");
			if(deviceidlist.size() != (deviceidlist.indexOf(string)+1))
				hql.append(",");
		}
		hql.append(")");
		hql.append(" GROUP BY (CITEM)");
		
		List<Object[]> queryresult = HibernateDao.GetInstance().querysql(hql.toString(), null);
		
		for (Object[] objects : queryresult) {
			BigInteger num = (BigInteger) objects[1];
			result.put((String)objects[0], num.intValue());
		}
		
		return result;
	}
	
	public static Map<String, Integer> QueryReportCountGroupByDevice(java.sql.Date date) {
		Map<String, Integer> result = new HashMap<>();
		
		StringBuffer hql = new StringBuffer();
		
		List<String> deviceidlist = ManagerDao.QueryDeviceList(SignedManager.GetInstance().getGB_SignedAccount());
		
		hql.append("SELECT DID,COUNT(CID) FROM TESTDATABEAN where DATE(TESTTIME)='");
		hql.append(date);
		hql.append("'");
		hql.append(" AND DID IN (");
		for (String string : deviceidlist) {
			hql.append("'"+string+"'");
			if(deviceidlist.size() != (deviceidlist.indexOf(string)+1))
				hql.append(",");
		}
		hql.append(")");
		hql.append(" GROUP BY (DID)");
		
		List<Object[]> queryresult = HibernateDao.GetInstance().querysql(hql.toString(), null);
		
		for (Object[] objects : queryresult) {
			BigInteger num = (BigInteger) objects[1];
			result.put((String)objects[0], num.intValue());
		}
		
		return result;
	}
	
	public static List<Object[]> QueryReportSummyChartData(Integer year, Integer month, List<String> itemlist, List<String> deviceidlist) {
		StringBuffer hql = new StringBuffer("SELECT ");
		
		String datestr1 = null;			//日期select
		String datestr2 = null;			//日期条件
		String datestr3 = null;			//日期分组
		if(year != null){
			if(month != null){
				datestr1 = "DATE_FORMAT(TESTTIME,'第%e天')";
				datestr2 = "DATE_FORMAT(TESTTIME,'%Y%c')='"+year+month+"'";
				datestr3 = "DATE_FORMAT(TESTTIME,'%d')";
			}
			else {
				datestr1 = "DATE_FORMAT(TESTTIME,'%c月')";
				datestr2 = "DATE_FORMAT(TESTTIME,'%Y')='"+year+"'";
				datestr3 = "DATE_FORMAT(TESTTIME,'%c')";
			}
		}
		else {
			datestr1 = "DATE_FORMAT(TESTTIME,'%Y年')";
			datestr2 = null;
			datestr3 = "DATE_FORMAT(TESTTIME,'%Y')";
		}
		
		hql.append(datestr1);
		hql.append(" ,CITEM, COUNT(CID) FROM TESTDATABEAN WHERE CITEM IN (");
		for (String string : itemlist) {
			hql.append("'"+string+"'");
			if(itemlist.size() != (itemlist.indexOf(string)+1))
				hql.append(",");
		}
		hql.append(")");
		
		hql.append(" AND DID IN (");
		for (String string : deviceidlist) {
			hql.append("'"+string+"'");
			if(deviceidlist.size() != (deviceidlist.indexOf(string)+1))
				hql.append(",");
		}
		hql.append(")");
		
		if(datestr2 != null){
			hql.append(" AND ");
			hql.append(datestr2);
		}
		
		hql.append(" GROUP BY ");
		hql.append(datestr3);
		hql.append(" ,CITEM");
		
		List<Object[]> queryresult = HibernateDao.GetInstance().querysql(hql.toString(), null);
		
		return queryresult;
	}
}
