package board.recruit.vo.region;

public class RecruitRegion {
	
	private int crr_code;					// region 코드
	private int crr_board_num;				// recruit board num
	
	public RecruitRegion() {}
	
	public RecruitRegion(int crr_code, int crr_board_num) {
		this.crr_code = crr_code;
		this.crr_board_num = crr_board_num;
	}
	
	// getter setter toString
	public int getCrr_code() {
		return crr_code;
	}
	public void setCrr_code(int crr_code) {
		this.crr_code = crr_code;
	}
	public int getCrr_board_num() {
		return crr_board_num;
	}
	public void setCrr_board_num(int crr_board_num) {
		this.crr_board_num = crr_board_num;
	}
	
	@Override
	public String toString() {
		return "RecruitRegion [crr_code=" + crr_code + ", crr_board_num=" + crr_board_num + "]";
	}
}
