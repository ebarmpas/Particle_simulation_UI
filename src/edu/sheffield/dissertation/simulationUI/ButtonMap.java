package edu.sheffield.dissertation.simulationUI;

import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PVector;
public class ButtonMap {
	private Map<String, Button> buttons;
	private PApplet p;
	public ButtonMap(PApplet p){
		this.p = p;
		buttons = new HashMap<String, Button>();
	}
	
	public void addButton(String label, char value, PVector handler, ButtonAppearance buttonAppearance) {
		buttons.put(label, new Button(value, label, handler, buttonAppearance, p));
	}
	
	public void addButton(String label, char value, PVector handler, ButtonAppearance buttonAppearance, boolean isPressed) {
		buttons.put(label, new Button(value, label, handler, buttonAppearance, isPressed, p));
	}
	
	public boolean isPressed(String key) {
		return buttons.get(key).isPressed();
	}
	
	public void renderButtons() {
		buttons.forEach((t, u) -> u.render());
	}
	
	public void checkIfClicked() {

		buttons.forEach((t, u) -> u.isClicked());
	}
}