package com.mgs.fantasi.profile;

import com.mgs.fantasi.properties.BorderDefinition;
import com.mgs.fantasi.properties.ColorDefinition;

import static com.mgs.fantasi.profile.UIPropertiesModifierFactory.ignore;
import static com.mgs.fantasi.profile.UIPropertiesModifierFactory.mergeOld;

public class UIStyle {
	private UIPropertiesMerger<BorderDefinition> border = ignore();
	private UIPropertiesMerger<ColorDefinition> backgroundColor = ignore();
	private ColorDefinition backgroundColorProperty;
	private BorderDefinition borderProperty;

	public UIStyle withBorder(BorderDefinition border) {
		if (border == null) throw new IllegalArgumentException();
		this.borderProperty = border;

		this.border = mergeOld(border);
		return this;
	}

	public UIPropertiesMerger<BorderDefinition> getBorderModifier() {
		return border;
	}

	public UIPropertiesMerger<ColorDefinition> getBackgroundColorModifier() {
		return backgroundColor;
	}

	public UIStyle withBackgroundColor(ColorDefinition color) {
		if (color == null) throw new IllegalArgumentException();
		this.backgroundColorProperty = color;

		this.backgroundColor = mergeOld(color);
		return this;
	}

	public ColorDefinition getBackgroundColorProperty() {
		return backgroundColorProperty;
	}

	public BorderDefinition getBorderProperty() {
		return borderProperty;
	}
}
