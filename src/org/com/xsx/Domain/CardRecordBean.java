package org.com.xsx.Domain;

public class CardRecordBean {
	private Integer id;
	private String item;					//��Ŀ
	private Integer num;					//��Ŀ�����Ϊ��������Ϊ��
	private java.sql.Timestamp dotime;		//�����ʱ��
	private String admin_a;					//��ǰ�����˵ĸ��˺ţ�һ��һ���ط��������豸ֻ��һ������Ա�˺ţ�
	private String handler;					//������
	private String name;					//����������
	private String deviceid;				//����ʱʹ�ã������ĸ��豸��ȡ
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public java.sql.Timestamp getDotime() {
		return dotime;
	}
	public void setDotime(java.sql.Timestamp dotime) {
		this.dotime = dotime;
	}
	public String getAdmin_a() {
		return admin_a;
	}
	public void setAdmin_a(String admin_a) {
		this.admin_a = admin_a;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
}
