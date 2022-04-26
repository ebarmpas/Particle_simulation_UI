package edu.sheffield.dissertation.simulationUI.setup;

import edu.sheffield.dissertation.simulationUI.components.ClickableMap;
import edu.sheffield.dissertation.simulationUI.components.Color;
import edu.sheffield.dissertation.simulationUI.components.ItemStyling;
import edu.sheffield.dissertation.simulationUI.components.ProcessingRunnable;

import processing.core.PApplet;
import processing.core.PVector;

public class AbstractSetupSketch implements ProcessingRunnable {

	private ClickableMap clickables;
	PApplet applet;
	public AbstractSetupSketch(PApplet applet) {
		this.applet = applet;
		
		PApplet.println(applet.width, applet.height);
		clickables = new ClickableMap(applet);
		ItemStyling buttonStyling = new ItemStyling(new Color(255, 255, 255), 
				new Color(0, 0, 0), 
				new Color(255, 255, 255), 
				(applet.width - applet.height) / 10, applet.width / 3, applet.height / 4);
		
		
		clickables.addButton("New simulation", 'n', new PVector(0, applet.height / 8), buttonStyling);
		clickables.addButton("Load simulation", 'l', new PVector(buttonStyling.getWidth(), applet.height / 8) , buttonStyling);
		clickables.addButton("Exit", 'e', new PVector(buttonStyling.getWidth() * 2, applet.height / 8), buttonStyling);
	}


	public int step() {
		clickables.render();
		
		if(clickables.isPressed("Exit"))
			return 0;
		else if(clickables.isPressed("New simulation"))
			return 2;
		else if(clickables.isPressed("Load simulation"))
			return 3;
		else 
			return -1;
		
	}

}
