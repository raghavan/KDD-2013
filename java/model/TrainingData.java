package model;

import java.util.ArrayList;
import java.util.List;

public class TrainingData {
	
	private int authorId;
	private List<Integer> deletedPapers;
	private List<Integer> confirmedPapers;
	
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public List<Integer> getDeletedPapers() {
		return deletedPapers;
	}
	public void setDeletedPapers(List<Integer> deletedPapers) {
		this.deletedPapers = deletedPapers;
	}
	public List<Integer> getConfirmedPapers() {
		return confirmedPapers;
	}
	public void setConfirmedPapers(List<Integer> confirmedPapers) {
		this.confirmedPapers = confirmedPapers;
	}
	public void addDeletedPaper(int deletedPaper){
		if(deletedPapers == null){
			deletedPapers = new ArrayList<Integer>();		
		}
		deletedPapers.add(deletedPaper);		
	}
	
	public void addConfirmedPaper(int confirmedPaper){
		if(confirmedPapers == null){
			confirmedPapers = new ArrayList<Integer>();		
		}
		confirmedPapers.add(confirmedPaper);		
	}

	public String getDeletedPaperIdsResultString() {
		StringBuilder str = new StringBuilder("");
		for(Integer paperId : deletedPapers){
			str.append(paperId +" "); 
		}
		return str.toString();	
	}
	

	public String getConfirmedPaperIdsResultString() {
		StringBuilder str = new StringBuilder("");
		for(Integer paperId : confirmedPapers){
			str.append(paperId +" "); 
		}
		return str.toString();	
	}
	
}
