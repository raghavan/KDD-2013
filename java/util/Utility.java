package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.PredictedResultMap;

public class Utility {

	public static String cleanString(String str) {
		if (str != null && !str.isEmpty()) {
			str = str.replace(",", " ");
			str = str.replace(":", " ");
			str = str.replace(";", " ");
			str = str.replace(".", " ");
		}
		return str;
	}

	public static void appendDataToFile(String fileName, String content) {
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			fstream = new FileWriter(fileName, true);
			out = new BufferedWriter(fstream);
			out.write(content);
			out.write("\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				fstream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteFile(String fileName) {
		try {
			File file = new File(fileName);
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PredictedResultMap readFromFile(String fileName) {
		FileInputStream fstream = null;
		PredictedResultMap predictedResultMap = new PredictedResultMap();
		try {
			fstream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		try {
			while ((strLine = br.readLine()) != null) {
				strLine = strLine.trim();
				String str[] = strLine.split(",");
				predictedResultMap.addPaper(Integer.parseInt(str[0]), Integer.parseInt(str[1]),
						Integer.parseInt(str[2]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return predictedResultMap;
	}

}
