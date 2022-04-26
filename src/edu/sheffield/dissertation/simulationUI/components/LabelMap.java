package edu.sheffield.dissertation.simulationUI.components;

import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PVector;

public class LabelMap {
	private Map<String, Label> labelMap;
	private PApplet p;
	
	public LabelMap(PApplet p) {
		this.p = p;
		labelMap = new HashMap<String, Label>();
	}
	
	public void addLabel(String key, String label, PVector handler, ItemStyling labelAppearance) {
		labelMap.put(key, new Label(label, labelAppearance, handler, p));
	}
	
	public void render() {
		labelMap.forEach((t, u) -> u.render());
	}
	
	public void updateLabelText(String key, String newText) {
		labelMap.get(key).setLabel(newText);
	}
}
