package tspGeneticAlgorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TourManager {

	private Map<Integer, City> cities;
	private int numCities;
	private static final int MAX_X = 10000;
	private static final int MAX_Y = 10000;
	
	public TourManager(int numCities) {
		cities = new HashMap<Integer, City>();
		this.numCities = numCities;
	}
	
	public City getCity(int id) {
		City retVal = cities.get(id);
		return retVal;
	}
	
	public void createTourMap() {
	    Set<City> citiesAdded = new HashSet<City>();
	    Random randomGenerator = new Random();
	    int i = 0;
	    while(i < numCities) {
	    	int x = randomGenerator.nextInt(MAX_X);
	    	int y = randomGenerator.nextInt(MAX_Y);
	    	City newCity = new City(i + 1, x, y);
	    	if(!citiesAdded.contains(newCity)) {
	    		cities.put(i + 1, newCity);
	    		i++;
	    	}
	    }
	}
	
	public int getNumCities() {
		return numCities;
	}
	
	public void printTourMap() {
		for(Integer key : cities.keySet()) {
			System.out.println("City ID:" + key + " " + cities.get(key));
		}
	}
	
	public static void testTourManager() {
		TourManager tm = new TourManager(25);
		tm.createTourMap();
		tm.printTourMap();
	}
}
	