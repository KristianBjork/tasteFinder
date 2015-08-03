package se.tasteFinder.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import model.Country;
import se.tasteFinder.representation.CountryRepresentation;
import se.tasteFinder.utils.StaticStrings;

@Stateless
@EJB(beanInterface = CountryBeanLocal.class, name = "CountryBean")
public class CountryBean implements CountryBeanLocal, CountryBeanRemote {
	
	@PersistenceContext(unitName = "tasteFinder")
	private EntityManager em;

	public Country findByNameAndLanguageCode(String name, String languageCode) {
		try{
			return (Country) em.createNamedQuery("Country.findByName").setParameter("name", name).setParameter("languagecode", languageCode).setParameter("type", StaticStrings.drinkCountry).getSingleResult();
		}catch(NoResultException  e){
			return createCountry(new CountryRepresentation(name, languageCode).getCountry());
		}catch(NonUniqueResultException e){
			System.out.println("None unique Country: " + name + ", " + languageCode + ", " + StaticStrings.drinkCountry);
			return null;
		}
	}

	private Country createCountry(Country country) {
		em.persist(country);
		return country;
	}

}
