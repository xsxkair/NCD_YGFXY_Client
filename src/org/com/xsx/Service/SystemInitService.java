package org.com.xsx.Service;

import org.com.xsx.Tools.HibernateSessionBean;

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
        	
			updateMessage("正在连接服务器，请稍候。。。"); 
    		updateProgress(1, 100);
    		
    		try {
    			HibernateSessionBean.GetInstance().Hibernate_Init();
			} catch (Exception e) {
				// TODO: handle exception
				updateMessage("服务器连接失败，请检查网络！");
				updateProgress(100, 100);
	    		Thread.sleep(1000); 
	    		
	    		return false;
			}
    		
    		updateProgress(100, 100);
    		Thread.sleep(1000); 

			return true;
		}
	}
}
