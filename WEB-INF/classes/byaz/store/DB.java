/*
 * CS 133: Problem set 5 - DB.java
 * 
 * Author: Bruce Yan, Angela Zhou
 * E-mail: byan@hmc.edu, azhou@hmc.edu
 * 
 * Creates a connection to local mySQL DB using database "byazps5"
 * Note: Make sure to run ps5.sql first!
 * 
 */

package byaz.store;
import java.sql.*;


public class DB {


    // Load the database driver once
    static {
	try {
            System.out.println("About to load the driver");
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println("The driver is loaded");
	}
	catch (ClassNotFoundException cnfe) {
	    throw new RuntimeException(cnfe);
	}
    }



    // Creates connections to the database.
  
    public static Connection openConnection () throws SQLException {
	return DriverManager.getConnection
            ("jdbc:mysql://localhost:3306/byazps5", // db name
	     "grader",      // user name
	     "allowme");     // password

}

}
