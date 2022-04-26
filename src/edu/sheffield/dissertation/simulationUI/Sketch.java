package edu.sheffield.dissertation.simulationUI;

import edu.sheffield.dissertation.simulationUI.components.ProcessingRunnable;
import edu.sheffield.dissertation.simulationUI.setup.AbstractSetupSketch;
import edu.sheffield.dissertation.simulationUI.visualize.HorizontalVisualizationSketch;
import processing.core.PApplet;

public class Sketch extends PApplet{
	
	private ProcessingRunnable current;
	private int mode;
	public void setup() {
		 frameRate(120);
		 surface.setTitle("Particle Simulation");
	     background(0);

	     mode = 1;

//	     sim.processingSetup();
	}
	
	public void draw() {

		if(mode == 0)
			exit();
		else if(mode == 1)
			current = new AbstractSetupSketch(this);
		else if(mode == 2)
			System.out.println("New sim");
		else if(mode == 3) {
			current = new HorizontalVisualizationSketch(this);
		}
		mode = current.step();

	}
	
	public void settings(){
        size((int)Math.round(displayWidth * 0.8), (int)Math.round(displayHeight * 0.8));
    }
}