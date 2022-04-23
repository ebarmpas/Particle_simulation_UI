package edu.sheffield.dissertation.simulationUI;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Renderable {
	
	protected PApplet p;
	protected String label;
	protected ItemStyling styling;
	protected PVector handler;
	public Renderable(String label, ItemStyling styling, PVector handler, PApplet p) {
		this.label = label;
		this.styling = styling;
		this.handler = handler;
		this.p = p;
	}
	public void render() {
		 styling.getStrokeColor().useStroke();
		 styling.getFillColor().useFill();
		 p.rectMode(PConstants.CORNER);
		
		 p.rect(handler.x, handler.y, styling.getWidth(),styling.getHeight());
		
		 styling.getTextColor().useFill();
		 p.textSize(styling.getTextSize());
		 p.textAlign(PConstants.CENTER, PConstants.CENTER);
		
		 p.text(label, handler.x +  (styling.getWidth() / 2), + handler.y +  (styling.getHeight() / 2));
	}
	
}
