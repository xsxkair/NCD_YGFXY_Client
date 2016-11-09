package org.com.xsx.UI.MainScene.DevicePage;

import java.util.List;

import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.DevicerBean;

public class DeviceDataPackage {
	private DeviceBean deviceBean;
	private DevicerBean devicerBean;
	private List<DevicerBean> devicerlist;
	
	public DeviceDataPackage(DeviceBean deviceBean, DevicerBean devicerBean, List<DevicerBean> devicerlist) {
		this.deviceBean = deviceBean;
		this.devicerBean = devicerBean;
		this.devicerlist = devicerlist;
	}

	public DeviceBean getDeviceBean() {
		return deviceBean;
	}

	public void setDeviceBean(DeviceBean deviceBean) {
		this.deviceBean = deviceBean;
	}

	public DevicerBean getDevicerBean() {
		return devicerBean;
	}

	public void setDevicerBean(DevicerBean devicerBean) {
		this.devicerBean = devicerBean;
	}

	public List<DevicerBean> getDevicerlist() {
		return devicerlist;
	}

	public void setDevicerlist(List<DevicerBean> devicerlist) {
		this.devicerlist = devicerlist;
	}
}
