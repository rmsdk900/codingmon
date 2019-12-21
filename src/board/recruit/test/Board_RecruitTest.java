package board.recruit.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import board.recruit.dao.RecruitDAO;
import board.recruit.dao.RecruitDAOImpl;
import board.recruit.vo.RecruitVO;

class Board_RecruitTest {

	@Test
	void test() {
		System.out.println("DAO Test");
		RecruitDAO dao = new RecruitDAOImpl();
		
		/*
		 * Criteria cri = new Criteria(); ArrayList<RecruitVO> list =
		 * dao.getCbrList(cri);
		 * 
		 * System.out.println(list); System.out.println(list.size());
		 */
		
		// RecruitVO br = dao.getCbrVO(1);
		RecruitVO br = new RecruitVO();
		//System.out.println(br);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String from = "20191220000000";
		String to = "20191231000000";
		String dead = "20191220000000";
		try {
			br.setCbr_title("제에목");
			br.setCbr_writer("이도경");
			br.setCbr_writer_num(1);
			br.setCbr_content("내애용");
			br.setCbr_pay(0.0);
			br.setCbr_date_from(sdf.parse(from));
			br.setCbr_date_to(sdf.parse(to));
			br.setCbr_deadline(sdf.parse(dead));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(br);
		
		dao.cbrWrite(br);
		
		RecruitVO rv = dao.getRecent(br.getCbr_writer_num());
		System.out.println(rv);
		System.out.println("rv 글 번호 = "+rv.getCbr_num());
		
	}

}
