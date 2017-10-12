package tspGeneticAlgorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Population {

	private TourManager tourManager;
	private List<Tour> tours;
	private int numIndividuals;
	private List<Double> tourCosts;
	private List<Integer> probabilities;
	private Tour fittestIndividual;
	
	public Population(int numCities, int numIndividuals) {
		this.numIndividuals = numIndividuals;
		tourManager = new TourManager(numCities);
		tourManager.createTourMap();
		tourManager.printTourMap();
		tours = new ArrayList<Tour>();
		tourCosts = new ArrayList<Double>();
		probabilities = new ArrayList<Integer>();
		for(int i = 0; i < numIndividuals; i++) {
			probabilities.add(0);
		}
		initializePopulation();
		printPopulation();
	}
	
	public void printFittestIndividual() {
		System.out.println(fittestIndividual);
		System.out.println("Fittest Cost: " + fittestIndividual.getTourCost(tourManager));
	}
	
	public Tour rouletteWheelSelection(int probability) {
		int i = 0;
		int currentProb = 0;
		do {
			currentProb = currentProb + probabilities.get(i);
			i++;
		} while((i < probabilities.size()) && (currentProb < probability));
		i--;
		//System.out.println("i here:" + i + "size" + probabilities.size());
		return tours.get(i);
	}
	
	public void generateNextPopulation() {
		int count = 0;
		List<Tour> nextTours = new ArrayList<Tour>();
		Random randomGenerator = new Random();
		int selections = numIndividuals / 2;
		while(count < selections) {
			int probability = randomGenerator.nextInt(100) + 1;
			Tour one = rouletteWheelSelection(probability);
			probability = randomGenerator.nextInt(100) + 1;
			Tour two = rouletteWheelSelection(probability);
			while(one.equals(two)) {
				probability = randomGenerator.nextInt(100) + 1;
				two = rouletteWheelSelection(probability);
			}
			Tour nextOne = one.crossOver(two);
			Tour nextTwo = two.crossOver(one);
			
			//System.out.println("One " + one + "Next One" + nextOne + "Two " + two + "Next Two" + nextTwo);
			
			nextOne.mutateTour();
			nextTwo.mutateTour();
			
			nextTours.add(nextOne);
			nextTours.add(nextTwo);
			
			count++;
		}
		tours = nextTours;
		setPopulationParameters();
	}
	
	public void setPopulationParameters() {
		double minTourCost = fittestIndividual.getTourCost(tourManager);
		
		for(int i = 0; i < tours.size(); i++) {
			double currentCost = tours.get(i).getTourCost(tourManager); 
			tourCosts.set(i, currentCost);
			if(currentCost < minTourCost) {
				minTourCost = currentCost;
				fittestIndividual = tours.get(i);
			}
		}
		
		setPopulationProbabilities();
	}
	
	public void initializePopulation() {
		Set<Tour> toursGenerated = new HashSet<Tour>();
		int count = 0;
		double minCost = Double.MAX_VALUE;
		while(count < numIndividuals) {
			Tour newTour = new Tour(tourManager.getNumCities());
			while(toursGenerated.contains(newTour)) {
				newTour.shuffleTour();
			}
			double currentTourCost = newTour.getTourCost(tourManager);
			if(currentTourCost < minCost) {
				minCost = currentTourCost;
				fittestIndividual = newTour;
			}
			tourCosts.add(currentTourCost);
			toursGenerated.add(newTour);
			tours.add(newTour);
			count++;
		}
		setPopulationProbabilities();
	}
	
	public void setPopulationProbabilities() {
		double sumTourCosts = getSumTourCosts();
		for(int i = 0; i < tourCosts.size(); i++) {
			int probability = (int) ((tourCosts.get(i) / sumTourCosts) * 100);
			probabilities.set(i, probability);
		}
	}
	
	public double getSumTourCosts() {
		double retVal = 0;
		for(int i = 0; i < tourCosts.size(); i++) {
			retVal += tourCosts.get(i);
		}
		return retVal;
	}
	
	public void printPopulation() {
		for(int i = 0; i < tours.size(); i++) {
			System.out.println("Tour" + (i + 1));
			System.out.println(tours.get(i));
			System.out.println("Tour Cost: " + tours.get(i).getTourCost(tourManager));
			System.out.println("Tour Probability: " + probabilities.get(i));
		}
		System.out.println("Fittest Individual = " + fittestIndividual + " Tour Cost = " + fittestIndividual.getTourCost(tourManager));
	}
	
	public static void main(String[] args) {
		Population p = new Population(5, 5);
		System.out.println(p.rouletteWheelSelection(65));
		p.generateNextPopulation();
		p.printPopulation();
	}
	
}