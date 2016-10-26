package org.com.xsx.Domain;

public class TestDataBean {
	
	private String cid;						//检测卡id	-- 对应cardbean
	private String did;						//设备id		-- 对应devicebean
	private Integer tester_id;				//测试人id	-- 对应personbean
	private Integer sample_id;				//被测标本id -- 对应samplebean
	
	private java.sql.Date testd;			//测试日期
	private java.sql.Time testt;			//测试时间
	private Float e_t;						//环境温度
	private Float o_t;						//检测卡温度
	private Integer outt;					//超时时间
	private Integer c_l;					//c线位置
	private Integer t_l;					//T线位置
	private Integer b_l;					//基线位置
	private String serie;					//测试曲线
	private Float t_c_v;					//峰高比
	private Float a_p;						//校准参数
	private Float b_v;						//原始结果
	private Float a_v;						//校准后结果
	private String r_re;					//报告结果
	private String r_desc;					//报告说明
	
	private java.sql.Timestamp	r_uptime;		//报告上传时间
	private java.sql.Timestamp	r_handletime;	//报告处理时间
	private String Manageraccount;				//审核人账号  -- 对应managerbean
	
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
	public Integer getTester_id() {
		return tester_id;
	}
	public void setTester_id(Integer tester_id) {
		this.tester_id = tester_id;
	}
	public Integer getSample_id() {
		return sample_id;
	}
	public void setSample_id(Integer sample_id) {
		this.sample_id = sample_id;
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
	public Float getA_p() {
		return a_p;
	}
	public void setA_p(Float a_p) {
		this.a_p = a_p;
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
	public java.sql.Timestamp getR_uptime() {
		return r_uptime;
	}
	public void setR_uptime(java.sql.Timestamp r_uptime) {
		this.r_uptime = r_uptime;
	}
	public java.sql.Timestamp getR_handletime() {
		return r_handletime;
	}
	public void setR_handletime(java.sql.Timestamp r_handletime) {
		this.r_handletime = r_handletime;
	}
	public String getManageraccount() {
		return Manageraccount;
	}
	public void setManageraccount(String manageraccount) {
		Manageraccount = manageraccount;
	}
	

}
