package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet responsible for listing form responses
@WebServlet("/form-responses")
public class FormResponsesList extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Task").setOrderBy(OrderBy.desc("timestamp"))
                .build();
        QueryResults<Entity> results = datastore.run(query);

        List<Long> formResponses = new ArrayList<>();
        while (results.hasNext()) {
            Entity entity = results.next();

            // long id = entity.getKey().getId();
            // String title = entity.getString("text-input");
            long timestamp = entity.getLong("timestamp");

            // String response = new Response(id, title, timestamp);
            // String hi = title;
            formResponses.add(timestamp);
        }

        // Gson gson = new Gson();

        response.setContentType("text/html;");
        // response.getWriter().println(gson.toJson(formResponses));
        response.getWriter().println(formResponses);
    }
}

// private class Response( ) {

// }
