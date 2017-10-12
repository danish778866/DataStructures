package tspGeneticAlgorithm;

import java.util.Scanner;

public class TspGeneticAlgorithm {
	
	private static final int NUM_ITERATIONS = 100;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the population size: ");
		int numIndividuals = in.nextInt();
		System.out.print("Enter the number of cities: ");
		int numCities = in.nextInt();
		Population tspPopulation = new Population(numCities, numIndividuals);
		System.out.println("Initially: ");
		//tspPopulation.printPopulation();
		tspPopulation.printFittestIndividual();
		for(int i = 0; i < NUM_ITERATIONS; i++) {
			//System.out.println("Iteration" + i);
			tspPopulation.generateNextPopulation();
		}
		System.out.println("Finally: ");
		//tspPopulation.printPopulation();
		tspPopulation.printFittestIndividual();
		in.close();
	}
	
}