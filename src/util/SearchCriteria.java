package util;

import java.util.ArrayList;

public class SearchCriteria extends Criteria{
	private String searchName;						// 회원명
	private String searchGender;					// 성별
	private ArrayList<Integer> searchAge;			// 나잇대
	private ArrayList<Integer> searchRegion;		// 활동 가능 지역
	private ArrayList<Integer> searchJob;			// 직업
	private ArrayList<Integer> searchWorkLang;		// 업무 가능 언어
	private ArrayList<Integer> searchLearnLang;		// 학습 중인 언어
	
	public SearchCriteria(int page, int perPageNum, String searchName, String searchGender, ArrayList<Integer> searchAge,
			ArrayList<Integer> searchRegion, ArrayList<Integer> searchJob, ArrayList<Integer> searchWorkLang,
			ArrayList<Integer> searchLearnLang) {
		super(page, perPageNum);
		this.searchName = searchName;
		this.searchGender = searchGender;
		this.searchAge = searchAge;
		this.searchRegion = searchRegion;
		this.searchJob = searchJob;
		this.searchWorkLang = searchWorkLang;
		this.searchLearnLang = searchLearnLang;
	}


	public String getSearchName() {
		return searchName;
	}


	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}


	public String getSearchGender() {
		return searchGender;
	}


	public void setSearchGender(String searchGender) {
		this.searchGender = searchGender;
	}


	public ArrayList<Integer> getSearchAge() {
		return searchAge;
	}


	public void setSearchAge(ArrayList<Integer> searchAge) {
		this.searchAge = searchAge;
	}


	public ArrayList<Integer> getSearchRegion() {
		return searchRegion;
	}


	public void setSearchRegion(ArrayList<Integer> searchRegion) {
		this.searchRegion = searchRegion;
	}


	public ArrayList<Integer> getSearchJob() {
		return searchJob;
	}


	public void setSearchJob(ArrayList<Integer> searchJob) {
		this.searchJob = searchJob;
	}


	public ArrayList<Integer> getSearchWorkLang() {
		return searchWorkLang;
	}


	public void setSearchWorkLang(ArrayList<Integer> searchWorkLang) {
		this.searchWorkLang = searchWorkLang;
	}


	public ArrayList<Integer> getSearchLearnLang() {
		return searchLearnLang;
	}


	public void setSearchLearnLang(ArrayList<Integer> searchLearnLang) {
		this.searchLearnLang = searchLearnLang;
	}


	@Override
	public String toString() {
		return super.toString()+"/ SearchCriteria [searchName=" + searchName + ", searchGender=" + searchGender + ", searchAge="
				+ searchAge + ", searchRegion=" + searchRegion + ", searchJob=" + searchJob + ", searchWorkLang="
				+ searchWorkLang + ", searchLearnLang=" + searchLearnLang + "]";
	}

	

	
	
	
	
	
	
}
