package com.cosmic.questionnaire;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class QuestionnaireJsonTransformer {
 
	@SuppressWarnings("unchecked")
	public JSONObject createJson() throws MalformedURLException, URISyntaxException {
		
		JSONObject questionnaireObject = new JSONObject();
		
		File questionnaireDirectory = new File("src/main/webapp/feedback/questionnaire");
		
//		File file = new File(QuestionnaireJsonTransformer.class.getClassLoader().getResource("feedback/questionnaire").getFile());
		
		System.out.println(questionnaireDirectory);
		
		if (questionnaireDirectory.isDirectory()) {
			String[] categories = questionnaireDirectory.list();
			JSONArray categoryArray = new JSONArray();
			for (String category : categories) {
				createCategoryObject(questionnaireDirectory, categoryArray,
						category);
				
			}
			questionnaireObject.put("categories", categoryArray);
		}
		return questionnaireObject;
		
	}

	@SuppressWarnings("unchecked")
	private void createCategoryObject(File questionnaireDirectory,
			JSONArray categoryArray, String category) throws MalformedURLException, URISyntaxException {
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
			JSONArray allQuestionsArray, String question) throws MalformedURLException, URISyntaxException {
		File questionDirectory = new File(categoryDirectory, question);
		JSONObject questionObject = new JSONObject();
		if (questionDirectory.isDirectory()) {
			JSONArray optionsArray = new JSONArray();
			String[] options = questionDirectory.list();
			for (String option : options) {
				File optionFile = new File(questionDirectory, option);
				JSONObject optionObject = new JSONObject();
				URL fullUrl = optionFile.toURI().toURL();
				
				URI uri = optionFile.toURI();
				uri.relativize(new URI("/app/src/main/webapp/feedback"));
				optionObject.put("imageUrl", uri);
				
				optionsArray.add(optionObject);
			}
			questionObject.put("title", questionDirectory.getName());
			questionObject.put("options", optionsArray);
			allQuestionsArray.add(questionObject);
		}
	}
}
