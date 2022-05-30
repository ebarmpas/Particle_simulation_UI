package edu.sheffield.dissertation.simulationUI.visualize;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.sheffield.dissertation.simulationUI.components.ClickableMap;
import edu.sheffield.dissertation.simulationUI.components.Color;
import edu.sheffield.dissertation.simulationUI.components.ItemStyling;
import edu.sheffield.dissertation.simulationUI.components.LabelMap;
import edu.sheffield.dissertation.simulationUI.components.ProcessingRunnable;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class VisualizationSketch extends ProcessingRunnable{

	private ItemStyling timeManipulationButtonStyling, timeManipulatonLabelStyling;
	private ItemStyling speciesLabelStyling, speciesSelectStyling;
	private ItemStyling backButtonStyling;
	
	private SimulationConfiguration simConf;	
	private Thread fileReaderThread;
	
	private List<Step> steps;
	private int currentStep;
	
	private int stepIncrement;
	private int deltaTime;
	private int activeSpecies;
	
	
	public VisualizationSketch(PApplet applet) {
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
		        
		        while(steps.size() < 1)
					try {
						TimeUnit.MILLISECONDS.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	        }else {
	        	applet.exitActual();
	        }
	}
 
    @SuppressWarnings("deprecation")
	public int step(){
    	
    	if((applet.frameCount % deltaTime == 0 && !clickables.isPressed("PAUSE")) || (clickables.isPressed("STEP") && clickables.isPressed("PAUSE"))) {
    		
    		List<Agent> p =  steps.get(currentStep).getAgents();
    		applet.background(0);
    		applet.strokeWeight(3);
	    	
	    	for(int i = 0; i < p.size(); i++) {
	    		Agent part = p.get(i);   		
	    		applet.stroke(part.getSpecies(), 255 , 255);
	    		renderAgent(part);
	    	}
	    	
	    	if(currentStep == 0 && stepIncrement == -1)
    			currentStep = steps.size() - 1;
	    	else if(currentStep == steps.size() - 1 && stepIncrement == 1)
    			currentStep = 0;
	    	else
	    		currentStep += stepIncrement;
	    	
    	}
    	{
    		Stats s =  steps.get(currentStep).getStat(activeSpecies);
	    	labels.updateLabelText("count", "COUNT: " + s.getCount());
	    	labels.updateLabelText("attractionMultiplier", "ATTRACTION MULTIPLIER: " + s.getAttractionMultiplier());
	    	labels.updateLabelText("repulsionMultiplier", "REPULSION MULTIPLIER: " + s.getRepulsionMultiplier());
	    	labels.updateLabelText("forceMultiplier", "FORCE MULTIPLIER: " + s.getForceMultiplier());
	    	labels.updateLabelText("visionRange", "VISION RANGE: " + s.getVisionRange());
	    	labels.updateLabelText("damage", "DAMAGE: " + s.getDamage());
	    	labels.updateLabelText("libido", "MAX LIBIDO: " + s.getLibido());
	    	labels.updateLabelText("health", "MAX HEALTH: " + s.getHealth());
	    	labels.updateLabelText("age", "MAX AGE: " + s.getAge());
	    	labels.updateLabelText("energy", "MAX ENERGY: " + s.getEnergy());
    	}
    	
    	if(clickables.isPressed("INVERT"))
    		stepIncrement = -1;
    	else
    		stepIncrement = 1;
    	
    	if(clickables.isPressed("SPEED +"))
    		if(deltaTime > 1)
       			deltaTime--;
    	
    	if(clickables.isPressed("SPEED -"))
    		deltaTime++;
    	
    	if(clickables.isPressed("NEXT SPECIES") && activeSpecies < simConf.getSpeciesNumber() - 1) {
    		activeSpecies++;
        	updateSpeciesStyling(speciesLabelStyling);
        	updateSpeciesStyling(speciesSelectStyling);
    		labels.updateLabelText("Species", "SPECIES: " + activeSpecies);
    	}
    		
    	if(clickables.isPressed("PREVIOUS SPECIES") && activeSpecies > 0) {
    		activeSpecies--;
        	updateSpeciesStyling(speciesLabelStyling);
        	updateSpeciesStyling(speciesSelectStyling);
    		labels.updateLabelText("Species", "SPECIES: " + activeSpecies);
    	} 	
    	
    	labels.updateLabelText("Time", "CURRENT STEP: " + currentStep + " / " + steps.size() + "  CURRENT DELTA TIME: " + deltaTime);

    	applet.stroke(255, 0, 255);
    	applet.strokeWeight(1);
    	renderLines();
    	clickables.render();
    	labels.render();
   
    	
    	if(clickables.isPressed("BACK")) {
    		fileReaderThread.stop();
    		return 1;
    	}	
    	else
    		return -1;
    }

    void renderAgent(Agent a) {
		applet.point(PApplet.map(a.getLocation().x, 0, 1000, 0, applet.height), PApplet.map(a.getLocation().y, 0, 1000, 0, applet.height));	

    }
    void renderLines() {
    	applet.line(applet.height, 0, applet.height + 1, applet.height);
    }
    void setupUserInterface() {
    	int margin = Math.round((applet.width - applet.height)/56);
		
		int currentY = margin;
		int baselineX = applet.height + margin;
		
		timeManipulatonLabelStyling = new ItemStyling(new Color(0, 0, 255), 
				new Color(0, 0, 0), 
				new Color(0, 0, 255), 
				margin * 2, (applet.width - applet.height) - (margin * 2), applet.height / 16);
		
		timeManipulationButtonStyling = new ItemStyling(new Color(0, 0, 255), 
				new Color(0, 0, 0), 
				new Color(0, 0, 255), 
				margin * 2, (timeManipulatonLabelStyling.getWidth() - (margin * 4)) / 5, timeManipulatonLabelStyling.getHeight());
		
		speciesLabelStyling = new ItemStyling(new Color(0, 255, 255), 
				new Color(0, 0, 0), 
				new Color(0, 0, 255), 
				margin * 2, timeManipulatonLabelStyling.getWidth(), timeManipulatonLabelStyling.getHeight() * 4/5);
		speciesSelectStyling = new ItemStyling(new Color(0, 255, 255), 
				new Color(0, 0, 0), 
				new Color(0, 0, 255), 
				margin * 2, (timeManipulatonLabelStyling.getWidth() / 2) - (margin / 2) , timeManipulatonLabelStyling.getHeight());
		 backButtonStyling =  new ItemStyling(new Color(0, 0, 255), 
				new Color(0, 0, 0), 
				new Color(0, 0, 255), 
				margin * 2, (timeManipulatonLabelStyling.getWidth() - (margin * 4)) / 5, timeManipulatonLabelStyling.getHeight() * 2 / 3);
		   
		applet.background(0);
		
		labels.addLabel("Time", "TIME CONTROLS", new PVector(baselineX , margin), timeManipulatonLabelStyling);
		currentY += timeManipulatonLabelStyling.getHeight() + margin;
        clickables.addSwitch("PAUSE", ' ', new PVector(baselineX ,  currentY), timeManipulationButtonStyling, true);
        clickables.addSwitch("INVERT", 'r', new PVector(baselineX + timeManipulationButtonStyling.getWidth() + margin, currentY), timeManipulationButtonStyling);
        clickables.addButton("STEP", 's', new PVector(baselineX + timeManipulationButtonStyling.getWidth() * 2 + margin * 2, currentY), timeManipulationButtonStyling);
        clickables.addButton("SPEED -", '-', new PVector(baselineX + timeManipulationButtonStyling.getWidth() * 3 + margin * 3, currentY), timeManipulationButtonStyling);
        clickables.addButton("SPEED +", '+', new PVector(baselineX + timeManipulationButtonStyling.getWidth() * 4 + margin * 4, currentY), timeManipulationButtonStyling);
        currentY += timeManipulationButtonStyling.getHeight() + margin;
        labels.addLabel("Species", "Species: 0", new PVector(baselineX, currentY), speciesLabelStyling);
        currentY += speciesLabelStyling.getHeight() + margin;
        clickables.addButton("PREVIOUS SPECIES", '[', new PVector(baselineX, currentY), speciesSelectStyling);
        clickables.addButton("NEXT SPECIES", ']', new PVector(baselineX + speciesSelectStyling.getWidth() + margin, currentY), speciesSelectStyling);
        
        currentY += speciesSelectStyling.getHeight() + margin;
        labels.addLabel("count", "COUNT: ", new PVector(baselineX, currentY), speciesLabelStyling);
        currentY += speciesLabelStyling.getHeight() + margin;
        labels.addLabel("attractionMultiplier", "ATTRACTION MULTIPLIER: ", new PVector(baselineX, currentY), speciesLabelStyling);
        currentY += speciesLabelStyling.getHeight() + margin;
        labels.addLabel("repulsionMultiplier", "REPULSION MULTIPLIER: ", new PVector(baselineX, currentY), speciesLabelStyling);
        currentY += speciesLabelStyling.getHeight() + margin;
        labels.addLabel("forceMultiplier", "FORCE MULTIPLIER: ", new PVector(baselineX, currentY), speciesLabelStyling);
        currentY += speciesLabelStyling.getHeight() + margin;
        labels.addLabel("visionRange", "VISION RANGE: ", new PVector(baselineX, currentY), speciesLabelStyling);
        currentY += speciesLabelStyling.getHeight() + margin;
        labels.addLabel("damage", "DAMAGE: ", new PVector(baselineX, currentY), speciesLabelStyling);
        currentY += speciesLabelStyling.getHeight() + margin;
        labels.addLabel("libido", "MAX LIBIDO: ", new PVector(baselineX, currentY), speciesLabelStyling);
        currentY += speciesLabelStyling.getHeight() + margin;
        labels.addLabel("health", "MAX HEALTH: ", new PVector(baselineX, currentY), speciesLabelStyling);
        currentY += speciesLabelStyling.getHeight() + margin;
        labels.addLabel("age", "MAX AGE: ", new PVector(baselineX, currentY), speciesLabelStyling);
        currentY += speciesLabelStyling.getHeight() + margin;
        labels.addLabel("energy", "MAX ENERGY: ", new PVector(baselineX, currentY), speciesLabelStyling);
        currentY += speciesLabelStyling.getHeight() + margin;
        
        clickables.addButton("BACK", '`',  new PVector(applet.width - backButtonStyling.getWidth() - margin, currentY), backButtonStyling);
        
    }
    
    private void updateSpeciesStyling(ItemStyling s) {
    	s.getStrokeColor().setA(activeSpecies);
    }
}
