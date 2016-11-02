package org.com.xsx.Domain;

public class CardBean {
	private String id;						//检测卡id	
	private String item;					//测试项目
	private Float n_v;					//正常值
	private Float l_v;					//最低值
	private Float h_v;					//最高值
	private String dw;					//单位
	private Integer t_l;					//t线位置
	private Integer bq_n;					//曲线数目
	private Float fend;					//分段峰高比
	private Float bq1_a;					//曲线1--a
	private Float bq1_b;					//曲线1--b
	private Float bq1_c;					//曲线1--c
	private Float bq2_a;					//曲线2--a
	private Float bq2_b;					//曲线2--b
	private Float bq2_c;					//曲线2--c
	private Integer waitt;				//反应时间
	private Integer c_l;					//c线位置
	private java.sql.Date outt;			//过期时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Float getN_v() {
		return n_v;
	}
	public void setN_v(Float n_v) {
		this.n_v = n_v;
	}
	public Float getL_v() {
		return l_v;
	}
	public void setL_v(Float l_v) {
		this.l_v = l_v;
	}
	public Float getH_v() {
		return h_v;
	}
	public void setH_v(Float h_v) {
		this.h_v = h_v;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public Integer getT_l() {
		return t_l;
	}
	public void setT_l(Integer t_l) {
		this.t_l = t_l;
	}
	public Integer getBq_n() {
		return bq_n;
	}
	public void setBq_n(Integer bq_n) {
		this.bq_n = bq_n;
	}
	public Float getFend() {
		return fend;
	}
	public void setFend(Float fend) {
		this.fend = fend;
	}
	public Float getBq1_a() {
		return bq1_a;
	}
	public void setBq1_a(Float bq1_a) {
		this.bq1_a = bq1_a;
	}
	public Float getBq1_b() {
		return bq1_b;
	}
	public void setBq1_b(Float bq1_b) {
		this.bq1_b = bq1_b;
	}
	public Float getBq1_c() {
		return bq1_c;
	}
	public void setBq1_c(Float bq1_c) {
		this.bq1_c = bq1_c;
	}
	public Float getBq2_a() {
		return bq2_a;
	}
	public void setBq2_a(Float bq2_a) {
		this.bq2_a = bq2_a;
	}
	public Float getBq2_b() {
		return bq2_b;
	}
	public void setBq2_b(Float bq2_b) {
		this.bq2_b = bq2_b;
	}
	public Float getBq2_c() {
		return bq2_c;
	}
	public void setBq2_c(Float bq2_c) {
		this.bq2_c = bq2_c;
	}
	public Integer getWaitt() {
		return waitt;
	}
	public void setWaitt(Integer waitt) {
		this.waitt = waitt;
	}
	public Integer getC_l() {
		return c_l;
	}
	public void setC_l(Integer c_l) {
		this.c_l = c_l;
	}
	public java.sql.Date getOutt() {
		return outt;
	}
	public void setOutt(java.sql.Date outt) {
		this.outt = outt;
	}

}
