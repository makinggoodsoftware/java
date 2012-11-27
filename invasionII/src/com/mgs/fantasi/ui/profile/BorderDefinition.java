package com.mgs.fantasi.ui.profile;

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
