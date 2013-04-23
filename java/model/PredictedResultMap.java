package model;

import java.util.HashMap;
import java.util.Map;

import util.Constants;

public class PredictedResultMap {

	Map<Integer, TrainingData> authorPapers = new HashMap<Integer, TrainingData>();

	public void addPaper(int authorId, int paper, int label) {
		TrainingData trainingData = new TrainingData();
		if (authorPapers.containsKey(authorId)) {
			trainingData = authorPapers.get(authorId);
		}
		if (label == -1) {
			trainingData.addDeletedPaper(paper);
		} else {
			trainingData.addConfirmedPaper(paper);
		}
		authorPapers.put(authorId, trainingData);
	}

	public String mapToResultString(int authorId){
		String str = authorId+Constants.COMMA+authorPapers.get(authorId).getDeletedPaperIdsResultString()+
				authorPapers.get(authorId).getConfirmedPaperIdsResultString();
		return str;
	}
	
}
