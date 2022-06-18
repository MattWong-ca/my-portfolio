package com.google.sps.servlets;

import java.io.IOException;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;

// Servlet that responds with JSON array
@WebServlet("/JSONarray")
public class JSONServlet extends HttpServlet {

    ArrayList<String> jsonArray = new ArrayList<>(Arrays.asList(
        "{\"name\": \"Kobe Bryant\", \"jerseyNumber\": 8}",
        "{\"name\": \"Michael Jordan\", \"jerseyNumber\": 23}",
            "{\"name\": \"Steph Curry\", \"jerseyNumber\": 30}"));
        
    ArrayList<String> jsonArray2 = new ArrayList<>(Arrays.asList(
            "1",
            "2",
            "3"));
    String json = convertToJsonUsingGson(jsonArray2);

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //   response.setContentType("text/html;");
    //   response.getWriter().println(jsonArray);

    response.setContentType("application/json;");
    response.getWriter().println(json);

      // response.sendRedirect("https://google.com");

  }
  
  private String convertToJsonUsingGson(ArrayList<String> testArray) {
      Gson gson = new Gson();
      String json = gson.toJson(testArray);
      return json;
  }
}