package edu.sheffield.dissertation.simulationUI;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import processing.core.PApplet;

 public abstract class AbstractSketch extends PApplet {

	private SimulationConfiguration simConf;
	
	protected ClickableMap clickables;
	protected LabelMap labels;
	
	private Thread fileReaderThread;
	
	protected List<Step> steps;
	protected int currentStep;
	private int stepIncrement;
	protected int activeSpecies;
		
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
	        
	        clickables = new ClickableMap(this);
	        labels = new LabelMap(this);
	        
	        setupUserInterface();
	        
	        currentStep = 0;
	        activeSpecies = 0;
	        
	        colorMode(HSB, simConf.getSpeciesNumber(), 255, 255);
        }else
        	exit();
    }
 
    public void draw(){
    	
    	if((frameCount % 3 == 0 && !clickables.isPressed("PAUSE")) || (clickables.isPressed("STEP") && clickables.isPressed("PAUSE"))) {
    		
    		List<Particle> p = steps.get(currentStep).getParticles();
    		background(0);
	    	strokeWeight(5);
	    	
	    	for(int i = 0; i < p.size(); i++) {
	    		Particle part = p.get(i);   		
	    		stroke(part.getSpecies(), 255 , 255);
	    		renderParticle(part);
	    	}
	    	
	    	if(currentStep == 0 && stepIncrement == -1)
    			currentStep = steps.size() - 1;
	    	else if(currentStep == steps.size() - 1 && stepIncrement == 1)
    			currentStep = 0;
	    	else
	    		currentStep += stepIncrement;
    	}
    	
    	if(clickables.isPressed("INVERT"))
    		stepIncrement = -1;
    	else
    		stepIncrement = 1;
    	
    	stroke(255, 0, 255);
    	strokeWeight(1);
    	renderLines();
    	clickables.render();
    	labels.render();
    	labels.updateLabelText("Time", "Time Controls: " + currentStep + " / " + simConf.getStepNumber());
    	
    }

    abstract void renderParticle(Particle p);
    abstract void renderLines();
    abstract void setupUserInterface();
    

    // method used only for setting the size of the window
    public void settings(){
        size((int)Math.round(displayWidth * 0.8), (int)Math.round(displayHeight * 0.8));
    }
    
}