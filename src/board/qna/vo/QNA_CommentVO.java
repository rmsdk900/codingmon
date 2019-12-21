package board.qna.vo;

import java.util.Date;

public class QNA_CommentVO {

	
		private int ccq_num;
		private int ccq_board_num;
		private int ccq_writer_num;
		private String ccq_writer_name;
		private String ccq_content;
		private Date ccq_regdate;
		private String ccq_delete;
		
		
		public QNA_CommentVO() {
			super();
		}

		public QNA_CommentVO(int ccq_writer_num, String ccq_writer_name, String ccq_content, int ccq_board_num) {
			this.ccq_writer_num = ccq_writer_num;
			this.ccq_writer_name = ccq_writer_name;
			this.ccq_content = ccq_content;
			this.ccq_board_num = ccq_board_num;
		}
		
		public QNA_CommentVO(int ccq_num, int ccq_writer_num, String ccq_writer_name, String ccq_content,
				Date ccq_regdate, String ccq_delete , int ccq_board_num) {
			this.ccq_num = ccq_num;
			this.ccq_writer_num = ccq_writer_num;
			this.ccq_writer_name = ccq_writer_name;
			this.ccq_content = ccq_content;
			this.ccq_regdate = ccq_regdate;
			this.ccq_delete = ccq_delete;
			this.ccq_board_num = ccq_board_num;
		}

		
		public int getCcq_num() {
			return ccq_num;
		}
		public void setCcq_num(int ccq_num) {
			this.ccq_num = ccq_num;
		}
		public int getCcq_board_num() {
			return ccq_board_num;
		}
		public void setCcq_board_num(int ccq_board_num) {
			this.ccq_board_num = ccq_board_num;
		}
		public int getCcq_writer_num() {
			return ccq_writer_num;
		}
		public void setCcq_writer_num(int ccq_writer_num) {
			this.ccq_writer_num = ccq_writer_num;
		}
		public String getCcq_writer_name() {
			return ccq_writer_name;
		}
		public void setCcq_writer_name(String ccq_writer_name) {
			this.ccq_writer_name = ccq_writer_name;
		}
		public String getCcq_content() {
			return ccq_content;
		}
		public void setCcq_content(String ccq_content) {
			this.ccq_content = ccq_content;
		}
		public Date getCcq_regdate() {
			return ccq_regdate;
		}
		public void setCcq_regdate(Date ccq_regdate) {
			this.ccq_regdate = ccq_regdate;
		}
		public String getCcq_delete() {
			return ccq_delete;
		}
		public void setCcq_delete(String ccq_delete) {
			this.ccq_delete = ccq_delete;
		}
		@Override
		public String toString() {
			return "QNA_CommentVO [ccq_num=" + ccq_num + ", ccq_board_num=" + ccq_board_num + ", ccq_writer_num="
					+ ccq_writer_num + ", ccq_writer_name=" + ccq_writer_name + ", ccq_content="
					+ ccq_content + ", ccq_regdate=" + ccq_regdate + ", ccq_delete=" + ccq_delete + "]";
		}
		
		
		
		
		
		
		
		
		
		
}
