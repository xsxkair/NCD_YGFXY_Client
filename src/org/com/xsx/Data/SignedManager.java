package org.com.xsx.Data;

import java.util.List;

import org.com.xsx.Domain.ManagerBean;
import org.com.xsx.Domain.PersonBean;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

public class SignedManager {

	private static SignedManager S_SignedManager = null;
	
	private ObjectProperty<Object[]> GB_SignedManager = new SimpleObjectProperty<Object[]>(null);
	
	private List<String> my_deviceids = null;
	
	private SignedManager(){
		
	}
	
	public static SignedManager GetInstance() {
		if(S_SignedManager == null)
			S_SignedManager = new SignedManager();
		
		return S_SignedManager;
	}

	public ObjectProperty<Object[]> getGB_SignedManager() {
		return GB_SignedManager;
	}
	
	public void setGB_SignedManager(Object[] gB_SignedManager) {
		
		JSONArray jsonArray;
		
		if((gB_SignedManager != null) && (gB_SignedManager[0] != null)){
			jsonArray = (JSONArray) JSONSerializer.toJSON(((ManagerBean)gB_SignedManager[0]).getDevicelist());
			my_deviceids = (List<String>) JSONSerializer.toJava(jsonArray);
			
			GB_SignedManager.set(gB_SignedManager);
			System.out.println(my_deviceids);
		}
		else{
			my_deviceids = null;
			GB_SignedManager.set(null);
		}
	}
	
	public ManagerBean GetSignedManagerAccountInfo(){
		if(GB_SignedManager.get() != null)
			return (ManagerBean) GB_SignedManager.get()[0];
		else
			return null;
	}
	
	public PersonBean GetSignedManagerPersonInfo(){
		if(GB_SignedManager.get() != null)
			return (PersonBean) GB_SignedManager.get()[1];
		else
			return null;
	}
	
	public List<String> GetManagerDeviceIdList() {
		return my_deviceids;
	}
}
