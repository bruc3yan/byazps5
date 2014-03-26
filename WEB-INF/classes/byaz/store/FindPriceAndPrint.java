// Servlet handling http://localhost:8080/ski/index.html

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
	String title = "Online Store Order Page";
	out.println(docType +
		    "<HTML>\n" +
		    "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
		    "<BODY>\n" +
		    "<H1 ALIGN=\"CENTER\">" + title + "</H1>");
	out.println("<P>\n" +
		    "Please choose from among these items:");
	out.println("<P><CENTER><TABLE>");
	out.println("<TR><TH>Item</TH><TH>Price</TH></TR>");
	



//Make a connection
	try {

	    // Establish network connection to database
	    Connection connection = DB.openConnection();
	    
	    // Create a statement for executing the query
	    Statement statement = connection.createStatement();
      
	    // Compose the SQL query
	    String query = "SELECT maker, model, speed, price FROM PCs pc Products pd, WHERE pc.model = pd.model, GROUP BY price";
      
	    // Send query to database and receive result
	    ResultSet resultSet = statement.executeQuery(query);
      
	    // Compose the rows.
	    while (resultSet.next()) {
		out.println("<TR>");
		out.println("<TD>" + resultSet.getString("maker") + "</TD>");
		out.println("<TD>" + resultSet.getInt("model") + "</TD>");
		out.println("<TD>" + resultSet.getDouble("speed") + "GHz" + "</TD>");
		out.println("<TD>" + "$" + resultSet.getDouble("price") + "</TD>");
		out.println("</TR>");
	    }
	    connection.close();
	}
	catch (SQLException sqle) {
	    throw new RuntimeException("Error accessing database: " + sqle);
	}


	out.println("</TABLE></CENTER>");
	out.println("<P><A HREF=\"cart\">View shopping cart</A>");
	out.println("</BODY></HTML>");
    



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
