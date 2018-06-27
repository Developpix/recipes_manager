/**
 * Class to create a session to a MySQL database
 * @author Developpix
 * @version 0.1
 */

package model.database.BD;

import java.sql.*;

public class SessionMySQL implements SessionDatabase {

	private Connection connection;
	
	public SessionMySQL(String host, int port, String databaseName, String user, String passwd) throws SQLException {
			
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+databaseName, user, passwd);
		
	}

	/**
	 * Method to get the connection to the database with the session
	 * @return a connection to the database
	 */
	public Connection getConnection() {
		
		return connection;
		
	}
	
	/**
	 * Method to close the connection
	 * @throws SQLException throw a SQLException when there is a problem with the database
	 */
	public void closeConnection() throws SQLException {
		
		this.connection.close();
		
	}
	
}
