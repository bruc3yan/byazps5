/*
 * CS 133: Problem set 5 - InsertSpecsIntoDatabase.java
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

public class InsertSpecsIntoDatabase extends HttpServlet {


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

	// Initiate a DB connection
	try {
	    // Establish network connection to database
	    Connection connection = DB.openConnection();
	    
	    // Create a statement for executing the query
	    Statement statement = connection.createStatement();
      	
      	// out.println(" <p>\n"+ "Qualified PCs and Printers that meet the following budget: <br />" + 
      	// 	"Budget: $" + request.getParameter("budget") + "<br />" +
      	// 	"Minimum PC speed: " + request.getParameter("minspeed") + "GHz <br />");
      	
      	//Check if model number already exists
      	String query = "SELECT model FROM Products pc WHERE pc.model = " + request.getParameter("newmodel") + ";";

      	ResultSet resultSet = statement.executeQuery(query);


      	/*
      	// If model number does not exist in the database, then we add it
      	if (resultSet.getInt("model") == NULL) 
      	{	
      		// Set up the query and then execute it
      		String queryPC = "INSERT INTO PCs VALUES (" + request.getParameter("newmodel") + ", " + request.getParameter("newspeed") + ", " + request.getParameter("newram") + ", " + request.getParameter("newhd") + ", " + request.getParameter("newprice") + ") ;"
      		String queryProduct = "INSERT INTO Products VALUES ("+ request.getParameter("newmodel") +", "+ request.getParameter("newmaker") +", PC);"

      		// Execute it
      		String executePC = statement.executeQuery(queryPC);
      		String executeProduct = statement.executeQuery(queryProduct);
      	}
      	// Otherwise, output an error message
      	else {
      		out.println("<h4>This model number already exists in the database!</h4>");
      	}
      	*/

      	/*
	    // Send query to database and receive result
	    ResultSet resultSet = statement.executeQuery(query);
      	*/

	    /*
		// Set up the table
		out.println("<table>");
		out.println("<tr><th>PC Model</th></tr>");

	    // Compose the rows.
	    while (resultSet.next()) {
		out.println("<tr>");
		out.println("<td>" + resultSet.getString("pc.model") + "</td>");
		out.println("</tr>");
	    }

	    // End of the table of results
		out.println("</table>");
		*/
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
