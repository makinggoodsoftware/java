package com.mgs.fantasi.properties;

import java.awt.*;

public class BorderDefinition {
	private final Color color;
	private final int width;

	public BorderDefinition(Color color, int width) {
		this.color = color;
		this.width = width;
	}

	public Color getColor() {
		return color;
	}

	public int getWidth() {
		return width;
	}
}
