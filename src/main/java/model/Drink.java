package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the drinks database table.
 * 
 */
@Entity
@Table(name="drinks")
@NamedQuery(name="Drink.findAll", query="SELECT d FROM Drink d")
public class Drink implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "drink", sequenceName = "drink_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drink")
	private Integer id;

	private Integer alcohol;

	private String color;

	private String drinkname;

	private String producer;

	//bi-directional many-to-one association to Drinktype
	@ManyToOne
	@JoinColumn(name="id_drinktype_fk")
	private Drinktype drinktype;

	//bi-directional many-to-one association to Smell
	@OneToMany(mappedBy="drink")
	private List<Smell> smells;

	//bi-directional many-to-one association to Taste
	@OneToMany(mappedBy="drink")
	private List<Taste> tastes;

	public Drink() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAlcohol() {
		return this.alcohol;
	}

	public void setAlcohol(Integer alcohol) {
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

}