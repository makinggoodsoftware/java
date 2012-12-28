package com.mgs.fantasi.profile;

import com.mgs.fantasi.properties.BorderFactory;
import com.mgs.fantasi.properties.ColorFactory;

import static com.mgs.fantasi.profile.NullProperty.nullProperty;


public class UIStyle {
	private PropertyDefinition<ColorFactory.Color> backgroundColorProperty = nullProperty(ColorFactory.Color.class);
    private PropertyDefinition<BorderFactory.Border> borderProperty = nullProperty(BorderFactory.Border.class);


	public UIStyle withBorder(PropertyDefinition<BorderFactory.Border> border) {
		if (border == null) throw new IllegalArgumentException();
		this.borderProperty = border;

		return this;
	}

	public UIStyle withBackgroundColor(PropertyDefinition<ColorFactory.Color> color) {
		if (color == null) throw new IllegalArgumentException();
		this.backgroundColorProperty = color;

		return this;
	}

	public PropertyDefinition<ColorFactory.Color> getBackgroundColorProperty() {
		return backgroundColorProperty;
	}

	public PropertyDefinition<BorderFactory.Border> getBorderProperty() {
		return borderProperty;
	}

}
