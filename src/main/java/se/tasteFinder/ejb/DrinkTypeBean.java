package se.tasteFinder.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import se.tasteFinder.representation.DrinkTypeRepresentation;
import model.Drinktype;

@Stateless(name="drinkType", mappedName="drinkType")
public class DrinkTypeBean implements DrinkTypeLocal, DrinkTypeRemote {
	
	@PersistenceContext(unitName="drinkType")
	private EntityManager em;
	
	public DrinkTypeBean(){
	}
	
	public Drinktype findById(int id){
		return null;
	}
	
	/*
	 * TODO:add fault handling
	 * (non-Javadoc)
	 * @see se.tasteFinder.ejb.DrinkTypeRemote#findByName(java.lang.String)
	 */
	public Drinktype findByName(String name){
		return (Drinktype) em.createNamedQuery("Drinktype.findByName").setParameter("name", name);
	}
	
	public void createDrinkType(Drinktype drinkType){
		em.persist(drinkType);
	}

	public void checkByNameElseInsert(String drinkTypeName) {
		Drinktype drinktype = (Drinktype) em.createNamedQuery("Drinktype.findByName").setParameter("name", drinkTypeName);
		if(drinktype == null){
			createDrinkType(new DrinkTypeRepresentation(drinkTypeName).getDrinkType());
		}
	}

}
