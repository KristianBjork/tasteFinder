package se.tasteFinder.representation;

import java.io.Serializable;

import javax.naming.NamingException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import se.tasteFinder.utils.StaticStrings;
import model.Drinktype;

@XmlRootElement(name="drinkType")
public class DrinkTypeRepresentation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String drinkTypeName;
	
	private String languageCode;
		
	public DrinkTypeRepresentation(){
	}
	
	public DrinkTypeRepresentation(String drinkTypeName, String languageCode) {
		this.drinkTypeName = drinkTypeName;
		this.languageCode = languageCode;
	}

	@XmlTransient
	public Drinktype getDrinkType() throws NamingException{
		Drinktype drinkType = new Drinktype();
		drinkType.addLanguages(new LanguageRepresentation(languageCode, drinkTypeName, StaticStrings.Drinktype).getLanguage());
		return drinkType;
	}

	public String getDrinkTypeName() {
		return drinkTypeName;
	}

	public void setDrinkTypeName(String drinkTypeName) {
		this.drinkTypeName = drinkTypeName;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

}
