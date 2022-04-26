package edu.sheffield.dissertation.simulationUI.components;

public class ItemStyling {
	private Color strokeColor;
	private Color fillColor;
	private Color textColor;
	private int textSize;
	private int sWidth;
	private int sHeight;
	public ItemStyling(Color strokeColor, Color fillColor, Color textColor, int textSize, int sWidth, int sHeight) {
		super();
		this.strokeColor = strokeColor;
		this.fillColor = fillColor;
		this.textColor = textColor;
		this.textSize = textSize;
		this.sWidth = sWidth;
		this.sHeight = sHeight;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}
	public Color getFillColor() {
		return fillColor;
	}
	public Color getTextColor() {
		return textColor;
	}
	public int getTextSize() {
		return textSize;
	}
	public int getWidth() {
		return sWidth;
	}
	public int getHeight() {
		return sHeight;
	}
	
}