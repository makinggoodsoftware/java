package com.mgs.fantasi.wireframe.grid;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;

public class CellContent<T> {
	private final T content;
	private final Fraction widthSizeRatio;
	private final Fraction heightSizeRatio;
	private final int x;
	private final int y;

	public CellContent(T content, Fraction heightSizeRatio, Fraction widthSizeRatio, int x, int y) {
		this.content = content;
		this.heightSizeRatio = heightSizeRatio;
		this.widthSizeRatio = widthSizeRatio;
		this.x = x;
		this.y = y;
	}

	public static <T> CellContent<T> evenlyDivided(T content, int x, int y) {
		return new CellContent<T>(content, Fractions.all(), Fractions.all(), x, y);
	}

	public static <T> CellContent<T> withPartialHeight(T content, Fraction heightRatio, int x, int y) {
		return new CellContent<T>(content, heightRatio, Fractions.allWithBase(heightRatio.getBase()), x, y);
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
