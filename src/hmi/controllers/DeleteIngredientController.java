package hmi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hmi.views.ManageRecipesView;

public class DeleteIngredientController implements ActionListener {

	private ManageRecipesView view;
	
	public DeleteIngredientController(ManageRecipesView view) {
		
		this.view = view;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(this.view.getIngredientSelected() != null) {
			
			// Remove the association with the ingredient
			this.view.getPageSelected().deleteIngredient(this.view.getIngredientSelected());
	
			// Update the JList of Ingredient
			this.view.updateIngredientsList();
			
		}
		
	}

}
