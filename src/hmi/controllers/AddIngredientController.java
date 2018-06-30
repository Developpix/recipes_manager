package hmi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hmi.views.AddIngredientToPageView;
import hmi.views.ManageRecipesView;
import model.CookBook;

public class AddIngredientController implements ActionListener {

	private ManageRecipesView view;
	private CookBook model;
	
	public AddIngredientController(ManageRecipesView view, CookBook model) {
		
		this.view = view;
		this.model = model;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(this.view.getPageSelected() != null) {
			// Close the current window
			this.view.setVisible(false);
			this.view.dispose();
			
			// Set the page edited
			this.model.editPage(this.view.getPageSelected());
			
			// Open the next window
			new AddIngredientToPageView(this.model);
		}
		
	}

}
