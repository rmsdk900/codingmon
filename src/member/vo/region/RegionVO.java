package member.vo.region;

public class RegionVO {
	private int cr_code;
	private String cr_name;
	
	public RegionVO() {}
	public RegionVO(int cr_code, String cr_name) {
		super();
		this.cr_code = cr_code;
		this.cr_name = cr_name;
	}
	
	public int getCr_code() {
		return cr_code;
	}
	public void setCr_code(int cr_code) {
		this.cr_code = cr_code;
	}
	public String getCr_name() {
		return cr_name;
	}
	public void setCr_name(String cr_name) {
		this.cr_name = cr_name;
	}
	@Override
	public String toString() {
		return "RegionVO [cr_code=" + cr_code + ", cr_name=" + cr_name + "]";
	}
}
