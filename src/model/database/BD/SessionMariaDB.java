/**
 * Class to create a session to a MariaDB database
 * @author Developpix
 * @version 0.1
 */

package model.database.BD;

import java.sql.*;

public class SessionMariaDB implements SessionDatabase {

	private Connection connection;
	
	public SessionMariaDB(String host, int port, String databaseName, String user, String passwd) {
		
		try {
			
			DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
			connection = DriverManager.getConnection("jdbc:mariadb://"+host+":"+port+"/"+databaseName, user, passwd);
			
			System.out.println("connexion réussi");
			
		} catch(SQLException e) {
			
			System.out.println("connexion échouée");
			
		}
		
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
	 */
	public void closeConnection() {
		
		try {
			
			this.connection.close();
			System.out.println("Déconnexion réussi");
			
		} catch (SQLException erreur) {

			System.out.println("Déconnexion échoué");
			System.out.println(erreur.getErrorCode() + " " + erreur.getMessage());
			
		}
		
	}
	
}
