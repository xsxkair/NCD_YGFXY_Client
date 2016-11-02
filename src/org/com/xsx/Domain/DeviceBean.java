package org.com.xsx.Domain;

public class DeviceBean {
	private String id;				//设备id
	private String daddr;			//设备使用地址
	private Long dltime;			//设备上次连接时间
	private Boolean disok;			//是否需要维修
	private Integer p_id;			//责任人
	private String p_list;			//操作人列表（json）
	private Boolean p_listnew;		//服务器端是否更新了设备操作人
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDaddr() {
		return daddr;
	}
	public void setDaddr(String daddr) {
		this.daddr = daddr;
	}
	public Long getDltime() {
		return dltime;
	}
	public void setDltime(Long dltime) {
		this.dltime = dltime;
	}
	public Boolean getDisok() {
		return disok;
	}
	public void setDisok(Boolean disok) {
		this.disok = disok;
	}
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public String getP_list() {
		return p_list;
	}
	public void setP_list(String p_list) {
		this.p_list = p_list;
	}
	public Boolean getP_listnew() {
		return p_listnew;
	}
	public void setP_listnew(Boolean p_listnew) {
		this.p_listnew = p_listnew;
	}
	
}
