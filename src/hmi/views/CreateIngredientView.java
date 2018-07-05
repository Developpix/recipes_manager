package hmi.views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CookBook;

public class CreateIngredientView extends JFrame {

	private CookBook model;
	
	private JTextField name;
	
	public CreateIngredientView(CookBook model) {
		
		super("Create ingredient - Recipes manager");
		this.model = model;
		
		/*
		 * Create a main panel and set the content pane
		 */
		JPanel mainPanel = new JPanel();
		// Set a grid layout with 4 lines and 1 column
		mainPanel.setLayout(new GridLayout(4, 1));
		this.setContentPane(mainPanel);
		// Add a label and a text field for the name of the ingredient
		JLabel labelName = new JLabel("Ingredient's name", JLabel.CENTER);
		mainPanel.add(labelName);
		this.name = new JTextField();
		mainPanel.add(this.name);
		// Create a button for validate the creation
		JButton createButton = new JButton("Create");
		mainPanel.add(createButton);
		// Create a button for cancel creation
		JButton cancelButton = new JButton("Cancel");
		mainPanel.add(cancelButton);

		/*
		 * Set preferences for the window
		 */
		this.setPreferredSize(new Dimension(300, 200));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		
		
	}
	
	/**
	 * Method for get the name of the ingredient
	 * @return the name of the ingredient
	 */
	public String getName() {
		
		return this.name.getText();
		
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
