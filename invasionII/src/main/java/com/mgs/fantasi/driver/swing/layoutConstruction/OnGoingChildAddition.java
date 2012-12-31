package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;

public class OnGoingChildAddition<T>{
	private final BaseLayoutConstructionStrategyStrategy<T> baseLayoutConstruction;
	private final View content;
	private T specifics;

	public OnGoingChildAddition(BaseLayoutConstructionStrategyStrategy<T> baseLayoutConstruction, View content) {
		this.baseLayoutConstruction = baseLayoutConstruction;
		this.content = content;
	}

	public void into(T specifics) {
		this.specifics = specifics;
		baseLayoutConstruction.doAdd(this);
	}

	public View getContent() {
		return content;
	}

	public T getSpecifics() {
		return specifics;
	}
}
