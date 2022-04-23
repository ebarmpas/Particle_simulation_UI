package edu.sheffield.dissertation.simulationUI;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public abstract class Clickable {
	protected char assignedKey;
	protected PApplet p;
	
	protected String label;
		
	protected ButtonAppearance buttonAppearance;
	protected PVector handler;
	
	protected int delay;
	protected int maxDelay;
	
	public Clickable(char assignedKey, String label, PVector handler, ButtonAppearance buttonAppearance, PApplet p) {
		this.assignedKey = assignedKey;
		this.label = label;
		this.handler = handler;
		this.buttonAppearance = buttonAppearance;
		
		this.p = p;
		
		delay = 0;
		maxDelay = 16;

	}
	public Clickable(char assignedKey, String label, PVector handler, ButtonAppearance buttonAppearance, boolean isPressed, PApplet p) {
		this.assignedKey = Character.toLowerCase(assignedKey);
		this.label = label;
		this.handler = handler;
		this.buttonAppearance = buttonAppearance;
		
		this.p = p;
		
		delay = 0;
		maxDelay = 16;
	}

	abstract public boolean isPressed();
	public void render() {
		 buttonAppearance.getStrokeColor().useStroke();
		 buttonAppearance.getFillColor().useFill();
		 p.rectMode(PConstants.CORNER);
		
		 p.rect(handler.x, handler.y, buttonAppearance.getButtonWidth(),buttonAppearance.getButtonHeight());
		
		 buttonAppearance.getTextColor().useFill();
		 p.textSize(buttonAppearance.getTextSize());
		 p.textAlign(PConstants.CENTER, PConstants.CENTER);
		
		 p.text(label, handler.x +  (buttonAppearance.getButtonWidth() / 2), + handler.y +  (buttonAppearance.getButtonHeight() / 2));
	}
	
	
}
