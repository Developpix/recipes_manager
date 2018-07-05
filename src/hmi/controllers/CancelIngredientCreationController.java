package hmi.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hmi.views.AddIngredientToPageView;
import hmi.views.CreateIngredientView;
import model.CookBook;

public class CancelIngredientCreationController implements ActionListener {

	private CreateIngredientView view;
	private CookBook model;
	
	public CancelIngredientCreationController(CreateIngredientView view, CookBook model) {
		
		this.view = view;
		this.model = model;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// Close current view
		this.view.setVisible(false);
		this.view.dispose();
		
		// Open the next view
		new AddIngredientToPageView(this.model);
		
	}

}
