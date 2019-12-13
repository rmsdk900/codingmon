package member.vo;

public class PasscodeVO {
	private String cp_email;
	private String cp_code;
	
	
	
	public PasscodeVO() {}
	public PasscodeVO(String cp_email, String cp_code) {
		this.cp_email = cp_email;
		this.cp_code = cp_code;
	}
	
	public String getCp_email() {
		return cp_email;
	}
	public void setCp_email(String cp_email) {
		this.cp_email = cp_email;
	}
	public String getCp_code() {
		return cp_code;
	}
	public void setCp_code(String cp_code) {
		this.cp_code = cp_code;
	}
	@Override
	public String toString() {
		return "PasscodeVO [cp_email=" + cp_email + ", cp_code=" + cp_code + "]";
	}
	
	
	
	
}
