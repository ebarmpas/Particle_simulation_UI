package edu.sheffield.dissertation.simulationUI;

public class VerticalLayout extends AbstractSketch {
	
	@Override
	void renderParticle(Particle p) {
    	point(map(p.getLocation().x, 0, 1000, 0, width), map(p.getLocation().y, 0, 1000, 0, width));
	}

	@Override
	void renderLines() {
		line(0, width, width + 1, width);
	}

	@Override
	void setupUserInterface() {
		// TODO Auto-generated method stub
		
	}
}