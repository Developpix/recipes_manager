package hmi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hmi.views.AddIngredientToPageView;
import hmi.views.CreateIngredientView;
import model.CookBook;

public class ValidateIngredientCreationController implements ActionListener {

	private CreateIngredientView view;
	private CookBook model;
	
	public ValidateIngredientCreationController(CreateIngredientView view, CookBook model) {
		
		this.view = view;
		this.model = model;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// If a name is enter
		if(!this.view.getName().isEmpty()) {
			
			// Close current view
			this.view.setVisible(false);
			this.view.dispose();
			
			// Create the ingredient
			this.model.createIngredient(this.view.getName());
			
			// Open the next view
			new AddIngredientToPageView(this.model);
			
		} else {
			
			this.view.printMessage("Name empty", "The name of the ingredient should be not empty");

		}
		
	}

}
