package edu.sheffield.dissertation.simulationUI;

import processing.core.PVector;

public class HorizontalLayout extends AbstractLayout {
	final int margin = Math.round(width/8);
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
		simulationControlButtonAppearance = new ButtonAppearance(new Color(255, 0, 255, this), 
				new Color(255, 0, 0, this), 
				new Color(255, 0, 255, this), 
				margin* 3, ((width - height) / 2) - (margin * 2), height/10);
        buttons.addButton("PAUSE", ' ', new PVector(height + margin , 0 + margin), simulationControlButtonAppearance, true);
        buttons.addButton("REWIND", 'r', new PVector(width - margin - simulationControlButtonAppearance.getButtonWidth(), margin), simulationControlButtonAppearance);
		
	}
}