package musicpep.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionManager {
	
	// ?serverTimezone=EST5EDT <-- add to end of URL if trouble connecting and on Mac/Linux
	private static final String URL = "jdbc:mysql://localhost:3306/musictracker";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	private static Connection connection = null;
	

	private static void makeConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		if (connection == null) {
			makeConnection();
		}

		return connection;
	}

}

