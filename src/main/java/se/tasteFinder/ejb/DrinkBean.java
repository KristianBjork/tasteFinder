package se.tasteFinder.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Drink;

@Stateless(name="DrinkBean", mappedName="DrinkBean")
public class DrinkBean implements DrinkBeanLocal, DrinkBeanRemote{
	
	@PersistenceContext(unitName="drink")
	private EntityManager em;
	
	public DrinkBean(){
	}

	public void createDrink(Drink drink){
		em.persist(drink);
	}
	
}
