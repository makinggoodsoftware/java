package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.rendering.Renderable;

public class OnGoingChildAddition<T>{
	private final BaseLayoutConstructionStrategyStrategy<T> baseLayoutConstruction;
	private final Renderable cellContent;
	private T specifics;

	public OnGoingChildAddition(BaseLayoutConstructionStrategyStrategy<T> baseLayoutConstruction, Renderable cellContent) {
		this.baseLayoutConstruction = baseLayoutConstruction;
		this.cellContent = cellContent;
	}

	public void into(T specifics) {
		this.specifics = specifics;
		baseLayoutConstruction.doAdd(this);
	}

	public Renderable getCellContent() {
		return cellContent;
	}

	public T getSpecifics() {
		return specifics;
	}
}
