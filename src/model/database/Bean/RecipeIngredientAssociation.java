/**
 * Class to associate an ingredient to a recipe
 * @author Developpix
 * @version 0.1
 */

package model.database.Bean;

public class RecipeIngredientAssociation {
	
	private int numRecipe, numIngredient, quantity;
	private String unit;
	
	public RecipeIngredientAssociation(int numRecipe, int numIngredient, int quantity, String unit) {
		this.numRecipe = numRecipe;
		this.numIngredient = numIngredient;
		this.quantity = quantity;
		this.unit = unit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getNumRecipe() {
		return numRecipe;
	}

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

	@Override
	public String toString() {
		return "RecipeIngredientAssociation [numRecipe=" + numRecipe + ", numIngredient=" + numIngredient
				+ ", quantity=" + quantity + ", unit=" + unit + "]";
	}
	
}
