package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.rendering.Renderable;

public class OnGoingChildContentConstruction<T>{
	private final OnGoingLayoutConstructionBaseImpl<T> onGoingLayoutConstruction;
	private final Renderable cellContent;
	private T specifics;

	public OnGoingChildContentConstruction(OnGoingLayoutConstructionBaseImpl<T> onGoingLayoutConstruction, Renderable cellContent) {
		this.onGoingLayoutConstruction = onGoingLayoutConstruction;
		this.cellContent = cellContent;
	}

	public void into(T specifics) {
		this.specifics = specifics;
		onGoingLayoutConstruction.doAdd(this);
	}

	public Renderable getCellContent() {
		return cellContent;
	}

	public T getSpecifics() {
		return specifics;
	}
}
