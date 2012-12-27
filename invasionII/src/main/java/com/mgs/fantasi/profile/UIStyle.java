package com.mgs.fantasi.profile;

import com.mgs.fantasi.properties.BorderDefinition;
import com.mgs.fantasi.properties.ColorDefinition;


public class UIStyle {
	private PropertyDefinition<ColorDefinition.ColorDefinitionBean> backgroundColorProperty = new NullProperty<ColorDefinition.ColorDefinitionBean>();
    private PropertyDefinition<BorderDefinition.BorderDefinitionBean> borderProperty = new NullProperty<BorderDefinition.BorderDefinitionBean>();


	public UIStyle withBorder(PropertyDefinition<BorderDefinition.BorderDefinitionBean> border) {
		if (border == null) throw new IllegalArgumentException();
		this.borderProperty = border;

		return this;
	}

	public UIStyle withBackgroundColor(PropertyDefinition<ColorDefinition.ColorDefinitionBean> color) {
		if (color == null) throw new IllegalArgumentException();
		this.backgroundColorProperty = color;

		return this;
	}

	public PropertyDefinition<ColorDefinition.ColorDefinitionBean> getBackgroundColorProperty() {
		return backgroundColorProperty;
	}

	public PropertyDefinition<BorderDefinition.BorderDefinitionBean> getBorderProperty() {
		return borderProperty;
	}

}
