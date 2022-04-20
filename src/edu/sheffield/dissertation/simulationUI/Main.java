package edu.sheffield.dissertation.simulationUI;

import java.awt.Dimension;
import java.awt.Toolkit;

import processing.core.PApplet;

public class Main {
	public static void main(String[] args) {
		PApplet.main(selectLayout());
	}
	
	private static String selectLayout() {
		String result;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		if(screenSize.getWidth() > screenSize.getHeight())
			result = "edu.sheffield.dissertation.simulationUI.HorizontalLayout";
		else
			result = "edu.sheffield.dissertation.simulationUI.VerticalLayout";
		return result;
	}
}