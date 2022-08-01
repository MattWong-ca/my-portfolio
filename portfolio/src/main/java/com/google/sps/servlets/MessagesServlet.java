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

import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.sps.servlets.Message;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/messages")
public class MessagesServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the value entered in the form.

        String textValue = request.getParameter("text-input");
        long timestamp = System.currentTimeMillis();

        // Print the value so you can see it in the server logs.
        System.out.println("You submitted: " + textValue);
        System.out.println(timestamp);

        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("Message");
        FullEntity taskEntity = Entity.newBuilder(keyFactory.newKey())
                .set("textValue", textValue)
                .set("timestamp", timestamp)
                .build();
        datastore.put(taskEntity);

        // Write the value to the response so the user can see it
        response.setContentType("text/html;");
        response.getWriter().println("You submitted: " + textValue);
        response.getWriter().println(timestamp);

        response.sendRedirect("/messages-list.html");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Message").setOrderBy(OrderBy.desc("timestamp"))
                .build();
        QueryResults<Entity> results = datastore.run(query);

        List<Message> formResponses = new ArrayList<>();
        while (results.hasNext()) {
            Entity entity = results.next();

            long id = entity.getKey().getId();
            String textValue = entity.getString("textValue");
            long timestamp = entity.getLong("timestamp");

            Message oneMessage = new Message(id, textValue, timestamp);
            formResponses.add(oneMessage);
        }
        Gson gson = new Gson();
        response.setContentType("application/json;");
        response.getWriter().println(gson.toJson(formResponses));
    }
}