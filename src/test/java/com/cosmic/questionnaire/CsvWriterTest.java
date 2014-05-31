package com.cosmic.questionnaire;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

public class CsvWriterTest {
	
	CsvFileWriter fileWriter = new CsvFileWriter();
	
	@SuppressWarnings("unchecked")
	@Test
	public void testFileWrite() throws IOException {
		JSONArray jsonArray = new JSONArray();
		
		JSONObject answer1 = new JSONObject();
		answer1.put("question", "How are you?");
		answer1.put("answer", "Fine");
		JSONObject answer2 = new JSONObject();
		answer2.put("question", "Question 2");
		answer2.put("answer", "Answer2");
		
		jsonArray.add(answer1);
		jsonArray.add(answer2);
		
		JSONObject testObject = new JSONObject();
		
		testObject.put("results", jsonArray);
		
		fileWriter.writeToCsv(testObject);
		
		System.out.println(testObject);
		
		File createdFile = new File("results.csv");
		
		assertTrue(createdFile.exists());
	}

}
