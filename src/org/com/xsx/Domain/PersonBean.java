package org.com.xsx.Domain;

public class PersonBean {
	private Integer id;
	private String pname;				//责任人
	private String page;					//责任人年龄
	private String psex;					//责任人性别
	private String pphone;				//责任人联系方式
	private String pjob;					//责任人职务
	private String pdesc;				//责任人备注
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPsex() {
		return psex;
	}
	public void setPsex(String psex) {
		this.psex = psex;
	}
	public String getPphone() {
		return pphone;
	}
	public void setPphone(String pphone) {
		this.pphone = pphone;
	}
	public String getPjob() {
		return pjob;
	}
	public void setPjob(String pjob) {
		this.pjob = pjob;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
}
