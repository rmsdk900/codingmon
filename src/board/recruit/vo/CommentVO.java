package board.recruit.vo;

import java.util.Date;

public class CommentVO {
	
	private int ccr_num;				// 댓글 번호
	private int ccr_board_num;			// 원본글 번호
	private int ccr_writer_num;			// 작성자 번호
	private String ccr_writer_name;		// 작성자 이름
	private String ccr_content;			// 내용
	private Date ccr_regdate;			// 등록일
	private String ccr_delete;			// 삭제 여부
	
	// 생성자
	public CommentVO() {
		super();
	}
	
	public CommentVO(int ccr_board_num, int ccr_writer_num, String ccr_writer_name, String ccr_content) {
		this.ccr_board_num = ccr_board_num;
		this.ccr_writer_num = ccr_writer_num;
		this.ccr_writer_name = ccr_writer_name;
		this.ccr_content = ccr_content;
	}
	
	public CommentVO(int ccr_num, int ccr_board_num, int ccr_writer_num, String ccr_writer_name, String ccr_content,
			Date ccr_regdate, String ccr_delete) {
		this.ccr_num = ccr_num;
		this.ccr_board_num = ccr_board_num;
		this.ccr_writer_num = ccr_writer_num;
		this.ccr_writer_name = ccr_writer_name;
		this.ccr_content = ccr_content;
		this.ccr_regdate = ccr_regdate;
		this.ccr_delete = ccr_delete;
	}
	
	// 이하 getter setter toString
	public int getCcr_num() {
		return ccr_num;
	}
	public void setCcr_num(int ccr_num) {
		this.ccr_num = ccr_num;
	}
	public int getCcr_board_num() {
		return ccr_board_num;
	}
	public void setCcr_board_num(int ccr_board_num) {
		this.ccr_board_num = ccr_board_num;
	}
	public int getCcr_writer_num() {
		return ccr_writer_num;
	}
	public void setCcr_writer_num(int ccr_writer_num) {
		this.ccr_writer_num = ccr_writer_num;
	}
	public String getCcr_writer_name() {
		return ccr_writer_name;
	}
	public void setCcr_writer_name(String ccr_writer_name) {
		this.ccr_writer_name = ccr_writer_name;
	}
	public String getCcr_content() {
		return ccr_content;
	}
	public void setCcr_content(String ccr_content) {
		this.ccr_content = ccr_content;
	}
	public Date getCcr_regdate() {
		return ccr_regdate;
	}
	public void setCcr_regdate(Date ccr_regdate) {
		this.ccr_regdate = ccr_regdate;
	}
	public String getCcr_delete() {
		return ccr_delete;
	}
	public void setCcr_delete(String ccr_delete) {
		this.ccr_delete = ccr_delete;
	}
	
	@Override
	public String toString() {
		return "CommentVO [ccr_num=" + ccr_num + ", ccr_board_num=" + ccr_board_num + ", ccr_writer_num="
				+ ccr_writer_num + ", ccr_writer_name=" + ccr_writer_name + ", ccr_content=" + ccr_content
				+ ", ccr_regdate=" + ccr_regdate + ", ccr_delete=" + ccr_delete + "]";
	}
}
