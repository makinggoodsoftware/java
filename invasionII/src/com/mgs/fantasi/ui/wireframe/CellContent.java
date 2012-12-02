package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.structures.Fraction;
import com.mgs.fantasi.structures.Fractions;

public class CellContent<T> {
	private final T content;
	private final Fraction widthSizeRatio;
	private final Fraction heightSizeRatio;

	private CellContent(T content, Fraction heightSizeRatio, Fraction widthSizeRatio) {
		this.content = content;
		this.heightSizeRatio = heightSizeRatio;
		this.widthSizeRatio = widthSizeRatio;
	}

	public static <T> CellContent<T> evenlyDivided(T content) {
		return new CellContent<T>(content, Fractions.all(), Fractions.all());
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
}
