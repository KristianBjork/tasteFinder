package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the smell database table.
 * 
 */
@Entity
@NamedQuery(name="Smell.findAll", query="SELECT s FROM Smell s")
public class Smell implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "smell", sequenceName = "smell_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "smell")
	private Integer id;

	private String smelltype;

	//bi-directional many-to-one association to Drink
	@ManyToOne
	@JoinColumn(name="id_drinks_fk")
	private Drink drink;

	public Smell() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSmelltype() {
		return this.smelltype;
	}

	public void setSmelltype(String smelltype) {
		this.smelltype = smelltype;
	}

	public Drink getDrink() {
		return this.drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

}