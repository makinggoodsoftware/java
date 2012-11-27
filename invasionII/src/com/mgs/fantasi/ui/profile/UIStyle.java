package com.mgs.fantasi.ui.profile;

import java.awt.*;

public class UIStyle {
	private BorderDefinition border;
	private Color backgroundColor;

	public UIStyle withBorder(BorderDefinition border) {
		this.border = border;
		return this;
	}

	public BorderDefinition getBorder() {
		return border;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public UIStyle withBackground(Color color) {
		this.backgroundColor = color;
		return this;
	}
}
