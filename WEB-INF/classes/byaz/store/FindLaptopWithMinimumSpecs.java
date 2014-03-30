/*
 * CS 133: Problem set 5 - FindLaptopWithMinimumSpecs.java
 * 
 * Author: Bruce Yan, Angela Zhou
 * E-mail: byan@hmc.edu, azhou@hmc.edu
 * 
 * Finds the laptop that matches the minimum specs
 * 
 */

package byaz.store;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import coreservlets.*;
import java.sql.*;

public class FindLaptopWithMinimumSpecs extends HttpServlet {


    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException {

	// Set up the response
	response.setContentType("text/html");

	// Begin composing the response
	PrintWriter out = response.getWriter();
	String docType =
	    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
	String title = "Find Laptop by Minimum Specs";
	out.println(docType +
		    "<html>\n" +
		    "<head><title>" + title + "</title></head>\n" +
		    "<link rel=\"stylesheet\" type=\"text/css\" media=\"all\" href=\"style.css\" />\n" +
		    "<body>\n" +
		    "<h1>" + title + "</h1>");
	out.println("<p>\n" + "PCs that matched your minimum requirement: <br />Speed: " + request.getParameter("speed") + "<br />RAM: " + request.getParameter("ram") + "<br />HDD size: " + request.getParameter("hds") + "<br />Screen: " + request.getParameter("screen") + "</p>\n");

	// Initiate a DB connection
	try {
	    // Establish network connection to database
	    Connection connection = DB.openConnection();
	    
	    // Create a statement for executing the query
	    Statement statement = connection.createStatement();
      
	    // Compose the SQL query
      	String query = "SELECT lp.model, speed, ram, hd, screen, price FROM Laptops lp, Products pd WHERE (lp.model = pd.model AND " + 
      		"speed >= " + request.getParameter("speed") + " AND " +
      		"ram >= " + request.getParameter("ram") + " AND " +
      		"hd >= " + request.getParameter("hds") + " AND " +
      		"screen >= " + request.getParameter("screen") + ")";

	    // Send query to database and receive result
	    ResultSet resultSet = statement.executeQuery(query);
      	
		// Set up the table
		out.println("<table>");
		out.println("<tr><th>Model</th><th>Speed</th><th>RAM</th><th>HD</th><th>Screen</th><th>Price</th></tr>");

	    // Compose the rows.
	    while (resultSet.next()) {
		out.println("<tr>");
		out.println("<td>" + resultSet.getString("lp.model") + "</td>");
		out.println("<td>" + resultSet.getInt("speed") + "GHz" + "</td>");
		out.println("<td>" + resultSet.getDouble("ram") + "GB" + "</td>");
		out.println("<td>" + resultSet.getDouble("hd") + "GB" + "</td>");
		out.println("<td>" + resultSet.getDouble("screen") + "inches" + "</td>");
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
