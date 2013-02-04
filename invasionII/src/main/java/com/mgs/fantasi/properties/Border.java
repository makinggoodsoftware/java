package com.mgs.fantasi.properties;

public class Border implements UIPropertyData {
	private final UIProperty<Color> color;
	private final int width;

	public Border(int width, UIProperty<Color> color) {
		this.width = width;
		this.color = color;
	}

	public int getWidth() {
		return width;
	}

	public UIProperty<Color> getColor() {
		return color;
	}
}
