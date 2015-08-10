package se.tasteFinder.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import model.Drink;

import org.compass.core.Compass;
import org.compass.core.CompassHit;
import org.compass.core.CompassHits;
import org.compass.core.CompassSession;
import org.compass.core.config.CompassConfiguration;
import org.compass.gps.CompassGps;
import org.compass.gps.CompassGpsDevice;
import org.compass.gps.device.jpa.JpaGpsDevice;
import org.compass.gps.impl.SingleCompassGps;

@Stateless
@EJB(beanInterface = SearchBeanLocal.class, name = "InitCompass")
public class SearchBean {

	@PersistenceUnit(unitName = "tasteFinder")
	private EntityManagerFactory emf;

	private Compass compass;
	
	private CompassGpsDevice jpaDevice;

	public SearchBean() {
		/* Add Configure to compass */
		compass = new CompassConfiguration().configure()
				.addScan(Drink.class.getPackage().getName()).buildCompass();
		CompassGps gps = new SingleCompassGps(compass);
		jpaDevice = new JpaGpsDevice("jpa", emf);
		gps.addGpsDevice(jpaDevice);
		gps.start();
		System.out.println("Starting compass");
		gps.index();
	}
	
	public void search(String query){
		try{
			CompassSession session = compass.openSession();
			CompassHits hits = session.find(query);
			for(CompassHit hit : hits){
				System.out.println("This is compass hit: " + hit.data());
			}
		}catch(Exception e){
			System.out.println("Error with compass search: " + e.getMessage());
		}
	}
}
