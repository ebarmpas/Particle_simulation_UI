package edu.sheffield.dissertation.simulationUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FileReader implements Runnable {
	private SimulationConfiguration simConf;
	List<Step> steps;
	public FileReader(SimulationConfiguration simConf) {
		this.simConf = simConf;
		steps = new ArrayList<Step>(simConf.getStepNumber());
	}
	public void run() {
		File folder = new File(simConf.getOutputDir()+"/steps");
		
		int stepNum = folder.list().length;
		
		System.out.println("Starting to parse files...");
		for(int i = 0; i < stepNum; i++) {
			try {
				steps.add(new Step(simConf.getOutputDir()+"/steps/step"+i));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		System.out.println("File parsing complete...");

	}
	public List<Step> getValue(){
		return steps;
	}
}
