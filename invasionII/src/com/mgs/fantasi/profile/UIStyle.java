package com.mgs.fantasi.profile;

import com.mgs.fantasi.properties.BorderDefinition;
import com.mgs.fantasi.properties.ColorDefinition;

import static com.mgs.fantasi.profile.UIPropertiesModifierFactory.forDefinition;
import static com.mgs.fantasi.profile.UIPropertiesModifierFactory.ignore;

public class UIStyle {
	private UIPropertiesModifier<BorderDefinition> border = ignore();
	private UIPropertiesModifier<ColorDefinition> backgroundColor = ignore();

	public UIStyle withBorder(BorderDefinition border) {
		if (border == null) throw new IllegalArgumentException();

		this.border = forDefinition(border);
		return this;
	}

	public UIPropertiesModifier<BorderDefinition> getBorderModifier() {
		return border;
	}

	public UIPropertiesModifier<ColorDefinition> getBackgroundColorModifier() {
		return backgroundColor;
	}

	public UIStyle withBackgroundColor(ColorDefinition color) {
		if (color == null) throw new IllegalArgumentException();

		this.backgroundColor = forDefinition(color);
		return this;
	}
}
