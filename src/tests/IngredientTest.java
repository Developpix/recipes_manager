package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.database.Bean.Ingredient;

class IngredientTest {

	@Test
	void testIngredient() {
	
		Ingredient i = new Ingredient(1, "test");
		
		assertEquals(1, i.getNumIngredient(), "test constructor");
		assertEquals("test", i.getName(), "test constructor");
		
	}

	@Test
	void testSetName() {
		
		Ingredient i = new Ingredient(1, "test");
		i.setName("cc");
		
		assertEquals(1, i.getNumIngredient(), "test constructor CT1");
		assertEquals("cc", i.getName(), "test constructor CT1");
		
	}

}
