package org.com.xsx.Service;

import java.util.List;

import org.com.xsx.Dao.DeviceInfoDao;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.UI.MainScene.DevicePage.DeviceTableItem;
import org.com.xsx.UI.MainScene.DevicePage.DeviceThumnPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class ReadDeviceInfoService extends ScheduledService<ObservableList<DeviceTableItem>>{
	
	private static ReadDeviceInfoService GB_ReadDeviceInfoService = null;
	
	private ReadDeviceInfoService() {
		// TODO Auto-generated constructor stub
	}
	
	public static ReadDeviceInfoService GetInstance() {
		if(GB_ReadDeviceInfoService == null){
			GB_ReadDeviceInfoService = new ReadDeviceInfoService();
			GB_ReadDeviceInfoService.setPeriod(Duration.minutes(5));
		}

		return GB_ReadDeviceInfoService;
	}
	
	@Override
	protected Task<ObservableList<DeviceTableItem>> createTask() {
		// TODO Auto-generated method stub
		return new ReadDeviceInfoTask();
	}
	
	class ReadDeviceInfoTask extends Task<ObservableList<DeviceTableItem>>{

		@Override
		protected ObservableList<DeviceTableItem> call(){
			// TODO Auto-generated method stub
			return ReadDeviceInfoFun();
		}
		
		private ObservableList<DeviceTableItem> ReadDeviceInfoFun(){
			ObservableList<DeviceTableItem> deviceTableItems = FXCollections.observableArrayList();
			
			List<Object[]> devices = DeviceInfoDao.QueryDeviceList(SignedManager.GetInstance().GetManagerDeviceIdList());

			for (Object[] deviceinfo : devices) {
				
				DeviceTableItem temp = new DeviceTableItem(deviceinfo);

				deviceTableItems.add(temp);
			}

			return deviceTableItems;
		}
	}
}
