package com.cosmic.questionnaire;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class QuestionnaireJsonTransformer {
 
	@SuppressWarnings("unchecked")
	public JSONObject createJson() throws MalformedURLException {
		
		JSONObject questionnaireObject = new JSONObject();
		
	//	File questionnaireDirectory = new File("feedback/questionnaire");
		
		File file = new File(QuestionnaireJsonTransformer.class.getClassLoader().getResource("feedback/questionnaire").getFile());
		
		System.out.println(file);
		
		if (file.isDirectory()) {
			String[] categories = file.list();
			JSONArray categoryArray = new JSONArray();
			for (String category : categories) {
				createCategoryObject(file, categoryArray,
						category);
				
			}
			questionnaireObject.put("categories", categoryArray);
		}
		return questionnaireObject;
		
	}

	@SuppressWarnings("unchecked")
	private void createCategoryObject(File questionnaireDirectory,
			JSONArray categoryArray, String category) throws MalformedURLException {
		File categoryDirectory = new File(questionnaireDirectory, category);
		JSONObject categoryObject = new JSONObject();
		if (categoryDirectory.isDirectory()) {
			String[] questions = categoryDirectory.list();
			JSONArray allQuestionsArray = new JSONArray();
			for (String question : questions) {
				createQuestionObject(categoryDirectory,
						allQuestionsArray, question);
				
			}
			categoryObject.put("category", categoryDirectory.getName());
			categoryObject.put("questions", allQuestionsArray);
			categoryArray.add(categoryObject);
			
		}
	}

	@SuppressWarnings("unchecked")
	private void createQuestionObject(File categoryDirectory,
			JSONArray allQuestionsArray, String question) throws MalformedURLException {
		File questionDirectory = new File(categoryDirectory, question);
		JSONObject questionObject = new JSONObject();
		if (questionDirectory.isDirectory()) {
			JSONArray optionsArray = new JSONArray();
			String[] options = questionDirectory.list();
			for (String option : options) {
				File optionFile = new File(questionDirectory, option);
				JSONObject optionObject = new JSONObject();
				optionObject.put("imageUrl", optionFile.toURI().toURL());
				optionsArray.add(optionObject);
			}
			questionObject.put("title", questionDirectory.getName());
			questionObject.put("options", optionsArray);
			allQuestionsArray.add(questionObject);
		}
	}
}
