package board.recruit.vo.job;

public class RecruitJob {
	
	private int crj_code;					// job 코드
	private int crj_board_num;				// recruit board num
	
	public int getCrj_code() {
		return crj_code;
	}
	public void setCrj_code(int crj_code) {
		this.crj_code = crj_code;
	}
	public int getCrj_board_num() {
		return crj_board_num;
	}
	public void setCrj_board_num(int crj_board_num) {
		this.crj_board_num = crj_board_num;
	}
	
	@Override
	public String toString() {
		return "RecruitJobVO [crj_code=" + crj_code + ", crj_board_num=" + crj_board_num + "]";
	}
}
