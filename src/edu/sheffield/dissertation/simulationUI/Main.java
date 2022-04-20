package edu.sheffield.dissertation.simulationUI;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import processing.core.PApplet;

public class Main extends PApplet {

	SimulationConfiguration simConf;
	ButtonMap buttons;
	
	Thread fileReaderThread;
	
	List<Step> steps;
	int currentStep;
	
	int smallSide;
	
    public void setup(){
        frameRate(120);
        surface.setTitle("Particle Simulation");
                
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter(".psc files", "psc"));
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	try {
     			simConf = new SimulationConfiguration(chooser.getSelectedFile());
     		} catch (FileNotFoundException e) {
     			simConf = null;
     		}
        }
        if(simConf != null) {
        	                	
        	surface.setTitle("Particle Simulation / " + simConf.getAppName());
        	
        	FileReader fileReader = new FileReader(simConf);
	        fileReaderThread = new Thread(fileReader);
	        fileReaderThread.start();
	        steps = fileReader.getValue();
	        	        
	        buttons = new ButtonMap(this);
	        buttons.addButton("paused",' ');
	        buttons.addButton("rewind", 'r');

	        currentStep = 0;
	        	       
	        if(width > height)
	        	smallSide = height;
	        else
	        	smallSide = width;
	        
	        System.out.println(smallSide);
	        
	        colorMode(HSB, simConf.getSpeciesNumber(), 255, 255);
	        rectMode(CORNER);
        }else
        	exit();
    }
 
    public void draw(){
    
    if(frameCount % 3 == 0 && !buttons.isPressed("paused")) {
    	
    	background(0);
    	strokeWeight(5);
    	
    	List<Particle> p = steps.get(currentStep).getParticles();
    	
    	for(int i = 0; i < p.size(); i++) {
    		Particle part = p.get(i);   		
    		stroke(part.getSpecies(), 255 , 255);
    		point(map(part.getLocation().x, 0, 1000, 0, smallSide), map(part.getLocation().y, 0, 1000, 0, smallSide));
    	}
    	
    	stroke(255, 0, 255);
    	strokeWeight(1);
    	if(smallSide == height)
    		line(smallSide, 0, smallSide + 1, smallSide);
    	else
    		line(0, smallSide, smallSide + 1, smallSide);
    	
    	
    	if(buttons.isPressed("rewind"))
    		if(currentStep == 0)
    			currentStep = steps.size() - 1;
    		else
    			currentStep--;
    	else
    		if(currentStep == steps.size() - 1)
    			currentStep = 0;
    		else
    			currentStep++;
    	}
    }

    // The argument passed to main must match the class name
    public static void main(String[] args) {
    	
        PApplet.main("edu.sheffield.dissertation.simulationUI.Main");
    }

    // method used only for setting the size of the window
    public void settings(){
        size((int)Math.round(displayWidth * 0.8), (int)Math.round(displayHeight * 0.8));
    }
    
}