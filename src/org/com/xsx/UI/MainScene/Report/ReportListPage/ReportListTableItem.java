package org.com.xsx.UI.MainScene.Report.ReportListPage;

import org.com.xsx.Domain.CardBean;
import org.com.xsx.Domain.TestDataBean;


public class ReportListTableItem {

	private Integer index;
	private String testitem;
	private java.sql.Timestamp testdate;
	private String testresult;
	private String tester;
	private String deviceid;
	private String simpleid;
	private String managername;
	private String reportresult;
	
	private TestDataBean reportdata = null;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getTestitem() {
		return testitem;
	}

	public void setTestitem(String testitem) {
		this.testitem = testitem;
	}


	public java.sql.Timestamp getTestdate() {
		return testdate;
	}

	public void setTestdate(java.sql.Timestamp testdate) {
		this.testdate = testdate;
	}

	public String getTestresult() {
		return testresult;
	}

	public void setTestresult(String testresult) {
		this.testresult = testresult;
	}

	public String getTester() {
		return tester;
	}

	public void setTester(String tester) {
		this.tester = tester;
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

	public String getManagername() {
		return managername;
	}

	public void setManagername(String managername) {
		this.managername = managername;
	}

	public String getReportresult() {
		return reportresult;
	}

	public void setReportresult(String reportresult) {
		this.reportresult = reportresult;
	}

	public TestDataBean getReportdata() {
		return reportdata;
	}

	public void setReportdata(TestDataBean reportdata) {
		this.reportdata = reportdata;
		
		this.testitem = null;
		this.testdate = null;
		this.testresult = null;
		this.tester = null;
		this.deviceid = null;
		this.simpleid = null;
		this.reportresult = null;
		
		if(this.reportdata != null){
			this.testitem = reportdata.getCitem();
			this.testresult = reportdata.getA_v() + " "+ reportdata.getCdw();
			this.testdate = reportdata.getTesttime();
			this.tester = reportdata.getT_name();
			this.deviceid = reportdata.getDid();
			this.simpleid = reportdata.getSampleid();
				
			if(reportdata.getResult() == null)
				this.reportresult = "Œ¥…Û∫À";
			else
				this.reportresult = reportdata.getResult();
		}
	}

}
