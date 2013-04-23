package model;

import java.util.Map;

public class AuthorDetail {
	
	private String authorName;
	private String affiliate;
	private int meanPublishYr;
	private int authorId;
	private Map<Integer,Integer> coAuthorWorkCount;
	private Map<String,Integer> keywordOccurenceCount;
	private int totalNumberOfCoAuthorsWorked;
	private int totalNumberKeywordsUsed;
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAffiliate() {
		return affiliate;
	}
	public void setAffiliate(String affiliate) {
		this.affiliate = affiliate;
	}
	public int getMeanPublishYr() {
		return meanPublishYr;
	}
	public void setMeanPublishYr(int meanPublishYr) {
		this.meanPublishYr = meanPublishYr;
	}
	
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public Map<Integer, Integer> getCoAuthorWorkCount() {
		return coAuthorWorkCount;
	}
	public void setCoAuthorWorkCount(Map<Integer, Integer> coAuthorWorkCount) {
		this.coAuthorWorkCount = coAuthorWorkCount;
	}
	public Map<String, Integer> getKeywordOccurenceCount() {
		return keywordOccurenceCount;
	}
	public void setKeywordOccurenceCount(Map<String, Integer> keywordOccurenceCount) {
		this.keywordOccurenceCount = keywordOccurenceCount;
	}
	public int getTotalNumberOfCoAuthorsWorked() {
		return totalNumberOfCoAuthorsWorked;
	}
	public void setTotalNumberOfCoAuthorsWorked(int totalNumberOfCoAuthorsWorked) {
		this.totalNumberOfCoAuthorsWorked = totalNumberOfCoAuthorsWorked;
	}
	public int getTotalNumberKeywordsUsed() {
		return totalNumberKeywordsUsed;
	}
	public void setTotalNumberKeywordsUsed(int totalNumberKeywordsUsed) {
		this.totalNumberKeywordsUsed = totalNumberKeywordsUsed;
	}	
		
}
