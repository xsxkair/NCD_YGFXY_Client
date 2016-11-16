package org.com.xsx.UI.MainScene.CardPage;

import java.util.List;
import java.util.Map;

import org.com.xsx.Dao.CardRecordDao;
import org.com.xsx.Dao.ReportDao;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class QueryCardRecordService extends Service<Object>{

	//查询类型
	private String QueryType = null;
	
	//查询参数
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
			if(QueryType.equals("查询总库存"))
				return this.QueryCardSummyNum();
			
			else if(QueryType.equals("查询设备库存"))
				return this.QueryReportCountByItem();
			
			else if(QueryType.equals("查询出入库记录"))
				return this.QueryReportCountByDevice();
			
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
		
		private Object QueryReportCountByDevice() {
			Map<String, Integer> data = ReportDao.QueryReportCountGroupByDevice(new java.sql.Date(System.currentTimeMillis()));
			
			return data;
		}
		
		private Object QueryReportCountDetail() {
			List<Object[]> result = ReportDao.QueryReportSummyChartData((Integer)parm[0], (Integer)parm[1], (List<String>)parm[2], (List<String>)parm[3]);
			return result;
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
