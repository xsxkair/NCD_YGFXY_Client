package org.com.xsx.UI.MainScene.DevicePage;

import javafx.scene.image.Image;

public class DeviceTableItem {
	
	private Image deviceico;
	
	private String deviceid;
	
	private String devicemanagername;
	private String devicemanagersex;
	private String devicemanagerage;
	private String devicemanagerjob;
	private String devicemanagerdesc;
	private String devicemanagerphone;
	
	private String deviceaddr;
	
	private String devicestatus;
	
	private DeviceThumnPane devicethumn;

	public DeviceTableItem(String id, String name, String sex, String age, String job, String desc,  String phone, String addr){
		this.deviceid = id;
		this.devicemanagername = name;
		this.devicemanagersex = sex;
		this.devicemanagerage = age;
		this.devicemanagerjob = job;
		this.devicemanagerdesc = desc;
		this.devicemanagerphone = phone;
		this.deviceaddr = addr;
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

	public String getDevicemanagersex() {
		return devicemanagersex;
	}

	public void setDevicemanagersex(String devicemanagersex) {
		this.devicemanagersex = devicemanagersex;
	}

	public String getDevicemanagerage() {
		return devicemanagerage;
	}

	public void setDevicemanagerage(String devicemanagerage) {
		this.devicemanagerage = devicemanagerage;
	}

	public String getDevicemanagerjob() {
		return devicemanagerjob;
	}

	public void setDevicemanagerjob(String devicemanagerjob) {
		this.devicemanagerjob = devicemanagerjob;
	}

	public String getDevicemanagerdesc() {
		return devicemanagerdesc;
	}

	public void setDevicemanagerdesc(String devicemanagerdesc) {
		this.devicemanagerdesc = devicemanagerdesc;
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
	
}
