package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;

/** Servlet that responds with the current date. */
@WebServlet("/experience")
public class ExperienceServlet extends HttpServlet {

    ArrayList<String> test = new ArrayList<>(Arrays.asList("{\"name\": \"John\", \"birthYear\": 1815}", "{\"name\": \"Bob\", \"birthYear\": 1815}", "{\"name\": \"Mary\", \"birthYear\": 1815}"));

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;");
    // response.getWriter().println("Experience");
    response.getWriter().println(test);
    // response.sendRedirect("https://google.com");

  }
}