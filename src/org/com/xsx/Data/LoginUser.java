package org.com.xsx.Data;

import java.util.List;

import org.com.xsx.Domain.ReportManagerBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

public class LoginUser {
	
	private static LoginUser GB_LoginUser = null;
	
	private ReportManagerBean GB_ReportManagerBean;
	
	private List<String> my_deviceids;
	
	private LoginUser(){
		
	}
	
	public static LoginUser GetInstance(){
		if(GB_LoginUser == null)
			GB_LoginUser = new LoginUser();
		
		return GB_LoginUser;
	}

	public ReportManagerBean getGB_ReportManagerBean() {
		return GB_ReportManagerBean;
	}

	public void setGB_ReportManagerBean(ReportManagerBean gB_ReportManagerBean) {
		JSONArray jsonArray;

		jsonArray = (JSONArray) JSONSerializer.toJSON(gB_ReportManagerBean.getDevicelist());
		my_deviceids = (List<String>) JSONSerializer.toJava(jsonArray);

		GB_ReportManagerBean = gB_ReportManagerBean;
	}

	public List<String> getMy_deviceids() {
		return my_deviceids;
	}

}
