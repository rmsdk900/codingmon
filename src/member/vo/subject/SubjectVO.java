package member.vo.subject;

public class SubjectVO {
	private int cs_code;
	private String cs_name;
	private int cs_category;
	
	public SubjectVO() {}
	public SubjectVO(int cs_code, String cs_name, int cs_category) {
		this.cs_code = cs_code;
		this.cs_name = cs_name;
		this.cs_category = cs_category;
	}
	
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
	public int getCs_category() {
		return cs_category;
	}
	public void setCs_category(int cs_category) {
		this.cs_category = cs_category;
	}
	
	@Override
	public String toString() {
		return "SubjectVO [cs_code=" + cs_code + ", cs_name=" + cs_name + ", cs_category=" + cs_category + "]";
	}
}
