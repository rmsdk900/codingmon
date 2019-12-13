package member.vo;

import java.util.Date;

public class MemberVO {
	private int cm_num;
	private String cm_email;
	private String cm_pw;
	private String cm_name;
	private String cm_phone;
	private String cm_addr;
	private Date cm_regdate;
	private String cm_join;
	private int cm_salt;
	
	// 생성자
	public MemberVO() {}
	
	public MemberVO(int cm_num, String cm_email, String cm_pw, String cm_name, String cm_phone, String cm_addr,
			Date cm_regdate, String cm_join, int cm_salt) {
		this.cm_num = cm_num;
		this.cm_email = cm_email;
		this.cm_pw = cm_pw;
		this.cm_name = cm_name;
		this.cm_phone = cm_phone;
		this.cm_addr = cm_addr;
		this.cm_regdate = cm_regdate;
		this.cm_join = cm_join;
		this.cm_salt = cm_salt;
	}


	// getter / setter / toString
	public int getCm_num() {
		return cm_num;
	}
	
	public void setCm_num(int cm_num) {
		this.cm_num = cm_num;
	}
	public String getCm_email() {
		return cm_email;
	}
	public void setCm_email(String cm_email) {
		this.cm_email = cm_email;
	}
	public String getCm_pw() {
		return cm_pw;
	}
	public void setCm_pw(String cm_pw) {
		this.cm_pw = cm_pw;
	}
	public String getCm_name() {
		return cm_name;
	}
	public void setCm_name(String cm_name) {
		this.cm_name = cm_name;
	}
	public String getCm_phone() {
		return cm_phone;
	}
	public void setCm_phone(String cm_phone) {
		this.cm_phone = cm_phone;
	}
	public String getCm_addr() {
		return cm_addr;
	}
	public void setCm_addr(String cm_addr) {
		this.cm_addr = cm_addr;
	}
	public Date getCm_regdate() {
		return cm_regdate;
	}
	public void setCm_regdate(Date cm_regdate) {
		this.cm_regdate = cm_regdate;
	}
	public String getCm_join() {
		return cm_join;
	}
	public void setCm_join(String cm_join) {
		this.cm_join = cm_join;
	}
	public int getCm_salt() {
		return cm_salt;
	}
	public void setCm_salt(int cm_salt) {
		this.cm_salt = cm_salt;
	}
	
	@Override
	public String toString() {
		return "MemberVO [cm_num=" + cm_num + ", cm_email=" + cm_email + ", cm_pw=" + cm_pw + ", cm_name=" + cm_name
				+ ", cm_phone=" + cm_phone + ", cm_addr=" + cm_addr + ", cm_regdate=" + cm_regdate + ", cm_join="
				+ cm_join + ", cm_salt=" + cm_salt + "]";
	}
	
	
	
}
