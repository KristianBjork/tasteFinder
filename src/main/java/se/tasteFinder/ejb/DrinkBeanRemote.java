package se.tasteFinder.ejb;

import javax.ejb.Remote;

import model.Drink;

@Remote
public interface DrinkBeanRemote {
	
	public void createDrink(Drink drink);

}
