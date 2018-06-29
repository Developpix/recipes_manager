package hmi.controllers;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import hmi.views.ManageRecipesView;

public class SelectIngredientController implements ListSelectionListener {

	private ManageRecipesView view;

	public SelectIngredientController(ManageRecipesView view) {
		
		this.view = view;
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		// Update the information about the ingredient selected
		this.view.updateIngredientInformation();
		
	}
	

}
