package edu.sheffield.dissertation.simulationUI;

import processing.core.PApplet;

public class Color {
	 float a, b, c;
	 PApplet p;
	  Color(float a, float b, float c, PApplet p){
	    this.a = a;
	    this.b = b;
	    this.c = c;
	    this.p = p;
	  }
	  
	  void useStroke(){
	    p.stroke(a,b,c);
	  }
	  
	  void useFill(){
	    p.fill(a,b,c);
	  }
	  
	  
}
