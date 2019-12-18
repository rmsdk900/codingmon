package member.vo;

public class MemberInfo {
	private int cmi_owner_num;
	private String cmi_intro;
	private String cmi_private; // 정보 공개 여부
	private String cmi_gender;
	private int cmi_age;
	private String cmi_career;
	private int cmi_achieve;
	private String cmi_title;
	
	public MemberInfo() {}
	
	public int getCmi_owner_num() {
		return cmi_owner_num;
	}
	public void setCmi_owner_num(int cmi_owner_num) {
		this.cmi_owner_num = cmi_owner_num;
	}
	public String getCmi_intro() {
		return cmi_intro;
	}
	public void setCmi_intro(String cmi_intro) {
		this.cmi_intro = cmi_intro;
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
	public String getCmi_career() {
		return cmi_career;
	}
	public void setCmi_career(String cmi_career) {
		this.cmi_career = cmi_career;
	}
	public int getCmi_achieve() {
		return cmi_achieve;
	}
	public void setCmi_achieve(int cmi_achieve) {
		this.cmi_achieve = cmi_achieve;
	}

	public String getCmi_title() {
		return cmi_title;
	}

	public void setCmi_title(String cmi_title) {
		this.cmi_title = cmi_title;
	}

	@Override
	public String toString() {
		return "MemberInfo [cmi_owner_num=" + cmi_owner_num + ", cmi_intro=" + cmi_intro + ", cmi_private="
				+ cmi_private + ", cmi_gender=" + cmi_gender + ", cmi_age=" + cmi_age + ", cmi_career=" + cmi_career
				+ ", cmi_achieve=" + cmi_achieve + ", cmi_title=" + cmi_title + "]";
	}
	
}
