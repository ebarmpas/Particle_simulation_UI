package edu.sheffield.dissertation.simulationUI.visualize;

import processing.core.PVector;

public class Agent {
	private PVector location;
	private int species;
	
	public Agent(PVector location, int species) {
		this.location = location;
		this.species = species;
	}

	public PVector getLocation() {
		return location;
	}
	public int getSpecies() {
		return species;
	}
	
}