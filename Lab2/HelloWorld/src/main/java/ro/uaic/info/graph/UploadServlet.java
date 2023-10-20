package ro.uaic.info.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.GraphMeasurer;
import org.jgrapht.alg.shortestpath.JohnsonShortestPaths;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger( UploadServlet.class.getName() );
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Part filePart = request.getPart("file");
            InputStream fileContent = filePart.getInputStream();

            // Parse the DIMACS file and calculate properties
            List<String> dimacsLines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    dimacsLines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            var graph = createGraphFromDIMACS(dimacsLines);

            request.setAttribute("order", graph.vertexSet().size());
            request.setAttribute("size", graph.edgeSet().size());

            int connectedComponents = new ConnectivityInspector<>(graph).connectedSets().size();
            request.setAttribute("connectedComponents", connectedComponents);

            // Calculate the minimum and maximum degree
            int minDegree = Integer.MAX_VALUE;
            int maxDegree = Integer.MIN_VALUE;
            DepthFirstIterator<Integer, DefaultEdge> iterator = new DepthFirstIterator<>(graph);
            for (Integer vertex : graph.vertexSet()) {
                int degree = graph.degreeOf(vertex);
                minDegree = Math.min(minDegree, degree);
                maxDegree = Math.max(maxDegree, degree);
            }

            request.setAttribute("minDegree", minDegree);
            request.setAttribute("maxDegree", maxDegree);

            GraphMeasurer g=new GraphMeasurer(graph,new JohnsonShortestPaths(graph));
            request.setAttribute("diameter",  g.getDiameter());
            request.setAttribute("radius",  g.getRadius());

            request.getRequestDispatcher("/result.jsp").forward(request, response);

        } catch (Exception e) {
            logger.severe("An error occurred in MyServlet: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }



    public static Graph<Integer, DefaultEdge> createGraphFromDIMACS(List<String> dimacsLines) {

        Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        for (String line : dimacsLines) {
            if (line.startsWith("p ")) {

                String[] parts = line.split("\\s+");
                int numVertices = Integer.parseInt(parts[2]);

                // Add vertices to the graph
                for (int i = 1; i <= numVertices; i++) {
                    graph.addVertex(i);
                }
            } else if (line.startsWith("e ")) {

                String[] parts = line.split("\\s+");
                int sourceVertex = Integer.parseInt(parts[1]);
                int targetVertex = Integer.parseInt(parts[2]);

                graph.addEdge(sourceVertex, targetVertex);
            }
        }
        return graph;
    }


}