package hmi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hmi.views.CreatePageView;
import hmi.views.ManageRecipesView;
import model.CookBook;

public class DeletePageController implements ActionListener {

	private ManageRecipesView view;
	private CookBook model;
	
	public DeletePageController(ManageRecipesView view, CookBook model) {
		
		this.view = view;
		this.model = model;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// Delete the page
		this.view.getPageSelected().delete();
		
		// Reload data from the database
		this.model.load();
		
		// Update the list in the view
		this.view.updatePagesList();
		
	}

}
