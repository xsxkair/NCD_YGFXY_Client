package org.com.xsx.UI.MainScene.DevicePage;

import java.util.List;

import org.com.xsx.Domain.DeviceBean;
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
	
	private DeviceBean devicebean;					//设备信息
	private PersonBean deviceperson;				//设备责任人信息
	private List<PersonBean> devicepersonlist;		//设备操作人列表
	
	public DeviceTableItem(Object[] deviceinfo){
		this.setDevicebean((DeviceBean) deviceinfo[0]);
		this.setDeviceperson((PersonBean) deviceinfo[1]);
		this.setDevicepersonlist((List<PersonBean>) deviceinfo[2]);
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
			this.setDevicestatus("离线");
		}
		else{
			this.setDeviceico(new Image(this.getClass().getResourceAsStream("/RES/deviceico_on.png")));
			if(devicebean.getDisok())
				this.setDevicestatus("正常");
			else
				this.setDevicestatus("异常");
		}
		
		this.setDeviceid(devicebean.getId());
		this.setDeviceaddr(devicebean.getDaddr());
		
		this.setDevicethumn(new DeviceThumnPane(this.getDeviceico(), this.getDeviceid()));
	}

	public PersonBean getDeviceperson() {
		return deviceperson;
	}

	public void setDeviceperson(PersonBean deviceperson) {
		this.deviceperson = deviceperson;
		
		if(deviceperson != null){
			this.setDevicemanagername(deviceperson.getPname());
			this.setDevicemanagerphone(deviceperson.getPphone());
		}
		else{
			this.setDevicemanagername(null);
			this.setDevicemanagerphone(null);
		}
	}

	public List<PersonBean> getDevicepersonlist() {
		return devicepersonlist;
	}

	public void setDevicepersonlist(List<PersonBean> devicepersonlist) {
		this.devicepersonlist = devicepersonlist;
	}
	
}
