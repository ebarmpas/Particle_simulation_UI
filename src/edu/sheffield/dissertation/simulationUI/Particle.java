package edu.sheffield.dissertation.simulationUI;

import processing.core.PVector;

public class Particle {
	private PVector location;
	
	public Particle(PVector location) {
		this.location = location;
	}

	public PVector getLocation() {
		return location;
	}
	
	
}
