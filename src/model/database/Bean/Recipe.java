/**
 * Class to create a recipe
 * @author Developpix
 * @version 0.1
 */

package model.database.Bean;

public class Recipe {
	
	private int numRecipe;
	private String name;
	
	/**
	 * Recipe's constructor to create a recipe with the specify numRecipe and name
	 * @param numRecipe the id of the recipe
	 * @param name the name of the recipe
	 */
	public Recipe(int numRecipe, String name) {
		
		this.numRecipe = numRecipe;
		this.name = name;
		
	}

	/**
	 * Getter to get the id of the recipe
	 * @return the id of the recipe
	 */
	public int getNumRecipe() {
		
		return numRecipe;
		
	}

	/**
	 * Getter to get the name of the recipe
	 * @return the name of the recipe
	 */
	public String getName() {
		
		return name;
		
	}

	/**
	 * Setter to change the name of the recipe
	 * @param name the new name
	 */
	public void setName(String name) {
		
		this.name = name;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Recipe other = (Recipe) obj;
		if (numRecipe != other.numRecipe)
			return false;
		return true;
	}

	/**
	 * Get a string representing the recipe
	 * @return the string corresponding to the recipe
	 */
	public String toString() {
		
		return "Recipe [numRecipe=" + numRecipe + ", name=" + name + "]";
	
	}
	
}
