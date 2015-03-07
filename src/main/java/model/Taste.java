package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the taste database table.
 * 
 */
@Entity
@NamedQuery(name="Taste.findAll", query="SELECT t FROM Taste t")
public class Taste implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "taste", sequenceName = "taste_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taste")
	private Integer id;

	private String tastetype;

	//bi-directional many-to-one association to Drink
	@ManyToOne
	@JoinColumn(name="id_drinks_fk")
	private Drink drink;

	public Taste() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTastetype() {
		return this.tastetype;
	}

	public void setTastetype(String tastetype) {
		this.tastetype = tastetype;
	}

	public Drink getDrink() {
		return this.drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

}