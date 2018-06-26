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

public class DAOStep {

	private SessionDatabase session;

	/**
	 * DAORecipe's constructor take a session to a database
	 * @param session a instance of a class which implements the interface SessionDatabase
	 */
	public DAOStep(SessionDatabase session) {
		
		this.session = session;
		
	}
	
	/**
	 * Method to insert a step in the database
	 * @param step the step which will be insert
	 */
	public void create(Step step) {
		
		try {
			
			String insertStep = "INSERT INTO Step VALUES(?, ?, ?)";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(insertStep);
			pstmt.setInt(1, step.getNumStep());
			pstmt.setInt(2, step.getNumRecipe());
			pstmt.setString(3, step.getText());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
	}
	
	/**
	 * Method to update a step in the database
	 * @param step the step
	 */
	public void update(Step step) {
		
		try {
			
			String updateStep = "UPDATE Step SET name=? WHERE numStep=? AND numRecipe=?";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(updateStep);
			pstmt.setString(1, step.getText());
			pstmt.setInt(2, step.getNumStep());
			pstmt.setInt(3, step.getNumRecipe());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
	}
	
	/**
	 * Method to delete a step in the database
	 * @param step the step
	 */
	public void delete(Step step) {
		
		try {
			
			String deleteStep = "DELETE FROM Step WHERE numStep=? AND numRecipe=?";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(deleteStep);
			pstmt.setInt(1, step.getNumStep());
			pstmt.setInt(2, step.getNumRecipe());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
	}
	
	/**
	 * Method to get the list of steps stored in the database
	 * @return the list of steps
	 */
	public List<Step> read() {
		
		List<Step> list = new LinkedList<>();
		
		try {
			
			String selectStep = "SELECT * FROM Step";
			
			Statement stmt = this.session.getConnection().createStatement();
			ResultSet res = stmt.executeQuery(selectStep);
			
			while(res.next()) {
				
				list.add(new Step(res.getInt(1), res.getString(2), res.getInt(3)));
				
			}
			
			stmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
		return list;
		
	}
	
	/**
	 * Method to get the list of steps associated to a recipe
	 * and stored in the database
	 * @param recipe the recipe
	 * @return the list of steps
	 */
	public List<Step> read(Recipe recipe) {
		
		List<Step> list = new LinkedList<>();
		
		try {
			
			String selectStep = "SELECT * FROM Step WHERE numRecipe=?";
			
			PreparedStatement pstmt = this.session.getConnection().prepareStatement(selectStep);
			pstmt.setInt(1, recipe.getNumRecipe());
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				
				list.add(new Step(res.getInt(1), res.getString(2), res.getInt(3)));
				
			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("Erreur SQL -> " + e.getMessage());
			
		}
		
		return list;
		
	}
	
}
