package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringEscapeUtils.*;
import org.json.simple.JSONObject;

import com.cosmic.questionnaire.QuestionnaireJsonTransformer;

public class JsonServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        QuestionnaireJsonTransformer transformer = new QuestionnaireJsonTransformer();
        JSONObject jsonObject = transformer.createJson();
        PrintWriter writer = resp.getWriter();
      //  writer.
        resp.setContentType("text/html");
        resp.setStatus(200);
        
        request.setAttribute("structure", jsonObject.toString());
        request.getRequestDispatcher("index.jsp").forward(request, resp);
    }
    
}
