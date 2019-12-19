package member.vo;

public class AgeVO {
	private int endAge;
	private int startAge;
	
	public int getEndAge() {
		return endAge;
	}
	public void setEndAge(int endAge) {
		this.endAge = endAge;
	}
	public int getStartAge() {
		return startAge;
	}
	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}
	
	@Override
	public String toString() {
		return "AgeVO [endAge=" + endAge + ", startAge=" + startAge + "]";
	}
	
	
	
}
