package org.com.xsx.UI.MainScene.DevicePage;

import java.util.List;

import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.DevicerBean;

public class DeviceDataPackage {
	private DeviceBean deviceBean;
	private List<DevicerBean> devicerlist;
	
	public DeviceDataPackage(DeviceBean deviceBean, List<DevicerBean> devicerlist) {
		this.deviceBean = deviceBean;
		this.devicerlist = devicerlist;
	}

	public DeviceBean getDeviceBean() {
		return deviceBean;
	}

	public void setDeviceBean(DeviceBean deviceBean) {
		this.deviceBean = deviceBean;
	}

	public List<DevicerBean> getDevicerlist() {
		return devicerlist;
	}

	public void setDevicerlist(List<DevicerBean> devicerlist) {
		this.devicerlist = devicerlist;
	}
}
