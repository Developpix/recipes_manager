/**
 * Class to create an ingredient
 * @author Developpix
 * @version 0.1
 */

package model.database.Bean;

public class Ingredient {
	
	private int numIngredient;
	private String name;
	
	/**
	 * Recipe's constructor to create a ingredient with the specify numIngredient and name
	 * @param numIngredient the id of the ingredient
	 * @param name the name of the ingredient
	 */
	public Ingredient(int numIngredient, String name) {
		
		this.numIngredient = numIngredient;
		this.name = name;
		
	}

	/**
	 * Getter to get the id of the ingredient
	 * @return the id of the ingredient
	 */
	public int getNumIngredient() {
		
		return numIngredient;
		
	}

	/**
	 * Getter to get the name of the ingredient
	 * @return the name of the ingredient
	 */
	public String getName() {
		
		return name;
		
	}

	/**
	 * Setter to change the name of the ingredient
	 * @param name the new name
	 */
	public void setName(String name) {
		
		this.name = name;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numIngredient;
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
		Ingredient other = (Ingredient) obj;
		if (numIngredient != other.numIngredient)
			return false;
		return true;
	}

	/**
	 * Get a string representing the ingredient
	 * @return the string corresponding to the ingredient
	 */
	public String toString() {
		return "Ingredient [numIngredient=" + numIngredient + ", name=" + name + "]";
	}
	
}
