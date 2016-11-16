package org.com.xsx.UI.MainScene.CardPage;

import java.util.List;
import java.util.Map;

import org.com.xsx.Dao.CardRecordDao;
import org.com.xsx.Dao.ReportDao;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class QueryCardRecordService extends Service<Object>{

	//��ѯ����
	private String QueryType = null;
	
	//��ѯ����
	private Object[] parm;
	
	public QueryCardRecordService(String type) {
		this.setQueryType(type);
	}
	
	@Override
	protected Task<Object> createTask() {
		// TODO Auto-generated method stub
		return new mytask();
	}
	
	class mytask extends Task<Object>{

		@Override
		protected Object call() throws Exception {
			// TODO Auto-generated method stub
			if(QueryType.equals("��ѯ�ܿ��"))
				return this.QueryCardSummyNum();
			
			else if(QueryType.equals("��ѯ�豸���"))
				return this.QueryReportCountByItem();
			
			else if(QueryType.equals("��ѯ������¼"))
				return this.QueryCardRecord();
			
			else 
				return null;
		}
		
		private Object QueryCardSummyNum() {
			Map<String, Integer> data = CardRecordDao.QueryCardRepertoryNumByItem();
			
			return data;
		}
		
		private Object QueryReportCountByItem() {
			Map<String, Integer> data = ReportDao.QueryReportCountGroupByItem(new java.sql.Date(System.currentTimeMillis()));
			
			return data;
		}
		
		private Object QueryCardRecord() {
			Object[] data = CardRecordDao.QueryCardRecordListByItem((String)parm[0], (Integer)parm[2], 50, (Boolean)parm[3]);
			
			return data;
		}
	}

	public String getQueryType() {
		return QueryType;
	}

	public void setQueryType(String queryType) {
		QueryType = queryType;
	}

	public Object[] getParm() {
		return parm;
	}

	public void setParm(Object[] parm) {
		this.parm = parm;
	}
}
