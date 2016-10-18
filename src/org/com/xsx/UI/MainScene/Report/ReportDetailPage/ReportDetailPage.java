package org.com.xsx.UI.MainScene.Report.ReportDetailPage;

import org.com.xsx.Domain.TestDataBean;

public class ReportDetailPage {
	
	private static ReportDetailPage GB_ReportDetailPage = null;
	
	private TestDataBean S_TestDataBean = null;
	
	private ReportDetailPage() {
		
	}
	
	private static ReportDetailPage GetInstance() {
		if(GB_ReportDetailPage == null)
			GB_ReportDetailPage = new ReportDetailPage();
		
		return GB_ReportDetailPage;
	}
	

}
