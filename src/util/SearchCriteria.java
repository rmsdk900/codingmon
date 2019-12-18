package util;

public class SearchCriteria extends Criteria{
	private String searchName;
	private String searchValue;
	
	public SearchCriteria(int page, int perPageNum, String searchName, String searchValue) {
		super(page, perPageNum);
		this.searchName = searchName;
		this.searchValue = searchValue;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public String toString() {
		return super.toString()+"/ SearchCriteria [searchName=" + searchName + ", searchValue=" + searchValue + "]";
	}
	
	
	
	
	
}
