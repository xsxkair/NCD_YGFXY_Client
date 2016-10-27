package org.com.xsx.Dao;

import java.util.ArrayList;
import java.util.List;

import org.com.xsx.Data.ReportFilterData;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Domain.TestDataBean;
import org.com.xsx.Tools.HibernateDao;

public class ReportDao {
	
	public static List<Object[]> QueryTestDataS(){

		StringBuffer hql = new StringBuffer("select t,c,d,p,s,m from TestDataBean t, CardBean c, DeviceBean d, PersonBean p, SampleBean s, ManagerBean m");
		hql.append(" where c.id=t.cid AND d.id=t.did AND t.tester_id=p.id AND t.sample_id=s.id AND t.manageraccount=m.account");
		ArrayList<Object> parms = new ArrayList<>();
		String tempstr;
		java.sql.Date tempdate;
		
		// 测试项目,如果过滤条件为null，则搜索所有项目
		tempstr = ReportFilterData.GetInstance().getTestitem();
		if(tempstr != null){
//			hql.append(" AND c.item like '%"+tempstr+"%'");
			hql.append(" AND c.item like :parm"+parms.size());
			parms.add("%"+tempstr+"%");
		}
		
/* 		// 测试时间, 如果时间条件为null，则搜索所有时间
		tempdate = ReportFilterData.GetInstance().getTesttime();
		if(tempdate != null){
			hql.append(" AND t.testd ='"+tempdate+"'");
//			hql.append(" AND t.testd =:parm"+parms.size());
//			parms.add(tempdate);
		}
		
		//测试人, 如果测试人条件为null，则搜索所有人
		tempstr = ReportFilterData.GetInstance().getTestername();
		if(tempstr != null){
			hql.append(" AND t.t_name like '%"+tempstr+"%'");
//			hql.append(" AND t.t_name like :parm"+parms.size());
//			parms.add("%"+tempstr+"%");
		}
		
		//测试设备,如果为null，则搜索所有设备
		tempstr = ReportFilterData.GetInstance().getDeviceid();
		if(tempstr != null){
			hql.append(" AND t.did='"+tempstr+"'");
//			hql.append(" AND t.did=:parm"+parms.size());
//			parms.add(tempstr);
		}
		else{
			List<String> deviceid = LoginUser.GetInstance().getMy_deviceids();
			if(deviceid.size() == 0)
				return null;
			
			hql.append(" AND t.did in (");
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
			hql.append(" AND t.sid like '%"+tempstr+"%'");
//			hql.append(" AND t.sid like :parm"+parms.size());
//			parms.add("%"+tempstr+"%");
		}

		//报告结果
		//如果为null，则显示所有
		//如果为合格，则显示所有合格的数据
		 //如果为不合格，则显示所有不合格的数据
		//如果为未处理，则显示所有未处理的数据
		tempstr = ReportFilterData.GetInstance().getReportresult();
		if(tempstr != null){
			if(tempstr.equals("未审核"))
				hql.append(" AND t.r_re is null");
			else{
				hql.append(" AND t.r_re='"+tempstr+"'");
//				hql.append(" AND t.r_re=:parm"+parms.size());
//				parms.add(tempstr);
			}	
		}
	
		hql.append(" limit " + ReportFilterData.GetInstance().getFirstindex() + "," +ReportFilterData.GetInstance().getPagesize());
		
		String string = hql.toString().replaceFirst("AND", "WHERE");
		
		StringBuffer hql1 = new StringBuffer("select * from TESTDATABEAN a INNER JOIN(");
		hql1.append(string);
		hql1.append(")b ON a.cid=b.cid");
		
		List<TestDataBean> list = HibernateDao.GetInstance().querysql(hql1.toString(), TestDataBean.class);
	*/	
		
		List<Object[]> list = HibernateDao.GetInstance().query(hql.toString(), parms.toArray(), ReportFilterData.GetInstance().getFirstindex(), ReportFilterData.GetInstance().getPagesize());
		return list;
	}
	
	public static Long QueryTestDataNum(){

		StringBuffer hql = new StringBuffer("select count(t.cid) from TestDataBean as t");
		ArrayList<Object> parms = new ArrayList<>();
		String tempstr;
		java.sql.Date tempdate;
/*		//测试项目,如果过滤条件为null，则搜索所有项目
		tempstr = ReportFilterData.GetInstance().getTestitem();
		if(tempstr != null){
			hql.append(" AND t.c_item like :parm"+parms.size());
			parms.add("%"+tempstr+"%");
		}
		
 		// 测试时间, 如果时间条件为null，则搜索所有时间
		tempdate = ReportFilterData.GetInstance().getTesttime();
		if(tempdate != null){
			hql.append(" AND t.testd =:parm"+parms.size());
			parms.add(tempdate);
		}
		
		// 测试人
		//如果测试人条件为null，则搜索所有人
		tempstr = ReportFilterData.GetInstance().getTestername();
		if(tempstr != null){
			hql.append(" AND t.t_name like :parm"+parms.size());
			parms.add("%"+tempstr+"%");
		}
		
		//测试设备
		//如果为null，则搜索所有设备
		tempstr = ReportFilterData.GetInstance().getDeviceid();
		if(tempstr != null){
			hql.append(" AND t.did=:parm"+parms.size());
			parms.add(tempstr);
		}
		else{
			hql.append(" AND t.did in (:parm"+parms.size() + ")");
			parms.add(SignedManager.GetInstance().GetManagerDeviceIdList());
		}
 		
		// 测试样本id
		//如果为null，则显示所有样本id的测试数据
		tempstr = ReportFilterData.GetInstance().getSimpleid();
		if(tempstr != null){
			hql.append(" AND t.sid like :parm"+parms.size());
			parms.add("%"+tempstr+"%");
		}

		//报告结果
		//如果为null，则显示所有
		//如果为合格，则显示所有合格的数据
		//如果为不合格，则显示所有不合格的数据
		//如果为未处理，则显示所有未处理的数据
		tempstr = ReportFilterData.GetInstance().getReportresult();
		if(tempstr != null){
			if(tempstr.equals("未审核"))
				hql.append(" AND t.r_re is null");
			else{
				hql.append(" AND t.r_re=:parm"+parms.size());
				parms.add(tempstr);
			}	
		}
		
		String string = hql.toString().replaceFirst("AND", "WHERE");
*/
		return (Long) HibernateDao.GetInstance().queryOne(hql.toString(), parms.toArray());
	}
}
