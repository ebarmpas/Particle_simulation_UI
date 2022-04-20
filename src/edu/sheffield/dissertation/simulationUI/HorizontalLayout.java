package edu.sheffield.dissertation.simulationUI;

public class HorizontalLayout extends AbstractLayout {

	@Override
	void renderParticle(Particle p) {
    	point(map(p.getLocation().x, 0, 1000, 0, height), map(p.getLocation().y, 0, 1000, 0, height));	
	}

	@Override
	void renderLines() {
		line(height, 0, height + 1, height);
	}
}