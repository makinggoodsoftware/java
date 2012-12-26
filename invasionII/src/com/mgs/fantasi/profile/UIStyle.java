package com.mgs.fantasi.profile;

import com.mgs.fantasi.properties.BorderDefinition;
import com.mgs.fantasi.properties.ColorDefinition;

public class UIStyle {
	private ColorDefinition backgroundColorProperty;
	private BorderDefinition borderProperty;

	public UIStyle withBorder(BorderDefinition border) {
		if (border == null) throw new IllegalArgumentException();
		this.borderProperty = border;

		return this;
	}

	public UIStyle withBackgroundColor(ColorDefinition color) {
		if (color == null) throw new IllegalArgumentException();
		this.backgroundColorProperty = color;

		return this;
	}

	public ColorDefinition getBackgroundColorProperty() {
		return backgroundColorProperty;
	}

	public BorderDefinition getBorderProperty() {
		return borderProperty;
	}
}
