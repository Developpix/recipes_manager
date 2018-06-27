/**
 * Class to create a Page which is contained in a CookBook. A Page is linked to a recipe
 * and contains all informations about this recipe
 * @author Developpix
 * @version 0.1
 */

package model.files;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;

import model.database.BD.SessionDatabase;
import model.database.Bean.Ingredient;
import model.database.Bean.Recipe;
import model.database.Bean.RecipeIngredientAssociation;
import model.database.Bean.Step;
import model.database.DAO.DAOIngredient;
import model.database.DAO.DAORecipe;
import model.database.DAO.DAORecipeIngredientAssociation;
import model.database.DAO.DAOStep;

public class Page extends AbstractListModel<Ingredient> {

	private String name;
	private SessionDatabase sessionDatabase;
	private List<Ingredient> listOfIngredients;
	private List<Step> listOfSteps;
	private Map<String, Integer> mapQuantity;
	private Map<String, String> mapUnits;
	
	/**
	 * Constructor to create the page which contains a recipe
	 * @param name the name of the recipe
	 */
	public Page(String name, List<Ingredient> ingredients) {
		
		this.name = name;
		this.listOfIngredients = ingredients;
		
	}
	
	/**
	 * Constructor to create a page which contains a recipe from a database
	 * @param recipe the recipe
	 * @param sessionDatabase the session establish to the database
	 */
	public Page(Recipe recipe, SessionDatabase sessionDatabase) {
		
		this.name = recipe.getName();
		this.sessionDatabase = sessionDatabase;
		
		DAOIngredient daoIngredient = new DAOIngredient(this.sessionDatabase);
		this.listOfIngredients = daoIngredient.read(recipe);
		
		this.mapQuantity = new HashMap<>();
		this.mapUnits = new HashMap<>();
		
		for(Ingredient ingredient : this.listOfIngredients) {
			
			DAORecipeIngredientAssociation daoAssociation = new DAORecipeIngredientAssociation(this.sessionDatabase);
			RecipeIngredientAssociation association = daoAssociation.read(recipe, ingredient);
			
			this.mapQuantity.put(ingredient.getName(), association.getQuantity());
			this.mapUnits.put(ingredient.getName(), association.getUnit());
			
		}
		
		DAOStep daoStep = new DAOStep(this.sessionDatabase);
		this.listOfSteps = daoStep.read(recipe);
		
	}

	/**
	 * Method to add an ingredient in the Page
	 * @param ingredient the ingredient to add
	 */
	public void addIngredient(Ingredient ingredient) {
		
		this.listOfIngredients.add(ingredient);
		
	}

	@Override
	public Ingredient getElementAt(int index) {
		
		if(this.listOfIngredients.size() <= 0)
			return null;
		else
			return this.listOfIngredients.get(index);
		
	}


	@Override
	public int getSize() {
		
		return this.listOfIngredients.size();
		
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}
