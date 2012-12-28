package com.mgs.fantasi.styles;

import com.mgs.fantasi.properties.BorderFactory;
import com.mgs.fantasi.properties.ColorFactory;
import com.mgs.fantasi.properties.UIPropertyProvider;

import static com.mgs.fantasi.properties.NullUIProperty.nullProperty;


public class UIStyle {
	private UIPropertyProvider<ColorFactory.Color> backgroundColorUIProperty = nullProperty(ColorFactory.Color.class);
    private UIPropertyProvider<BorderFactory.Border> borderUIProperty = nullProperty(BorderFactory.Border.class);


	public UIStyle withBorder(UIPropertyProvider<BorderFactory.Border> border) {
		if (border == null) throw new IllegalArgumentException();
		this.borderUIProperty = border;

		return this;
	}

	public UIStyle withBackgroundColor(UIPropertyProvider<ColorFactory.Color> color) {
		if (color == null) throw new IllegalArgumentException();
		this.backgroundColorUIProperty = color;

		return this;
	}

	public UIPropertyProvider<ColorFactory.Color> getBackgroundColorUIProperty() {
		return backgroundColorUIProperty;
	}

	public UIPropertyProvider<BorderFactory.Border> getBorderUIProperty() {
		return borderUIProperty;
	}

}
