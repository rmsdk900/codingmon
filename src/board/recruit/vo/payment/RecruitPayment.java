package board.recruit.vo.payment;

public class RecruitPayment {
	
	private int crp_code;				// payment code
	private int crp_board_num;			// recruit board num
	
	public RecruitPayment() {}

	public RecruitPayment(int crp_code, int crp_board_num) {
		this.crp_code = crp_code;
		this.crp_board_num = crp_board_num;
	}

	// getter setter toString
	public int getCrp_code() {
		return crp_code;
	}

	public void setCrp_code(int crp_code) {
		this.crp_code = crp_code;
	}

	public int getCrp_board_num() {
		return crp_board_num;
	}

	public void setCrp_board_num(int crp_board_num) {
		this.crp_board_num = crp_board_num;
	}

	@Override
	public String toString() {
		return "RecruitPayment [crp_code=" + crp_code + ", crp_board_num=" + crp_board_num + "]";
	}
	
	

}
