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

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Initialize ArrayList and convert its type to JSON
    ArrayList<String> jsonArray = new ArrayList<>(Arrays.asList(" JSON is cool", " GraphQL is cool", " Gson is cool"));
    response.setContentType("application/json;");

    // Uncomment + use this ArrayList once an athlete object is created (To be done)
    // ArrayList<String> jsonArray = new ArrayList<>(Arrays.asList(
    //         "{\"name\": \"Kobe Bryant\", \"jerseyNumber\": 8}",
    //         "{\"name\": \"Michael Jordan\", \"jerseyNumber\": 23}",
    //         "{\"name\": \"Steph Curry\", \"jerseyNumber\": 30}"));
    
    // Convert to JSON using Gson
    Gson gson = new Gson();
    String json = gson.toJson(jsonArray);

    // Send the JSON as the response
    response.getWriter().println(json);

    // To be done later: create a proper redirect
    // response.sendRedirect("link");
  }
}