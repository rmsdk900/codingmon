package board.recruit.vo.job;

public class codingmon_jobVO {
	
	private int cj_code;			// 직업 코드
	private String cj_name;			// 직업명
	
	public codingmon_jobVO() {}
	
	public codingmon_jobVO(int cj_code, String cj_name) {
		this.cj_code = cj_code;
		this.cj_name = cj_name;
	}

	// 이하 getter setter toString
	public int getCj_code() {
		return cj_code;
	}

	public void setCj_code(int cj_code) {
		this.cj_code = cj_code;
	}

	public String getCj_name() {
		return cj_name;
	}

	public void setCj_name(String cj_name) {
		this.cj_name = cj_name;
	}

	@Override
	public String toString() {
		return "codingmon_jobVO [cj_code=" + cj_code + ", cj_name=" + cj_name + "]";
	}
	
	
	
	

}
