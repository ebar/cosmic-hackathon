package com.cosmic.questionnaire;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CsvFileWriter {
	
	public void writeToCsv(JSONObject resultsJson) throws IOException {
		
		File results = new File("results.csv");
		FileWriter fileWriter = new FileWriter(results);
		
		fileWriter.write("Questions");
		fileWriter.write(",");
		fileWriter.write("Answers");
		fileWriter.write(System.getProperty("line.separator"));
		
		JSONArray jsonArray = (JSONArray) resultsJson.get("results");

		for (Object object : jsonArray.toArray()) {
			JSONObject jsonObject = (JSONObject) object;
			fileWriter.write(jsonObject.get("question").toString());
			fileWriter.write(",");
			fileWriter.write(jsonObject.get("answer").toString());
			fileWriter.write(System.getProperty("line.separator"));
		}
		fileWriter.close();
	}

}
