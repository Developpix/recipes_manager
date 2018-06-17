/**
 * Class to create a unit
 * @author Developpix
 * @version 0.1
 */

package model.database.Bean;

public class Unit {

	private int id;
	private String name;
	
	/**
	 * Unit's constructor to create a unit
	 * @param id the id of the unit
	 * @param name the name of the unit
	 */
	public Unit(int id, String name) {
		
		this.id = id;
		this.name = name;
		
	}

	/**
	 * Getter to get the name of the unit
	 * @return the name of the unit
	 */
	public String getName() {
		
		return name;
		
	}

	/**
	 * Setter to change the name of the unit
	 * @param name the new name
	 */
	public void setName(String name) {
		
		this.name = name;
		
	}

	/**
	 * Getter to get the id of the unit
	 * @return the id of the unit
	 */
	public int getId() {
		
		return id;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Unit other = (Unit) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Convert a unit in a string
	 * @return the string representing the unit
	 */
	@Override
	public String toString() {
		return "Unit [id=" + id + ", name=" + name + "]";
	}
	
}
