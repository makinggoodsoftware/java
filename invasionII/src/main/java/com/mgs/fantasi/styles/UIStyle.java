package com.mgs.fantasi.styles;

import com.mgs.fantasi.properties.*;


public class UIStyle {
	private final UIPropertiesBuilder underlyingBuilder = new AllUndefinedUIPropertiesBuilder();

	public UIStyle withBorder(UIProperty<Border> border) {
		underlyingBuilder.withBorder(border);
		return this;
	}

	public UIStyle withBackgroundColor(UIProperty<Color> color) {
		underlyingBuilder.withBackgroundColor(color);
		return this;
	}

	public UIPropertiesBuilder getUnderlyingBuilder() {
		return underlyingBuilder;
	}
}
