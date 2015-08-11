package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableComponent;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.SearchableReference;

/**
 * The persistent class for the drinks database table.
 * 
 */
@Searchable
@Entity
@Table(name = "drinks")
@NamedQueries(value = { @NamedQuery(name = "Drink.findAll", query = "SELECT d FROM Drink d"),
		@NamedQuery(name = "Drink.findByName", query = "SELECT d FROM Drink d WHERE d.drinkname=:name") })
public class Drink implements Serializable {
	private static final long serialVersionUID = 1L;

	@SearchableId
	@Id
	@SequenceGenerator(name = "drink", sequenceName = "drink_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drink")
	private Integer id;

	private Double alcohol;

	@SearchableProperty
	private String color;

	@SearchableProperty(name = "name")
	private String drinkname;

	@SearchableProperty
	private String producer;

	// bi-directional many-to-one association to Drinktype
	@SearchableComponent
	@ManyToOne
	@JoinColumn(name = "id_drinktype_fk")
	private Drinktype drinktype;

	// bi-directional many-to-one association to Country
	@SearchableComponent
	@ManyToOne
	@JoinColumn(name = "id_country_fk")
	private Country country;

	// bi-directional many-to-one association to Smell
	@OneToMany(mappedBy = "drink")
	private List<Smell> smells;

	// bi-directional many-to-one association to Taste
	@OneToMany(mappedBy = "drink")
	private List<Taste> tastes;

	// bi-directional many-to-one association to Languages
	@SearchableComponent
	@OneToMany(mappedBy = "drink", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Language> languages;

	public Drink() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAlcohol() {
		return this.alcohol;
	}

	public void setAlcohol(Double alcohol) {
		this.alcohol = alcohol;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDrinkname() {
		return this.drinkname;
	}

	public void setDrinkname(String drinkname) {
		this.drinkname = drinkname;
	}

	public String getProducer() {
		return this.producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Drinktype getDrinktype() {
		return this.drinktype;
	}

	public void setDrinktype(Drinktype drinktype) {
		this.drinktype = drinktype;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Smell> getSmells() {
		return this.smells;
	}

	public void setSmells(List<Smell> smells) {
		this.smells = smells;
	}

	public Smell addSmell(Smell smell) {
		getSmells().add(smell);
		smell.setDrink(this);

		return smell;
	}

	public Smell removeSmell(Smell smell) {
		getSmells().remove(smell);
		smell.setDrink(null);

		return smell;
	}

	public List<Taste> getTastes() {
		return this.tastes;
	}

	public void setTastes(List<Taste> tastes) {
		this.tastes = tastes;
	}

	public Taste addTaste(Taste taste) {
		getTastes().add(taste);
		taste.setDrink(this);

		return taste;
	}

	public Taste removeTaste(Taste taste) {
		getTastes().remove(taste);
		taste.setDrink(null);

		return taste;
	}

	public List<Language> getLanguages() {
		if (this.languages == null) {
			this.languages = new LinkedList<Language>();
		}
		return this.languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public Language addLanguages(Language language) {
		List<Language> languages = getLanguages();
		for (Language lang : languages) {
			if (lang.getType().equals(language.getType()) && lang.getLanguagecode().equals(language.getLanguagecode())
					&& lang.getText().equals(language.getText()))
				return language;
		}
		languages.add(language);
		language.setDrink(this);

		return language;
	}

	public Language removeLanguage(Language language) {
		getLanguages().remove(language);
		language.setDrink(null);

		return language;
	}

}