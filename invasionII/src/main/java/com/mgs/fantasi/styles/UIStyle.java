package com.mgs.fantasi.styles;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.data.Border;
import com.mgs.fantasi.properties.data.Color;

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
