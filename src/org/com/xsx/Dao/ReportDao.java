package org.com.xsx.Dao;

import java.util.ArrayList;
import java.util.List;

import org.com.xsx.Data.ReportFilterData;
import org.com.xsx.Domain.TestDataBean;
import org.com.xsx.Tools.HibernateDao;

public class ReportDao {
	
	//��ȡ���в�����Ŀ
	public static List<String> QueryTestItemS(){

		String hql = "select distinct t.c_item from TestDataBean as t order by t.c_item";
		
		System.out.println(hql.toString());
		
		List<String> list =HibernateDao.GetInstance().query(hql.toString(), null, null, null);
		
		return list;
	}
	
	//��ȡ���в����豸
	public static List<String> QueryTestDeviceS(){

		String hql = "select distinct t.did from TestDataBean as t order by t.did";
			
		System.out.println(hql.toString());
			
		List<String> list =HibernateDao.GetInstance().query(hql.toString(), null, null, null);
			
		return list;
	}
	
	//��ȡ���в�����Ʒid
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
		 * ������Ŀ
		 * �����������Ϊnull��������������Ŀ
		 */
		tempstr = ReportFilterData.GetInstance().getTestitem();
		if(tempstr != null){
			hql.append(" AND t.c_item=:parm"+parms.size());
			parms.add(tempstr);
		}
		
 		/*
 		 * ����ʱ��
 		 * ���ʱ������Ϊnull������������ʱ��
 		 */
		tempdate = ReportFilterData.GetInstance().getTesttime();
		if(tempdate != null){
			hql.append(" AND t.testd =:parm"+parms.size());
			parms.add(tempdate);
		}
		
		/*
		 * ������
		 * �������������Ϊnull��������������
		 */
/*		if(ReportFilterData.GetInstance().getTestername() != null){
			hql.append(" AND d.name=:mytester");
		}*/
		
		/*
		 * �����豸
		 * ���Ϊnull�������������豸
		 */
		tempstr = ReportFilterData.GetInstance().getDeviceid();
		if(tempstr != null){
			hql.append(" AND t.did=:parm"+parms.size());
			parms.add(tempstr);
		}
 		
		/*
		 * ��������id
		 * ���Ϊnull������ʾ��������id�Ĳ�������
		 */
		tempstr = ReportFilterData.GetInstance().getSimpleid();
		if(tempstr != null){
			hql.append(" AND t.sid=:parm"+parms.size());
			parms.add(tempstr);
		}

		/*
		 * ������
		 * ���Ϊnull������ʾ����
		 * ���Ϊ�ϸ�����ʾ���кϸ������
		 * ���Ϊ���ϸ�����ʾ���в��ϸ������
		 * ���Ϊδ��������ʾ����δ���������
		 */
		tempstr = ReportFilterData.GetInstance().getReportresult();
		if(tempstr != null){
			if(tempstr.equals("δ����"))
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
		 * ������Ŀ
		 * �����������Ϊnull��������������Ŀ
		 */
		tempstr = ReportFilterData.GetInstance().getTestitem();
		if(tempstr != null){
			hql.append(" AND t.c_item=:parm"+parms.size());
			parms.add(tempstr);
		}
		
 		/*
 		 * ����ʱ��
 		 * ���ʱ������Ϊnull������������ʱ��
 		 */
		tempdate = ReportFilterData.GetInstance().getTesttime();
		if(tempdate != null){
			hql.append(" AND t.testd =:parm"+parms.size());
			parms.add(tempdate);
		}
		
		/*
		 * ������
		 * �������������Ϊnull��������������
		 */
/*		if(ReportFilterData.GetInstance().getTestername() != null){
			hql.append(" AND d.name=:mytester");
		}*/
		
		/*
		 * �����豸
		 * ���Ϊnull�������������豸
		 */
		tempstr = ReportFilterData.GetInstance().getDeviceid();
		if(tempstr != null){
			hql.append(" AND t.did=:parm"+parms.size());
			parms.add(tempstr);
		}
 		
		/*
		 * ��������id
		 * ���Ϊnull������ʾ��������id�Ĳ�������
		 */
		tempstr = ReportFilterData.GetInstance().getSimpleid();
		if(tempstr != null){
			hql.append(" AND t.sid=:parm"+parms.size());
			parms.add(tempstr);
		}

		/*
		 * ������
		 * ���Ϊnull������ʾ����
		 * ���Ϊ�ϸ�����ʾ���кϸ������
		 * ���Ϊ���ϸ�����ʾ���в��ϸ������
		 * ���Ϊδ��������ʾ����δ���������
		 */
		tempstr = ReportFilterData.GetInstance().getReportresult();
		if(tempstr != null){
			if(tempstr.equals("δ����"))
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
