package com.cosmic.questionnaire;

import java.io.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class QuestionnaireJsonTransformer {
 
	@SuppressWarnings("unchecked")
	public JSONObject createJson() {
		
		JSONObject questionnaireObject = new JSONObject();
		
		File questionnaireDirectory = new File("feedback/questionnaire");
		
		if (questionnaireDirectory.isDirectory()) {
			String[] categories = questionnaireDirectory.list();
			JSONArray categoryArray = new JSONArray();
			for (String category : categories) {
				File categoryDirectory = new File(questionnaireDirectory, category);
				JSONObject categoryObject = new JSONObject();
				if (categoryDirectory.isDirectory()) {
					String[] questions = categoryDirectory.list();
					JSONArray allQuestionsArray = new JSONArray();
					for (String question : questions) {
						File questionDirectory = new File(categoryDirectory, question);
						JSONObject questionObject = new JSONObject();
						if (questionDirectory.isDirectory()) {
							JSONArray optionsArray = new JSONArray();
							String[] options = questionDirectory.list();
							for (String option : options) {
								File optionFile = new File(questionDirectory, option);
								JSONObject optionObject = new JSONObject();
								optionObject.put("imageUrl", optionFile.toURI());
								optionsArray.add(optionObject);
							}
							questionObject.put("title", questionDirectory.getName());
							questionObject.put("options", optionsArray);
							allQuestionsArray.add(questionObject);
						}
						
					}
					categoryObject.put("category", categoryDirectory.getName());
					categoryObject.put("questions", allQuestionsArray);
					categoryArray.add(categoryObject);
					
				}
				
			}
			questionnaireObject.put("categories", categoryArray);
		}
		return questionnaireObject;
		
	}
}
