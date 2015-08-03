package se.tasteFinder.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import model.Drinktype;
import se.tasteFinder.representation.DrinkTypeRepresentation;
import se.tasteFinder.utils.StaticStrings;

@EJB(beanInterface = DrinkTypeLocal.class, name = "DrinkTypeBean")
@Stateless
public class DrinkTypeBean implements DrinkTypeLocal, DrinkTypeRemote {

	@PersistenceContext(unitName = "tasteFinder")
	private EntityManager em;

	public DrinkTypeBean() {
	}

	public Drinktype findById(int id) {
		return null;
	}

	/*
	 * TODO:add fault handling (non-Javadoc)
	 * 
	 * @see se.tasteFinder.ejb.DrinkTypeRemote#findByName(java.lang.String)
	 */
	public Drinktype findByNameAndLanguageCode(String name, String languageCode) {
		try{
			return (Drinktype) em.createNamedQuery("Drinktype.findByName").setParameter("name", name).setParameter("languagecode", languageCode).setParameter("type", StaticStrings.Drinktype).getSingleResult();
		} catch (NoResultException  e){
			return null;
		}catch(NonUniqueResultException e){
			return null;
		}
	}
			
	public void createDrinkType(Drinktype drinkType) {
		em.persist(drinkType);
		em.flush();
	}

	public void checkByNameElseInsert(String drinkTypeName, String languageCode) {
		try{
			em.createNamedQuery("Drinktype.findByName").setParameter("name", drinkTypeName).setParameter("languagecode", languageCode).setParameter("type", StaticStrings.Drinktype).getSingleResult();
		}catch(NoResultException  e){
			try {
				this.createDrinkType(new DrinkTypeRepresentation(drinkTypeName, languageCode).getDrinkType());
			} catch (NamingException en) {
				System.out.println("None unique drinkType: " + drinkTypeName);
			}
		}catch(NonUniqueResultException e){
			System.out.println("None unique drinkType: " + drinkTypeName);
		}
	}

}
