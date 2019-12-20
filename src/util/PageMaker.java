package util;

import member.vo.AgeVO;

public class PageMaker {
	
	private int totalCount;			// 전체 게시물의 개수
	private int startPage;			// 게시판의 화면에 보여질 시작 페이지 번호
	private int endPage;			// 게시판의 화면에 보여질 마지막 페이지 번호
	private boolean prev;			// 이전 페이지 버튼 활성화 여부
	private boolean next;			// 다음 페이지 버튼 활성화 여부
	private int displayPageNum=5;	// 한번에 보여줄 페이지 개수
	private int maxPage;
	
	private Criteria cri;					// 게시물 검색 정보
	
	public PageMaker() {
		this(0);
	}
	
	public PageMaker(int totalCount) {
		this(new Criteria(),totalCount);
	}
	
	public PageMaker(Criteria cri , int totalCount) {
		setCri(cri);
		setTotalCount(totalCount);
	}
	
	public void calcPaging() {
		
		endPage = (int)Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum;
		
		startPage = (endPage - displayPageNum)+1;
		
		maxPage = (int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		
		if(endPage > maxPage)endPage = maxPage;
		// 1page가 아닐 경우 이전 페이지 존재 한칸씩 이동
		// prev = startPage == 1 ? false : true;
		
		// 페이지 블럭의 첫페이지가 아닐경우 블럭 페이지 이전페이지 존재 
		prev = (endPage - displayPageNum <= 0) ? false : true;
		
		// 현제 페이지 에서 다음 페이지 존재 한칸씩 다음 페이지 이동
		//next = (endPage*cri.getPerPageNum() >= totalCount) ? false : true;
		
		// 현재 보이는 마지막 페이지 정보가  맥스 페이지 와 같으면 현재 페이지가 마지막 페이지 
		next = (endPage == maxPage) ? false : true;
		
		
	}
	
	public int getMaxPage() {
		return maxPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcPaging();
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		if(cri == null){
			setCri(new Criteria());
		}
		this.displayPageNum = displayPageNum;
		calcPaging();
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public SearchCriteria getSearch() {
		if(this.cri instanceof SearchCriteria) {
			return (SearchCriteria)this.cri;
		}
		return null;
	}
	//페이징 처리 관련
	public String makingQuery(int page) {
		SearchCriteria sCri = getSearch();
		System.out.println("sCri 확인 : "+sCri);
		StringBuilder sb = new StringBuilder();
		sb.append("?page="+page);
		if(sCri.getSearchGender() != null && !sCri.getSearchGender().trim().equals("")) {
			sb.append("&");
			sb.append("searchGender="+sCri.getSearchGender());
		}
		
		if(sCri.getSearchAge() != null && sCri.getSearchAge().size() > 0) {
			for(AgeVO i : sCri.getSearchAge()) {
				sb.append("&");
				sb.append("searchAge="+i.getAge());
			}
		}
		
		if(sCri.getSearchRegion() != null && sCri.getSearchRegion().size() > 0) {
			for(Integer i :sCri.getSearchRegion()) {
				sb.append("&");
				sb.append("searchRegion="+i);
			}
		}
		if(sCri.getSearchJob() != null && sCri.getSearchJob().size() > 0) {
			for(Integer i :sCri.getSearchJob()) {
				sb.append("&");
				sb.append("searchJob="+i);
			}
		}
		
		if(sCri.getSearchWorkLang() != null && sCri.getSearchWorkLang().size() > 0) {
			for(Integer i :sCri.getSearchWorkLang()) {
				sb.append("&");
				sb.append("searchWorkLang="+i);
			}
		}
		
		if(sCri.getSearchLearnLang() != null && sCri.getSearchLearnLang().size() > 0) {
			for(Integer i :sCri.getSearchLearnLang()) {
				sb.append("&");
				sb.append("searchLearnLang="+i);
			}
		}
		
		
		System.out.println(sb.toString());
		return sb.toString();
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", maxPage=" + maxPage + ", cri="
				+ cri + "]";
	}
}
