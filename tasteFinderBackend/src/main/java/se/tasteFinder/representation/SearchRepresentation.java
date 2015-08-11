package se.tasteFinder.representation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import model.Drink;
import model.Language;

@XmlRootElement(name="Search")
public class SearchRepresentation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String producer;
	private Map<String,String> drinkTypeName;
	private Map<String,String> country;
	
	public SearchRepresentation(){
	}

	public SearchRepresentation(Drink drink) {
		this.name = drink.getDrinkname();
		this.producer = drink.getProducer();
		drinkTypeName = new HashMap<String,String>();
		for(Language language : drink.getDrinktype().getLanguages()){
			drinkTypeName.put(language.getLanguagecode(), language.getText()); 
		}
		country = new HashMap<String,String>();
		for(Language language : drink.getCountry().getLanguages()){
			country.put(language.getLanguagecode(), language.getText());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getDrinkTypeName() {
		return drinkTypeName;
	}

	public void setDrinkTypeName(Map<String, String> drinkTypeName) {
		this.drinkTypeName = drinkTypeName;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Map<String, String> getCountry() {
		return country;
	}

	public void setCountry(Map<String, String> country) {
		this.country = country;
	}
	
}
