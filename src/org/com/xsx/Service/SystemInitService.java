package org.com.xsx.Service;

import org.com.xsx.Dao.SoftwareDao;
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
        	
			Boolean temp;
			int i=0;
			
			updateMessage("正在连接服务器，请稍候。。。"); 
    		updateProgress(1, 100);
    		for (; i < 15; i++) {
    			updateProgress(i, 100);
    			Thread.sleep(300); 
			}
    		
    		try {
    			HibernateSessionBean.GetInstance().Hibernate_Init();
			} catch (Exception e) {
				// TODO: handle exception
				updateMessage("服务器连接失败，请检查网络！");
	    		Thread.sleep(1000); 
	    		
	    		return false;
			}
    		
    		
    		updateMessage("检查更新中。。。");
    		for (; i < 30; i++) {
    			updateProgress(i, 100);
    			Thread.sleep(300); 
			}
    		temp = SoftwareDao.CheckUpdate();
    		if(null == temp)
    			updateMessage("读取失败");
    		else if (temp) {
    			updateMessage("有更新可用");
    			System.out.println("运行更新程序");
    			System.exit(0);
			}
    		else {
    			updateMessage("已是最新版");
			}
    		Thread.sleep(1000); 
    		
    		updateProgress(100, 100);
    		Thread.sleep(1000); 

			return true;
		}
	}
}
