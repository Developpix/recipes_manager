package hmi.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import hmi.controllers.ConnectionDatabaseController;
import hmi.controllers.ResetDatabaseInformationController;

public class WelcomeView extends JFrame {
	
	private JTextField adressDatabase;
	private JTextField nameDatabase;
	private JTextField userName;
	private JTextField portDatabase;
	
	private JPasswordField userPassword;
	
	private JComboBox<String> typeOfDatabase;
	
	public WelcomeView() {
		
		super("Welcome - Recipe manager");
		
		/*
		 * Create a main panel for the view
		 */
		JTabbedPane mainPanel = new JTabbedPane();
		this.setContentPane(mainPanel);
		
		/*
		 * Create a panel to load the data from a database
		 */
		JPanel panelDatabase = new JPanel();
		// Set a border layout for the panel
		panelDatabase.setLayout(new BorderLayout());
		// Add the tab to the main panel
		mainPanel.add("Database", panelDatabase);
		// Set that the default selected tab is the database tab
		mainPanel.setSelectedComponent(panelDatabase);
		/*
		 * Panel for the north
		 */
		JPanel panelDatabaseNorth = new JPanel();
		// Add the panel to the north of the database panel
		panelDatabase.add(panelDatabaseNorth, BorderLayout.NORTH);
		// Add a grid layout to the panel for the north with 5 lines and 1 column
		panelDatabaseNorth.setLayout(new GridLayout(5, 1));
		
		// Create a panel for the types of the database
		JPanel panelTypeDatabase = new JPanel();
		// Add the panel to the panel for the north of the tab
		panelDatabaseNorth.add(panelTypeDatabase);
		// Add a grid layout to the panel for the type with 2 lines and 1 column
		panelTypeDatabase.setLayout(new GridLayout(2, 1));
		// Add a label for the type
		panelTypeDatabase.add(new JLabel("Type of the database :", JLabel.CENTER));
		// Create a list of the types
		String[] listOfTypes = {"MySQL", "MariaDB", "Oracle"};
		// Add a JComboBox to select the type of the database
		typeOfDatabase = new JComboBox(listOfTypes);
		panelTypeDatabase.add(typeOfDatabase);
		
		// Create a panel for the address of the database
		JPanel panelAddressDatabase = new JPanel();
		// Add the panel for the address to the panel for the north of the tab
		panelDatabaseNorth.add(panelAddressDatabase);
		// Add a grid layout to the panel for the address with 2 lines and 1 column
		panelAddressDatabase.setLayout(new GridLayout(2, 1));
		// Add a label for the address
		panelAddressDatabase.add(new JLabel("Address of the database :", JLabel.CENTER));
		// Add a JTextField for enter a address for the database
		adressDatabase = new JTextField();
		panelAddressDatabase.add(adressDatabase);
		
		// Create a panel for the name of the database
		JPanel panelNameDatabase = new JPanel();
		// Add the panel for the name to the panel for the north of the tab
		panelDatabaseNorth.add(panelNameDatabase);
		// Add a grid layout to the panel for the name with 2 lines and 1 column
		panelNameDatabase.setLayout(new GridLayout(2, 1));
		// Add a label for the name
		panelNameDatabase.add(new JLabel("Name of the database :", JLabel.CENTER));
		// Add a JTextField for enter a name for the database
		nameDatabase = new JTextField();
		panelNameDatabase.add(nameDatabase);
		
		// Create a panel for the port of the database
		JPanel panelPortDatabase = new JPanel();
		// Add the panel for the port to the panel for the north of the tab
		panelDatabaseNorth.add(panelPortDatabase);
		// Add a grid layout to the panel for the name with 2 lines and 1 column
		panelPortDatabase.setLayout(new GridLayout(2, 1));
		// Add a label for the name
		panelPortDatabase.add(new JLabel("Port of the database :", JLabel.CENTER));
		// Add a JTextField for enter a name for the database
		portDatabase = new JTextField();
		panelPortDatabase.add(portDatabase);
		
		// Create a panel for the informations for the connection
		JPanel panelConnectionInformations = new JPanel();
		// Add the panel for the address to the panel for the north of the tab
		panelDatabaseNorth.add(panelConnectionInformations);
		// Add a grid layout with 2 lines and 2 columns to the panel for the
		//informations about the connection
		panelConnectionInformations.setLayout(new GridLayout(2, 2));
		// Add a label for the user name
		panelConnectionInformations.add(new JLabel("User name :", JLabel.CENTER));
		// Add a label for the password
		panelConnectionInformations.add(new JLabel("Password :", JLabel.CENTER));
		// Add a JTextField for the name
		userName = new JTextField();
		panelConnectionInformations.add(userName);
		// Add a JPasswordField for the password of the user
		userPassword = new JPasswordField();
		panelConnectionInformations.add(userPassword);
		/*
		 * Create a panel for validate the connection or reset the values
		 */
		JPanel panelActionsDatabase = new JPanel();
		// Set a grid layout with 1 line and 2 columns to the panel for action
		panelActionsDatabase.setLayout(new GridLayout(1, 2));
		// Add the panel to the south of the database panel
		panelDatabase.add(panelActionsDatabase, BorderLayout.SOUTH);
		// Add a reset button to the panel
		JButton resetButton = new JButton("Reset");
		// Add a controller for the reset to the JButton
		resetButton.addActionListener(new ResetDatabaseInformationController(this));
		panelActionsDatabase.add(resetButton);
		// Add a connection button to the panel
		JButton connectionButton = new JButton("Connection");
		connectionButton.addActionListener(new ConnectionDatabaseController(this));
		panelActionsDatabase.add(connectionButton);
		
		/*
		 * Set preferences for the window
		 */
		this.setPreferredSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		
	}
	
	/**
	 * Function to reset the informations entered for the connection
	 * to the database
	 */
	public void resetInformationsDatabase() {
		
		this.typeOfDatabase.setSelectedIndex(0);
		this.adressDatabase.setText("");
		this.nameDatabase.setText("");
		this.userName.setText("");
		this.userPassword.setText("");
		
	}
	
	/**
	 * Method to get the type of database selected
	 * @return the type selected
	 */
	public String getTypeOfDatabaseSelected() {
		
		return this.typeOfDatabase.getSelectedItem().toString();
		
	}
	
	/**
	 * Method to get the address of the database
	 * @return the address of the datatabase
	 */
	public String getAddressOfDatabase() {
		
		return this.adressDatabase.getText();
		
	}

	/**
	 * Method to get the name of the database
	 * @return the name of the datatabase
	 */
	public String getNameOfDatabase() {
		
		return this.nameDatabase.getText();
		
	}

	/**
	 * Method to get the port of the database
	 * @return the port of the datatabase
	 * @throws NumberFormatException throw this exception if the port is not a number
	 */
	public int getPortOfDatabase() throws NumberFormatException {
		
		return Integer.parseInt(this.portDatabase.getText());
		
	}

	/**
	 * Method to get the user name
	 * @return the user name
	 */
	public String getUserName() {
		
		return this.userName.getText();
		
	}

	/**
	 * Method to get the user password
	 * @return the user password
	 */
	public String getUserPassword() {
		
		return String.copyValueOf(this.userPassword.getPassword());
		
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
