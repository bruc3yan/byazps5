// Servlet handling http://localhost:8080/ski/index.html

package byaz.store;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import coreservlets.*;

public class FindPriceAndPrint extends HttpServlet {

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException {

	PrintWriter output;
        //	String fileName = "C:\\\\servlets\\alee\\ski\\test.out";
	//String fileName = "//Users//alee//servlets//alee//ski//test2.out";
	String fileName = "//Users//bruce//www//apache-tomcat-7.0.52//webapps//byazps5//WEB-INF//classes//alee//ski//jsptest.in";

	output = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
	output.println(request.getParameter("name"));
	output.print(request.getParameter("depth") + " ");
	output.println(request.getParameter("open"));
	output.println(request.getParameter("condition"));

	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	String title = "Your report for " + 
	    request.getParameter("name") + 
	    " accepted";
	out.println(ServletUtilities.headWithTitle(title) +
		    "<BODY BGCOLOR=\"#FDF5E6\">\n" +
		    "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
		    "<UL>\n" +
		    "  <LI><B>Name of Resort</B>: "
		    + request.getParameter("name") + "\n" +
		    "  <LI><B>Snow Depth</B>: "
		    + request.getParameter("depth") + "\n" +
		    "  <LI><B>Open/Closed</B>: "
		    + request.getParameter("open") + "\n" +
		    "  <LI><B>Condition</B>: "
		    + request.getParameter("condition") + "\n" +
		    "</UL>\n" +
		    "</BODY></HTML>");
	out.flush();
	out.close();
	output.flush();
	output.close();
    }
}
