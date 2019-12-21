package board.recruit.vo;

import java.util.Date;

public class RecruitVO {
	
	private int cbr_num;				// 글번호
	private String cbr_title;			// 제목
	private String cbr_writer;			// 작성자 이름
	private int cbr_writer_num;			// 작성자 번호
	private String cbr_content;			// 내용
	private Double cbr_pay;				// 임금
	private Date cbr_regdate;			// 등록일
	private Date cbr_date_from;			// 작업 시작 예정일
	private Date cbr_date_to;			// 작업 마감 예정일
	private Date cbr_deadline;			// 게시글 등록 종료일
	
	public RecruitVO() {}

	public RecruitVO(int cbr_num, String cbr_title, String cbr_writer, int cbr_writer_num, String cbr_content,
			Double cbr_pay, Date cbr_regdate, Date cbr_date_from, Date cbr_date_to, Date cbr_deadline) {
		super();
		this.cbr_num = cbr_num;
		this.cbr_title = cbr_title;
		this.cbr_writer = cbr_writer;
		this.cbr_writer_num = cbr_writer_num;
		this.cbr_content = cbr_content;
		this.cbr_pay = cbr_pay;
		this.cbr_regdate = cbr_regdate;
		this.cbr_date_from = cbr_date_from;
		this.cbr_date_to = cbr_date_to;
		this.cbr_deadline = cbr_deadline;
	}

	// getter setter toString
	public int getCbr_num() {
		return cbr_num;
	}

	public void setCbr_num(int cbr_num) {
		this.cbr_num = cbr_num;
	}

	public String getCbr_title() {
		return cbr_title;
	}

	public void setCbr_title(String cbr_title) {
		this.cbr_title = cbr_title;
	}

	public String getCbr_writer() {
		return cbr_writer;
	}

	public void setCbr_writer(String cbr_writer) {
		this.cbr_writer = cbr_writer;
	}

	public int getCbr_writer_num() {
		return cbr_writer_num;
	}

	public void setCbr_writer_num(int cbr_writer_num) {
		this.cbr_writer_num = cbr_writer_num;
	}

	public String getCbr_content() {
		return cbr_content;
	}

	public void setCbr_content(String cbr_content) {
		this.cbr_content = cbr_content;
	}

	public Double getCbr_pay() {
		return cbr_pay;
	}

	public void setCbr_pay(Double cbr_pay) {
		this.cbr_pay = cbr_pay;
	}

	public Date getCbr_regdate() {
		return cbr_regdate;
	}

	public void setCbr_regdate(Date cbr_regdate) {
		this.cbr_regdate = cbr_regdate;
	}

	public Date getCbr_date_from() {
		return cbr_date_from;
	}

	public void setCbr_date_from(Date cbr_date_from) {
		this.cbr_date_from = cbr_date_from;
	}

	public Date getCbr_date_to() {
		return cbr_date_to;
	}

	public void setCbr_date_to(Date cbr_date_to) {
		this.cbr_date_to = cbr_date_to;
	}

	public Date getCbr_deadline() {
		return cbr_deadline;
	}

	public void setCbr_deadline(Date cbr_deadline) {
		this.cbr_deadline = cbr_deadline;
	}

	@Override
	public String toString() {
		return "RecruitVO [cbr_num=" + cbr_num + ", cbr_title=" + cbr_title + ", cbr_writer=" + cbr_writer
				+ ", cbr_writer_num=" + cbr_writer_num + ", cbr_content=" + cbr_content + ", cbr_pay=" + cbr_pay
				+ ", cbr_regdate=" + cbr_regdate + ", cbr_date_from=" + cbr_date_from + ", cbr_date_to=" + cbr_date_to
				+ ", cbr_deadline=" + cbr_deadline + "]";
	}
	
	
}
