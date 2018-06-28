package hmi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hmi.views.CreatePageView;
import hmi.views.ManageRecipesView;
import model.CookBook;

public class CreatePageController implements ActionListener {

	private CreatePageView view;
	private CookBook model;
	
	public CreatePageController(CreatePageView view, CookBook model) {
		
		this.view = view;
		this.model = model;
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(!this.view.getRecipeName().isEmpty()) {
		// If the name is not empty
			
			// Close the current view
			this.view.setVisible(false);
			this.view.dispose();
			
			// Create the page
			this.model.createPage(this.view.getRecipeName());
			
			// Open the next view
			new ManageRecipesView(this.model);
			
		} else {
		
			// Print error message
			this.view.printMessage("Name empty", "The name of the recipe should be not empty !");
			
		}

	}

}
