package edu.sheffield.dissertation.simulationUI.visualize;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PVector;
import processing.data.JSONObject;


public class Step extends PApplet{
	private List<Particle> particles;
	
	public Step(String step) throws FileNotFoundException {
		particles = new ArrayList<Particle>();
		File[] files = new File(step).listFiles(new FilenameFilter(){
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
				particles.add(new Particle(new PVector(loc.getFloat("x"), loc.getFloat("y")), species));
			}
			scan.close();
		}
	}
	public List<Particle> getParticles() {
		return particles;
	}

}