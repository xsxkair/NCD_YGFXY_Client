package test;

import java.util.Date;

import org.com.xsx.Domain.CardBean;
import org.com.xsx.Domain.DeviceBean;
import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Domain.PersonBean;
import org.com.xsx.Domain.SampleBean;
import org.com.xsx.Domain.TestDataBean;
import org.com.xsx.Tools.HibernateSessionBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class test {
	
	@Test
	public void test1() {
		HibernateSessionBean.GetInstance().Hibernate_Init();
		
		Session session = HibernateSessionBean.GetInstance().getSession();
		Transaction tx = session.beginTransaction();
		
		Integer startindex = 0;
		
		String[] items = {"BNP", "MYO", "CRP", "PCT", "CK-MB", "CTNI", "DIR"};
		String[] devices = {"NCD-1", "NCD-2", "NCD-3", "NCD-4", "NCD-5", "NCD-6", "NCD-7", "NCD-8", "NCD-9", "NCD-10", };
		
		for (int j=0; j<10; j++) {
			DeviceBean device = new DeviceBean();
			device.setId(devices[j]);
			session.save(device);
			
			ManagerBean manager = new ManagerBean();
			manager.setAccount("Manager-"+j);
			manager.setPassword("Manager-"+j);
			session.save(manager);
		}
		session.flush();
		session.clear();
		
		for(int i=startindex; i<100000+startindex; i++){
			CardBean card = new CardBean();
			card.setId("card-"+i);
			card.setItem(items[(int) (Math.random()*6)]);
			session.save(card);
			
			PersonBean tester = new PersonBean();
			tester.setPname(devices[(int) (Math.random()*9)]+"²Ù×÷ÈË"+(int) (Math.random()*9));
			session.save(tester);
			
			SampleBean sample = new SampleBean();
			sample.setS_id("sample-"+(int) (Math.random()*500000));
			session.save(sample);
			
			TestDataBean testData = new TestDataBean();
			testData.setCid(card.getId());
			testData.setDid(devices[(int) (Math.random()*9)]);
			testData.setSample_id(sample.getId());
			testData.setTester_id(tester.getId());
			testData.setManageraccount("Manager-"+(int) (Math.random()*9));
			testData.setSerie("[524,524,524,523,524,523,523,523,523,523,523,523,523,523,522,522,522,522,522,522,522,522,522,522,522,522,522,522,522,522,522,522,522,522,522,522,521,521,521,521,521,521,521,521,521,520,520,520,520,520,520,520,519,519,520,519,519,519,519,519,519,518,518,518,518,518,518,518,519,519,519,519,519,520,520,521,521,521,522,522,522,522,523,523,523,522,523,523,522,522,522,521,521,520,520,520,519,518,518,517]}");
			session.save(testData);
			
			if(i % 20 == 0)
			{
				session.flush();
				session.clear();
				System.out.println(i+" "+new Date());
			}
				
		}
		
		tx.commit();
		HibernateSessionBean.GetInstance().closeSession();
	}
}
