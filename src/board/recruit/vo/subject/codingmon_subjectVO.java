package board.recruit.vo.subject;

public class codingmon_subjectVO {
	
	private int cs_code;				// 언어 코드
	private String cs_name;				// 언어 이름
	
	public codingmon_subjectVO() {}
	
	public codingmon_subjectVO(int cs_code, String cs_name) {
		this.cs_code = cs_code;
		this.cs_name = cs_name;
	}

	// getter setter toString
	public int getCs_code() {
		return cs_code;
	}

	public void setCs_code(int cs_code) {
		this.cs_code = cs_code;
	}

	public String getCs_name() {
		return cs_name;
	}

	public void setCs_name(String cs_name) {
		this.cs_name = cs_name;
	}

	@Override
	public String toString() {
		return "codingmon_subjectVO [cs_code=" + cs_code + ", cs_name=" + cs_name + "]";
	}
	
	

}
