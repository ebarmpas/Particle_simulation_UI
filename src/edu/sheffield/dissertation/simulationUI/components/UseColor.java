package edu.sheffield.dissertation.simulationUI.components;

import processing.core.PApplet;

public class UseColor {

	public static void stroke(Color c, PApplet p) {
		p.stroke(c.getA(), c.getB(), c.getC());
	}
	public static void fill(Color c, PApplet p) {
		p.fill(c.getA(), c.getB(), c.getC());
	}
}
