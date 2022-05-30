package edu.sheffield.dissertation.simulationUI.visualize;

public class Stats {
	
	private int count;
	
	private double attractionMultiplier;
	private double repulsionMultiplier;
	private double forceMultiplier;
	
	private double visionRange;
	private double damage;
	
	private double libido;
	private double age;
	private double health;
	private double energy;
	
	
	
	public Stats(int count, double attractionMultiplier, double repulsionMultiplier, double forceMultiplier, double visionRange,
			double damage, double libido, double age, double health, double energy) {
		this.count = count;

		this.attractionMultiplier = attractionMultiplier;
		this.repulsionMultiplier = repulsionMultiplier;
		this.forceMultiplier = forceMultiplier;
		
		this.visionRange = visionRange;
		this.damage = damage;
		
		this.libido = libido;
		this.age = age;
		this.health = health;
		this.energy = energy;
	}
	public int getCount() {
		return count;
	}
	public double getAttractionMultiplier() {
		return attractionMultiplier;
	}
	public double getRepulsionMultiplier() {
		return repulsionMultiplier;
	}
	public double getForceMultiplier() {
		return forceMultiplier;
	}
	public double getVisionRange() {
		return visionRange;
	}
	public double getDamage() {
		return damage;
	}
	public double getLibido() {
		return libido;
	}
	public double getAge() {
		return age;
	}
	public double getHealth() {
		return health;
	}
	public double getEnergy() {
		return energy;
	}
	
	
}
