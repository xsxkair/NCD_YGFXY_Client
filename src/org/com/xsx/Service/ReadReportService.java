package org.com.xsx.Service;

import java.util.List;

import org.com.xsx.Dao.ReportDao;
import org.com.xsx.Data.ReportFilterData;
import org.com.xsx.Data.SelectedReportList;
import org.com.xsx.Domain.TestDataBean;
import org.com.xsx.UI.MainScene.Report.ReportListPage.ReportListTableItem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ReadReportService extends Service<ObservableList<ReportListTableItem>>{
	
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
	protected Task<ObservableList<ReportListTableItem>> createTask() {
		// TODO Auto-generated method stub
		return new ReadDeviceInfoTask();
	}
	
	class ReadDeviceInfoTask extends Task<ObservableList<ReportListTableItem>>{

		@Override
		protected ObservableList<ReportListTableItem> call(){
			// TODO Auto-generated method stub
			return ReadDeviceInfoFun();
		}
		
		private ObservableList<ReportListTableItem> ReadDeviceInfoFun(){
			ObservableList<ReportListTableItem> reportTableItems = FXCollections.observableArrayList();
			
			List<Object[]> reportdatas = ReportDao.QueryTestDataS();
			System.out.println("ÊýÄ¿"+reportdatas.size());
			for (Object[] objects : reportdatas) {

				ReportListTableItem temp = new ReportListTableItem();
    			
    			temp.setReportdata(objects);
    			temp.setIndex(ReportFilterData.GetInstance().getFirstindex()+1+reportdatas.indexOf(objects));

    			reportTableItems.add(temp);
    			
    			updateProgress(reportdatas.indexOf(objects), reportdatas.size());
			}

			return reportTableItems;
		}
	}
}
