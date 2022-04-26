package edu.sheffield.dissertation.simulationUI.visualize;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.sheffield.dissertation.simulationUI.components.ClickableMap;
import edu.sheffield.dissertation.simulationUI.components.LabelMap;
import edu.sheffield.dissertation.simulationUI.components.ProcessingRunnable;
import processing.core.PApplet;
import processing.core.PConstants;

public abstract class AbstractVisualizationSketch implements ProcessingRunnable{

private SimulationConfiguration simConf;
	
	protected ClickableMap clickables;
	protected LabelMap labels;
	
	private Thread fileReaderThread;
	
	protected List<Step> steps;
	protected int currentStep;
	private int stepIncrement;
	private int deltaTime;
	protected int activeSpecies;
	
	protected PApplet applet;

	public AbstractVisualizationSketch(PApplet applet) {
		this.applet = applet;
		
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
	        	
	        	FileReader fileReader = new FileReader(simConf);
		        fileReaderThread = new Thread(fileReader);
		        fileReaderThread.start();
		        steps = fileReader.getValue();
		        
		        clickables = new ClickableMap(applet);
		        labels = new LabelMap(applet);
		        setupUserInterface();
		        
		        currentStep = 0;
		        activeSpecies = 0;
		        deltaTime = 3;
		        applet.colorMode(PConstants.HSB, simConf.getSpeciesNumber(), 255, 255);
	        }else {
	        	applet.exitActual();
	        }
	}
 
    public int step(){
    	
    	if((applet.frameCount % deltaTime == 0 && !clickables.isPressed("PAUSE")) || (clickables.isPressed("STEP") && clickables.isPressed("PAUSE"))) {
    		
    		List<Particle> p = steps.get(currentStep).getParticles();
    		applet.background(0);
    		applet.strokeWeight(5);
	    	
	    	for(int i = 0; i < p.size(); i++) {
	    		Particle part = p.get(i);   		
	    		applet.stroke(part.getSpecies(), 255 , 255);
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
    	
    	if(clickables.isPressed("Speed +"))
    		if(deltaTime > 1)
       			deltaTime--;
    	
    	if(clickables.isPressed("Speed -"))
    		deltaTime++;
    	
    	applet.stroke(255, 0, 255);
    	applet.strokeWeight(1);
    	renderLines();
    	clickables.render();
    	labels.render();
    	labels.updateLabelText("Time", "Current step: " + currentStep + " / " + simConf.getStepNumber() + "  Current delta time: " + deltaTime);
		return -1;
    }

    abstract void renderParticle(Particle p);
    abstract void renderLines();
    abstract void setupUserInterface();  
    
}
