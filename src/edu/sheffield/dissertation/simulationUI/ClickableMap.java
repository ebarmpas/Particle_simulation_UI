package edu.sheffield.dissertation.simulationUI;

import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PVector;
public class ClickableMap {
	private Map<String, Clickable> pressables;
	private PApplet p;
	public ClickableMap(PApplet p){
		this.p = p;
		pressables = new HashMap<String, Clickable>();
	}
	
	public void addSwitch(String label, char value, PVector handler, ButtonAppearance buttonAppearance) {
		pressables.put(label, new Switch(value, label, handler, buttonAppearance, p));
	}
	
	public void addSwitch(String label, char value, PVector handler, ButtonAppearance buttonAppearance, boolean isPressed) {
		pressables.put(label, new Switch(value, label, handler, buttonAppearance, isPressed, p));
	}
	public void addButton(String label, char value, PVector handler, ButtonAppearance buttonAppearance) {
		pressables.put(label, new Button(value, label, handler, buttonAppearance, p));
	}
	
	public boolean isPressed(String key) {
		return pressables.get(key).isPressed();
	}
	
	public void render() {
		pressables.forEach((t, u) -> u.render());
	}

}
/*if(((p.mouseX >= handler.x && p.mouseX <= handler.x + buttonAppearance.getButtonWidth() && 
p.mouseY >= handler.y && p.mouseY <= handler.y + buttonAppearance.getButtonHeight()&& 
p.mousePressed ) || 
(p.keyPressed && p.key == assignedKey))
&& delay >= maxDelay) {

delay = 0;
isPressed = !isPressed;
}else
delay++;

return isPressed;
*/