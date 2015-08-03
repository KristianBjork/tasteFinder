package se.tasteFinder.ejb;

import javax.ejb.Local;

import model.Country;

@Local
public interface CountryBeanLocal {
	
	Country findByNameAndLanguageCode(String name, String languageCode);

}
