package ro.uaic.info.graph;

import java.io.*;
import java.util.Random;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/InputGraphPage")
public class GraphInputServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger( GraphInputServlet.class.getName() );

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Forward the request to the HTML page
        logRequestInfo(request);
        logger.severe("WAA");

        request.getRequestDispatcher("/graph_input.jsp").forward(request, response);
    }

    private void logRequestInfo(HttpServletRequest request) {
        String httpMethod = request.getMethod();
        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String clientLanguage = request.getHeader("Accept-Language");
        String parameterValue = request.getParameter("your_parameter_name");

        logger.severe("HTTP Method: " + httpMethod);
        logger.severe("IP Address: " + ipAddress);
        logger.severe("User-Agent: " + userAgent);
        logger.severe("Client Language(s): " + clientLanguage);
        logger.severe("Request Parameter: " + parameterValue);
    }
}