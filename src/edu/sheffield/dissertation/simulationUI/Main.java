package edu.sheffield.dissertation.simulationUI;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import processing.core.PApplet;

public class Main extends PApplet {

	SimulationConfiguration simConf;
	ButtonMap buttons;
	List<Step> steps;
	Button test;
	int currentStep, delay, maxDelay;
	
	
    public void setup(){
        frameRate(60);
        surface.setResizable(true);
        surface.setLocation(0, 0);
        surface.setTitle("Particle Simulation");
        
        background(0);
        
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            ".psc files", "psc");
        chooser.setFileFilter(filter);
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	try {
     			simConf = new SimulationConfiguration(chooser.getSelectedFile());
     			steps = FileReader.parseParticles(simConf);
     		} catch (FileNotFoundException e) {
     			e.printStackTrace();
     			exit();
     		}
                
        } else
        	exit();
        
        buttons = new ButtonMap(this);
        buttons.addButton("paused",' ');
        buttons.addButton("rewind", 'r');
        
        delay = 0;
        maxDelay = 10;
        currentStep = 0;
        
        
    }
 
    public void draw(){
    	
    	
   
    
    if(frameCount % 3 == 0 && !buttons.isPressed("paused")) {
    	
    	background(0);
    	stroke(255,0,0);
    	strokeWeight(10);
    	
    	List<Particle> p = steps.get(currentStep).getParticles();

    	for(int i = 0; i < p.size(); i++) {
    		stroke(255, 0 ,0);
    		point(p.get(i).getLocation().x, p.get(i).getLocation().y);
    	}
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
        size(displayWidth, displayHeight);
    }
}