package org.com.xsx.UI.MainScene.Report.ReportListPage;

import org.com.xsx.Domain.CardBean;
import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.PersonBean;
import org.com.xsx.Domain.SampleBean;
import org.com.xsx.Domain.TestDataBean;


public class ReportListTableItem {

	private Integer index;
	private String testitem;
	private String testdate;
	private Float testresult;
	private String tester;
	private String deviceid;
	private String simpleid;
	private String managername;
	private String reportresult;
	
	/*
	 * 1 -- 测试数据
	 * 2 -- 试剂卡数据
	 * 3 -- 设备数据
	 * 4 -- 操作人数据
	 * 5 -- 样品数据
	 * 6 -- 审核人数据
	 */
	private Object[] reportdata;

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
		
		if(reportdata[0] != null) {
			this.testdate = ((TestDataBean)reportdata[0]).getTestd()+" "+((TestDataBean)reportdata[0]).getTestt();
			this.testresult = ((TestDataBean)reportdata[0]).getA_v();
			
			if((((TestDataBean)reportdata[0]).getR_re() == null) || (((TestDataBean)reportdata[0]).getR_re().length() == 0))
				this.reportresult = "未审核";
			else
				this.reportresult = ((TestDataBean)reportdata[0]).getR_re();
		}
		else {
			this.testdate = null;
			this.testresult = null;
			this.reportresult = null;
		}
		
		if(reportdata[1] != null)
			this.testitem = ((CardBean)reportdata[1]).getItem();
		else
			this.testitem = null;
		
		if(reportdata[2] != null)
			this.deviceid = ((DeviceBean)reportdata[2]).getId();
		else
			this.deviceid = null;
		
		if(reportdata[3] != null)
			this.tester = ((PersonBean)reportdata[3]).getPname();
		else
			this.tester = null;
		
		if(reportdata[4] != null)
			this.simpleid = ((SampleBean)reportdata[4]).getS_id();
		else
			this.simpleid = null;
		
		if(reportdata[5] != null)
			this.simpleid = ((SampleBean)reportdata[5]).getS_id();
		else
			this.simpleid = null;
	}

}
