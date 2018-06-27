package hmi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hmi.views.WelcomeView;

public class ResetDatabaseInformationController implements ActionListener {

	private WelcomeView view;
	
	/**
	 * Constructor for the ResetDatabaseInformationController
	 * @param view the view associated to the controller
	 */
	public ResetDatabaseInformationController(WelcomeView view) {
		
		this.view = view;
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		this.view.resetInformationsDatabase();

	}

}
