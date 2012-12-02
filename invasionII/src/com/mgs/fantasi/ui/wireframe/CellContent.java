package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.structures.Fraction;
import com.mgs.fantasi.structures.Fractions;

public class CellContent<T> {
	private final Fraction sizeRatio;
	private final T content;

	private CellContent(Fraction sizeRatio, T content) {
		this.sizeRatio = sizeRatio;
		this.content = content;
	}

	public static <T> CellContent<T> evenlyDivided(T content) {
		return new CellContent<T>(Fractions.all(), content);
	}

	public T getContent() {
		return content;
	}

	public Fraction getSizeRatio() {
		return sizeRatio;
	}
}
