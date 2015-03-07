package se.tasteFinder.representation;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import model.Drinktype;

@XmlRootElement(name="drinkType")
public class DrinkTypeRepresentation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String drinkTypeName;
	
	public DrinkTypeRepresentation(){
	}
	
	public DrinkTypeRepresentation(String drinkTypeName) {
		this.drinkTypeName = drinkTypeName;
	}

	@XmlTransient
	public Drinktype getDrinkType(){
		Drinktype drinkType = new Drinktype();
		drinkType.setDrinktype(drinkTypeName);
		return drinkType;
	}

	public String getDrinkTypeName() {
		return drinkTypeName;
	}

	public void setDrinkTypeName(String drinkTypeName) {
		this.drinkTypeName = drinkTypeName;
	}

}
