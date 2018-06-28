/**
 * Class to manipulate the table which contains the list of the recipes
 * @author Developpix
 * @version 0.1
 */

package model.database.DAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import model.database.BD.*;
import model.database.Bean.*;

public class DAORecipe {

	private SessionDatabase session;

	/**
	 * DAORecipe's constructor take a session to a database
	 * @param session a instance of a class which implements the interface SessionDatabase
	 */
	public DAORecipe(SessionDatabase session) {
		
		this.session = session;
		
	}
	
	/**
	 * Method to insert a recipe in the database
	 * @param recipe the recipe which will be insert
	 */
	public void create(String name) {
		
		try {
			
			if (!existRecipe(name)) {
			
				String insertRecipe = "INSERT INTO Recipe (name) VALUES(?)";
				
				
				PreparedStatement pstmt = this.session.getConnection().prepareStatement(insertRecipe);
				pstmt.setString(1, name);
				pstmt.executeUpdate();
				pstmt.close();
			
			}
			
		} catch (SQLException e) {
			
			// If the table doesn't exist, we create the table and create the recipe
			if(e.getErrorCode() == 1146) {
				
				createTable();
				create(name);
				
			} else {

				System.out.println("Erreur SQL -> " + e.getMessage());
				
			}
		}
		
	}
	
	/**
	 * Method to update a recipe in the database
	 * @param recipe the recipe
	 */
	public void update(Recipe recipe) {
		
		try {
			
			String updateRecipe = "UPDATE Recipe SET name=? WHERE numRecipe=?";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(updateRecipe);
			pstmt.setString(1, recipe.getName());
			pstmt.setInt(2, recipe.getNumRecipe());
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
	 * Method to delete a recipe in the database
	 * @param name the name of the recipe
	 */
	public void delete(String name) {
		
		try {
			
			String deleteRecipe = "DELETE FROM Recipe WHERE name=?";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(deleteRecipe);
			pstmt.setString(1, name);
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
	 * Method to get the list of recipes stored in the database
	 * @return the list of recipes
	 */
	public List<Recipe> read() {
		
		List<Recipe> list = new LinkedList<>();
		
		try {
			
			String selectRecipe = "SELECT * FROM Recipe";
			
			Statement stmt = this.session.getConnection().createStatement();
			ResultSet res = stmt.executeQuery(selectRecipe);
			
			while(res.next()) {
				
				list.add(new Recipe(res.getInt(1), res.getString(2)));
				
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
	 * Method to get the list of recipes associated to an ingredient
	 * and stored in the database
	 * @param ingredient the ingredient
	 * @return the list of recipes
	 */
	public List<Recipe> read(Ingredient ingredient) {
		
		List<Recipe> list = new LinkedList<>();
		
		try {
			
			String selectRecipe = "SELECT * FROM Recipe WHERE numRecipe"
					+ " IN (SELECT numRecipe FROM RecipeIngredientAssociation"
					+ " WHERE numIngredient=?)";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(selectRecipe);
			pstmt.setInt(1, ingredient.getNumIngredient());
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				list.add(new Recipe(res.getInt(1), res.getString(2)));
				
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
	 * Method to create the table recipe
	 */
	public void createTable() {
		
		String createTable = "CREATE OR REPLACE TABLE Recipe (\n" + 
				"	numRecipe INT(4) AUTO_INCREMENT,\n" + 
				"	name VARCHAR(255),\n" + 
				"	CONSTRAINT PK_Recipe PRIMARY KEY (numRecipe)\n" + 
				")";
		
		try {
			
			Statement stmt = this.session.getConnection().createStatement();
			
			stmt.executeQuery(createTable);
			
			stmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
		
	}
	
	/**
	 * Method to search if a recipe exist in the database
	 * @param name of the recipe
	 * @return true if the recipe exist or false if not
	 */
	public boolean existRecipe(String name) {
		
		try {
			
			String selectRecipe = "SELECT * FROM Recipe WHERE name=?";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(selectRecipe);
			pstmt.setString(1, name);
			ResultSet res = pstmt.executeQuery();
			
			// Calculate the number of line in the table
			int i = 0;
			while (res.next())
				i++;
			
			// Test if there is one or more recipe with this name in the database
			boolean exist = i >= 1;
			
			pstmt.close();
			
			return exist;
			
		} catch (SQLException e) {
			
			// If the table doesn't exist, we create the table
			if(e.getErrorCode() == 1146) {
				
				createTable();
				
			} else {

				System.out.println("Erreur SQL -> " + e.getMessage());
				
			}
			
			return false;
			
		}
		
	}
	
}
