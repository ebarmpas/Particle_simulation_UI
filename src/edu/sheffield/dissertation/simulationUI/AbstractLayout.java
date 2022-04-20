package edu.sheffield.dissertation.simulationUI;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import processing.core.PApplet;

 public abstract class AbstractLayout extends PApplet {

	private SimulationConfiguration simConf;
	protected ButtonMap buttons;
	
	private Thread fileReaderThread;
	
	protected List<Step> steps;
	public int currentStep;
		
    public void setup(){
        frameRate(120);
        surface.setTitle("Particle Simulation");
        background(0);
        
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
	        
	        buttons.addButton("paused",' ', true);
	        buttons.addButton("rewind", 'r');

	        currentStep = 0;
	     	        
	        colorMode(HSB, simConf.getSpeciesNumber(), 255, 255);
	        rectMode(CORNER);
        }else
        	exit();
    }
 
    public void draw(){
    	
    	if(frameCount % 3 == 0 && !buttons.isPressed("paused")) {
    		
    		List<Particle> p = steps.get(currentStep).getParticles();
    		background(0);
	    	strokeWeight(5);
	    	
	    	for(int i = 0; i < p.size(); i++) {
	    		Particle part = p.get(i);   		
	    		stroke(part.getSpecies(), 255 , 255);
	    		renderParticle(part);
	    	}
	   }
    	
	   stroke(255, 0, 255);
	   strokeWeight(1);
	   renderLines();
	   
	   handleEvents();
    }

    abstract void renderParticle(Particle p);
    abstract void renderLines();
    
    private void handleEvents() {
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

    // method used only for setting the size of the window
    public void settings(){
        size((int)Math.round(displayWidth * 0.8), (int)Math.round(displayHeight * 0.8));
    }
    
}