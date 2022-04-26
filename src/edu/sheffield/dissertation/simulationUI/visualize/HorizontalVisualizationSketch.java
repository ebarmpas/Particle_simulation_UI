package edu.sheffield.dissertation.simulationUI.visualize;

import edu.sheffield.dissertation.simulationUI.components.Color;
import edu.sheffield.dissertation.simulationUI.components.ItemStyling;
import processing.core.PApplet;
import processing.core.PVector;

public class HorizontalVisualizationSketch extends AbstractVisualizationSketch {
	public HorizontalVisualizationSketch(PApplet applet) {
		super(applet);
	}

	int margin;
	private ItemStyling timeManipulationButtonStyling, timeManipulatonLabelStyling;

	@Override
	void renderParticle(Particle p) {
		applet.point(PApplet.map(p.getLocation().x, 0, 1000, 0, applet.height), PApplet.map(p.getLocation().y, 0, 1000, 0, applet.height));	
	}

	@Override
	void renderLines() {
		applet.line(applet.height, 0, applet.height + 1, applet.height);
	}

	@Override
	void setupUserInterface() {
		margin = Math.round((applet.width - applet.height)/50);
//    	PApplet.print(applet.width, applet.height, margin, "\n");
		int currentY = margin;
		int baselineX = applet.height + margin;
				
		timeManipulatonLabelStyling = new ItemStyling(new Color(255, 0, 255), 
				new Color(255, 0, 0), 
				new Color(255, 0, 255), 
				margin * 3, (applet.width - applet.height) - (margin * 2), applet.height / 16);
		
		timeManipulationButtonStyling = new ItemStyling(new Color(255, 0, 255), 
				new Color(255, 0, 0), 
				new Color(255, 0, 255), 
				margin * 3, timeManipulatonLabelStyling.getWidth() / 3, applet.height / 16);
		
		applet.background(0);
		
		labels.addLabel("Time", "Time controls", new PVector(applet.height + margin , margin), timeManipulatonLabelStyling);
		currentY += timeManipulatonLabelStyling.getHeight() + margin;
        clickables.addSwitch("PAUSE", ' ', new PVector(baselineX ,  currentY), timeManipulationButtonStyling, true);
        clickables.addSwitch("INVERT", 'r', new PVector(baselineX + timeManipulationButtonStyling.getWidth(), currentY), timeManipulationButtonStyling);
        clickables.addButton("STEP", 's', new PVector(baselineX+ timeManipulationButtonStyling.getWidth() * 2, currentY), timeManipulationButtonStyling);
        currentY += timeManipulationButtonStyling.getHeight();

		
	}
}