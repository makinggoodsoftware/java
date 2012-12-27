package com.mgs.fantasi.profile;

import com.mgs.fantasi.properties.BorderDefinition;
import com.mgs.fantasi.properties.ColorFactory;

import static com.mgs.fantasi.profile.NullProperty.nullProperty;


public class UIStyle {
	private PropertyDefinition<ColorFactory.Color> backgroundColorProperty = nullProperty(ColorFactory.Color.class);
    private PropertyDefinition<BorderDefinition.BorderUI> borderProperty = nullProperty(BorderDefinition.BorderUI.class);


	public UIStyle withBorder(PropertyDefinition<BorderDefinition.BorderUI> border) {
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

	public PropertyDefinition<BorderDefinition.BorderUI> getBorderProperty() {
		return borderProperty;
	}

}
