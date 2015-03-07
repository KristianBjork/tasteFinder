package se.tasteFinder.representation;

import java.io.Serializable;

import javax.naming.NamingException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import se.tasteFinder.ejb.DrinkTypeRemote;
import se.tasteFinder.utils.BeanFetcher;
import model.Drink;

@XmlRootElement(name="Drink")
public class DrinkRepresentation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String drinkTypeName;
	private String name;
	private String producer;
	private int alcohol;
	private String color;
	
	public DrinkRepresentation(){
	}
	
	@XmlTransient
	public Drink getDrink() throws NamingException{
		Drink drink = new Drink();
		drink.setDrinktype(((DrinkTypeRemote) BeanFetcher.getInstance().getBean("DrinkTypeBean")).findByName(drinkTypeName));
		drink.setDrinkname(name);
		drink.setProducer(producer);
		drink.setAlcohol(alcohol);
		drink.setColor(color);
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

	public int getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(int alcohol) {
		this.alcohol = alcohol;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
