package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Do later: import jSoup to sanitize user input

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the value entered in the form.
        String textValue = request.getParameter("text-input");

        // Print the value so you can see it in the server logs.
        System.out.println("You submitted: " + textValue);

        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("Message");
        FullEntity taskEntity = Entity.newBuilder(keyFactory.newKey())
                .set("textValue", textValue)
                .build();
        datastore.put(taskEntity);

        // Write the value to the response so the user can see it
        response.setContentType("text/html;");
        response.getWriter().println("You submitted: " + textValue);
    }
}