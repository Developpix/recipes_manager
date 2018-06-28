/**
 * Class to create a view to create a Page
 * @author Developpix
 * @version 0.1
 */

package hmi.views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hmi.controllers.CancelPageCreationController;
import hmi.controllers.CreatePageController;
import model.CookBook;

public class CreatePageView extends JFrame {

	private CookBook model;
	
	private JTextField name;
	
	public CreatePageView(CookBook model) {
		
		super("Create a page - Recipes manager");
		this.model = model;
		
		/*
		 * Create a main panel
		 */
		JPanel mainPanel = new JPanel();
		// Add a grid layout with 4 lines and 1 column to the panel
		mainPanel.setLayout(new GridLayout(4, 1));
		this.setContentPane(mainPanel);
		// Add a label to the panel
		JLabel textName = new JLabel("Name of the recipe", JLabel.CENTER);
		mainPanel.add(textName);
		// Add a text field for te name
		this.name = new JTextField();
		mainPanel.add(name);
		// Add a button to create the page
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new CreatePageController(this, this.model));
		mainPanel.add(createButton);
		// Add a button to cancel the creation
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelPageCreationController(this, this.model));
		mainPanel.add(cancelButton);

		/*
		 * Set preferences for the window
		 */
		this.setPreferredSize(new Dimension(600, 300));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		
	}
	
	/**
	 * Method to get the name of the recipe enter
	 * @return the name
	 */
	public String getRecipeName() {
		
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
