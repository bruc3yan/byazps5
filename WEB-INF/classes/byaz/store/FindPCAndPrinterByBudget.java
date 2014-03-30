/*
 * CS 133: Problem set 5 - FindPCAndPrinterByBudget.java
 * 
 * Author: Bruce Yan, Angela Zhou
 * E-mail: byan@hmc.edu, azhou@hmc.edu
 * 
 * Finds all combinations of the PC and Printer by an input budget
 * 
 */

package byaz.store;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import coreservlets.*;
import java.sql.*;

public class FindPCAndPrinterByBudget extends HttpServlet {


    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException {

	// Set up the response
	response.setContentType("text/html");

	// Begin composing the response
	PrintWriter out = response.getWriter();
	String docType =
	    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
	String title = "Find PC and Printer by Budget";
	out.println(docType +
		    "<html>\n" +
		    "<head><title>" + title + "</title></head>\n" +
		    "<link rel=\"stylesheet\" type=\"text/css\" media=\"all\" href=\"style.css\" />\n" +
		    "<body>\n" +
		    "<h1>" + title + "</h1>");

	// Initiate a DB connection
	try {
	    // Establish network connection to database
	    Connection connection = DB.openConnection();
	    
	    // Create a statement for executing the query
	    Statement statement = connection.createStatement();
      
      	out.println(" <p>\n"+ "Qualified PCs and Printers that meet the following budget: <br />" + 
      		"Budget: $" + request.getParameter("budget") + "<br />" +
      		"Minimum PC speed: " + request.getParameter("minspeed") + "GHz <br />");
      	String query = "SELECT pr.model, pc.model FROM Products pd1, Products pd2, Printers pr, PCs pc " +
      		"WHERE pd1.model = pr.model AND pd2.model = pc.model AND (pc.price + pr.price) < " + 
      		request.getParameter("budget") + " AND (pc.speed >" + request.getParameter("minspeed") + ") ;";


	    // Send query to database and receive result
	    ResultSet resultSet = statement.executeQuery(query);
      	
		// Set up the table
		out.println("<table>");
		out.println("<tr><th>Printer Model</th><th>PC Model</th></tr>");

	    // Compose the rows.
	    while (resultSet.next()) {
		out.println("<tr>");
		out.println("<td>" + resultSet.getString("pr.model") + "</td>");
		out.println("<td>" + resultSet.getString("pc.model") + "</td>");
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
