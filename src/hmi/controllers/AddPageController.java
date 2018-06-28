package hmi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hmi.views.CreatePageView;
import hmi.views.ManageRecipesView;
import model.CookBook;

public class AddPageController implements ActionListener {

	private ManageRecipesView view;
	private CookBook model;
	
	public AddPageController(ManageRecipesView view, CookBook model) {
		
		this.view = view;
		this.model = model;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// Close the current window
		this.view.setVisible(false);
		this.view.dispose();
		
		// Open the next window
		new CreatePageView(this.model);
		
	}

}
