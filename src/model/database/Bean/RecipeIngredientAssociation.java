/**
 * Class to associate an ingredient to a recipe
 * @author Developpix
 * @version 0.1
 */

package model.database.Bean;

public class RecipeIngredientAssociation {
	
	private int numRecipe, numIngredient, quantity;
	private String unit;
	
	/**
	 * Constructor the class create a association between a recipe and an ingredient
	 * @param numRecipe the id of the recipe
	 * @param numIngredient the id of the ingredient
	 * @param quantity the quantity of ingredient
	 * @param unit the unit of the quantity
	 */
	public RecipeIngredientAssociation(int numRecipe, int numIngredient, int quantity, String unit) {
		this.numRecipe = numRecipe;
		this.numIngredient = numIngredient;
		this.quantity = quantity;
		this.unit = unit;
	}

	/**
	 * Getter to get the quantity of ingredient associated
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Setter to change the quantity of ingredient associated
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Getter to get the unity of the quantity
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Setter to change the unity of the quantity
	 * @param unit the new unit
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * Getter to get the id of the recipe
	 * @return the id of the recipe
	 */
	public int getNumRecipe() {
		return numRecipe;
	}

	/**
	 * Getter to get the id of the ingredient
	 * @return the id of the ingredient
	 */
	public int getNumIngredient() {
		return numIngredient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numIngredient;
		result = prime * result + numRecipe;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeIngredientAssociation other = (RecipeIngredientAssociation) obj;
		if (numIngredient != other.numIngredient)
			return false;
		if (numRecipe != other.numRecipe)
			return false;
		return true;
	}

	/**
	 * Convert the association in a string
	 * @return the string corresponding to the association
	 */
	@Override
	public String toString() {
		return "RecipeIngredientAssociation [numRecipe=" + numRecipe + ", numIngredient=" + numIngredient
				+ ", quantity=" + quantity + ", unit=" + unit.toString() + "]";
	}
	
}
