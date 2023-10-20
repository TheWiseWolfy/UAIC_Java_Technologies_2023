package ro.uaic.info.graph;

import java.io.*;
import java.util.Random;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/GraphGenerator")
public class GraphGeneratorServlet  extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String numVerticesStr = request.getParameter("numVertices");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Generated Tree</title></head>");
        out.println("<body>");

        if (numVerticesStr != null) {
            try {
                int numVertices = Integer.parseInt(numVerticesStr);

                // Generate a random tree's adjacency matrix
                int[][] adjacencyMatrix = generateRandomTree(numVertices);

                // Display the adjacency matrix as an HTML table
                out.println("<h2>Generated Tree</h2>");
                out.println("<table border='1'>");
                for (int i = 0; i < numVertices; i++) {
                    out.println("<tr>");
                    for (int j = 0; j < numVertices; j++) {
                        out.println("<td>" + adjacencyMatrix[i][j] + "</td>");
                    }
                    out.println("</tr>");
                }
                out.println("</table>");
            } catch (NumberFormatException e) {
                out.println("<p>Invalid input. Please enter a valid number of vertices.</p>");
            }
        } else {
            out.println("<p>Please enter the number of vertices in the form above.</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }

    // Method to generate a random tree's adjacency matrix
    private int[][] generateRandomTree(int numVertices) {
        int[][] adjacencyMatrix = new int[numVertices][numVertices];
        Random random = new Random();

        // Initialize the adjacency matrix
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }

        // Generate a random tree by adding edges
        for (int i = 1; i < numVertices; i++) {
            int parent = random.nextInt(i);
            adjacencyMatrix[parent][i] = 1;
            adjacencyMatrix[i][parent] = 1;
        }

        return adjacencyMatrix;
    }

}
