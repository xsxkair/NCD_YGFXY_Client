package org.com.xsx.Service;

import java.util.List;

import org.com.xsx.Dao.DeviceInfoDao;
import org.com.xsx.Data.LoginUser;
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
			GB_ReadDeviceInfoService.setPeriod(Duration.minutes(1));
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
			
			List<DeviceInfoBean> devices = DeviceInfoDao.QueryDeviceS(LoginUser.GetInstance().getMy_deviceids());
			
			long currenttime = System.currentTimeMillis();
			long devicetime;
			
			Image off = null;
			Image on = null;
			off = new Image(this.getClass().getResourceAsStream("/RES/deviceico_off.png"));
			on = new Image(this.getClass().getResourceAsStream("/RES/deviceico_on.png"));
			
			for (DeviceInfoBean deviceInfoBean : devices) {
				
				DeviceTableItem temp = new DeviceTableItem(deviceInfoBean.getId(), deviceInfoBean.getDname(), deviceInfoBean.getDsex(), deviceInfoBean.getDage(),
						deviceInfoBean.getDjob(), deviceInfoBean.getDdesc(), deviceInfoBean.getDphone(), deviceInfoBean.getDaddr());
				
				devicetime = deviceInfoBean.getDltime();
				
				if((currenttime > devicetime) && (currenttime - devicetime > 120000)){
					temp.setDeviceico(off);
					temp.setDevicestatus("离线");
				}	
				else{
					temp.setDeviceico(on);
					if(deviceInfoBean.getDisok())
						temp.setDevicestatus("正常");
					else
						temp.setDevicestatus("异常");
				}
					
				temp.setDevicethumn(new DeviceThumnPane(temp.getDeviceico(), temp.getDeviceid()));
				
				deviceTableItems.add(temp);
			}
			
			return deviceTableItems;
		}
	}
}
