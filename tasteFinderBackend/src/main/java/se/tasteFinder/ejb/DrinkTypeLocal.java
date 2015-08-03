package se.tasteFinder.ejb;

import javax.ejb.Local;

import model.Drinktype;

@Local
public interface DrinkTypeLocal {

	Drinktype findByNameAndLanguageCode(String name, String languageCode);

	void checkByNameElseInsert(String drinkTypeName, String languageCode);
	
}
