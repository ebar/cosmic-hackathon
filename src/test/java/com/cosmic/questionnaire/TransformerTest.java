package com.cosmic.questionnaire;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

public class TransformerTest {
	
	public QuestionnaireJsonTransformer transformer = new QuestionnaireJsonTransformer(); 
	
	@Test
	public void shouldTransformtoJSON() throws URISyntaxException, MalformedURLException {
		JSONObject jsonObject = transformer.createJson();
		assertTrue(jsonObject.containsKey("categories"));
		System.out.println(jsonObject);
	}

}
