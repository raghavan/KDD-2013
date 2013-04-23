package datahandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.AuthorDetail;
import model.PaperDetail;
import model.TrainingData;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import util.Constants;
import util.Utility;

public class DBHandler {

	private ResultSet getResults(String query, Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (MySQLSyntaxErrorException me) {
			System.out.println("Error in select query =" + query);
		} catch (SQLException e) {
			System.out.println("Error in select query =" + query);
			e.printStackTrace();
		}
		return rs;
	}

	public List<Integer> getAllAuthorIdsInTraining(String query) {		
		List<Integer> authorIds = new ArrayList<Integer>();
		if (query != null) {
			ResultSet results = getResults(query, DBConnect.getConnection());
			try {
				if (results != null) {
					while (results.next()) {
						authorIds.add(results.getInt("authorId"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return authorIds;
	}

	public TrainingData getTrainingDataForAuthor(Integer authorIdInTraining) {
		String query = Constants.GET_AUTHOR_TRAIN_DATA_FOR_AUTHOR_ID+authorIdInTraining;
		TrainingData trainingData = new TrainingData();
		if (query != null) {
			ResultSet results = getResults(query, DBConnect.getConnection());
			trainingData = makeTrainingData(results);
		}
		return trainingData;
	}

	private TrainingData makeTrainingData(ResultSet results) {
		TrainingData trainingData = new TrainingData();
		try {
			if (results != null) {
				while (results.next()) {
					List<Integer> deletedPapers = getIntegerListFromSpaceSeparatedValues(results
							.getString("DeletedPaperIds"));
					List<Integer> confirmedPapers = getIntegerListFromSpaceSeparatedValues(results
							.getString("ConfirmedPaperIds"));
					trainingData.setAuthorId(results.getInt("AuthorId"));
					trainingData.setDeletedPapers(deletedPapers);
					trainingData.setConfirmedPapers(confirmedPapers);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trainingData;
	}

	// 2324 34343 2325 -> [2324,34343,2325]
	private List<Integer> getIntegerListFromSpaceSeparatedValues(String spaceSeparatedString) {
		List<Integer> numbers = new ArrayList<Integer>();
		for (String str : spaceSeparatedString.split(" ")) {
			numbers.add(Integer.parseInt(str));
		}
		return numbers;
	}

	// 2324 34343 2325 -> [2324,34343,2325]
	private List<String> getStringListFromSpaceSeparatedValues(String spaceSeparatedString) {
		List<String> keywords = new ArrayList<String>();
		for (String str : spaceSeparatedString.split(" ")) {
			keywords.add(str.trim());
		}
		return keywords;
	}

	public AuthorDetail getAuthorDetail(Integer authorId) {
		AuthorDetail authorDetail = new AuthorDetail();
		ResultSet resultSet = getResults(Constants.GET_AUTHOR_DETAIL_FOR_AUTHOR_ID + authorId,
				DBConnect.getConnection());
		authorDetail = setBaseAuthorDetails(resultSet, authorDetail);

		resultSet = getResults(Constants.GET_AUTHOR_AVG_PUBLISH_YR + authorId, DBConnect.getConnection());
		authorDetail = setAvgPubyear(resultSet, authorDetail);

		resultSet = getResults(Constants.GET_CO_AUTHORS_WORKED_WITH_AUTHOR + authorId, DBConnect.getConnection());
		authorDetail = setCoAuthorsWorked(resultSet, authorDetail);

		resultSet = getResults(Constants.GET_AUTHOR_KEYWORDS_USED + authorId, DBConnect.getConnection());
		authorDetail = setKeywordUsedCount(resultSet, authorDetail);

		return authorDetail;
	}

	private AuthorDetail setAvgPubyear(ResultSet results, AuthorDetail authorDetail) {
		try {
			if (results != null) {
				while (results.next()) {
					authorDetail.setMeanPublishYr(results.getInt("mean_yr"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authorDetail;
	}

	private AuthorDetail setCoAuthorsWorked(ResultSet results, AuthorDetail authorDetail) {
		Map<Integer, Integer> coAuthorCount = new HashMap<Integer, Integer>();
		int totalCoAuthorCount = 0;
		try {
			if (results != null) {
				while (results.next()) {
					int coAuthorId = results.getInt("AuthorId");
					int count = 0;
					if (coAuthorCount.containsKey(coAuthorId)) {
						count = coAuthorCount.get(coAuthorId);
					}
					count += 1;
					coAuthorCount.put(coAuthorId, count);
				}
				totalCoAuthorCount +=1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		authorDetail.setTotalNumberOfCoAuthorsWorked(totalCoAuthorCount);
		authorDetail.setCoAuthorWorkCount(coAuthorCount);
		return authorDetail;
	}

	private AuthorDetail setKeywordUsedCount(ResultSet results, AuthorDetail authorDetail) {
		Map<String, Integer> keywordUsedCount = new HashMap<String, Integer>();
		int totalKeywordsUsed =0;
		try {
			if (results != null) {
				while (results.next()) {
					String keywordsRaw = results.getString("keyword").trim();
					if (keywordsRaw != null && !keywordsRaw.isEmpty()) {
						String cleanedKeywords = Utility.cleanString(keywordsRaw);
						List<String> keywords = getStringListFromSpaceSeparatedValues(cleanedKeywords);
						for (String keyword : keywords) {
							int count = 0;
							if (keywordUsedCount.containsKey(keyword)) {
								count = keywordUsedCount.get(keyword);
							}
							count += 1;
							keywordUsedCount.put(keyword, count);
						}
					}
				}
				totalKeywordsUsed+=1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		authorDetail.setTotalNumberKeywordsUsed(totalKeywordsUsed);
		authorDetail.setKeywordOccurenceCount(keywordUsedCount);
		return authorDetail;
	}

	private AuthorDetail setBaseAuthorDetails(ResultSet results, AuthorDetail authorDetail) {
		try {
			if (results != null) {
				while (results.next()) {
					authorDetail.setAffiliate(results.getString("Name"));
					authorDetail.setAuthorName(results.getString("Affiliation"));
					authorDetail.setAuthorId(results.getInt("Id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authorDetail;
	}

	public PaperDetail getPaperDetail(Integer paperId) {
		PaperDetail paperDetail = new PaperDetail();
		ResultSet resultSet = getResults(Constants.GET_PAPER_DETAILS + paperId,
				DBConnect.getConnection());
		paperDetail = setBasePaperDetails(resultSet, paperDetail);

		resultSet = getResults(Constants.GET_PAPER_CO_AUTHOR + paperId, DBConnect.getConnection());
		paperDetail = setCoAuthors(resultSet, paperDetail);

		return paperDetail;
	}

	private PaperDetail setCoAuthors(ResultSet results, PaperDetail paperDetail) {
		List<Integer> authors = new ArrayList<Integer>();
		try {
			if (results != null) {
				while (results.next()) {
					authors.add(results.getInt("AuthorId"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		paperDetail.setCoAuthors(authors);
		return paperDetail;
	}

	private PaperDetail setBasePaperDetails(ResultSet results, PaperDetail paperDetail) {
		try {
			if (results != null) {
				while (results.next()) {
					paperDetail.setPaperId(results.getInt("PaperId"));
					paperDetail.setPaperTitle(results.getString("Title"));
					paperDetail.setYear(results.getInt("Year"));
					paperDetail.setAuthorNameInPaper(results.getString("Name"));
					paperDetail.setAffiliateNameInPaper(results.getString("Affiliation"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paperDetail;
	}

	public List<Integer> getPaperIdsFromValidTableForAuthor(int authorId) {
		List<Integer> papers = new ArrayList<Integer>();
		ResultSet resultSet = getResults(Constants.GET_PAPERs_FROM_VALID_BY_AUTHOR + authorId,
				DBConnect.getConnection());
		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					papers = getIntegerListFromSpaceSeparatedValues(resultSet.getString("paperIds"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return papers;
	}

}
