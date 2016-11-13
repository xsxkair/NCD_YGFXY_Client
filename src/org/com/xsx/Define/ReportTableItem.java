package org.com.xsx.Define;

import org.com.xsx.Domain.TestDataBean;

public class ReportTableItem {
	private Integer index;
	private String testitem;
	private java.sql.Timestamp testdate;
	private String testresult;
	private String tester;
	private String deviceid;
	private String simpleid;
	private String managername;
	private String reportresult;
	
	private TestDataBean testdata;

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

	public TestDataBean getTestdata() {
		return testdata;
	}

	public void setTestdata(TestDataBean testdata) {
		this.testdata = testdata;
		
		this.testitem = null;
		this.testdate = null;
		this.testresult = null;
		this.tester = null;
		this.deviceid = null;
		this.simpleid = null;
		this.reportresult = null;
		
		if(this.testdata != null){
			this.testitem = testdata.getCitem();
			this.testresult = testdata.getA_v() + " "+ testdata.getCdw();
			this.testdate = testdata.getTesttime();
			this.tester = testdata.getT_name();
			this.deviceid = testdata.getDid();
			this.simpleid = testdata.getSampleid();
				
			if(testdata.getResult() == null)
				this.reportresult = "Î´ÉóºË";
			else
				this.reportresult = testdata.getResult();
		}
	}
}
