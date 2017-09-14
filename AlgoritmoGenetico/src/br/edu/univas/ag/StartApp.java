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
		random = new Random();
		
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
		
		System.out.println("A melhor resposta foi: " + bestIndividual.toString());
	}

	private void executeMutation() {
		int max = (int) (currentGeneration.size() * 0.02);
		ArrayList<Integer> mutatedIndividualIndex = new ArrayList<Integer>();

		for (int i = 0; i < max; i++) {
			int index = random.nextInt(currentGeneration.size());
			
			while (mutatedIndividualIndex.contains(index)) {
				index = random.nextInt(currentGeneration.size());
			}
			
			mutatedIndividualIndex.add(index);
			Individual individual = currentGeneration.get(index);
			Vector<Integer> positions = individual.getPositions();
			
			int position = random.nextInt(8);
			Integer oldValue = positions.get(position);
			
			if (oldValue == 8) {
				oldValue = 1;
			} else {
				oldValue += 1;
			}
			
			positions.set(position, oldValue);
		}
	}

	private void executeCrossing() {
		nextGeneration = new ArrayList<Individual>();
		
		while (nextGeneration.size() != currentGeneration.size()) {
			Individual individualOne = chooseParent();
			Individual individualTwo = chooseParent();
			
			Vector<Integer> positionsIndividualOne = individualOne.getPositions();
			Vector<Integer> positionsIndividualTwo = individualTwo.getPositions();
			
			int crossPoint = random.nextInt(5) + 1;
			
			Vector<Integer> positionChildOne = new Vector<Integer>();
			
			positionChildOne.addAll(
					positionsIndividualOne.subList(0, crossPoint));
			
			positionChildOne.addAll(
					positionsIndividualTwo.subList(
							crossPoint, positionsIndividualTwo.size()));
			
			Vector<Integer> positionChildTwo = new Vector<Integer>();
			
			positionChildTwo.addAll(
					positionsIndividualTwo.subList(0, crossPoint));
			
			positionChildTwo.addAll(
					positionsIndividualOne.subList(
							crossPoint, positionsIndividualOne.size()));
			
			
			Individual childOne = new Individual(positionChildOne);
			Individual childTwo = new Individual(positionChildTwo);
			nextGeneration.add(childOne);
			nextGeneration.add(childTwo);
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












