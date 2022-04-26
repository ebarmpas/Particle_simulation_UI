package edu.sheffield.dissertation.simulationUI.components;

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
		UseColor.stroke(styling.getStrokeColor(), p);
		UseColor.fill(styling.getFillColor(), p);
		p.rectMode(PConstants.CORNER);
		
		p.rect(handler.x, handler.y, styling.getWidth(),styling.getHeight());
		
		UseColor.fill(styling.getTextColor(), p);
		p.textSize(styling.getTextSize());
		p.textAlign(PConstants.CENTER, PConstants.CENTER);
		
		p.text(label, handler.x +  (styling.getWidth() / 2), + handler.y +  (styling.getHeight() / 2));
	}
	
}
