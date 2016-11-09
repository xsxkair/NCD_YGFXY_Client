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
		
		
		// ������Ŀ,�����������Ϊnull��������������Ŀ
		tempstr = ReportFilterData.GetInstance().getTestitem();
		if(tempstr != null){
			hql.append(" AND CITEM like '%"+tempstr+"%'");
		}
		
 		// ����ʱ��, ���ʱ������Ϊnull������������ʱ��
		tempdate = ReportFilterData.GetInstance().getTesttime();
		if(tempdate != null){
			hql.append(" AND DATE(TESTTIME) ='"+tempdate+"'");
		}
		
		//������, �������������Ϊnull��������������
		tempstr = ReportFilterData.GetInstance().getTestername();
		if(tempstr != null){
			hql.append(" AND T_NAME like '%"+tempstr+"%'");
		}
		
		//�����豸,���Ϊnull�������������豸
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
			
 		
		//��������id,���Ϊnull������ʾ��������id�Ĳ�������
		tempstr = ReportFilterData.GetInstance().getSimpleid();
		if(tempstr != null){
			hql.append(" AND SAMPLEID like '%"+tempstr+"%'");
		}

		//������
		//���Ϊnull������ʾ����
		//���Ϊ�ϸ�����ʾ���кϸ������
		 //���Ϊ���ϸ�����ʾ���в��ϸ������
		//���Ϊδ��������ʾ����δ���������
		tempstr = ReportFilterData.GetInstance().getReportresult();
		if(tempstr != null){
			if(tempstr.equals("δ���"))
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

		//ɾ����⿨
		CardBean cardBean = ReportDao.ReadCardByCID(reportdata.getCid());
		HibernateDao.GetInstance().delete(cardBean);
		
		//ɾ����Ʒ
		PersonBean personBean = PersonDao.QueryPerson(reportdata.getS_id());
		HibernateDao.GetInstance().delete(personBean);
		
		//ɾ����������
		HibernateDao.GetInstance().delete(reportdata);
	}
	
	public static Object[] QueryTableDataList(boolean isquerytotalnum){

		BigInteger totalnum = null;
		
		StringBuffer hql = new StringBuffer("from TESTDATABEAN");

		ArrayList<Object> parms = new ArrayList<>();
		String tempstr;
		java.sql.Date tempdate;
		
		
		// ������Ŀ,�����������Ϊnull��������������Ŀ
		tempstr = ReportFilterData.GetInstance().getTestitem();
		if(tempstr != null){
			hql.append(" AND CITEM like '%"+tempstr+"%'");
		}
		
 		// ����ʱ��, ���ʱ������Ϊnull������������ʱ��
		tempdate = ReportFilterData.GetInstance().getTesttime();
		if(tempdate != null){
			hql.append(" AND DATE(TESTTIME) ='"+tempdate+"'");
		}
		
		//������, �������������Ϊnull��������������
		tempstr = ReportFilterData.GetInstance().getTestername();
		if(tempstr != null){
			hql.append(" AND T_NAME like '%"+tempstr+"%'");
		}
		
		//�����豸,���Ϊnull�������������豸
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
			
 		
		//��������id,���Ϊnull������ʾ��������id�Ĳ�������
		tempstr = ReportFilterData.GetInstance().getSimpleid();
		if(tempstr != null){
			hql.append(" AND SAMPLEID like '%"+tempstr+"%'");
		}

		//������
		//���Ϊnull������ʾ����
		//���Ϊ�ϸ�����ʾ���кϸ������
		 //���Ϊ���ϸ�����ʾ���в��ϸ������
		//���Ϊδ��������ʾ����δ���������
		tempstr = ReportFilterData.GetInstance().getReportresult();
		if(tempstr != null){
			if(tempstr.equals("δ���"))
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
