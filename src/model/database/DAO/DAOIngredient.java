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

public class DAOIngredient {

	private SessionDatabase session;

	/**
	 * DAORecipe's constructor take a session to a database
	 * @param session a instance of a class which implements the interface SessionDatabase
	 */
	public DAOIngredient(SessionDatabase session) {
		
		this.session = session;
		
	}
	
	/**
	 * Method to insert a ingredient in the database
	 * @param ingredient the ingredient which will be insert
	 */
	public void create(Ingredient ingredient) {
		
		try {
			
			String insertIngredient = "INSERT INTO Ingredient (name) VALUES(?)";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(insertIngredient);
			pstmt.setString(1, ingredient.getName());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
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
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
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
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
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
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
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
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
		return list;
		
	}
	
}
