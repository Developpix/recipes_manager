/**
 * Class to create a view to create a Page
 * @author Developpix
 * @version 0.1
 */

package hmi.views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hmi.controllers.CancelAddingIngredientController;
import hmi.controllers.CreateIngredientController;
import hmi.controllers.ValidateAddingIngredientController;
import model.CookBook;
import model.database.Bean.Ingredient;

public class AddIngredientToPageView extends JFrame {

	private CookBook model;
	
	private JComboBox<Ingredient> listIngredients;
	
	private JTextField quantity;
	private JTextField unit;
	
	public AddIngredientToPageView(CookBook model) {
		
		super("Add an ingredient - Recipes manager");
		this.model = model;
		
		/*
		 * Create a main panel
		 */
		JPanel mainPanel = new JPanel();
		// Set a grid layout to the main panel with 8 lines and 1 column
		mainPanel.setLayout(new GridLayout(8, 1));
		// Set the content pane is the mainPanel
		this.setContentPane(mainPanel);
		
		// Create a label for the list of ingredient
		JLabel ingredientLabel = new JLabel("Ingredient to add", JLabel.CENTER);
		mainPanel.add(ingredientLabel);
		// Create a JComboBox which contains the ingredients which can be added
		this.listIngredients = new JComboBox<>(this.model.getAllIngredientsNotAdded(this.model.getPageEdited()));
		mainPanel.add(this.listIngredients);
		// Create a label and text field for the quantity
		JLabel quantityLabel = new JLabel("Quantity", JLabel.CENTER);
		mainPanel.add(quantityLabel);
		this.quantity = new JTextField();
		mainPanel.add(this.quantity);
		// Create a label and a text field for the unit
		JLabel unitLabel = new JLabel("Unit", JLabel.CENTER);
		mainPanel.add(unitLabel);
		this.unit = new JTextField();
		mainPanel.add(this.unit);
		// Create a button for create an ingredient
		JButton createIngredient = new JButton("Create an ingredient");
		createIngredient.addActionListener(new CreateIngredientController(this, this.model));
		mainPanel.add(createIngredient);
		/*
		 * Create a panel for the actions and set a grid layout with 2 columns
		 */
		JPanel actionsPanel = new JPanel();
		actionsPanel.setLayout(new GridLayout(1, 2));
		mainPanel.add(actionsPanel);
		// Create a button to cancel and validate the adding
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ValidateAddingIngredientController(this, this.model));
		actionsPanel.add(addButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelAddingIngredientController(this, this.model));
		actionsPanel.add(cancelButton);
		
		/*
		 * Set preferences for the window
		 */
		this.setPreferredSize(new Dimension(600, 300));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		
	}
	
	/**
	 * Method to get the ingredient selected
	 * @return the ingredient selected
	 */
	public Ingredient getIngredientSelected() {
		
		return (Ingredient) this.listIngredients.getSelectedItem();
		
	}
	
	/**
	 * Method to get the quantity
	 * @return the quantity
	 * @throws NumberFormatException if the quantity is not a number
	 */
	public int getQuantity() throws NumberFormatException {
		
		return Integer.parseInt(this.quantity.getText());
		
	}
	
	/**
	 * Method to get the unit
	 * @return the unit
	 */
	public String getUnit() {
		
		return this.unit.getText();
		
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
