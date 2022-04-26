package edu.sheffield.dissertation.simulationUI.run;

import edu.sheffield.dissertation.simulationUI.components.Color;
import edu.sheffield.dissertation.simulationUI.components.ItemStyling;

import processing.core.PVector;

public class HorizontalRunSketch extends AbstracRunSketch {
	final int margin = Math.round(width/8);
	private ItemStyling timeManipulationButtonStyling, timeManipulatonLabelStyling;

	@Override
	void renderParticle(Particle p) {
    	point(map(p.getLocation().x, 0, 1000, 0, height), map(p.getLocation().y, 0, 1000, 0, height));	
	}

	@Override
	void renderLines() {
		line(height, 0, height + 1, height);
	}

	@Override
	void setupUserInterface() {
		int currentY = margin;
		int baselineX = height + margin;
		timeManipulatonLabelStyling = new ItemStyling(new Color(255, 0, 255, this), 
				new Color(255, 0, 0, this), 
				new Color(255, 0, 255, this), 
				margin * 3, (width - height) - (margin * 2), height / 16);
		timeManipulationButtonStyling = new ItemStyling(new Color(255, 0, 255, this), 
				new Color(255, 0, 0, this), 
				new Color(255, 0, 255, this), 
				margin * 3, timeManipulatonLabelStyling.getWidth() / 3, height / 16);
		
		labels.addLabel("Time", "Time controls", new PVector(height + margin , margin), timeManipulatonLabelStyling);
		currentY += timeManipulatonLabelStyling.getHeight() + margin;
        clickables.addSwitch("PAUSE", ' ', new PVector(baselineX ,  currentY), timeManipulationButtonStyling, true);
        clickables.addSwitch("INVERT", 'r', new PVector(baselineX + timeManipulationButtonStyling.getWidth(), currentY), timeManipulationButtonStyling);
        clickables.addButton("STEP", 's', new PVector(baselineX+ timeManipulationButtonStyling.getWidth() * 2, currentY), timeManipulationButtonStyling);
        currentY += timeManipulationButtonStyling.getHeight();

		
	}
}