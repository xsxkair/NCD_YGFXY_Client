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
		
		// ������Ŀ,�����������Ϊnull��������������Ŀ
		tempstr = ReportFilterData.GetInstance().getTestitem();
		if(tempstr != null){
			hql.append(" AND t.ITEM like '%"+tempstr+"%'");
		}
		
 		// ����ʱ��, ���ʱ������Ϊnull������������ʱ��
		tempdate = ReportFilterData.GetInstance().getTesttime();
		if(tempdate != null){
			hql.append(" AND t.TESTTIME ='"+tempdate+"'");
		}
		
		//������, �������������Ϊnull��������������
		tempstr = ReportFilterData.GetInstance().getTestername();
		if(tempstr != null){
			hql.append(" AND t.T_NAME like '%"+tempstr+"%'");
		}
		
		//�����豸,���Ϊnull�������������豸
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
			
 		
		//��������id,���Ϊnull������ʾ��������id�Ĳ�������
		tempstr = ReportFilterData.GetInstance().getSimpleid();
		if(tempstr != null){
			hql.append(" AND t.SAMPLEID like '%"+tempstr+"%'");
		}

		//������
		//���Ϊnull������ʾ����
		//���Ϊ�ϸ�����ʾ���кϸ������
		 //���Ϊ���ϸ�����ʾ���в��ϸ������
		//���Ϊδ��������ʾ����δ���������
		tempstr = ReportFilterData.GetInstance().getReportresult();
		if(tempstr != null){
			if(tempstr.equals("δ���"))
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
			System.out.println("��ѯ��Ŀ");
			
			hql1.setLength(0);
			hql1.append("select count(t.CID)");
			hql1.append(hql);
			
			totalnum = (Long) HibernateDao.GetInstance().queryonesql(hql1.toString());
			System.out.println(totalnum);
		}

		return new Object[]{list, totalnum};
	}
}
