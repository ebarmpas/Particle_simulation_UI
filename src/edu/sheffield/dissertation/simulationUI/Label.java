package edu.sheffield.dissertation.simulationUI;

import processing.core.PApplet;
import processing.core.PVector;

public class Label extends Renderable {

	public Label(String label, ItemStyling styling, PVector handler, PApplet p) {
		super(label, styling, handler, p);
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
}
