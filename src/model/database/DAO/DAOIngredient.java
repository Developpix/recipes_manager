/**
 * Class to manipulate the table which contains the list of the ingredients
 * @author Developpix
 * @version 0.1
 */

package model.database.DAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import model.database.BD.*;
import model.database.Bean.*;

public class DAOIngredient {

	private SessionDatabase session;

	/**
	 * DAOIngredient's constructor take a session to a database
	 * @param session a instance of a class which implements the interface SessionDatabase
	 */
	public DAOIngredient(SessionDatabase session) {
		
		this.session = session;
		
	}
	
	/**
	 * Method to insert a ingredient in the database
	 * @param the name of the ingredient which will be insert
	 */
	public void create(String name) {
		
		try {
			
			String insertIngredient = "INSERT INTO Ingredient (name) VALUES(?)";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(insertIngredient);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			
			// If the table doesn't exist, we create the table and create ingredient
			if(e.getErrorCode() == 1146) {
				
				createTable();
				create(name);
				
			} else {

				System.out.println("Erreur SQL -> " + e.getMessage());
				
			}
		}
		
	}
	
	/**
	 * Method to update a ingredient in the database
	 * @param ingredient the ingredient
	 */
	public void update(Ingredient ingredient) {
		
		try {
			
			String updateIngredient = "UPDATE Ingredient SET ingredient=? WHERE numIngredient=?";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(updateIngredient);
			pstmt.setString(1, ingredient.getName());
			pstmt.setInt(2, ingredient.getNumIngredient());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			
			// If the table doesn't exist, we create the table
			if(e.getErrorCode() == 1146) {
				
				createTable();
				
			} else {

				System.out.println("Erreur SQL -> " + e.getMessage());
				
			}
		}
		
	}
	
	/**
	 * Method to delete a ingredient in the database
	 * @param ingredient the ingredient
	 */
	public void delete(Ingredient ingredient) {
		
		try {
			
			String deleteIngredient = "DELETE FROM Ingredient WHERE numIngredient=?";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(deleteIngredient);
			pstmt.setInt(1, ingredient.getNumIngredient());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			
			// If the table doesn't exist, we create the table
			if(e.getErrorCode() == 1146) {
				
				createTable();
				
			} else {

				System.out.println("Erreur SQL -> " + e.getMessage());
				
			}
		}
		
	}
	
	/**
	 * Method to get the list of ingredients stored in the database
	 * @return the list of ingredients
	 */
	public List<Ingredient> read() {
		
		List<Ingredient> list = new LinkedList<>();
		
		try {
			
			String selectIngredient = "SELECT * FROM Ingredient";
			
			Statement stmt = this.session.getConnection().createStatement();
			ResultSet res = stmt.executeQuery(selectIngredient);
			
			while(res.next()) {
				
				list.add(new Ingredient(res.getInt(1), res.getString(2)));
				
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			
			// If the table doesn't exist, we create the table
			if(e.getErrorCode() == 1146) {
				
				createTable();
				
			} else {

				System.out.println("Erreur SQL -> " + e.getMessage());
				
			}
		}
		
		return list;
		
	}
	
	/**
	 * Method to get the list of ingredients associated to a recipe
	 * and stored in the database
	 * @param recipe the recipe
	 * @return the list of ingredients
	 */
	public List<Ingredient> read(Recipe recipe) {
		
		List<Ingredient> list = new LinkedList<>();
		
		try {
			
			String selectIngredient = "SELECT * FROM Ingredient WHERE numIngredient"
					+ " IN (SELECT numIngredient FROM RecipeIngredientAssociation"
					+ " WHERE numRecipe=?)";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(selectIngredient);
			pstmt.setInt(1, recipe.getNumRecipe());
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				list.add(new Ingredient(res.getInt(1), res.getString(2)));
				
			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			
			// If the table doesn't exist, we create the table
			if(e.getErrorCode() == 1146) {
				
				createTable();
				
			} else {

				System.out.println("Erreur SQL -> " + e.getMessage());
				
			}
			
		}
		
		return list;
		
	}
	
	/**
	 * Method to create the table Ingredient
	 */
	public void createTable() {
		
		String createTable = "CREATE OR REPLACE TABLE Ingredient (\n" + 
				"	numIngredient INT(4) AUTO_INCREMENT,\n" + 
				"	name VARCHAR(255),\n" + 
				"	CONSTRAINT PK_Ingredient PRIMARY KEY (numIngredient)\n" + 
				")";
		
		try {
			
			Statement stmt = this.session.getConnection().createStatement();
			
			stmt.executeQuery(createTable);
			
			stmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
		
	}
	
}
