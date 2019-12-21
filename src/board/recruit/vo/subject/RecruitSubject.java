package board.recruit.vo.subject;

public class RecruitSubject {
	
	private int crs_code;
	private int crs_board_num;
	
	public RecruitSubject() {}
	
	public RecruitSubject(int crs_code, int crs_board_num) {
		this.crs_code = crs_code;
		this.crs_board_num = crs_board_num;
	}
	
	public int getCrs_code() {
		return crs_code;
	}
	public void setCrs_code(int crs_code) {
		this.crs_code = crs_code;
	}
	public int getCrs_board_num() {
		return crs_board_num;
	}
	public void setCrs_board_num(int crs_board_num) {
		this.crs_board_num = crs_board_num;
	}
	
	@Override
	public String toString() {
		return "RecruitSubject [crs_code=" + crs_code + ", crs_board_num=" + crs_board_num + "]";
	}
	
	

}
