package org.com.xsx.Domain;

public class SampleBean {
	private Integer id;				//主键
	private String s_id;			//样品id
	private Integer p_id;			//样品人信息--对应person
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
}
