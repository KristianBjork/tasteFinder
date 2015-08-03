package se.tasteFinder.ejb;

import javax.ejb.Remote;

import model.Drinktype;

@Remote
public interface DrinkTypeRemote {

	Drinktype findByNameAndLanguageCode(String name, String languageCode);

	void checkByNameElseInsert(String drinkTypeName, String languageCode);

}
