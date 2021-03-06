/*
 * CS 133: Problem set 5 - FindPriceAndPrint.java
 * 
 * Author: Bruce Yan, Angela Zhou
 * E-mail: byan@hmc.edu, azhou@hmc.edu
 * 
 * Finds the PC that matches the input price the closest
 * 
 */

package byaz.store;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import coreservlets.*;
import java.sql.*;

public class FindPriceAndPrint extends HttpServlet {


    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException {

	// Set up the response
	response.setContentType("text/html");

	// Begin composing the response
	PrintWriter out = response.getWriter();
	String docType =
	    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
	String title = "PCs Price Check";
	out.println(docType +
		    "<html>\n" +
		    "<head><title>" + title + "</title></head>\n" +
		    "<link rel=\"stylesheet\" type=\"text/css\" media=\"all\" href=\"style.css\" />\n" +
		    "<body>\n" +
		    "<h1>" + title + "</h1>");
	out.println("<p>\n" + "PCs that matched your price of: $" + request.getParameter("price") +  "</p>\n");

	// Initiate a DB connection
	try {
	    // Establish network connection to database
	    Connection connection = DB.openConnection();
	    
	    // Create a statement for executing the query
	    Statement statement = connection.createStatement();
      
	    // Compose the SQL query
      	String query = "(SELECT maker, pd.model, speed, price FROM PCs pc, Products pd WHERE pc.model = pd.model ORDER BY ABS(price-" + request.getParameter("price") + ") ASC LIMIT 1)";

	    // Send query to database and receive result
	    ResultSet resultSet = statement.executeQuery(query);
      	
		// Set up the table
		out.println("<table>");
		out.println("<tr><th>Maker</th><th>Model</th><th>Speed</th><th>Price</th></tr>");

	    // Compose the rows.
	    while (resultSet.next()) {
		out.println("<tr>");
		out.println("<td>" + resultSet.getString("maker") + "</td>");
		out.println("<td>" + resultSet.getInt("pd.model") + "</td>");
		out.println("<td>" + resultSet.getDouble("speed") + "GHz" + "</td>");
		out.println("<td>" + "$" + resultSet.getDouble("price") + "</td>");
		out.println("</tr>");
	    }

	    // End of the table of results
		out.println("</table>");

	    // Close DB connection
	    connection.close();
	}
	catch (SQLException sqle) {
	    throw new RuntimeException("Error accessing database: " + sqle);
	}

	// Navigation back to the home page
	out.println("<p><a href=\"index.html\">Return to previous page</a></p>");
	out.println("</body></html>");
    
    // Housekeeping
	out.flush();
	out.close();
    }
}
