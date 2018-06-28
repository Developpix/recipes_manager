/**
 * Class to create a CookBook which is a book which contains a list of Pages.
 * @author Developpix
 * @version 0.1
 */

package model;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractListModel;

import model.database.BD.SessionDatabase;
import model.database.Bean.Recipe;
import model.database.DAO.DAORecipe;
import model.files.Page;

public class CookBook extends AbstractListModel<Page> {

	private String dataMod;
	private SessionDatabase sessionDatabase;
	private List<Page> listOfPages;
	private File saveFile;
	
	/**
	 * Constructor to create a CookBook with data from a database
	 * @param sessionDatabase
	 */
	public CookBook(SessionDatabase sessionDatabase) {
		
		this.dataMod = "DATABASE";
		this.listOfPages = new LinkedList<>();
		this.sessionDatabase = sessionDatabase;
		
	}
	
	/**
	 * Method to create a CookBook with data from a JSON file
	 * @param fileURI the URI of the file
	 */
	public CookBook(String fileURI) {

		this.dataMod = "FILE";
		this.saveFile = new File(fileURI);
		
	}
	
	/**
	 * Create a Page for a recipe
	 * @param name the name of the recipe
	 */
	public void createPage(String name) {
		
		if(this.dataMod.equals("DATABASE")) {
			
			new Page(name, this.sessionDatabase);
			load();
			
		}
		
	}

	/**
	 * Method to get a Page at the position specify in the CookBook
	 * @param index the position of the Page you want to get in the CookBook
	 * @return the Page at the position
	 */
	@Override
	public Page getElementAt(int index) {
		
		if(listOfPages.size() <= 0)
			return null;
		else
			return listOfPages.get(index);
		
	}

	/**
	 * Method to get the number of Pages store in the CookBook
	 * @return the number of Pages
	 */
	@Override
	public int getSize() {
		
		return listOfPages.size();
		
	}
	
	/**
	 * Method to load data from the file/database associated to the CookBook
	 * if is possible
	 */
	public void load() {
		
		if(this.dataMod.equals("DATABASE")) {
			
			DAORecipe daoRecipe = new DAORecipe(this.sessionDatabase);
			List<Recipe> listOfRecipes = daoRecipe.read();
			this.listOfPages = new LinkedList<>();
			
			for(Recipe recipe : listOfRecipes) {
			
				listOfPages.add(new Page(recipe, this.sessionDatabase));
				
			}
			
		} else if (this.dataMod.equals("FILE")) {
			
			// TODO
			
		}
		
	}
	
}
