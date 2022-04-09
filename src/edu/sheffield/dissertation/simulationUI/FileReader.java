package edu.sheffield.dissertation.simulationUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

	public static List<Step> parseParticles(SimulationConfiguration simConf) throws FileNotFoundException{
		List<Step> steps = new ArrayList<Step>(simConf.getStepNumber());
		File folder = new File(simConf.getOutputDir()+"/steps");
		
		int stepNum = folder.list().length;
		System.out.println("Starting to parse files...");
		for(int i = 0; i < stepNum; i++) {
			steps.add(new Step(simConf.getOutputDir()+"/steps/step"+i));
		}
		System.out.println("File parsing complete...");


		return steps;
	}
}