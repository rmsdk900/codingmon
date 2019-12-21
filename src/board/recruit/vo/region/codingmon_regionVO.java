package board.recruit.vo.region;

public class codingmon_regionVO {
	
	private int cr_code;				// 지역 코드
	private String cr_name;				// 지역 이름
	
	public codingmon_regionVO() {}
	
	public codingmon_regionVO(int cr_code, String cr_name) {
		this.cr_code = cr_code;
		this.cr_name = cr_name;
	}

	// getter setter toString
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
		return "codingmon_regionVO [cr_code=" + cr_code + ", cr_name=" + cr_name + "]";
	}
	
	

}
