package org.com.xsx.UI.MainScene.DevicePage;

import java.util.List;

import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.DevicerBean;
import org.com.xsx.Domain.PersonBean;

import javafx.scene.image.Image;

public class DeviceTableItem {
	
	private Image deviceico;
	private String deviceid;
	private String devicemanagername;
	private String devicemanagerphone;
	private String deviceaddr;
	private String devicestatus;
	
	private DeviceThumnPane devicethumn;
	
	private DeviceBean devicebean;					//�豸��Ϣ
	
	public DeviceTableItem(DeviceBean deviceBean){
		this.setDevicebean(deviceBean);
	}
	
	public Image getDeviceico() {
		return deviceico;
	}

	public void setDeviceico(Image deviceico) {
		this.deviceico = deviceico;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getDevicemanagername() {
		return devicemanagername;
	}

	public void setDevicemanagername(String devicemanagername) {
		this.devicemanagername = devicemanagername;
	}

	public String getDevicemanagerphone() {
		return devicemanagerphone;
	}

	public void setDevicemanagerphone(String devicemanagerphone) {
		this.devicemanagerphone = devicemanagerphone;
	}

	public String getDeviceaddr() {
		return deviceaddr;
	}

	public void setDeviceaddr(String deviceaddr) {
		this.deviceaddr = deviceaddr;
	}

	public String getDevicestatus() {
		return devicestatus;
	}

	public void setDevicestatus(String devicestatus) {
		this.devicestatus = devicestatus;
	}

	public DeviceThumnPane getDevicethumn() {
		return devicethumn;
	}

	public void setDevicethumn(DeviceThumnPane devicethumn) {
		this.devicethumn = devicethumn;
	}

	public DeviceBean getDevicebean() {
		return devicebean;
	}

	public void setDevicebean(DeviceBean devicebean) {
		this.devicebean = devicebean;
		
		long currenttime = System.currentTimeMillis();
		Long devicetime = devicebean.getDltime();

		if((devicetime == null) || ((currenttime > devicetime) && (currenttime - devicetime > 120000))){
			this.setDeviceico(new Image(this.getClass().getResourceAsStream("/RES/deviceico_off.png")));
			this.setDevicestatus("����");
		}
		else{
			this.setDeviceico(new Image(this.getClass().getResourceAsStream("/RES/deviceico_on.png")));
			if(devicebean.getDisok())
				this.setDevicestatus("����");
			else
				this.setDevicestatus("�쳣");
		}
		
		this.setDeviceid(devicebean.getId());
		this.setDeviceaddr(devicebean.getDaddr());
		this.setDevicemanagername(devicebean.getName());
		this.setDevicemanagerphone(devicebean.getPhone());
		
		this.setDevicethumn(new DeviceThumnPane(this.getDeviceico(), this.getDeviceid()));
	}

}
