package member.vo.job;

public class JobVO {
	private int cj_code;
	private String cj_name;
	
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
		return "JobVO [cj_code=" + cj_code + ", cj_name=" + cj_name + "]";
	}
	
	
	
}
