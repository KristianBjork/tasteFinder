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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableComponent;
import org.compass.annotations.SearchableId;


/**
 * The persistent class for the drinktype database table.
 * 
 */
@Searchable(root = false)
@Entity
@NamedQueries(
	value = {
			@NamedQuery(name="Drinktype.findAll", query="SELECT d FROM Drinktype d"),
			@NamedQuery(name="Drinktype.findByName", query="SELECT d FROM Drinktype d JOIN d.languages l WHERE l.text=:name and l.languagecode=:languagecode and l.type=:type"),
	}
)
public class Drinktype implements Serializable {
	private static final long serialVersionUID = 1L;

	@SearchableId
	@Id
	@SequenceGenerator(name = "drinkType", sequenceName = "drinkType_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drinkType")
	private Integer id;

	//bi-directional many-to-one association to Languages
	@SearchableComponent
	@OneToMany(mappedBy="drinktype", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Language> languages;

	//bi-directional many-to-one association to Drink
	@OneToMany(mappedBy="drinktype")
	private List<Drink> drinks;

	public Drinktype() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Language> getLanguages() {
		if(this.languages == null){
			this.languages = new LinkedList<Language>();
		}
		return this.languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public Language addLanguages(Language language) {
		List<Language> languages = getLanguages();
		for(Language lang :languages){
			if(lang.getType().equals(language.getType())
			&& lang.getLanguagecode().equals(language.getLanguagecode())
			&& lang.getText().equals(language.getText()))
				return language;
		}
		languages.add(language);
		language.setDrinktype(this);

		return language;
	}

	public Language removeLanguage(Language language) {
		getLanguages().remove(language);
		language.setDrink(null);

		return language;
	}

	public List<Drink> getDrinks() {
		return this.drinks;
	}

	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}

	public Drink addDrink(Drink drink) {
		getDrinks().add(drink);
		drink.setDrinktype(this);

		return drink;
	}

	public Drink removeDrink(Drink drink) {
		getDrinks().remove(drink);
		drink.setDrinktype(null);

		return drink;
	}

}