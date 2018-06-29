package hmi.controllers;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import hmi.views.ManageRecipesView;

public class SelectPageController implements ListSelectionListener {

	private ManageRecipesView view;

	public SelectPageController(ManageRecipesView view) {
		
		this.view = view;
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		// Reload the ingredient list
		this.view.reloadIngredientsList();
		
	}
	

}
