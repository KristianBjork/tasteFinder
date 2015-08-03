package se.tasteFinder.ejb;

import javax.ejb.Remote;

import model.Country;

@Remote
public interface CountryBeanRemote {
	
	Country findByNameAndLanguageCode(String name, String languageCode);


}
