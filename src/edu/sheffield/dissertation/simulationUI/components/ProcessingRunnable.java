package edu.sheffield.dissertation.simulationUI.components;

import processing.core.PApplet;

public abstract class ProcessingRunnable {

	protected PApplet applet;
	
	protected ClickableMap clickables;
	protected LabelMap labels;
	
	public abstract int step();
}
