package hmi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hmi.views.AddIngredientToPageView;
import hmi.views.ManageRecipesView;
import model.CookBook;
import model.database.Bean.Ingredient;

public class ValidateAddingIngredientController implements ActionListener {

	private AddIngredientToPageView view;
	private CookBook model;
	
	
	public ValidateAddingIngredientController(AddIngredientToPageView view, CookBook model) {
		
		this.view = view;
		this.model = model;
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			
			// Add the ingredient to the recipe (the page)
			this.model.getPageEdited().addIngredient(this.view.getIngredientSelected(),
					this.view.getQuantity(),
					this.view.getUnit());
					
			// Close the curent view
			this.view.setVisible(false);
			this.view.dispose();
			
			// Open the next view
			new ManageRecipesView(this.model);
		
		} catch (NumberFormatException e) {
			
			this.view.printMessage("Quantity not a number", "The value of the quantity is not a number");
			
		}
		
	}

}
