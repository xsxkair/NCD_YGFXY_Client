package org.com.xsx.Domain;

public class DeviceBean {
	private String id;				//设备id
	private String daddr;			//设备使用地址
	private Long dltime;			//设备上次连接时间
	private Boolean disok;			//是否需要维修
	private String name;				//姓名
	private String age;					//年龄
	private String sex;					//性别
	private String phone;				//联系方式
	private String job;					//职务
	private String dsc;					//备注
	private Boolean p_listnew;		//服务器端是否更新了设备操作人
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDsc() {
		return dsc;
	}
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	public Boolean getP_listnew() {
		return p_listnew;
	}
	public void setP_listnew(Boolean p_listnew) {
		this.p_listnew = p_listnew;
	}

	
}
