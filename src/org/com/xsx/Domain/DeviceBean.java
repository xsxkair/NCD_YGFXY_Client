package org.com.xsx.Domain;

public class DeviceBean {
	private String id;				//�豸id
	private String daddr;			//�豸ʹ�õ�ַ
	private Long dltime;			//�豸�ϴ�����ʱ��
	private Boolean disok;			//�Ƿ���Ҫά��
	private Integer p_id;			//������
	private String p_list;			//�������б�json��
	private Boolean p_listnew;		//���������Ƿ�������豸������
	
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
