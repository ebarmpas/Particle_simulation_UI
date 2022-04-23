package edu.sheffield.dissertation.simulationUI;

import processing.core.PApplet;
import processing.core.PVector;

public class Switch extends Clickable {

	private boolean isPressed;

	public Switch(char assignedKey, String label, PVector handler, ButtonAppearance buttonAppearance, PApplet p) {
		super(assignedKey, label, handler, buttonAppearance, p);
		
		isPressed = false;
	}
	public Switch(char assignedKey, String label, PVector handler, ButtonAppearance buttonAppearance, boolean isPressed, PApplet p) {
		super(assignedKey, label, handler, buttonAppearance, isPressed, p);
		
		this.isPressed = isPressed;

	}
	@Override
	public boolean isPressed() {
		if(((p.mouseX >= handler.x && p.mouseX <= handler.x + buttonAppearance.getButtonWidth() && 
				p.mouseY >= handler.y && p.mouseY <= handler.y + buttonAppearance.getButtonHeight()&& 
				p.mousePressed ) || 
				(p.keyPressed && p.key == assignedKey))
				&& delay >= maxDelay) {
				
				delay = 0;
				isPressed = !isPressed;
			}else
				delay++;
			
			return isPressed;
	}

}
