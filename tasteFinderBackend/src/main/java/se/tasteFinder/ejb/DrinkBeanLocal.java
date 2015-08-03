package se.tasteFinder.ejb;

import javax.ejb.Local;

import model.Drink;

@Local
public interface DrinkBeanLocal {
	
	public void createDrink(Drink drink);
	
	public void checkForDuplicatesElseInsert(Drink drink);
	
}
