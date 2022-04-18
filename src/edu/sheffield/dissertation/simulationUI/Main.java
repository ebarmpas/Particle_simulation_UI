package edu.sheffield.dissertation.simulationUI;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import processing.core.PApplet;

public class Main extends PApplet {

	SimulationConfiguration simConf;
	ButtonMap buttons;
	
	FileReader fileReader;
	Thread fileReaderThread;
	
	List<Step> steps;
	int currentStep, delay, maxDelay;
	
    public void setup(){
        frameRate(60);
        surface.setResizable(true);
        surface.setLocation(0, 0);
        surface.setTitle("Particle Simulation");
        
        background(0);
        
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter(".psc files", "psc"));
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	try {
     			simConf = new SimulationConfiguration(chooser.getSelectedFile());
     		} catch (FileNotFoundException e) {
     			e.printStackTrace();
     			exit();
     		}
                
        } else
        	exit();
        
        fileReader = new FileReader(simConf);
        fileReaderThread = new Thread(fileReader);
        
        fileReaderThread.start();
        
        steps = fileReader.getValue();
        
        surface.setTitle("Particle Simulation / " + simConf.getAppName());
        
        
        buttons = new ButtonMap(this);
        buttons.addButton("paused",' ');
        buttons.addButton("rewind", 'r');
        
        delay = 0;
        maxDelay = 10;
        currentStep = 0;

        colorMode(HSB, simConf.getSpeciesNumber(), 255, 255);

        
        
    }
 
    public void draw(){
    
    if(frameCount % 3 == 0 && !buttons.isPressed("paused")) {
    	
    	background(0);
    	strokeWeight(10);
    	
    	List<Particle> p = steps.get(currentStep).getParticles();

    	for(int i = 0; i < p.size(); i++) {
    		stroke(p.get(i).getSpecies(), 255 , 255);
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