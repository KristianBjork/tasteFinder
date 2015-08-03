package se.tasteFinder.representation;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import se.tasteFinder.utils.StaticStrings;
import model.Country;

@XmlRootElement(name = "country")
public class CountryRepresentation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String countryName;
	
	private String languageCode;
	
	public CountryRepresentation(String countryName, String languageCode){
		this.countryName = countryName;
		this.languageCode = languageCode;
	}
	
	@XmlTransient
	public Country getCountry(){
		Country country = new Country();
		country.addLanguages(new LanguageRepresentation(languageCode, countryName, StaticStrings.drinkCountry).getLanguage());
		return country;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

}
