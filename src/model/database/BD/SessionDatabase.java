/**
 * Interface to describe a session to a database
 * @author Developpix
 * @version 0.1
 */

package model.database.BD;

import java.sql.*;

public interface SessionDatabase {
	
	/**
	 * Method to get the connection to the database with the session
	 * @return a connection to the database
	 */
	public Connection getConnection();
	
	/**
	 * Method to close the connection
	 */
	public void closeConnection();
	
}
