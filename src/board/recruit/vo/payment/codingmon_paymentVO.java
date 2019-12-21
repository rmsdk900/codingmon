package board.recruit.vo.payment;

public class codingmon_paymentVO {
	
	private int cpm_code;			// payment code
	private String cpm_name;		// payment 이름
	
	public codingmon_paymentVO() {}
	
	public codingmon_paymentVO(int cpm_code, String cpm_name) {
		this.cpm_code = cpm_code;
		this.cpm_name = cpm_name;
	}
	
	// 이하 getter setter toString
	public int getCpm_code() {
		return cpm_code;
	}
	public void setCpm_code(int cpm_code) {
		this.cpm_code = cpm_code;
	}
	public String getCpm_name() {
		return cpm_name;
	}
	public void setCpm_name(String cpm_name) {
		this.cpm_name = cpm_name;
	}
	
	@Override
	public String toString() {
		return "CPM_VO [cpm_code=" + cpm_code + ", cpm_name=" + cpm_name + "]";
	}
	
	

}
