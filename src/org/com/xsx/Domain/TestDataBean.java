package org.com.xsx.Domain;

public class TestDataBean {
	
	private String cid;						//检测卡id	-- 对应cardbean
	private String item;					//测试项目
	private String did;						//设备id		-- 对应devicebean
	private Integer t_id;					//测试人id	-- 对应personbean
	private String t_name;					//测试人姓名
	private Integer s_id;					//被测人索引 -- 对应person id，如果未从客户处获取信息，则为null
	private String sampleid;				//样品id -- 测试时输入
	private String m_account;				//审核人账号
	private String m_name;					//审核人姓名
	private java.sql.Timestamp	uptime;		//报告上传时间
	private java.sql.Timestamp	handletime;	//报告处理时间
	
	private java.sql.Timestamp testtime;	//测试时间
	private Float e_t;						//环境温度
	private Float o_t;						//检测卡温度
	private Integer outt;					//超时时间
	private Integer c_l;					//c线位置
	private Integer t_l;					//T线位置
	private Integer b_l;					//基线位置
	private String serie_a;					//测试曲线1
	private String serie_b;					//测试曲线2
	private String serie_c;					//测试曲线3
	private String serie_d;					//测试曲线4
	private Float t_c_v;					//峰高比
	private Float a_p;						//校准参数
	private Float b_v;						//原始结果
	private Float a_v;						//校准后结果
	private String result;					//报告结果
	private String r_desc;					//报告说明
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public Integer getT_id() {
		return t_id;
	}
	public void setT_id(Integer t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public Integer getS_id() {
		return s_id;
	}
	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}
	public String getSampleid() {
		return sampleid;
	}
	public void setSampleid(String sampleid) {
		this.sampleid = sampleid;
	}
	public String getM_account() {
		return m_account;
	}
	public void setM_account(String m_account) {
		this.m_account = m_account;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public java.sql.Timestamp getUptime() {
		return uptime;
	}
	public void setUptime(java.sql.Timestamp uptime) {
		this.uptime = uptime;
	}
	public java.sql.Timestamp getHandletime() {
		return handletime;
	}
	public void setHandletime(java.sql.Timestamp handletime) {
		this.handletime = handletime;
	}
	public java.sql.Timestamp getTesttime() {
		return testtime;
	}
	public void setTesttime(java.sql.Timestamp testtime) {
		this.testtime = testtime;
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
	public String getSerie_a() {
		return serie_a;
	}
	public void setSerie_a(String serie_a) {
		this.serie_a = serie_a;
	}
	public String getSerie_b() {
		return serie_b;
	}
	public void setSerie_b(String serie_b) {
		this.serie_b = serie_b;
	}
	public String getSerie_c() {
		return serie_c;
	}
	public void setSerie_c(String serie_c) {
		this.serie_c = serie_c;
	}
	public String getSerie_d() {
		return serie_d;
	}
	public void setSerie_d(String serie_d) {
		this.serie_d = serie_d;
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getR_desc() {
		return r_desc;
	}
	public void setR_desc(String r_desc) {
		this.r_desc = r_desc;
	}

}
