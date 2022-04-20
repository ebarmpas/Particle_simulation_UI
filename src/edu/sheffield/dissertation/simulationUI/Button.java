package edu.sheffield.dissertation.simulationUI;

import processing.core.PApplet;

public class Button {

	private char assignedKey;

	private int delay;
	private int maxDelay;
	private PApplet p;
	private boolean isPressed;
	
	public Button(char assignedKey, PApplet p) {
		this.p = p;
		this.assignedKey = assignedKey;
		isPressed = false;
		delay = 0;
		maxDelay = 16;
	}
	public Button(char assignedKey, boolean isPressed, PApplet p) {
		this.p = p;
		this.assignedKey = assignedKey;
		this.isPressed = isPressed;
		delay = 0;
		maxDelay = 16;
	}

	public boolean isPressed() {
		
		if(p.keyPressed && Character.toLowerCase(p.key) == assignedKey && delay >= maxDelay) {
			delay = 0;
			isPressed = !isPressed;
		}else
			delay++;
		
		return isPressed;
	}
}