package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.PropertyDefinition;

import java.awt.*;

public class ColorDefinition implements PropertyDefinition<ColorDefinition> {
	private Color color;

	private ColorDefinition(Color color) {
		this.color = color;
	}

	public static ColorDefinition noColor() {
		return new ColorDefinition(null);
	}
	
	public static ColorDefinition colorFromAwtColor(Color color){
		return new ColorDefinition(color);
	}

	public Color asAwtColor (){
		return color;
	}

	public boolean isDefined() {
		return color != null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ColorDefinition)) return false;

		ColorDefinition that = (ColorDefinition) o;

		if (color != null ? !color.equals(that.color) : that.color != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return color != null ? color.hashCode() : 0;
	}

	@Override
	public ColorDefinition copy() {
		return new ColorDefinition(color);
	}
}
