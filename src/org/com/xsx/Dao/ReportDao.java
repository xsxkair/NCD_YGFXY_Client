package org.com.xsx.Dao;

import java.util.ArrayList;
import java.util.List;

import org.com.xsx.Data.ReportFilterData;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Tools.HibernateDao;

public class ReportDao {
	
	public static Object[] QueryTestDataS(boolean isquerytotalnum){

		StringBuffer hql = new StringBuffer();
		StringBuffer hql1 = new StringBuffer("select t,c,d,p,s,m");
		StringBuffer hql2 = new StringBuffer(" from TestDataBean t, CardBean c, DeviceBean d, PersonBean p, SampleBean s, ManagerBean m");
		hql2.append(" where c.id=t.cid AND d.id=t.did AND p.id=t.tester_id AND s.id=t.sample_id AND m.account=t.manageraccount");
		ArrayList<Object> parms = new ArrayList<>();
		String tempstr;
		java.sql.Date tempdate;
		
		// ������Ŀ,�����������Ϊnull��������������Ŀ
		tempstr = ReportFilterData.GetInstance().getTestitem();
		if(tempstr != null){
			
//			hql.append(" AND c.item like '%"+tempstr+"%'");
			hql2.append(" AND c.item like :parm"+parms.size());
			parms.add("%"+tempstr+"%");
		}
		
 		// ����ʱ��, ���ʱ������Ϊnull������������ʱ��
		tempdate = ReportFilterData.GetInstance().getTesttime();
		if(tempdate != null){
//			hql.append(" AND t.testd ='"+tempdate+"'");
			hql2.append(" AND t.testd =:parm"+parms.size());
			parms.add(tempdate);
		}
		
		//������, �������������Ϊnull��������������
		tempstr = ReportFilterData.GetInstance().getTestername();
		if(tempstr != null){
//			hql.append(" AND t.t_name like '%"+tempstr+"%'");
			hql2.append(" AND p.pname like :parm"+parms.size());
			parms.add("%"+tempstr+"%");
		}
		
		//�����豸,���Ϊnull�������������豸
		tempstr = ReportFilterData.GetInstance().getDeviceid();
		if(tempstr != null){
//			hql.append(" AND t.did='"+tempstr+"'");
			hql2.append(" AND d.id=:parm"+parms.size());
			parms.add(tempstr);
		}
		else{
			List<String> deviceid = SignedManager.GetInstance().GetManagerDeviceIdList();
			if(deviceid.size() == 0)
				return null;
			
/*			hql.append(" AND d.id in (");
			for(int i=0; i<deviceid.size(); i++){
				if(i == 0)
					hql.append("'" + deviceid.get(i) + "'");
				else
					hql.append(", '" + deviceid.get(i) + "'");
			}

			hql.append(")");
*/
			hql2.append(" AND d.id in (:parm"+parms.size()+")");
			parms.add(deviceid);
		}
			
 		
		//��������id,���Ϊnull������ʾ��������id�Ĳ�������
		tempstr = ReportFilterData.GetInstance().getSimpleid();
		if(tempstr != null){
//			hql.append(" AND t.sid like '%"+tempstr+"%'");
			hql2.append(" AND s.s_id like :parm"+parms.size());
			parms.add("%"+tempstr+"%");
		}

		//������
		//���Ϊnull������ʾ����
		//���Ϊ�ϸ�����ʾ���кϸ������
		 //���Ϊ���ϸ�����ʾ���в��ϸ������
		//���Ϊδ��������ʾ����δ���������
		tempstr = ReportFilterData.GetInstance().getReportresult();
		if(tempstr != null){
			if(tempstr.equals("δ���"))
				hql2.append(" AND t.r_re is null");
			else{
//				hql.append(" AND t.r_re='"+tempstr+"'");
				hql2.append(" AND t.r_re=:parm"+parms.size());
				parms.add(tempstr);
			}	
		}		
		
		hql.append(hql1);
		hql.append(hql2);
		List<Object[]> list = HibernateDao.GetInstance().query(hql.toString(), parms.toArray(), ReportFilterData.GetInstance().getFirstindex(), ReportFilterData.GetInstance().getPagesize());
		
		if(isquerytotalnum){
			System.out.println("��ѯ��Ŀ");
			hql.setLength(0);
			hql.append("select count(t.cid)");
			hql.append(hql2);
			Long totalnum = (Long) HibernateDao.GetInstance().queryOne(hql.toString(), parms.toArray());
			return new Object[]{list, totalnum};
		}
		else{
			return new Object[]{list, null};
		}
	}
}
