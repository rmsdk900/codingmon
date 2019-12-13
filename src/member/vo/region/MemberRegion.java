package member.vo.region;

public class MemberRegion {
	private int cmr_code;
	private int cmr_owner_num;
	
	
	public MemberRegion() {}
	public MemberRegion(int cmr_code, int cmr_owner_num) {
		this.cmr_code = cmr_code;
		this.cmr_owner_num = cmr_owner_num;
	}
	public int getCmr_code() {
		return cmr_code;
	}
	public void setCmr_code(int cmr_code) {
		this.cmr_code = cmr_code;
	}
	public int getCmr_owner_num() {
		return cmr_owner_num;
	}
	public void setCmr_owner_num(int cmr_owner_num) {
		this.cmr_owner_num = cmr_owner_num;
	}
	@Override
	public String toString() {
		return "MemberRegion [cmr_code=" + cmr_code + ", cmr_owner_num=" + cmr_owner_num + "]";
	}
}
