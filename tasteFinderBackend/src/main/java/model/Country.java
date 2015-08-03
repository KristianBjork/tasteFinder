package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.sun.istack.Nullable;

@Entity
@NamedQueries(
		value = {
				@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c"),
				@NamedQuery(name="Country.findByName", query="SELECT c FROM Country c JOIN c.languages l WHERE l.text=:name and l.languagecode=:languagecode and l.type=:type"),
		}
	)
public class Country implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "country", sequenceName = "country_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country")
	private Integer id;

	//bi-directional many-to-one association to Languages
	@OneToMany(mappedBy="country", cascade = CascadeType.ALL)
	@Nullable
	private List<Language> languages;

	//bi-directional many-to-one association to Drink
	@OneToMany(mappedBy="country")
	private List<Drink> drinks;
	
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
		language.setCountry(this);

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
		drink.setCountry(this);

		return drink;
	}

	public Drink removeDrink(Drink drink) {
		getDrinks().remove(drink);
		drink.setDrinktype(null);

		return drink;
	}

}
