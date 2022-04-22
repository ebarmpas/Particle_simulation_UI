package edu.sheffield.dissertation.simulationUI;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Button {
	private char assignedKey;
	private PApplet p;
	
	private String label;
	
	private int delay;
	private int maxDelay;
	private boolean isPressed;
	
	private ButtonAppearance buttonAppearance;
	private PVector handler;
	
	public Button(char assignedKey, String label, PVector handler, ButtonAppearance buttonAppearance, PApplet p) {
		this.assignedKey = assignedKey;
		this.label = label;
		this.handler = handler;
		this.buttonAppearance = buttonAppearance;
		
		this.p = p;
		
		isPressed = false;
		delay = 0;
		maxDelay = 16;
	}
	public Button(char assignedKey, String label, PVector handler, ButtonAppearance buttonAppearance, boolean isPressed, PApplet p) {
		this.assignedKey = assignedKey;
		this.label = label;
		this.handler = handler;
		this.buttonAppearance = buttonAppearance;
		
		this.p = p;
		
		this.isPressed = isPressed;
		delay = 0;
		maxDelay = 16;
	}

	public boolean isPressed() {
		return isPressed;
	}
	public boolean isClicked() {
		if(((p.mouseX >= handler.x && p.mouseX <= handler.x + buttonAppearance.getButtonWidth() && 
			p.mouseY >= handler.y && p.mouseY <= handler.y + buttonAppearance.getButtonHeight()&& 
			p.mousePressed ) || 
			(p.keyPressed && Character.toLowerCase(p.key) == assignedKey))
			&& delay >= maxDelay) {
			delay = 0;
			isPressed = !isPressed;
		}else
			delay++;
		
		return isPressed;
	}
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
