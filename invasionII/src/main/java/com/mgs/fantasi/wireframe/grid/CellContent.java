package com.mgs.fantasi.wireframe.grid;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.views.View;

public class CellContent {
	private final View content;
	private final Fraction widthSizeRatio;
	private final Fraction heightSizeRatio;

	public CellContent(View content, Fraction heightSizeRatio, Fraction widthSizeRatio) {
		this.content = content;
		this.heightSizeRatio = heightSizeRatio;
		this.widthSizeRatio = widthSizeRatio;
	}

	public static CellContent evenlyDivided(View content) {
		return new CellContent(content, Fractions.all(), Fractions.all());
	}

	public static CellContent withPartialHeight(View content, Fraction heightRatio) {
		return new CellContent(content, heightRatio, Fractions.allWithBase(heightRatio.getBase()));
	}

	public View getContent() {
		return content;
	}

	public Fraction getWidthSizeRatio() {
		return widthSizeRatio;
	}

	public Fraction getHeightSizeRatio() {
		return heightSizeRatio;
	}

	@Override
	public String toString() {
		return "CellContent{" +
				"content=" + content +
				", widthSizeRatio=" + widthSizeRatio +
				", heightSizeRatio=" + heightSizeRatio +
				'}';
	}
}
