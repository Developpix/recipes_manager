/**
 * Class to create a step
 * @author Developpix
 * @version 0.1
 */

package model.database.Bean;

public class Step {
	
	private int numStep, numRecipe;
	private String text;
	
	/**
	 * Step's constructor to create a step for the recipe
	 * @param numStep the id of the step
	 * @param text the text of the step
	 * @param numRecipe the id of the recipe associated to the step
	 */
	public Step(int numStep, String text, int numRecipe) {
		
		this.numStep = numStep;
		this.numRecipe = numRecipe;
		this.text = text;
		
	}

	/**
	 * Getter to get the text of the step
	 * @return the text of the step
	 */
	public String getText() {
		
		return text;
		
	}

	/**
	 * Setter to change the text of the step
	 * @param text the new text
	 */
	public void setText(String text) {
		
		this.text = text;
		
	}

	/**
	 * Getter to get the id of the step
	 * @return the id of the step
	 */
	public int getNumStep() {
		
		return numStep;
		
	}

	/**
	 * Getter to get the id of the recipe associated
	 * @return the id of the recipe associated
	 */
	public int getNumRecipe() {
		
		return numRecipe;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numRecipe;
		result = prime * result + numStep;
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
		Step other = (Step) obj;
		if (numRecipe != other.numRecipe)
			return false;
		if (numStep != other.numStep)
			return false;
		return true;
	}

	/**
	 * Get a string representing the step
	 * @return a string corresponding to the step
	 */
	public String toString() {
		
		return "Step [numStep=" + numStep + ", numRecipe=" + numRecipe + ", text=" + text + "]";
		
	}
		
}
