package com.mgs.fantasi.properties;

import com.mgs.fantasi.profile.Mergeable;

public class BorderDefinition implements Mergeable<BorderDefinition> {
	private ColorDefinition color;
	private int width;

	public static BorderDefinition noBorder (){
		return new BorderDefinition(ColorDefinition.noColor(), 0);
	}

	public BorderDefinition(ColorDefinition color, int width) {
		this.color = color;
		this.width = width;
	}

	public ColorDefinition getColor() {
		return color;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BorderDefinition)) return false;

		BorderDefinition that = (BorderDefinition) o;

		if (width != that.width) return false;
		if (color != null ? !color.equals(that.color) : that.color != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = color != null ? color.hashCode() : 0;
		result = 31 * result + width;
		return result;
	}

	@Override
	public void merge(BorderDefinition into) {
		into.color = this.color;
		into.width = this.width;
	}
}
