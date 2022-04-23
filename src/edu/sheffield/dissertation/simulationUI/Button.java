package edu.sheffield.dissertation.simulationUI;

import processing.core.PApplet;
import processing.core.PVector;

public class Button extends Clickable {

	public Button(char assignedKey, String label, PVector handler, ItemStyling buttonAppearance, PApplet p) {
		super(assignedKey, label, handler, buttonAppearance, p);
	}
	
	@Override
	public boolean isPressed() {
		if(((p.mouseX >= handler.x && p.mouseX <= handler.x + styling.getWidth() && 
				p.mouseY >= handler.y && p.mouseY <= handler.y + styling.getHeight()&& 
				p.mousePressed ) || 
				(p.keyPressed && p.key == assignedKey))
				&& delay >= maxDelay) {
				delay = 0;
				return true;
			}else {
				delay++;
				return false;
			}
	}

}
