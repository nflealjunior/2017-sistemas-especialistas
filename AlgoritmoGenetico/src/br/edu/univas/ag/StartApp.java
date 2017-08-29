package br.edu.univas.ag;

import java.util.ArrayList;

public class StartApp {

	private ArrayList<Individual> currentGeneration;
	private ArrayList<Individual> nextGeneration;
	 
	public static void main(String[] args) {
		StartApp app = new StartApp();
		app.execute();
	}
	
	public void execute() {
		
		createInitialPopulation();
		evaluate();
		int epoch = 1;
		
		while (stopCriteria()) {
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
		// TODO implements
	}

	private boolean stopCriteria() {
		// TODO implements
		return false;
	}

	private void evaluate() {
		// TODO implements
	}

	private void createInitialPopulation() {
		// TODO implements
	}
	
}
