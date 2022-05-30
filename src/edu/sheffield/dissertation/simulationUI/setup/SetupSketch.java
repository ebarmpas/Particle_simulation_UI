package edu.sheffield.dissertation.simulationUI.setup;

import edu.sheffield.dissertation.simulationUI.components.ClickableMap;
import edu.sheffield.dissertation.simulationUI.components.Color;
import edu.sheffield.dissertation.simulationUI.components.ItemStyling;
import edu.sheffield.dissertation.simulationUI.components.ProcessingRunnable;
import edu.sheffield.dissertation.simulationUI.components.UseColor;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class SetupSketch extends ProcessingRunnable {
	
	private ItemStyling buttonStyling;
	public SetupSketch(PApplet applet) {
		this.applet = applet;
		
//		PApplet.println(applet.width, applet.height);
		applet.colorMode(PConstants.RGB);
		clickables = new ClickableMap(applet);
		buttonStyling = new ItemStyling(new Color(255F, 255F, 255F), 
				new Color(0F, 0F, 0F), 
				new Color(255F, 255F, 255F), 
				(applet.width - applet.height) / 10, applet.width / 3, applet.height / 4);
		
		
		clickables.addButton("New simulation", 'n', new PVector(0, applet.height / 8), buttonStyling);
		clickables.addButton("Load simulation", 'l', new PVector(buttonStyling.getWidth(), applet.height / 8) , buttonStyling);
		clickables.addButton("Exit", 'e', new PVector(buttonStyling.getWidth() * 2, applet.height / 8), buttonStyling);
	}


	public int step() {
		applet.background(0);
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
