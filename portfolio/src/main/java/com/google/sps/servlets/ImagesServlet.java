package com.google.sps.servlets;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Bucket;

// Takes an image submitted by the user and uploads it to Cloud Storage, and then displays it as HTML in the response
@WebServlet("/upload")
@MultipartConfig
public class ImagesServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the file chosen by the user.
        Part filePart = request.getPart("image");
        String fileName = filePart.getSubmittedFileName();
        InputStream fileInputStream = filePart.getInputStream();

        // Upload the file and get its URL
        String uploadedFileUrl = uploadToCloudStorage(fileName, fileInputStream);

        response.sendRedirect("/upload");
    }

    // Uploads a file to Cloud Storage and returns the uploaded file's URL
    private static String uploadToCloudStorage(String fileName, InputStream fileInputStream) {
        String projectId = "mwong-sps-summer22";
        String bucketName = "mwong-sps-summer22.appspot.com";
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        // Upload the file to Cloud Storage.
        Blob blob = storage.create(blobInfo, fileInputStream);

        // Return the uploaded file's URL.
        return blob.getMediaLink();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // List all of the uploaded files.
        String projectId = "mwong-sps-summer22";
        String bucketName = "mwong-sps-summer22.appspot.com";
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        Bucket bucket = storage.get(bucketName);
        Page<Blob> blobs = bucket.list();

        // Output <img> elements as HTML.
        response.setContentType("text/html;");
        for (Blob blob : blobs.iterateAll()) {
            String imgTag = String.format("<img width=\"500\" src=\"%s\" />", blob.getMediaLink());
            response.getWriter().println(imgTag);
            response.getWriter().println("<br>");
        }
    }
}
