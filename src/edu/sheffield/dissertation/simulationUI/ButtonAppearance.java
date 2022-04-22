package edu.sheffield.dissertation.simulationUI;

public class ButtonAppearance {
	private Color strokeColor;
	private Color fillColor;
	private Color textColor;
	private int textSize;
	private int buttonWidth;
	private int buttonHeight;
	public ButtonAppearance(Color strokeColor, Color fillColor, Color textColor, int textSize, int buttonWidth,
			int buttonHeight) {
		super();
		this.strokeColor = strokeColor;
		this.fillColor = fillColor;
		this.textColor = textColor;
		this.textSize = textSize;
		this.buttonWidth = buttonWidth;
		this.buttonHeight = buttonHeight;
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
	public int getButtonWidth() {
		return buttonWidth;
	}
	public int getButtonHeight() {
		return buttonHeight;
	}
	
}