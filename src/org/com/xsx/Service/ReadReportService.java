package org.com.xsx.Service;

import java.util.List;

import org.com.xsx.Dao.ReportDao;
import org.com.xsx.Data.ReportFilterData;
import org.com.xsx.Domain.TestDataBean;
import org.com.xsx.UI.MainScene.Report.ReportListPage.ReportListTableItem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ReadReportService extends Service<Object[]>{
	
	private static ReadReportService GB_ReadReportService = null;
	
	private ReadReportService() {
		// TODO Auto-generated constructor stub
	}
	
	public static ReadReportService GetInstance() {
		if(GB_ReadReportService == null){
			GB_ReadReportService = new ReadReportService();
		}

		return GB_ReadReportService;
	}
	
	@Override
	protected Task<Object[]> createTask() {
		// TODO Auto-generated method stub
		return new ReadDeviceInfoTask();
	}
	
	class ReadDeviceInfoTask extends Task<Object[]>{

		@Override
		protected Object[] call(){
			// TODO Auto-generated method stub
			return ReadDeviceInfoFun();
		}
		
		private Object[] ReadDeviceInfoFun(){
			ObservableList<ReportListTableItem> reportTableItems = FXCollections.observableArrayList();
			
			Object[] reportdatas = ReportDao.QueryTestDataS(ReportFilterData.GetInstance().isFilterisnew());
			
			List<Object[]> reportdatalist =  (List<Object[]>) reportdatas[0];
			
			for (Object[] report : reportdatalist) {
				
				
				ReportListTableItem temp = new ReportListTableItem();
    			
    			temp.setReportdata(report);
    			temp.setIndex(ReportFilterData.GetInstance().getFirstindex()+1+reportdatalist.indexOf(report));

    			reportTableItems.add(temp);
    			
    			updateProgress(reportdatalist.indexOf(report), reportdatalist.size());
			}

			return new Object[]{reportTableItems, reportdatas[1]};
		}
	}
}
