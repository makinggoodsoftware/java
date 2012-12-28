package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;

public class OnGoingChildAddition<T>{
	private final BaseLayoutConstructionStrategyStrategy<T> baseLayoutConstruction;
	private final View cellContent;
	private T specifics;

	public OnGoingChildAddition(BaseLayoutConstructionStrategyStrategy<T> baseLayoutConstruction, View cellContent) {
		this.baseLayoutConstruction = baseLayoutConstruction;
		this.cellContent = cellContent;
	}

	public void into(T specifics) {
		this.specifics = specifics;
		baseLayoutConstruction.doAdd(this);
	}

	public View getCellContent() {
		return cellContent;
	}

	public T getSpecifics() {
		return specifics;
	}
}
