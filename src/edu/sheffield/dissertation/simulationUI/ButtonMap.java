package edu.sheffield.dissertation.simulationUI;

import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
public class ButtonMap {
	private Map<String, Button> buttons;
	private PApplet p;
	public ButtonMap(PApplet p){
		this.p = p;
		buttons = new HashMap<String, Button>();
	}
	
	public void addButton(String key, char value) {
		buttons.put(key, new Button(value, p));
	}
	
	public void addButton(String key, char value, boolean isPressed) {
		buttons.put(key, new Button(value, isPressed, p));
	}
	
	public boolean isPressed(String key) {
		return buttons.get(key).isPressed();
	}
}