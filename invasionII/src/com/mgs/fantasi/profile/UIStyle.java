package com.mgs.fantasi.profile;

import com.mgs.fantasi.properties.BorderDefinition;
import com.mgs.fantasi.properties.ColorDefinition;

public class UIStyle {
	private PropertyDefinition<ColorDefinition> backgroundColorProperty = new PropertyDefinitionBase.NullPropertyDefinition<ColorDefinition>();
	private PropertyDefinition<BorderDefinition> borderProperty = new PropertyDefinitionBase.NullPropertyDefinition<BorderDefinition>();

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

	public PropertyDefinition<ColorDefinition> getBackgroundColorProperty() {
		return backgroundColorProperty;
	}

	public PropertyDefinition<BorderDefinition> getBorderProperty() {
		return borderProperty;
	}
}
