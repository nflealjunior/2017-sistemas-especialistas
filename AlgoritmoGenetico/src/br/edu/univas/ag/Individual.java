package br.edu.univas.ag;

import java.util.Vector;

public class Individual {
	
	//esse vetor irá conter:
	//  * cada posição do vetor representa a coluna do tabuleiro
	//	* cada valor dentro do vetor representa a linha do tabuleiro
	private Vector<Integer> positions;
	
	//essa é a nota do individuo
	//quanto maior -> melhor
	private int fitness;

	public Vector<Integer> getPositions() {
		return positions;
	}

	public void setPositions(Vector<Integer> positions) {
		this.positions = positions;
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
	
}
