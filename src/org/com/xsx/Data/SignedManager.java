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
	
	private ObjectProperty<ManagerBean> GB_SignedManager = new SimpleObjectProperty<ManagerBean>(null);
	
	private List<String> my_deviceids = null;
	
	private SignedManager(){
		
	}
	
	public static SignedManager GetInstance() {
		if(S_SignedManager == null)
			S_SignedManager = new SignedManager();
		
		return S_SignedManager;
	}

	public ObjectProperty<ManagerBean> getGB_SignedManager() {
		return GB_SignedManager;
	}
	
	public void setGB_SignedManager(ManagerBean gB_SignedManager) {
		
		JSONArray jsonArray;
		
		if(gB_SignedManager != null){
			jsonArray = (JSONArray) JSONSerializer.toJSON(gB_SignedManager.getDevicelist());
			my_deviceids = (List<String>) JSONSerializer.toJava(jsonArray);
			
			GB_SignedManager.set(gB_SignedManager);
		}
		else{
			my_deviceids = null;
			GB_SignedManager.set(null);
		}
	}
	
	public ManagerBean GetSignedManagerInfo(){
		return GB_SignedManager.get();
	}
	
	public List<String> GetManagerDeviceIdList() {
		return my_deviceids;
	}
}
