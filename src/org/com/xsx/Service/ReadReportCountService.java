package org.com.xsx.Service;

import org.com.xsx.Dao.ReportDao;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ReadReportCountService extends Service<Long>{

	private static ReadReportCountService S_ReadReportCountService = null;
	
	private ReadReportCountService() {
		// TODO Auto-generated constructor stub
	}
	
	public static ReadReportCountService GetInstance() {
		if(S_ReadReportCountService == null)
			S_ReadReportCountService = new ReadReportCountService();
		
		return S_ReadReportCountService;
	}
	
	@Override
	protected Task<Long> createTask() {
		// TODO Auto-generated method stub
		return new mytask();
	}
	
	class mytask extends Task<Long>{

		@Override
		protected Long call() throws Exception {
			// TODO Auto-generated method stub
			System.out.println("¶ÁÊýÄ¿");
			return ReportDao.QueryTestDataNum();
		}
	}
}
