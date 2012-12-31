package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.rendering.wireframe.Wireframe;
import com.mgs.fantasi.views.View;

public class OnGoingChildAddition<T, Z extends Wireframe>{
	private final BaseLayoutConstructionStrategyStrategy<T, Z> baseLayoutConstruction;
	private final View content;
	private T specifics;

	public OnGoingChildAddition(BaseLayoutConstructionStrategyStrategy<T, Z> baseLayoutConstruction, View content) {
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
