package edu.sheffield.dissertation.simulationUI.visualize;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PVector;
import processing.data.JSONObject;


public class Step extends PApplet{
	private List<Agent> agents;
	private Map<Integer, Stats> stats;
	
	public Step(String outputDir, int speciesNumber, int step) throws FileNotFoundException {
		stats = new HashMap<Integer, Stats>();
		int capacity = 0;
		for(int i = 0; i < speciesNumber; i++) {
			Scanner scan = new Scanner(new File(outputDir +"/stats/stats"+ step + "/stats" + i +".json"));
			JSONObject s = parseJSONObject(scan.nextLine());
			stats.put(i, new Stats(s.getInt("count"),
					s.getDouble("attractionMultiplier"),
					s.getDouble("repulsionMultiplier"),
					s.getDouble("forceMultiplier"),
					s.getDouble("visionRange"),
					s.getDouble("damage"),
					s.getDouble("libido"),
					s.getDouble("age"),
					s.getDouble("health"),
					s.getDouble("energy")));
			scan.close();
			
			capacity += s.getInt("count");
		}
				
		agents = new ArrayList<Agent>(capacity);
		
		File[] files = new File(outputDir +"/steps/step"+ step).listFiles(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {
				return name.contains(".json") && !name.contains(".crc");
			}
		});
		
		
		for(int i = 0; i < files.length; i++) {
			Scanner scan = new Scanner(files[i]);
			
			while(scan.hasNext()) {
				JSONObject p = parseJSONObject(scan.nextLine());	
				JSONObject loc = p.getJSONObject("location");
				int species = p.getInt("species");
				agents.add(new Agent(new PVector(loc.getFloat("x"), loc.getFloat("y")), species));
			}
			scan.close();
		}
		
	}
	public List<Agent> getAgents() {
		return agents;
	}

	public Stats getStat(int species) {
		return stats.get(species);
	}
	

}