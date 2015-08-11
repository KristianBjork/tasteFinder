package se.tasteFinder.utils;

import javax.persistence.EntityManagerFactory;

import org.compass.core.Compass;
import org.compass.core.config.CompassConfiguration;
import org.compass.gps.CompassGps;
import org.compass.gps.CompassGpsDevice;
import org.compass.gps.device.jpa.JpaGpsDevice;
import org.compass.gps.device.jpa.lifecycle.TopLinkEssentialsJpaEntityLifecycleInjector;
import org.compass.gps.impl.SingleCompassGps;

import model.Drink;

public class CompassFetcher {
    private static CompassFetcher compassFetcher = null;
	private static Compass compass;
	private static CompassGpsDevice jpaDevice;
	private static CompassGps gps;
    
    private CompassFetcher(EntityManagerFactory emf){
    	try{
    	/* Add Configure to compass */
		compass = new CompassConfiguration().setConnection("/tmp/index")
				.setSetting("compass.transaction.factory", "org.compass.core.transaction.JTASyncTransactionFactory")
				.addScan(Drink.class.getPackage().getName()).buildCompass();
		gps = new SingleCompassGps(compass);
		jpaDevice = new JpaGpsDevice("jpa", emf);
		gps.addGpsDevice(jpaDevice);
		gps.start();

		System.out.println("Starting compass");
		gps.index();
    	}catch (Exception e){
    		System.out.println(e.getMessage());
    	}
    }
    
    public static CompassFetcher getInstance(EntityManagerFactory emf) {
        if (compassFetcher == null) {
        	compassFetcher = new CompassFetcher(emf);
        }	
        return compassFetcher;
    }
    
    public Compass getCompass(){
        return compass;
    }
    
    public void indexCompass(){
    	gps.index();
    }
    
    public CompassGpsDevice getCompassGpsDevice(){
    	return jpaDevice;
    }

}
