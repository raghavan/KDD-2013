package model;

import util.Constants;

public class FeatureVector {
	
	private int authorId;
	private int paperId;
	private int devFromMeanYrOfPublish;
	private double sumOfOccurOfCoAuthByTotalAuth;
	private int editDistanceBetweenAuthorNames;
	private double sumOfOccurOfKeywrdByTotalKeywrd;
	private int label;
	private boolean isTraining;
	
	public FeatureVector(int authorId, int paperId) {	
		this.authorId = authorId;
		this.paperId = paperId;
	}
	
	
	public int getDevFromMeanYrOfPublish() {
		return devFromMeanYrOfPublish;
	}
	public void setDevFromMeanYrOfPublish(int devFromMeanYrOfPublish) {
		this.devFromMeanYrOfPublish = devFromMeanYrOfPublish;
	}
	public double getSumOfOccurOfCoAuthByTotalAuth() {
		return sumOfOccurOfCoAuthByTotalAuth;
	}
	public void setSumOfOccurOfCoAuthByTotalAuth(double sumOfOccurOfCoAuthByTotalAuth) {
		this.sumOfOccurOfCoAuthByTotalAuth = sumOfOccurOfCoAuthByTotalAuth;
	}
	public int getEditDistanceBetweenAuthorNames() {
		return editDistanceBetweenAuthorNames;
	}
	public void setEditDistanceBetweenAuthorNames(int editDistanceBetweenAuthorNames) {
		this.editDistanceBetweenAuthorNames = editDistanceBetweenAuthorNames;
	}
	public double getSumOfOccurOfKeywrdByTotalKeywrd() {
		return sumOfOccurOfKeywrdByTotalKeywrd;
	}
	public void setSumOfOccurOfKeywrdByTotalKeywrd(double sumOfOccurOfKeywrdByTotalKeywrd) {
		this.sumOfOccurOfKeywrdByTotalKeywrd = sumOfOccurOfKeywrdByTotalKeywrd;
	}
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
	public boolean isTraining() {
		return isTraining;
	}
	public void setTraining(boolean isTraining) {
		this.isTraining = isTraining;
	}


	public String getFeatureVectorizedString() {
		String str = authorId+Constants.COMMA+paperId+Constants.COMMA+getDevFromMeanYrOfPublish()+Constants.COMMA+
				getSumOfOccurOfCoAuthByTotalAuth()+Constants.COMMA+getEditDistanceBetweenAuthorNames()+Constants.COMMA+
				getSumOfOccurOfKeywrdByTotalKeywrd();
		
		if(isTraining()){
			str = str+Constants.COMMA+getLabel(); 
		}
		
		return str;
	}
	
	
	
	
}
