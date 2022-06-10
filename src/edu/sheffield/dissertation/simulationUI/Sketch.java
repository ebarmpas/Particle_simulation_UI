package edu.sheffield.dissertation.simulationUI;

import edu.sheffield.dissertation.simulationUI.components.ProcessingRunnable;
import edu.sheffield.dissertation.simulationUI.visualize.VisualizationSketch;
import processing.core.PApplet;

public class Sketch extends PApplet{
	
	private ProcessingRunnable current;

	public void setup() {
		 frameRate(120);
		 surface.setTitle("Agent Simulation");
	     background(0);
	     current = new VisualizationSketch(this);
	     
	}
	
	public void draw() {

		current.step();

	}
	
	public void settings(){
        size((int)Math.round(displayWidth * 0.8), (int)Math.round(displayHeight * 0.8));
    }
}