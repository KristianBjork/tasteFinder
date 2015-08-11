package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.compass.annotations.Cascade;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.SearchableReference;

@Searchable(root = false)
@Entity
public class Language implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@SearchableId
	@Id
	@SequenceGenerator(name = "language", sequenceName = "language_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language")
	private Integer id;
	
	@SearchableProperty
	private String languagecode;
	
	@SearchableProperty
	private String type;
	
	@SearchableProperty
	private String text;
	
	//bi-directional many-to-one association to Drink
	@ManyToOne
	@JoinColumn(name="id_drinktype_fk")
	private Drinktype drinktype;
	
	//bi-directional many-to-one association to Drink
	@ManyToOne
	@JoinColumn(name="id_drink_fk")
	private Drink drink;
	
	//bi-directional many-to-one association to Drink
	@ManyToOne
	@JoinColumn(name="id_country_fk")
	private Country country;

	public String getLanguagecode() {
		return languagecode;
	}

	public void setLanguagecode(String languagecode) {
		this.languagecode = languagecode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Drinktype getDrinktype() {
		return this.drinktype;
	}

	public void setDrinktype(Drinktype drinktype) {
		this.drinktype = drinktype;
	}
	
	public Drink getDrink(){
		return this.drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public Country getCountry(){
		return this.country;
	}
	

}
