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
	private Recipe recipe;
	private SessionDatabase sessionDatabase;
	private List<Ingredient> listOfIngredients;
	private List<Step> listOfSteps;
	private Map<String, Integer> mapQuantity;
	private Map<String, String> mapUnits;
	
	/**
	 * Constructor to create the page which contains a recipe
	 * @param name the name of the recipe
	 */
	public Page(String name) {
		
		this.name = name;
		this.listOfIngredients = new LinkedList<>();
		this.mapQuantity = new HashMap<>();
		this.mapUnits = new HashMap<>();
		this.listOfSteps = new LinkedList<>();
		
	}
	
	/**
	 * Constructor to create a page which contains a recipe and store in the database
	 * @param name the name of the recipe
	 * @param sessionDatabase the session establish to the database
	 */
	public Page(String name, SessionDatabase sessionDatabase) {
		
		this(name);
		this.sessionDatabase = sessionDatabase;
		
		DAORecipe daoRecipe = new DAORecipe(this.sessionDatabase);
		daoRecipe.create(name);
		this.recipe = daoRecipe.read(name);
		
	}
	
	/**
	 * Constructor to create a page which contains a recipe from a database
	 * @param recipe the recipe
	 * @param sessionDatabase the session establish to the database
	 */
	public Page(Recipe recipe, SessionDatabase sessionDatabase) {

		this.recipe = recipe;
		this.name = recipe.getName();
		this.sessionDatabase = sessionDatabase;
		
		DAOIngredient daoIngredient = new DAOIngredient(this.sessionDatabase);
		this.listOfIngredients = daoIngredient.read(recipe);
		
		this.listOfIngredients = new LinkedList<>();
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
	
	/**
	 * Method to add an association with an ingredient
	 * @param ingredient the ingredient
	 * @param quantity the quantity
	 * @param unit the unit for the quantity
	 */
	public void addIngredient(Ingredient ingredient, int quantity, String unit) {
		
		addIngredient(ingredient);
		this.mapQuantity.put(ingredient.getName(), quantity);
		this.mapUnits.put(ingredient.getName(), unit);

		// If the application is connected to a database
		if(this.sessionDatabase != null) {
		
			DAORecipeIngredientAssociation daoAssociation = new DAORecipeIngredientAssociation(this.sessionDatabase);
			RecipeIngredientAssociation association = new RecipeIngredientAssociation(this.recipe.getNumRecipe(),
					ingredient.getNumIngredient(),
					quantity,
					unit);
			daoAssociation.create(association);
			
		}
			
	}
	
	/**
	 * Method to delete the Page
	 */
	public void delete() {
		
		DAORecipe daoRecipe = new DAORecipe(this.sessionDatabase);
		daoRecipe.delete(this.name);
		
	}
	
	/**
	 * Method to remove an association with an ingredient
	 * @param ingredient the ingredient
	 */
	public void deleteIngredient(Ingredient ingredient) {
		
		this.listOfIngredients.remove(ingredient);
		
		// If the application is connected to a database
		if(this.sessionDatabase != null) {
		
			DAORecipeIngredientAssociation daoAssociation = new DAORecipeIngredientAssociation(this.sessionDatabase);
			RecipeIngredientAssociation association = daoAssociation.read(this.recipe, ingredient);
			daoAssociation.delete(association);
			
		}
		
	}
	
	/**
	 * Method to get the list of ingredients associated to the recipe
	 * @return the list of ingredients
	 */
	public List<Ingredient> getIngredients() {
		
		return this.listOfIngredients;
		
	}

	/**
	 * Method to get the quantity of the ingredient in the recipe
	 * @param ingredient the ingredient
	 * @return the quantity
	 */
	public int getQuantity(Ingredient ingredient) {
		
		return this.mapQuantity.get(ingredient.getName());
		
	}

	/**
	 * Method to get the unit for the quantity of the ingredient in the recipe
	 * @param ingredient the ingredient
	 * @return the unit
	 */
	public String getUnit(Ingredient ingredient) {
		
		return this.mapUnits.get(ingredient.getName());
		
	}
	
	public String getText() {
		
		String text = "";
		
		for (Step step : this.listOfSteps) {
			
			text += "- " + step.getText() + "\n";
			
		}
		
		return text;
		
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
