package member.vo.job;

public class MemberJob {
	private int cij_code;
	private int cij_owner_num;
	
	public int getCij_code() {
		return cij_code;
	}
	public void setCij_code(int cij_code) {
		this.cij_code = cij_code;
	}
	public int getCij_owner_num() {
		return cij_owner_num;
	}
	public void setCij_owner_num(int cij_owner_num) {
		this.cij_owner_num = cij_owner_num;
	}
	@Override
	public String toString() {
		return "InfoAndJob [cij_code=" + cij_code + ", cij_owner_num=" + cij_owner_num + "]";
	}
	
	
	
}
