package org.com.xsx.Dao;

import java.util.ArrayList;
import java.util.List;

import org.com.xsx.Data.ReportFilterData;
import org.com.xsx.Domain.TestDataBean;
import org.com.xsx.Tools.HibernateDao;

public class ReportDao {
	
	//读取所有测试项目
	public static List<String> QueryTestItemS(){

		String hql = "select distinct t.c_item from TestDataBean as t order by t.c_item";
		
		System.out.println(hql.toString());
		
		List<String> list =HibernateDao.GetInstance().query(hql.toString(), null, null, null);
		
		return list;
	}
	
	//读取所有测试设备
	public static List<String> QueryTestDeviceS(){

		String hql = "select distinct t.did from TestDataBean as t order by t.did";
			
		System.out.println(hql.toString());
			
		List<String> list =HibernateDao.GetInstance().query(hql.toString(), null, null, null);
			
		return list;
	}
	
	//读取所有测试样品id
	public static List<String> QueryTestSampleS(){

		String hql = "select distinct t.sid from TestDataBean as t order by t.sid";
			
		System.out.println(hql.toString());
			
		List<String> list =HibernateDao.GetInstance().query(hql.toString(), null, null, null);
			
		return list;
	}
	
	public static List<TestDataBean> QueryTestDataS(){

		StringBuffer hql = new StringBuffer("select t from TestDataBean as t");
		ArrayList<Object> parms = new ArrayList<>();
		String tempstr;
		java.sql.Date tempdate;
		/*
		 * 测试项目
		 * 如果过滤条件为null，则搜索所有项目
		 */
		tempstr = ReportFilterData.GetInstance().getTestitem();
		if(tempstr != null){
			hql.append(" AND t.c_item=:parm"+parms.size());
			parms.add(tempstr);
		}
		
 		/*
 		 * 测试时间
 		 * 如果时间条件为null，则搜索所有时间
 		 */
		tempdate = ReportFilterData.GetInstance().getTesttime();
		if(tempdate != null){
			hql.append(" AND t.testd =:parm"+parms.size());
			parms.add(tempdate);
		}
		
		/*
		 * 测试人
		 * 如果测试人条件为null，则搜索所有人
		 */
/*		if(ReportFilterData.GetInstance().getTestername() != null){
			hql.append(" AND d.name=:mytester");
		}*/
		
		/*
		 * 测试设备
		 * 如果为null，则搜索所有设备
		 */
		tempstr = ReportFilterData.GetInstance().getDeviceid();
		if(tempstr != null){
			hql.append(" AND t.did=:parm"+parms.size());
			parms.add(tempstr);
		}
 		
		/*
		 * 测试样本id
		 * 如果为null，则显示所有样本id的测试数据
		 */
		tempstr = ReportFilterData.GetInstance().getSimpleid();
		if(tempstr != null){
			hql.append(" AND t.sid=:parm"+parms.size());
			parms.add(tempstr);
		}

		/*
		 * 报告结果
		 * 如果为null，则显示所有
		 * 如果为合格，则显示所有合格的数据
		 * 如果为不合格，则显示所有不合格的数据
		 * 如果为未处理，则显示所有未处理的数据
		 */
		tempstr = ReportFilterData.GetInstance().getReportresult();
		if(tempstr != null){
			if(tempstr.equals("未处理"))
				hql.append(" AND t.r_re is null");
			else{
				hql.append(" AND t.r_re=:parm"+parms.size());
				parms.add(tempstr);
			}	
		}
		
		String string = hql.toString().replaceFirst("AND", "WHERE");
		
		System.out.println(string);
		
		List<TestDataBean> list =HibernateDao.GetInstance().query(string, parms.toArray(), ReportFilterData.GetInstance().getFirstindex(), ReportFilterData.GetInstance().getPagesize());
		
		return list;
	}
	
	public static Long QueryTestDataNum(){

		StringBuffer hql = new StringBuffer("select count(*) from TestDataBean as t");
		ArrayList<Object> parms = new ArrayList<>();
		String tempstr;
		java.sql.Date tempdate;
		/*
		 * 测试项目
		 * 如果过滤条件为null，则搜索所有项目
		 */
		tempstr = ReportFilterData.GetInstance().getTestitem();
		if(tempstr != null){
			hql.append(" AND t.c_item=:parm"+parms.size());
			parms.add(tempstr);
		}
		
 		/*
 		 * 测试时间
 		 * 如果时间条件为null，则搜索所有时间
 		 */
		tempdate = ReportFilterData.GetInstance().getTesttime();
		if(tempdate != null){
			hql.append(" AND t.testd =:parm"+parms.size());
			parms.add(tempdate);
		}
		
		/*
		 * 测试人
		 * 如果测试人条件为null，则搜索所有人
		 */
/*		if(ReportFilterData.GetInstance().getTestername() != null){
			hql.append(" AND d.name=:mytester");
		}*/
		
		/*
		 * 测试设备
		 * 如果为null，则搜索所有设备
		 */
		tempstr = ReportFilterData.GetInstance().getDeviceid();
		if(tempstr != null){
			hql.append(" AND t.did=:parm"+parms.size());
			parms.add(tempstr);
		}
 		
		/*
		 * 测试样本id
		 * 如果为null，则显示所有样本id的测试数据
		 */
		tempstr = ReportFilterData.GetInstance().getSimpleid();
		if(tempstr != null){
			hql.append(" AND t.sid=:parm"+parms.size());
			parms.add(tempstr);
		}

		/*
		 * 报告结果
		 * 如果为null，则显示所有
		 * 如果为合格，则显示所有合格的数据
		 * 如果为不合格，则显示所有不合格的数据
		 * 如果为未处理，则显示所有未处理的数据
		 */
		tempstr = ReportFilterData.GetInstance().getReportresult();
		if(tempstr != null){
			if(tempstr.equals("未处理"))
				hql.append(" AND t.r_re is null");
			else{
				hql.append(" AND t.r_re=:parm"+parms.size());
				parms.add(tempstr);
			}	
		}
		
		String string = hql.toString().replaceFirst("AND", "WHERE");
		
		System.out.println(string);
		
		return (Long) HibernateDao.GetInstance().queryOne(string, parms.toArray());
	}
}
