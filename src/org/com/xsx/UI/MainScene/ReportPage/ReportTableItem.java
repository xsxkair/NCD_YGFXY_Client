package org.com.xsx.UI.MainScene.ReportPage;

import org.com.xsx.Domain.TestDataBean;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ReportTableItem {

	private BooleanProperty isselected;
	private Integer index;
	private String testitem;
	private String testdate;
	private Float testresult;
	private String tester;
	private String deviceid;
	private String simpleid;
	private String reportresult;
	
	private TestDataBean testdatabean;

	public ReportTableItem(){
		isselected = new SimpleBooleanProperty();
	}
	
	public BooleanProperty getIsselected() {
		return isselected;
	}

	public void setIsselected(Boolean isselected) {
		this.isselected.set(isselected);
	}

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


	public String getTestdate() {
		return testdate;
	}

	public void setTestdate(String testdate) {
		this.testdate = testdate;
	}

	public Float getTestresult() {
		return testresult;
	}

	public void setTestresult(Float testresult) {
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

	public String getReportresult() {
		return reportresult;
	}

	public void setReportresult(String reportresult) {
		this.reportresult = reportresult;
	}

	public TestDataBean getTestdatabean() {
		return testdatabean;
	}

	public void setTestdatabean(TestDataBean testdatabean) {
		this.testdatabean = testdatabean;
		
		this.testitem = this.testdatabean.getC_item();
		this.testdate = new String(this.testdatabean.getTestd()+" "+this.getTestdatabean().getTestt()) ;
		this.testresult = this.testdatabean.getA_v();
		this.tester = this.testdatabean.getT_name();
		this.deviceid = this.testdatabean.getDid();
		this.simpleid = this.testdatabean.getSid();
		
		if((this.testdatabean.getR_re() == null) || (this.testdatabean.getR_re().length() == 0))
			this.reportresult = "Î´ÉóºË";
		else
			this.reportresult = this.testdatabean.getR_re();
	}
}
