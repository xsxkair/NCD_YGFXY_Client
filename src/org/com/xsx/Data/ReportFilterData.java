package org.com.xsx.Data;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

public class ReportFilterData {
	private String testitem = null;
	private java.sql.Date testtime = null;
	
	private String testername = null;
	private String deviceid = null;
	private String simpleid = null;
	private String reportresult = null;
	
	private Boolean isgettotal = true;
	private int datatotalnum = 0;												//数据总数
	private int pageindex;													//当前页数
	private int pagesize = 50;
	
	
	
	private static ReportFilterData S_ReportFilterData = null;
	
	private ReportFilterData(){
		
	}
	
	public static ReportFilterData GetInstance(){
		if(S_ReportFilterData == null)
			S_ReportFilterData = new ReportFilterData();
		
		return S_ReportFilterData;
	}

	public int getDatatotalnum() {
		return datatotalnum;
	}

	public void setDatatotalnum(int datatotalnum) {
		this.datatotalnum = datatotalnum;
	}

	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	public int getPagesize() {
		return pagesize;
	}
	
	public int getFirstindex(){
		return pagesize*pageindex;
	}

	public String getTestitem() {
		return testitem;
	}

	public void setTestitem(String testitem) {
		this.testitem = testitem;
	}

	public java.sql.Date getTesttime() {
		return testtime;
	}

	public void setTesttime(java.sql.Date testtime) {
		this.testtime = testtime;
	}

	public String getTestername() {
		return testername;
	}

	public void setTestername(String testername) {
		this.testername = testername;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getSimpleid() {
		return simpleid;
	}

	public void setSimpleid(String simpleid) {
		this.simpleid = simpleid;
	}

	public String getReportresult() {
		return reportresult;
	}

	public void setReportresult(String reportresult) {
		this.reportresult = reportresult;
	}

	public Boolean getIsgettotal() {
		return isgettotal;
	}

	public void setIsgettotal(Boolean isgettotal) {
		this.isgettotal = isgettotal;
	}

}
