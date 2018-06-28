package hmi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hmi.views.CreatePageView;
import hmi.views.ManageRecipesView;
import model.CookBook;

public class CancelPageCreationController implements ActionListener {

	private CreatePageView view;
	private CookBook model;
	
	public CancelPageCreationController(CreatePageView view, CookBook model) {
		
		this.view = view;
		this.model = model;
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// Close the current view
		this.view.setVisible(false);
		this.view.dispose();
		
		// Open the next view
		new ManageRecipesView(this.model);

	}

}
