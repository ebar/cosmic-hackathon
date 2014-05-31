package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringEscapeUtils.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cosmic.questionnaire.CsvFileWriter;
import com.cosmic.questionnaire.QuestionnaireJsonTransformer;

public class QuestionnaireServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        QuestionnaireJsonTransformer transformer = new QuestionnaireJsonTransformer();
        JSONObject jsonObject;
		try {
			jsonObject = transformer.createJson();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        resp.setContentType("text/html");
        resp.setStatus(200);
        
        request.setAttribute("structure", jsonObject.toString());
        request.getRequestDispatcher("index.jsp").forward(request, resp);
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
	           throws ServletException, IOException{
		JSONParser parser = new JSONParser();
		Object object = "";
		try {
			object = parser.parse(request.getParameter("results"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject jsonArray = (JSONObject) object;
		CsvFileWriter csvWriter = new CsvFileWriter();
		csvWriter.writeToCsv(jsonArray);
		
		
		PrintWriter out = resp.getWriter();
		out.println("Thankyou!");
	}
	
    
}
