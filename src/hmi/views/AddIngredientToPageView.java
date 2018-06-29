/**
 * Class to create a view to create a Page
 * @author Developpix
 * @version 0.1
 */

package hmi.views;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import model.CookBook;
import model.database.Bean.Ingredient;
import model.files.Page;

public class AddIngredientToPageView extends JFrame {

	private CookBook model;
	private Page page;
	
	private JList<Ingredient> listIngredients;
	private JList<Ingredient> listIngredientsInPage;
	
	public AddIngredientToPageView(CookBook model, Page page) {
		
		super("Add an ingredient - Recipes manager");
		this.model = model;
		this.page = page;
		
		/*
		 * Create a main panel
		 */
		JSplitPane mainPanel = new JSplitPane();
		this.setContentPane(mainPanel);
		
		/*
		 * Create a JList for the list of ingredients and add to the main panel
		 */
		this.listIngredients = new JList<>(this.model.getAllIngredientsNotAdded(this.page));
		mainPanel.setLeftComponent(new JScrollPane(this.listIngredients));

		/*
		 * Create a JList for the list of ingredients contained in the recipe
		 */
		this.listIngredientsInPage = new JList<>(this.page);
		mainPanel.setRightComponent(new JScrollPane(this.listIngredientsInPage));
		
		/*
		 * Set preferences for the window
		 */
		this.setPreferredSize(new Dimension(600, 300));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		
	}
	
	/**
	 * Method to show a window dialog to print an error message
	 * @param title the title of the window
	 * @param message the error message
	 */
	public void printMessage(String title, String message) {
		
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
		
	}
	
}
