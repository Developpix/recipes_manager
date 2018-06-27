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

public class DAORecipeIngredientAssociation {

	private SessionDatabase session;

	/**
	 * DAORecipe's constructor take a session to a database
	 * @param session a instance of a class which implements the interface SessionDatabase
	 */
	public DAORecipeIngredientAssociation(SessionDatabase session) {
		
		this.session = session;
		
	}
	
	/**
	 * Method to insert an association between a recipe and a ingredient in the database
	 * @param association the association between the recipe and the ingredient which will be insert
	 */
	public void create(RecipeIngredientAssociation association) {
		
		try {
			
			String insertAssociation = "INSERT INTO RecipeIngredientAssociation VALUES(?, ?, ?, ?)";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(insertAssociation);
			pstmt.setInt(1, association.getNumIngredient());
			pstmt.setInt(2, association.getNumRecipe());
			pstmt.setInt(3, association.getQuantity());
			pstmt.setString(4, association.getUnit());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
	}
	
	/**
	 * Method to update a association between a recipe and a ingredient in the database
	 * @param association the association
	 */
	public void update(RecipeIngredientAssociation association) {
		
		try {
			
			String updateAssociation = "UPDATE RecipeIngredientAssociation SET quantity=?, unit=?"
					+ " WHERE numRecipe=? AND numIngredient=?";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(updateAssociation);
			pstmt.setInt(1, association.getQuantity());
			pstmt.setString(2, association.getUnit());
			pstmt.setInt(3, association.getNumRecipe());
			pstmt.setInt(4, association.getNumIngredient());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
	}
	
	/**
	 * Method to delete an association between a recipe and an ingredient in the database
	 * @param association the association
	 */
	public void delete(RecipeIngredientAssociation association) {
		
		try {
			
			String deleteAssociation = "DELETE FROM RecipeIngredientAssociation"
					+" WHERE numRecipe=? AND numIngredient=?";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(deleteAssociation);
			pstmt.setInt(1, association.getNumRecipe());
			pstmt.setInt(2, association.getNumIngredient());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
	}
	
	/**
	 * Method to get the list of associations between recipe and ingredient stored in the database
	 * @return the list of associations
	 */
	public List<RecipeIngredientAssociation> read() {
		
		List<RecipeIngredientAssociation> list = new LinkedList<>();
		
		try {
			
			String selectAssociation = "SELECT * FROM RecipeIngredientAssociation";
			
			Statement stmt = this.session.getConnection().createStatement();
			ResultSet res = stmt.executeQuery(selectAssociation);
			
			while(res.next()) {
				
				list.add(new RecipeIngredientAssociation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(2)));
				
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
		return list;
		
	}
	
	/**
	 * Method to get the list of associations with ingredient associated to a recipe
	 * and stored in the database
	 * @param recipe the recipe
	 * @return the list of RecipeIngredientAssociation
	 */
	public List<RecipeIngredientAssociation> read(Recipe recipe) {
		
		List<RecipeIngredientAssociation> list = new LinkedList<>();
		
		try {
			
			String selectAssociation = "SELECT * FROM RecipeIngredientAssociation"
					+ " WHERE numRecipe=?";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(selectAssociation);
			pstmt.setInt(1, recipe.getNumRecipe());
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				list.add(new RecipeIngredientAssociation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(2)));
				
			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
		return list;
		
	}
	
	/**
	 * Method to get the associations between an ingredient and a recipe
	 * and stored in the database
	 * @param recipe the recipe
	 * @param ingredient the ingredient
	 * @return the list of RecipeIngredientAssociation
	 */
	public RecipeIngredientAssociation read(Recipe recipe, Ingredient ingredient) {
		
		RecipeIngredientAssociation association = null;
		
		try {
			
			String selectAssociation = "SELECT * FROM RecipeIngredientAssociation"
					+ " WHERE numRecipe=? AND numIngredient=?";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(selectAssociation);
			pstmt.setInt(1, recipe.getNumRecipe());
			pstmt.setInt(2, ingredient.getNumIngredient());
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				association = new RecipeIngredientAssociation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4));
				
			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
		return association;
		
	}
	
}
