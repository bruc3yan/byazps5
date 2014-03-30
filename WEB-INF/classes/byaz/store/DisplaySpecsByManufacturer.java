/*
 * CS 133: Problem set 5 - DisplaySpecsByManufacturer.java
 * 
 * Author: Bruce Yan, Angela Zhou
 * E-mail: byan@hmc.edu, azhou@hmc.edu
 * 
 * Displays the specs by input manufacturer
 * 
 */

package byaz.store;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import coreservlets.*;
import java.sql.*;

public class DisplaySpecsByManufacturer extends HttpServlet {


    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException {

	// Set up the response
	response.setContentType("text/html");

	// Begin composing the response
	PrintWriter out = response.getWriter();
	String docType =
	    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
	String title = "Display specifications by Manufacturer";
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
      
	    // Compose the SQL query

      	// ******************* TABLE FOR LAPTOPS ******************* //
      	out.println(" <p>\n"+ "Qualified laptops are: <br />");
      	String query1 = "SELECT * FROM Laptops lp, Products pd WHERE (lp.model = pd.model AND " + 
      		"maker LIKE " + "\"" + request.getParameter("maker") + "\""  +")";

	    // Send query to database and receive result
	    ResultSet resultSet1 = statement.executeQuery(query1);
      	
		// Set up the table
		out.println("<table>");
		out.println("<tr><th>Model</th><th>Speed</th><th>RAM</th><th>HD</th><th>Screen</th><th>Price</th></tr>");

	    // Compose the rows.
	    while (resultSet1.next()) {
		out.println("<tr>");
		out.println("<td>" + resultSet1.getString("model") + "</td>");
		out.println("<td>" + resultSet1.getInt("speed") + "GHz" + "</td>");
		out.println("<td>" + resultSet1.getDouble("ram") + "GB" + "</td>");
		out.println("<td>" + resultSet1.getDouble("hd") + "GB" + "</td>");
		out.println("<td>" + resultSet1.getDouble("screen") + "inches" + "</td>");
		out.println("<td>" + "$" + resultSet1.getDouble("price") + "</td>");
		out.println("</tr>");
	    }

	    // End of the table of results
		out.println("</table>");
		///////////////////////////////////////////////////////////////////

		
      	// ******************* TABLE FOR DESKTOPS ******************* //
      	out.println(" <p>\n"+ "Qualified desktops are: <br />");
      	String query2 = "SELECT * FROM PCs pc, Products pd WHERE (pc.model = pd.model AND " + 
      		"maker LIKE " + "\"" + request.getParameter("maker") + "\"" + ")";

	    // Send query to database and receive result
	    ResultSet resultSet2 = statement.executeQuery(query2);
      	
		// Set up the table
		out.println("<table>");
		out.println("<tr><th>Model</th><th>Speed</th><th>RAM</th><th>HD</th><th>Price</th></tr>");

	    // Compose the rows.
	    while (resultSet2.next()) {
		out.println("<tr>");
		out.println("<td>" + resultSet2.getString("model") + "</td>");
		out.println("<td>" + resultSet2.getInt("speed") + "GHz" + "</td>");
		out.println("<td>" + resultSet2.getDouble("ram") + "GB" + "</td>");
		out.println("<td>" + resultSet2.getDouble("hd") + "GB" + "</td>");
		out.println("<td>" + "$" + resultSet2.getDouble("price") + "</td>");
		out.println("</tr>");
	    }

	    // End of the table of results
		out.println("</table>");
		///////////////////////////////////////////////////////////////////


      	// ******************* TABLE FOR PRINTERS ******************* //
      	out.println(" <p>\n"+ "Qualified printers are: <br />");
      	String query3 = "SELECT * FROM Printers ps, Products pd WHERE (ps.model = pd.model AND " + 
      		"maker LIKE " + "\"" + request.getParameter("maker") + "\"" + ")";

	    // Send query to database and receive result
	    ResultSet resultSet3 = statement.executeQuery(query3);
      	
		// Set up the table
		out.println("<table>");
		out.println("<tr><th>Model</th><th>Color</th><th>Type</th><th>Price</th></tr>");

	    // Compose the rows.
	    while (resultSet3.next()) {
		out.println("<tr>");
		out.println("<td>" + resultSet3.getString("model") + "</td>");
		out.println("<td>" + resultSet3.getString("color")  + "</td>");
		out.println("<td>" + resultSet3.getString("printertype")  + "</td>");
		out.println("<td>" + "$" + resultSet3.getDouble("price") + "</td>");
		out.println("</tr>");
	    }

	    // End of the table of results
		out.println("</table>");
		///////////////////////////////////////////////////////////////////

		
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
