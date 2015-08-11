package se.tasteFinder.ejb;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.compass.core.Compass;
import org.compass.core.CompassHit;
import org.compass.core.CompassHits;
import org.compass.core.CompassSession;

import model.Drink;
import se.tasteFinder.representation.SearchRepresentation;
import se.tasteFinder.utils.CompassFetcher;

@Stateless
@EJB(beanInterface = SearchBeanLocal.class, name = "SearchBean")
public class SearchBean implements SearchBeanLocal {

	@PersistenceContext(unitName = "tasteFinder")
	private EntityManager em;

	@PersistenceUnit(unitName = "tasteFinder")
	private EntityManagerFactory emf;

	public SearchBean() {
	}

	public List<SearchRepresentation> search(String query) {
		Compass compass = CompassFetcher.getInstance(emf).getCompass();
		List<SearchRepresentation> result = new LinkedList<SearchRepresentation>();
		CompassSession session = null;
		try {
			System.out.println("Opening compass session");
			session = compass.openSession();
			System.out.println("Searching for: " + query);
			CompassHits hits = session.find(query);
			for (CompassHit hit : hits) {
				if(hit != null){
					System.out.println("This is compass hit: " + ((Drink)hit.getData()).getDrinkname());
					result.add(new SearchRepresentation(((Drink)hit.getData())));
				}
			}
			return result;
		} catch (Exception e) {
			System.out.println("Error with compass search: " + e.getMessage());
			/* TODO: add fault handling */
		} finally {
			if(session != null)
				session.close();
		}
		return result;
	}
	
}
