package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.measurements.Fraction;
import com.mgs.fantasi.measurements.Fractions;

public class CellContent<T> {
	private final T content;
	private final Fraction widthSizeRatio;
	private final Fraction heightSizeRatio;

	public CellContent(T content, Fraction heightSizeRatio, Fraction widthSizeRatio) {
		this.content = content;
		this.heightSizeRatio = heightSizeRatio;
		this.widthSizeRatio = widthSizeRatio;
	}

	public static <T> CellContent<T> evenlyDivided(T content) {
		return new CellContent<T>(content, Fractions.all(), Fractions.all());
	}

	public static <T> CellContent<T> withPartialHeight(T content, Fraction heightRatio) {
		return new CellContent<T>(content, heightRatio, Fractions.allWithBase(heightRatio.getBase()));
	}

	public T getContent() {
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
