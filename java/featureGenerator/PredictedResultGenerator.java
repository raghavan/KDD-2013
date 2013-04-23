package featureGenerator;

import java.util.List;

import datahandler.DBHandler;

import util.Constants;
import util.Utility;
import model.PredictedResultMap;

public class PredictedResultGenerator {

	public static void main(String args[]){
		Utility.deleteFile(Constants.FILE_RESULT_TO_UPLOAD);
		PredictedResultMap predictedResultMap = Utility.readFromFile(Constants.FILE_LR_PREDICTED_RESULT);
		DBHandler dbHandler = new DBHandler();
		
		List<Integer> authorIdInValidData = dbHandler.getAllAuthorIdsInTraining(Constants.GET_AUTHOR_IDs_IN_VALID);
		for(Integer authorId : authorIdInValidData){
			Utility.appendDataToFile(Constants.FILE_RESULT_TO_UPLOAD, predictedResultMap.mapToResultString(authorId));
		}
	}
	
}
