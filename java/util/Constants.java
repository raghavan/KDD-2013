package util;

public interface Constants {
	
		// DB props
		String dbDriver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:8889/kdd2013";
		String username = "root";
		String password = "root";
		
		//Queries
		String GET_AUTHOR_IDs_IN_TRAINING = "select authorid from train_data where is_trained = 0";
		String GET_AUTHOR_IDs_IN_VALID = "select authorid from valid_data";
		String GET_PAPERs_FROM_VALID_BY_AUTHOR = "select paperIds from valid_data where authorId = ";
		String GET_AUTHOR_DETAIL_FOR_AUTHOR_ID = "select * from author where id =  ";
		String GET_AUTHOR_TRAIN_DATA_FOR_AUTHOR_ID = "select * from train_data where authorId = ";
		String GET_CO_AUTHORS_WORKED_WITH_AUTHOR = "select pa1.`AuthorId` from `paper_author` pa1,`paper_author` pa2 where pa1.`PaperId` = pa2.`PaperId`  and pa2.`AuthorId` = ";
		String GET_AUTHOR_AVG_PUBLISH_YR = "select * from author_mean_year where author_id = ";
		String GET_AUTHOR_KEYWORDS_USED = "select p.`Keyword`  from paper p, `paper_author` pa where p.id = pa.`PaperId` and p.`Keyword` is not null and pa.`AuthorId` = ";
		
		String GET_PAPER_DETAILS = "select * from paper p,`paper_author` pa where p.`Id` = pa.`PaperId` and pa.`PaperId` = ";
		String GET_PAPER_CO_AUTHOR = "select * from paper_author where paperid = ";
		
		String FILE_TRAINING_FEATURE_VECTOR = "files/trainingFeatures.csv";		
		String FILE_TESTING_FEATURE_VECTOR = "files/testingFeatures.csv";
		String FILE_LR_PREDICTED_RESULT = "files/logRegPredict.csv";
		String FILE_SVM_PREDICTED_RESULT = "files/svmPredict.csv";
		String FILE_RESULT_TO_UPLOAD = "files/final_results_to_upload.csv";
		
		String COMMA = ",";
		
		
		

	}
