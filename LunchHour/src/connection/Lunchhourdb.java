package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Lunchhourdb {

private static Connection con;

// Pristupa drajveru u JAR fajlu
private static Connection createConnection() {
	try {
	    System.out.println("Loading driver...");
	    Class.forName("com.mysql.jdbc.Driver");
	    System.out.println("Driver loaded!");
	  } catch (ClassNotFoundException e) {
	    throw new RuntimeException("Cannot find the driver in the classpath!", e);
	  }
	
	      try {
	      Class.forName("com.mysql.jdbc.Driver");
	      String dbName = "Cafeteria";
	      String userName = "root";
	      String password = "root";
	      String hostname = "lunchhourdb.codmmpb86f3e.us-east-1.rds.amazonaws.com";
	      String port = "3306";
	      String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
	    		    port + "/" + dbName + "?user=" + userName + "&password=" + password;
	      System.out.println(jdbcUrl);
	      con = DriverManager.getConnection(jdbcUrl,userName,password);
	      System.out.println("Success on conncetion");
	      return con;
	    }
	    catch (ClassNotFoundException e) {System.out.print("Driver Connection problem"); }
	    catch (SQLException ex) {
	        // Handle any errors
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
	      }
    return null;
}

public static Connection get() {
    if (con == null) {
        con = createConnection();
    }
    return con;
}
}