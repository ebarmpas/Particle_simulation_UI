package edu.sheffield.dissertation.simulationUI.components;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Clickable extends Renderable {
	protected char assignedKey;

	
	protected int delay;
	protected int maxDelay;
	
	public Clickable(char assignedKey, String label, PVector handler, ItemStyling styling, PApplet p) {
		super(label, styling, handler, p);
		this.assignedKey = assignedKey;
		this.label = label;
		
		this.p = p;
		
		delay = 0;
		maxDelay = 16;

	}
	public Clickable(char assignedKey, String label, PVector handler, ItemStyling styling, boolean isPressed, PApplet p) {
		super(label, styling, handler, p);
		this.assignedKey = assignedKey;
		this.label = label;
		
		this.p = p;
		
		delay = 0;
		maxDelay = 16;
	}

	abstract public boolean isPressed();

}
