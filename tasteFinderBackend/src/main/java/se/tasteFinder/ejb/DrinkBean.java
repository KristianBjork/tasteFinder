package se.tasteFinder.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import se.tasteFinder.representation.LanguageRepresentation;
import model.Drink;
import model.Language;

@Stateless
@EJB(beanInterface = DrinkBeanLocal.class, name = "DrinkBean")
public class DrinkBean implements DrinkBeanLocal, DrinkBeanRemote {

	@PersistenceContext(unitName = "tasteFinder")
	private EntityManager em;

	public DrinkBean() {
	}

	public void createDrink(Drink drink) {
		em.persist(drink);
	}

	@SuppressWarnings("unchecked")
	public void checkForDuplicatesElseInsert(Drink drink) {
		List<Drink> databaseDrinks = (List<Drink>) em
				.createNamedQuery("Drink.findByName")
				.setParameter("name", drink.getDrinkname()).getResultList();
		if(databaseDrinks.size() > 0){
			for(Drink databaseDrink : databaseDrinks){
				System.out.println("Compareing drink");
				if(compareDrink(databaseDrink, drink)){
					/*
					 * Found match, should not create new Drink
					 */
					databaseDrink = updateDrinkValues(databaseDrink, drink);
					updateDrink(databaseDrink);
					return;
				}
			}
		}
		createDrink(drink);
	}
	
	private Drink updateDrinkValues(Drink databaseDrink, Drink drink) {
		for(Language lang :drink.getLanguages()){
			databaseDrink.addLanguages(new LanguageRepresentation(lang.getLanguagecode(), lang.getText(), lang.getType()).getLanguage());
		}
		databaseDrink.setColor(drink.getColor());

		return databaseDrink;
	}

	public void updateDrink(Drink drink){
		em.merge(drink);
	}

	private boolean compareDrink(Drink databaseDrink, Drink drink) {
		if(databaseDrink.getAlcohol().equals(drink.getAlcohol())
		&& databaseDrink.getProducer().equals(drink.getProducer())
		&& databaseDrink.getDrinktype().getId().equals(drink.getDrinktype().getId()))
			return true;
		return false;
	}

}
