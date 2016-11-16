package org.com.xsx.Domain;

public class CardRecordBean {
	private Integer id;
	private String item;					//项目
	private Integer num;					//数目，入库为正，出库为负
	private java.sql.Timestamp dotime;		//出入库时间
	private String admin_a;					//当前操作人的父账号（一般一个地方的所有设备只有一个管理员账号）
	private String handler;					//操作人
	private String name;					//出库领料人
	private String deviceid;				//出库时使用，表明哪个设备领取
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public java.sql.Timestamp getDotime() {
		return dotime;
	}
	public void setDotime(java.sql.Timestamp dotime) {
		this.dotime = dotime;
	}
	public String getAdmin_a() {
		return admin_a;
	}
	public void setAdmin_a(String admin_a) {
		this.admin_a = admin_a;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
}
