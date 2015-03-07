package se.tasteFinder.ejb;

import javax.ejb.Remote;

import model.Drinktype;

@Remote
public interface DrinkTypeRemote {

	Drinktype findByName(String drinkTypeName);

	void checkByNameElseInsert(String drinkType);

}
