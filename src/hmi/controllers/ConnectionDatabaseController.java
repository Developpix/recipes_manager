package hmi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import hmi.views.WelcomeView;
import model.CookBook;
import model.database.BD.SessionDatabase;
import model.database.BD.SessionMariaDB;
import model.database.BD.SessionMySQL;

public class ConnectionDatabaseController implements ActionListener {

	private WelcomeView view;
	
	/**
	 * Constructor for ConnectionDatabaseController
	 * @param view the view associated to the controller
	 */
	public ConnectionDatabaseController(WelcomeView view) {
		
		this.view = view;
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			
			// Get the type of the database
			String databaseType = this.view.getTypeOfDatabaseSelected();
			
			// Create a variable to store the session establish with the database
			SessionDatabase sessionDatabase = null;
			
			if (databaseType.equals("MySQL")) {
				
				sessionDatabase = new SessionMySQL(this.view.getAddressOfDatabase(),
						this.view.getPortOfDatabase(),
						this.view.getNameOfDatabase(),
						this.view.getUserName(),
						this.view.getUserPassword());
				
			} else if (databaseType.equals("MariaDB")) {
	
				sessionDatabase = new SessionMariaDB(this.view.getAddressOfDatabase(),
						this.view.getPortOfDatabase(),
						this.view.getNameOfDatabase(),
						this.view.getUserName(),
						this.view.getUserPassword());
				
			}
			
			// Create the model
			CookBook cookBook = new CookBook(sessionDatabase);
			// Load data for the model
			cookBook.load();
		
		} catch (SQLException e) {
		
			this.view.printMessage("Connection error", "Connection to the database can't be establish !");
			
		} catch (NumberFormatException e) {
			
			this.view.printMessage("Invalid number for the port", "The number of the port is not a number !");
			
		}

	}

}
