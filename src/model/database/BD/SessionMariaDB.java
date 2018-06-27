/**
 * Class to create a session to a MariaDB database
 * @author Developpix
 * @version 0.1
 */

package model.database.BD;

import java.sql.*;

public class SessionMariaDB implements SessionDatabase {

	private Connection connection;
	
	public SessionMariaDB(String host, int port, String databaseName, String user, String passwd) throws SQLException {
		
		DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
		connection = DriverManager.getConnection("jdbc:mariadb://"+host+":"+port+"/"+databaseName, user, passwd);
		
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
