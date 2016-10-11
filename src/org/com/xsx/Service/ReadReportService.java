package org.com.xsx.Service;

import java.util.List;

import org.com.xsx.Dao.ReportDao;
import org.com.xsx.Data.ReportFilterData;
import org.com.xsx.Data.SelectedReportList;
import org.com.xsx.Domain.TestDataBean;
import org.com.xsx.UI.MainScene.ReportPage.ReportTableItem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ReadReportService extends Service<ObservableList<ReportTableItem>>{
	
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
	protected Task<ObservableList<ReportTableItem>> createTask() {
		// TODO Auto-generated method stub
		return new ReadDeviceInfoTask();
	}
	
	class ReadDeviceInfoTask extends Task<ObservableList<ReportTableItem>>{

		@Override
		protected ObservableList<ReportTableItem> call(){
			// TODO Auto-generated method stub
			return ReadDeviceInfoFun();
		}
		
		private ObservableList<ReportTableItem> ReadDeviceInfoFun(){
			ObservableList<ReportTableItem> reportTableItems = FXCollections.observableArrayList();
			
			System.out.println("¶ÁÊý¾Ý");
			
			List<TestDataBean> testDataBeans = ReportDao.QueryTestDataS();
			
			for (TestDataBean testDataBean : testDataBeans) {
				ReportTableItem temp = new ReportTableItem();
    			
    			temp.setTestdatabean(testDataBean);
    			temp.setIndex(ReportFilterData.GetInstance().getFirstindex()+1+testDataBeans.indexOf(testDataBean));
    			
    			if(SelectedReportList.GetInstance().getSelectedDataMap().get(testDataBean.getCid()) == null)
    				temp.setIsselected(false);
    			else
    				temp.setIsselected(true);
    			reportTableItems.add(temp);
    			
    			updateProgress(testDataBeans.indexOf(testDataBean), testDataBeans.size());
			}

			return reportTableItems;
		}
	}
}
