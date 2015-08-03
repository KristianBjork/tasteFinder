package se.tasteFinder.representation;

import java.io.Serializable;

import javax.naming.NamingException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.Static;

import se.tasteFinder.ejb.CountryBeanLocal;
import se.tasteFinder.ejb.DrinkTypeLocal;
import se.tasteFinder.ejb.DrinkTypeRemote;
import se.tasteFinder.utils.BeanFetcher;
import se.tasteFinder.utils.StaticStrings;
import model.Drink;

@XmlRootElement(name="Drink")
public class DrinkRepresentation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String drinkTypeName;
	private String languageCode;
	private String name;
	private String producer;
	private Double alcohol;
	private String color;
	private String country;

	public DrinkRepresentation(){
	}
	
	@XmlTransient
	public Drink getDrink() throws NamingException{
		Drink drink = new Drink();
		drink.setDrinktype(((DrinkTypeLocal) BeanFetcher.getInstance().getBean("java:/global/tasteFinderWeb/DrinkTypeBean")).findByNameAndLanguageCode(drinkTypeName, languageCode));
		drink.setDrinkname(name);
		drink.setProducer(producer);
		drink.setAlcohol(alcohol);
		drink.setColor(color);
		drink.setCountry(((CountryBeanLocal) BeanFetcher.getInstance().getBean("java:/global/tasteFinderWeb/CountryBean")).findByNameAndLanguageCode(country, languageCode));
		return drink;
	}

	public String getDrinkTypeName() {
		return drinkTypeName;
	}

	public void setDrinkTypeName(String drinkTypeName) {
		this.drinkTypeName = drinkTypeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public double getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

}
