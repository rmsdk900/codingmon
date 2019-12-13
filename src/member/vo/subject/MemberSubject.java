package member.vo.subject;

public class MemberSubject {
	private int cms_code;
	private int cms_owner_num;
	private int cms_category;
	
	public MemberSubject() {}
	public MemberSubject(int cms_code, int cms_owner_num, int cms_category) {
		this.cms_code = cms_code;
		this.cms_owner_num = cms_owner_num;
		this.cms_category = cms_category;
	}
	
	public int getCms_code() {
		return cms_code;
	}
	public void setCms_code(int cms_code) {
		this.cms_code = cms_code;
	}
	public int getCms_owner_num() {
		return cms_owner_num;
	}
	public void setCms_owner_num(int cms_owner_num) {
		this.cms_owner_num = cms_owner_num;
	}
	public int getCms_category() {
		return cms_category;
	}
	public void setCms_category(int cms_category) {
		this.cms_category = cms_category;
	}
	@Override
	public String toString() {
		return "MemberSubject [cms_code=" + cms_code + ", cms_owner_num=" + cms_owner_num + ", cms_category="
				+ cms_category + "]";
	}
}
