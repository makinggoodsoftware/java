package com.mgs.fantasi.profile;

import com.mgs.fantasi.properties.BorderDefinition;
import com.mgs.fantasi.properties.ColorDefinition;

import static com.mgs.fantasi.properties.BorderDefinition.noBorder;
import static com.mgs.fantasi.properties.ColorDefinition.noColor;

public class UIStyle {
	private BorderDefinition border = noBorder();
	private ColorDefinition backgroundColor = noColor();

	public UIStyle withBorder(BorderDefinition border) {
		if (border == null) throw new IllegalArgumentException();

		this.border = border;
		return this;
	}

	public BorderDefinition getBorder() {
		return border;
	}

	public ColorDefinition getBackgroundColor() {
		return backgroundColor;
	}

	public UIStyle withBackground(ColorDefinition color) {
		if (color == null) throw new IllegalArgumentException();

		this.backgroundColor = color;
		return this;
	}
}
