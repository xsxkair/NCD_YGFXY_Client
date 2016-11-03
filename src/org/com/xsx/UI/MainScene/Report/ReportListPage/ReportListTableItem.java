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
	
	private Object[] reportdata = null;

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

	public Object[] getReportdata() {
		return reportdata;
	}

	public void setReportdata(Object[] reportdata) {
		this.reportdata = reportdata;
		
		this.testitem = null;
		this.testdate = null;
		this.testresult = null;
		this.tester = null;
		this.deviceid = null;
		this.simpleid = null;
		this.reportresult = null;
		
		if(this.reportdata != null){

			TestDataBean testDataBean = (TestDataBean) this.reportdata[0];
			CardBean cardBean = (CardBean) this.reportdata[1];
			if(testDataBean != null && cardBean != null){
				this.testitem = testDataBean.getCitem();
				this.testresult = testDataBean.getA_v() + " "+ cardBean.getDw();
				this.testdate = testDataBean.getTesttime();
				this.tester = testDataBean.getT_name();
				this.deviceid = testDataBean.getDid();
				this.simpleid = testDataBean.getSampleid();
				
				if(testDataBean.getResult() == null)
					this.reportresult = "Œ¥…Û∫À";
				else
					this.reportresult = testDataBean.getResult();
			}
			else {
				this.testitem = null;
				this.testdate = null;
				this.testresult = null;
				this.tester = null;
				this.deviceid = null;
				this.simpleid = null;
				this.reportresult = null;
			}
		}
	}

}
