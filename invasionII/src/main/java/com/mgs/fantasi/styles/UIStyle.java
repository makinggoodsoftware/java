package com.mgs.fantasi.styles;

import com.mgs.fantasi.properties.Border;
import com.mgs.fantasi.properties.Color;
import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.UIProperty;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.allUndefined;


public class UIStyle {
	private final UIPropertiesBuilder underlyingBuilder = allUndefined();

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
