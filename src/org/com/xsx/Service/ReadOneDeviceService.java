package org.com.xsx.Service;

import java.util.List;

import org.com.xsx.Dao.DeviceInfoDao;
import org.com.xsx.Data.SignedManager;
import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.UI.MainScene.DevicePage.DeviceDataPackage;
import org.com.xsx.UI.MainScene.DevicePage.DeviceTableItem;
import org.com.xsx.UI.MainScene.DevicePage.DeviceThumnPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class ReadOneDeviceService extends ScheduledService<DeviceDataPackage>{
	
	private String S_DeviceID;
	
	@Override
	protected Task<DeviceDataPackage> createTask() {
		// TODO Auto-generated method stub
		return new ReadDeviceInfoTask();
	}
	
	class ReadDeviceInfoTask extends Task<DeviceDataPackage>{

		@Override
		protected DeviceDataPackage call(){
			// TODO Auto-generated method stub
			return ReadDeviceInfoFun();
		}
		
		private DeviceDataPackage ReadDeviceInfoFun(){
			
			DeviceDataPackage devicedata = DeviceInfoDao.QueryDevice(S_DeviceID);

			return devicedata;
		}
	}

	public String getS_DeviceID() {
		return S_DeviceID;
	}

	public void setS_DeviceID(String s_DeviceID) {
		S_DeviceID = s_DeviceID;
	}
}
