package edu.sheffield.dissertation.simulationUI;

import processing.core.PVector;

public class Particle {
	private PVector location;
	private int species;
	
	public Particle(PVector location, int species) {
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