package hmi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hmi.views.AddIngredientToPageView;
import hmi.views.ManageRecipesView;
import model.CookBook;
import model.database.Bean.Ingredient;

public class CancelAddingIngredientController implements ActionListener {

	private AddIngredientToPageView view;
	private CookBook model;
	
	
	public CancelAddingIngredientController(AddIngredientToPageView view, CookBook model) {
		
		this.view = view;
		this.model = model;
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
				
		// Close the curent view
		this.view.setVisible(false);
		this.view.dispose();
		
		// Open the next view
		new ManageRecipesView(this.model);
		
	}

}
