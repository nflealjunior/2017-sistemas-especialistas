package br.edu.univas.ag;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class StartApp {

	private Individual bestIndividual;
	private Random random;
	private ArrayList<Individual> currentGeneration;
	private ArrayList<Individual> nextGeneration;
	
	public static void main(String[] args) {
		StartApp app = new StartApp();
		app.execute();
	}
	
	public void execute() {
		random = new Random(7);
		
		createInitialPopulation();
		evaluate();
		int epoch = 1;
		
		while (stopCriteria(epoch)) {
			executeCrossing();
			currentGeneration = nextGeneration;
			executeMutation();
			evaluate();
			epoch++;
		}
	}

	private void executeMutation() {
		// TODO implements
	}

	private void executeCrossing() {
		nextGeneration = new ArrayList<Individual>();
		
		while (nextGeneration.size() != currentGeneration.size()) {
			Individual individualOne = chooseParent();
			Individual individualTwo = chooseParent();
			
			Vector<Integer> positionsIndividualOne = individualOne.getPositions();
			Vector<Integer> positionsIndividualTwo = individualTwo.getPositions();
			
			int crossPoint = random.nextInt(5) + 1;
			
			
		}		
	}
	
	private Individual chooseParent() {
		int position = random.nextInt(currentGeneration.size());
		return currentGeneration.get(position);
	}

	private boolean stopCriteria(int epoch) {
		return Parameter.MAX_ITERATION > epoch;
	}

	private void evaluate() {
		for (Individual individual : currentGeneration) {
			individual.calculateFitness();
			
			if (bestIndividual == null || 
					bestIndividual.getFitness() < individual.getFitness()) {
				bestIndividual = individual;
			}				
		}
	}

	private void createInitialPopulation() {
		currentGeneration = new ArrayList<Individual>();
		
		for (int i = 0; i < Parameter.POPULATION_SIZE; i++) {
			Vector<Integer> positions = new Vector<Integer>();
			
			for (int j = 0; j < Parameter.NUMBER_OF_QUEENS; j++) {
				int position = random.nextInt(8);
				position++;
				positions.add(position);
			}
			
			Individual individual = new Individual(positions);
			currentGeneration.add(individual);
		}		
	}
	
}












