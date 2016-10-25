package org.com.xsx.Service;

import java.io.IOException;

import org.com.xsx.Dao.SoftwareDao;
import org.com.xsx.Data.SoftwareInfo;
import org.com.xsx.Tools.HibernateSessionBean;
import org.com.xsx.UI.LoginScene.LoginScene;
import org.com.xsx.UI.MainScene.ContainerPane;
import org.com.xsx.UI.MainScene.DevicePage.DevicePage;
import org.com.xsx.UI.MainScene.Report.ReportDetailPage.ReportDetailPage;
import org.com.xsx.UI.MainScene.Report.ReportListPage.ReportListPage;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class SystemInitService extends Service<Boolean>{
	
	private static SystemInitService GB_SystemInitService = null;
	
	private SystemInitService() {
		// TODO Auto-generated constructor stub
	}
	
	public static SystemInitService GetInstance() {
		if(GB_SystemInitService == null)
			GB_SystemInitService = new SystemInitService();
		
		return GB_SystemInitService;
	}
	
	@Override
	protected Task<Boolean> createTask() {
		// TODO Auto-generated method stub
		return new systeminittask();
	}
	
	class systeminittask extends Task<Boolean>{

		@Override
		protected Boolean call() throws Exception {
			// TODO Auto-generated method stub
			return systeminit_fun();
		}
		
		private Boolean systeminit_fun() throws InterruptedException{
        	
			Boolean temp;
			int i=0;
			
			updateMessage("�������ӷ����������Ժ򡣡���"); 
    		updateProgress(1, 100);
    		for (; i < 15; i++) {
    			updateProgress(i, 100);
    			Thread.sleep(300); 
			}
    		
    		try {
    			HibernateSessionBean.GetInstance().Hibernate_Init();
			} catch (Exception e) {
				// TODO: handle exception
				updateMessage("����������ʧ�ܣ��������磡");
	    		Thread.sleep(1000); 
	    		
	    		return false;
			}
    		
    		
    		updateMessage("�������С�����");
    		for (; i < 30; i++) {
    			updateProgress(i, 100);
    			Thread.sleep(100); 
			}
    		temp = SoftwareDao.CheckUpdate();
    		if(null == temp)
    			updateMessage("��ȡʧ��");
    		else if (temp) {
    			updateMessage("�����������³���");
    			Thread.sleep(1000); 
    			try {
					Runtime.getRuntime().exec(".\\jre\\bin\\javaw -jar UPDATE.jar "+ SoftwareInfo.version);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			System.exit(0);
			}
    		else {
    			updateMessage("�������°�");
			}
    		Thread.sleep(1000); 
    		
    		//����UI
    		//��ʼ����½����
    		updateMessage("1");
    		LoginScene.GetInstance().UI_Init();
    		updateProgress(32, 100);
    		
    		updateMessage("2");
    		ContainerPane.GetInstance().UI_Init();
    		updateProgress(34, 100);
    		
    		updateMessage("3");
    		ReportListPage.GetInstance().UI_Init();
    		updateProgress(36, 100);
    		
    		updateMessage("4");
    		ReportDetailPage.GetInstance().UI_Init();
    		updateProgress(38, 100);
    		
    		updateMessage("5");
    		DevicePage.GetInstance().UI_Init();
    		updateProgress(40, 100);
    		
    		updateProgress(100, 100);
    		Thread.sleep(1000); 

			return true;
		}
	}
}
