package board.promotion.vo;

import java.util.ArrayList;
import java.util.Date;

import member.vo.job.MemberJob;
import member.vo.region.MemberRegion;
import member.vo.subject.MemberSubject;

public class ResumeVO {
	
	private int cm_num;
	private String cm_email;
	private String cm_name;
	private String cm_phone;
	private Date cm_regdate;
	private String cm_join;
	
	private String cmi_private;
	private String cmi_gender;
	private int cmi_age;
	private String cmi_title;
	
	private ArrayList<MemberJob> cij;
	
	private ArrayList<MemberRegion> cmr;

	private ArrayList<MemberSubject> cms;

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

	public String getCmi_private() {
		return cmi_private;
	}

	public void setCmi_private(String cmi_private) {
		this.cmi_private = cmi_private;
	}

	public String getCmi_gender() {
		return cmi_gender;
	}

	public void setCmi_gender(String cmi_gender) {
		this.cmi_gender = cmi_gender;
	}

	public int getCmi_age() {
		return cmi_age;
	}

	public void setCmi_age(int cmi_age) {
		this.cmi_age = cmi_age;
	}

	public String getCmi_title() {
		return cmi_title;
	}

	public void setCmi_title(String cmi_title) {
		this.cmi_title = cmi_title;
	}

	public ArrayList<MemberJob> getCij() {
		return cij;
	}

	public void setCij(ArrayList<MemberJob> cij) {
		this.cij = cij;
	}

	public ArrayList<MemberRegion> getCmr() {
		return cmr;
	}

	public void setCmr(ArrayList<MemberRegion> cmr) {
		this.cmr = cmr;
	}

	public ArrayList<MemberSubject> getCms() {
		return cms;
	}

	public void setCms(ArrayList<MemberSubject> cms) {
		this.cms = cms;
	}

	@Override
	public String toString() {
		return "ResumeVO [cm_num=" + cm_num + ", cm_email=" + cm_email + ", cm_name=" + cm_name + ", cm_phone="
				+ cm_phone + ", cm_regdate=" + cm_regdate + ", cm_join=" + cm_join + ", cmi_private=" + cmi_private
				+ ", cmi_gender=" + cmi_gender + ", cmi_age=" + cmi_age + ", cmi_title=" + cmi_title + ", cij=" + cij
				+ ", cmr=" + cmr + ", cms=" + cms + "]";
	}
	
}
