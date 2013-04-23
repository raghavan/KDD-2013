package model;

import java.util.List;

public class PaperDetail {
	private int paperId;
	private String paperTitle;
	private String authorNameInPaper;
	private String affiliateNameInPaper;
	private List<String> keywordsUsed;
	private List<Integer> coAuthors;
	private int year;
	
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public String getAuthorNameInPaper() {
		return authorNameInPaper;
	}
	public void setAuthorNameInPaper(String authorNameInPaper) {
		this.authorNameInPaper = authorNameInPaper;
	}
	public String getAffiliateNameInPaper() {
		return affiliateNameInPaper;
	}
	public void setAffiliateNameInPaper(String affiliateNameInPaper) {
		this.affiliateNameInPaper = affiliateNameInPaper;
	}
	public List<String> getKeywordsUsed() {
		return keywordsUsed;
	}
	public void setKeywordsUsed(List<String> keywordsUsed) {
		this.keywordsUsed = keywordsUsed;
	}
	public List<Integer> getCoAuthors() {
		return coAuthors;
	}
	public void setCoAuthors(List<Integer> coAuthors) {
		this.coAuthors = coAuthors;
	}
	public String getPaperTitle() {
		return paperTitle;
	}
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
	
}
