package org.com.xsx.Domain;

public class DeviceInfoBean {
	private String id;				//设备id
	private String daddr;				//设备使用地址
	private String dname;				//责任人
	private String dage;					//责任人年龄
	private String dsex;					//责任人性别
	private String dphone;				//责任人联系方式
	private String djob;					//责任人职务
	private String ddesc;				//责任人备注
	private Long dltime;				//设备上次连接时间
	private Boolean disok;					//是否需要维修
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDaddr() {
		return daddr;
	}
	public void setDaddr(String daddr) {
		this.daddr = daddr;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDage() {
		return dage;
	}
	public void setDage(String dage) {
		this.dage = dage;
	}
	public String getDsex() {
		return dsex;
	}
	public void setDsex(String dsex) {
		this.dsex = dsex;
	}
	public String getDphone() {
		return dphone;
	}
	public void setDphone(String dphone) {
		this.dphone = dphone;
	}
	public String getDjob() {
		return djob;
	}
	public void setDjob(String djob) {
		this.djob = djob;
	}
	public String getDdesc() {
		return ddesc;
	}
	public void setDdesc(String ddesc) {
		this.ddesc = ddesc;
	}
	public Long getDltime() {
		return dltime;
	}
	public void setDltime(Long dltime) {
		this.dltime = dltime;
	}
	public Boolean getDisok() {
		return disok;
	}
	public void setDisok(Boolean disok) {
		this.disok = disok;
	}

}
