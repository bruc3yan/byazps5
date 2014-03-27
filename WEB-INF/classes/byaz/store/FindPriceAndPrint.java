
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
		    "<head><title>" + title + "</TITLE></head>\n" +
		    "<link rel=\"stylesheet\" type=\"text/css\" media=\"all\" href=\"style.css\" />\n" +
		    "<body>\n" +
		    "<h1>" + title + "</h1>");
	out.println("<p>\n" +
		    "PCs that matched your price of: $" + request.getParameter("price") + 
			"</p>\n");

	// Set up the table
	out.println("<table>");
	out.println("<tr><th>Maker</th><th>Model</th><th>Speed</th><th>Price</th></tr>");
	

	//Make a connection
	try {
	    // Establish network connection to database
	    Connection connection = DB.openConnection();
	    
	    // Create a statement for executing the query
	    Statement statement = connection.createStatement();
      
	    // Compose the SQL query
	    // Original version
	    	//String query = "SELECT maker, pc.model, speed, price FROM PCs pc, Products pd WHERE pc.model = pd.model GROUP BY pc.price;";
      	// Complicate version that doesn't do much
			//(SELECT maker, pd.model, speed, price FROM PCs pc, Products pd WHERE pc.model = pd.model AND price >= 1501 ORDER BY price LIMIT 3) UNION ALL (SELECT maker, pd.model, speed, price FROM PCs pc, Products pd WHERE pc.model = pd.model AND price < 1501 ORDER BY price DESC LIMIT 3);
      	// !!! New version !!!
      	String query = "(SELECT maker, pd.model, speed, price FROM PCs pc, Products pd WHERE pc.model = pd.model ORDER BY ABS(price-" + request.getParameter("price") + ") ASC LIMIT 1)";

	    // Send query to database and receive result
	    ResultSet resultSet = statement.executeQuery(query);
      
	    // Compose the rows.
	    while (resultSet.next()) {
		out.println("<tr>");
		out.println("<td>" + resultSet.getString("maker") + "</td>");
		out.println("<td>" + resultSet.getInt("model") + "</td>");
		out.println("<td>" + resultSet.getDouble("speed") + "GHz" + "</td>");
		out.println("<td>" + "$" + resultSet.getDouble("price") + "</td>");
		out.println("</tr>");
	    }
	    connection.close();
	}
	catch (SQLException sqle) {
	    throw new RuntimeException("Error accessing database: " + sqle);
	}


	out.println("</table>");
	// End of the table of results

	out.println("<p><a href=\"index.html\">Return to previous page</a></p>");
	out.println("</body></html>");
    


	// PrintWriter output;
 //        //	String fileName = "C:\\\\servlets\\alee\\ski\\test.out";
	// //String fileName = "//Users//alee//servlets//alee//ski//test2.out";
	// String fileName = "//Users//bruce//www//apache-tomcat-7.0.52//webapps//byazps5//WEB-INF//classes//alee//ski//jsptest.in";

	// output = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
	// output.println(request.getParameter("name"));
	// output.print(request.getParameter("depth") + " ");
	// output.println(request.getParameter("open"));
	// output.println(request.getParameter("condition"));

	// response.setContentType("text/html");
	// PrintWriter out = response.getWriter();
	// String title = "Your report for " + 
	//     request.getParameter("name") + 
	//     " accepted";
	// out.println(ServletUtilities.headWithTitle(title) +
	// 	    "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	// 	    "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
	// 	    "<UL>\n" +
	// 	    "  <LI><B>Name of Resort</B>: "
	// 	    + request.getParameter("name") + "\n" +
	// 	    "  <LI><B>Snow Depth</B>: "
	// 	    + request.getParameter("depth") + "\n" +
	// 	    "  <LI><B>Open/Closed</B>: "
	// 	    + request.getParameter("open") + "\n" +
	// 	    "  <LI><B>Condition</B>: "
	// 	    + request.getParameter("condition") + "\n" +
	// 	    "</UL>\n" +
	// 	    "</BODY></HTML>");
	// out.flush();
	// out.close();
	// output.flush();
	// output.close();
    }
}
