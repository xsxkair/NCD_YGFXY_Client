package org.com.xsx.Domain;

public class TestDataBean {
	
	private String cid;			//检测卡id
	private String did;		//设备id
	
	private String c_item;					//测试项目
	private Float c_n_v;					//正常值
	private Float c_l_v;					//最低值
	private Float c_h_v;					//最高值
	private String c_dw;					//单位
	private Integer c_t_l;					//t线位置
	private Integer c_bq_n;				//曲线数目
	private Float c_fend;					//分段峰高比
	private Float c_bq1_a;					//曲线1--a
	private Float c_bq1_b;					//曲线1--b
	private Float c_bq1_c;					//曲线1--c
	private Float c_bq2_a;					//曲线2--a
	private Float c_bq2_b;					//曲线2--b
	private Float c_bq2_c;					//曲线2--c
	private Integer c_waitt;				//反应时间
	private Integer c_c_l;					//c线位置
	private java.sql.Date c_outt;					//过期时间
	
	private java.sql.Date testd;			//测试日期
	private java.sql.Time testt;			//测试时间
	private Float e_t;				//环境温度
	private Float o_t;				//检测卡温度
	private Integer outt;		//超时时间
	private Integer c_l;			//c线位置
	private Integer t_l;			//T线位置
	private Integer b_l;			//基线位置
	private String serie;			//测试曲线
	private Float t_c_v;		//峰高比
	private Float b_v;			//原始结果
	private Float a_v;			//校准后结果
	private String sid;		//样品id
	private String r_re;	//报告结果
	private String r_desc;		//报告说明
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getC_item() {
		return c_item;
	}
	public void setC_item(String c_item) {
		this.c_item = c_item;
	}
	public Float getC_n_v() {
		return c_n_v;
	}
	public void setC_n_v(Float c_n_v) {
		this.c_n_v = c_n_v;
	}
	public Float getC_l_v() {
		return c_l_v;
	}
	public void setC_l_v(Float c_l_v) {
		this.c_l_v = c_l_v;
	}
	public Float getC_h_v() {
		return c_h_v;
	}
	public void setC_h_v(Float c_h_v) {
		this.c_h_v = c_h_v;
	}
	public String getC_dw() {
		return c_dw;
	}
	public void setC_dw(String c_dw) {
		this.c_dw = c_dw;
	}
	public Integer getC_t_l() {
		return c_t_l;
	}
	public void setC_t_l(Integer c_t_l) {
		this.c_t_l = c_t_l;
	}
	public Integer getC_bq_n() {
		return c_bq_n;
	}
	public void setC_bq_n(Integer c_bq_n) {
		this.c_bq_n = c_bq_n;
	}
	public Float getC_fend() {
		return c_fend;
	}
	public void setC_fend(Float c_fend) {
		this.c_fend = c_fend;
	}
	public Float getC_bq1_a() {
		return c_bq1_a;
	}
	public void setC_bq1_a(Float c_bq1_a) {
		this.c_bq1_a = c_bq1_a;
	}
	public Float getC_bq1_b() {
		return c_bq1_b;
	}
	public void setC_bq1_b(Float c_bq1_b) {
		this.c_bq1_b = c_bq1_b;
	}
	public Float getC_bq1_c() {
		return c_bq1_c;
	}
	public void setC_bq1_c(Float c_bq1_c) {
		this.c_bq1_c = c_bq1_c;
	}
	public Float getC_bq2_a() {
		return c_bq2_a;
	}
	public void setC_bq2_a(Float c_bq2_a) {
		this.c_bq2_a = c_bq2_a;
	}
	public Float getC_bq2_b() {
		return c_bq2_b;
	}
	public void setC_bq2_b(Float c_bq2_b) {
		this.c_bq2_b = c_bq2_b;
	}
	public Float getC_bq2_c() {
		return c_bq2_c;
	}
	public void setC_bq2_c(Float c_bq2_c) {
		this.c_bq2_c = c_bq2_c;
	}
	public Integer getC_waitt() {
		return c_waitt;
	}
	public void setC_waitt(Integer c_waitt) {
		this.c_waitt = c_waitt;
	}
	public Integer getC_c_l() {
		return c_c_l;
	}
	public void setC_c_l(Integer c_c_l) {
		this.c_c_l = c_c_l;
	}

	public java.sql.Date getC_outt() {
		return c_outt;
	}
	public void setC_outt(java.sql.Date c_outt) {
		this.c_outt = c_outt;
	}
	public java.sql.Date getTestd() {
		return testd;
	}
	public void setTestd(java.sql.Date testd) {
		this.testd = testd;
	}
	public java.sql.Time getTestt() {
		return testt;
	}
	public void setTestt(java.sql.Time testt) {
		this.testt = testt;
	}
	public Float getE_t() {
		return e_t;
	}
	public void setE_t(Float e_t) {
		this.e_t = e_t;
	}
	public Float getO_t() {
		return o_t;
	}
	public void setO_t(Float o_t) {
		this.o_t = o_t;
	}
	public Integer getOutt() {
		return outt;
	}
	public void setOutt(Integer outt) {
		this.outt = outt;
	}
	public Integer getC_l() {
		return c_l;
	}
	public void setC_l(Integer c_l) {
		this.c_l = c_l;
	}
	public Integer getT_l() {
		return t_l;
	}
	public void setT_l(Integer t_l) {
		this.t_l = t_l;
	}
	public Integer getB_l() {
		return b_l;
	}
	public void setB_l(Integer b_l) {
		this.b_l = b_l;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Float getT_c_v() {
		return t_c_v;
	}
	public void setT_c_v(Float t_c_v) {
		this.t_c_v = t_c_v;
	}
	public Float getB_v() {
		return b_v;
	}
	public void setB_v(Float b_v) {
		this.b_v = b_v;
	}
	public Float getA_v() {
		return a_v;
	}
	public void setA_v(Float a_v) {
		this.a_v = a_v;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getR_re() {
		return r_re;
	}
	public void setR_re(String r_re) {
		this.r_re = r_re;
	}
	public String getR_desc() {
		return r_desc;
	}
	public void setR_desc(String r_desc) {
		this.r_desc = r_desc;
	}
	

}
