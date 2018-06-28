/**
 * The view for manage the recipes
 * @author Developpix
 * @version 0.1
 */

package hmi.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import hmi.controllers.AddPageController;
import hmi.controllers.DeleteIngredientController;
import hmi.controllers.DeletePageController;
import model.CookBook;
import model.database.Bean.Ingredient;
import model.files.Page;

public class ManageRecipesView extends JFrame {

	private CookBook model;
	
	private JList<Page> listPages;
	private JList<Ingredient> listIngredients;
	
	private JButton addPage;
	private JButton delPage;
	private JButton addIngredient;
	private JButton delIngredient;
	
	public ManageRecipesView(CookBook model) {
		
		super("Manage recipes - Recipes manager");
		this.model = model;
		
		/*
		 * Create a main panel and set to the content pane of the JFrame
		 */
		JTabbedPane mainPanel = new JTabbedPane();
		this.setContentPane(mainPanel);
		
		/*
		 * Create a panel for the tab ingredients
		 */
		JPanel ingredientsPanel = new JPanel();
		// Add the tab
		mainPanel.add("Ingredients", ingredientsPanel);
		// Set a grid layout with 1 line and 3 columns to the panel
		ingredientsPanel.setLayout(new GridLayout(1, 3));
		
		/*
		 * Create a panel for manage the list of the pages
		 */
		JPanel panelManagePages = new JPanel();
		ingredientsPanel.add(panelManagePages);
		// Add a border layout to the panel
		panelManagePages.setLayout(new BorderLayout());
		// Add a JLabel, to show that here it's the list of pages, to the north
		panelManagePages.add(new JLabel("Pages", JLabel.CENTER), BorderLayout.NORTH);
		// Add a JList which contains the list of recipe, a Page represent a recipe in the CookBook
		this.listPages = new JList<>(this.model);
		// Add the JList in a scroll pane to the panel
		panelManagePages.add(new JScrollPane(listPages), BorderLayout.CENTER);
		/*
		 * Create a panel for the actions for manage the list of pages
		 */
		JPanel panelActionsManagePages = new JPanel();
		// Add the panel for actions in the bottom
		panelManagePages.add(panelActionsManagePages, BorderLayout.SOUTH);
		// Add a grid layout with 1 line and 2 columns to the panel
		panelActionsManagePages.setLayout(new GridLayout(1, 2));
		// Add a button to add a page
		this.addPage = new JButton("Add");
		this.addPage.addActionListener(new AddPageController(this, this.model));
		panelActionsManagePages.add(addPage);
		// Add a button to delete a page
		this.delPage = new JButton("Delete");
		this.delPage.addActionListener(new DeletePageController(this, this.model));
		panelActionsManagePages.add(delPage);
		
		/*
		 * Create a panel for manage the list of the ingredients
		 */
		JPanel panelManageIngredients = new JPanel();
		ingredientsPanel.add(panelManageIngredients);
		// Add a border layout to the panel
		panelManageIngredients.setLayout(new BorderLayout());
		// Add a JLabel, to show that here it's the list of ingredients, to the north
		panelManageIngredients.add(new JLabel("Ingredients", JLabel.CENTER), BorderLayout.NORTH);
		// Add a JList which contains the list of ingredient associate to the recipe
		if(this.model.getSize() > 0)
			this.listIngredients = new JList<>(this.model.getElementAt(0));
		// If model contains 0 pages the JList is empty
		else
			this.listIngredients = new JList<>();
		// Add the JList in a scroll pane to the panel
		panelManageIngredients.add(new JScrollPane(listIngredients), BorderLayout.CENTER);
		/*
		 * Create a panel for the actions for manage the list of ingredients
		 */
		JPanel panelActionsManageIngredients = new JPanel();
		// Add the panel for actions in the bottom
		panelManageIngredients.add(panelActionsManageIngredients, BorderLayout.SOUTH);
		// Add a grid layout with 1 line and 2 columns to the panel
		panelActionsManageIngredients.setLayout(new GridLayout(1, 2));
		// Add a button to add a ingredient
		this.addIngredient = new JButton("Add");
		panelActionsManageIngredients.add(addIngredient);
		// Add a button to delete a ingredient
		this.delIngredient = new JButton("Delete");
		this.delIngredient.addActionListener(new DeleteIngredientController(this));
		panelActionsManageIngredients.add(delIngredient);
		
		/*
		 * Create a panel for the information about the association between
		 * the recipe and the ingredient selected and add this to the tab
		 */
		JPanel informationsAboutAssocation = new JPanel();
		ingredientsPanel.add(informationsAboutAssocation);
		
		/*
		 * Set preferences for the window
		 */
		this.setPreferredSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		
	}
	
	/**
	 * Method to update the JList of pages
	 */
	public void updatePagesList() {
		
		this.listPages.updateUI();
		
	}
	
	/**
	 * Method to update the JList of ingredients
	 */
	public void updateIngredientsList() {
		
		this.listIngredients.updateUI();
		
	}
	
	/**
	 * Method to get the Page selected
	 * @return the Page selected
	 */
	public Page getPageSelected() {
		
		return this.listPages.getSelectedValue();
		
	}
	
	/**
	 * Method to get the ingredient selected
	 * @return the ingredient selected
	 */
	public Ingredient getIngredientSelected() {
		
		return this.listIngredients.getSelectedValue();
		
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
