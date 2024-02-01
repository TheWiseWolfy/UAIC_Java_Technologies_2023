package com.unwise.lab7;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@WebServlet("/graph-endpoint")
public class GraphServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set content type to plain text
        response.setContentType("text/plain");
        // Write simple text response
        response.getWriter().write("This is a GET request to the graph endpoint");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        // Read JSON array from request body
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }

        // Send response
        response.getWriter().write("Received JSON array: " + requestBody.toString());

        ObjectMapper mapper = new ObjectMapper();

        try {
            List<Integer> list = mapper.readValue(requestBody.toString(), new TypeReference<List<Integer>>() {});
            System.out.println(list);   // prints: [14, 3, 11]
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}