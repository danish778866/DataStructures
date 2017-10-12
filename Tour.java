package tspGeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tour {
	
	private List<Integer> tour;
	private int numCities;
	
	public Tour(int numCities) {
		tour = new ArrayList<Integer>();
		for(int i = 1; i <= numCities; i++) {
			tour.add(i);
		}
		this.numCities = numCities;
	}
	
	public double getTourCost(TourManager tm) {
		double tourCost = 0;
		for(int i = 0; i < tour.size() - 1; i++) {
			City one = tm.getCity(tour.get(i));
			City two = tm.getCity(tour.get(i + 1));
			tourCost = tourCost + one.getDistance(two);
		}
		City one = tm.getCity(tour.get(0));
		City two = tm.getCity(tour.get(tour.size() - 1));
		tourCost += one.getDistance(two);
		return tourCost;
	}
	
	public void shuffleTour() {
		if(tour != null) {
			Random randomGenerator = new Random();
			boolean[] citiesConsidered = new boolean[numCities + 1];
			for(int i = 0; i <= numCities; i++) {
				citiesConsidered[i] = false;
			}
			int i = 0;
			while(i < numCities) {
				int rand = randomGenerator.nextInt(numCities) + 1;
				if(!citiesConsidered[rand]) {
					tour.set(i, rand);
					citiesConsidered[rand] = true;
					i++;
				}
			}
		}
	}
	
	public void mutateTour() {
		Random randomGenerator = new Random();
		int probability = randomGenerator.nextInt(100);
		if(probability <= 20) {
			int i = randomGenerator.nextInt(numCities);
			int j = randomGenerator.nextInt(numCities);
			while(i == j) {
				j = randomGenerator.nextInt(numCities);	
			}
			Integer temp = tour.get(i);
			tour.set(i, tour.get(j));
			tour.set(j, temp);
		}
	}
	
	public void setCityAtIndex(int idx, int val) {
		this.tour.set(idx, val);
	}
	
	public int getCityAtIndex(int idx) {
		return this.tour.get(idx);
	}
	
	public Tour crossOver(Tour anotherTour) {
		Tour newTour = new Tour(numCities);
		Random randomGenerator = new Random();
		int i = randomGenerator.nextInt(numCities);
		int j = randomGenerator.nextInt(numCities);
		while(i == j) {
			j = randomGenerator.nextInt(numCities);	
		}
		if(j < i) {
			int temp = i;
			i = j;
			j = temp;
		}
		//System.out.println(i + " " + j);
		boolean[] citiesConsidered = new boolean[numCities + 1];
		for(int k = 0; k <= numCities; k++) {
			citiesConsidered[k] = false;
		}
		for(int k = i; k <= j; k++) {
			newTour.setCityAtIndex(k, anotherTour.getCityAtIndex(k));
			
			citiesConsidered[anotherTour.getCityAtIndex(k)] = true;
		}
		//System.out.println("New Tour:"+ newTour);
		int currentIdx = 0;
		int k = 0;
		while(k < i) {
			if(!citiesConsidered[this.getCityAtIndex(currentIdx)]) {
				newTour.setCityAtIndex(k, this.getCityAtIndex(currentIdx));
				currentIdx++;
				k++;
			} else {
				currentIdx++;
			}
		}
		//System.out.println("New Tour:"+ k + " " + newTour);
		k = j + 1;
		while(k < numCities) {
			if(!citiesConsidered[this.getCityAtIndex(currentIdx)]) {
				newTour.setCityAtIndex(k, this.getCityAtIndex(currentIdx));
				currentIdx++;
				k++;
			} else {
				currentIdx++;
			}
		}
		//System.out.println("New Tour:"+ k + " " + newTour);
		return newTour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tour == null) ? 0 : tour.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tour other = (Tour) obj;
		if (tour == null) {
			if (other.tour != null)
				return false;
		} else if (!tour.equals(other.tour))
			return false;
		return true;
	}
	
	public int getNumCities() {
		return numCities;
	}
	
	public void mutateTour(int i, int j) {
		int temp = this.getCityAtIndex(i);
		this.setCityAtIndex(i, this.getCityAtIndex(j));
		this.setCityAtIndex(j, temp);
	}
	
	@Override
	public String toString() {
		return "Tour [tour=" + tour + ", numCities=" + numCities + "]";
	}

	public static void main(String[] args) {
		Tour one = new Tour(10);
		System.out.println(one);
		one.shuffleTour();
		System.out.println(one);
		one.shuffleTour();
		System.out.println(one);
	}
	
}