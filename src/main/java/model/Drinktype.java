package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the drinktype database table.
 * 
 */
@Entity
@NamedQueries(
	value = {
			@NamedQuery(name="Drinktype.findAll", query="SELECT d FROM Drinktype d"),
			@NamedQuery(name="Drinktype.findByName", query="SELECT d FROM Drinktype d WHERE d.drinktype=:name"),
	}
)
public class Drinktype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "drinkType", sequenceName = "drinkType_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drinkType")
	private Integer id;

	private String drinktype;

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

	public String getDrinktype() {
		return this.drinktype;
	}

	public void setDrinktype(String drinktype) {
		this.drinktype = drinktype;
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