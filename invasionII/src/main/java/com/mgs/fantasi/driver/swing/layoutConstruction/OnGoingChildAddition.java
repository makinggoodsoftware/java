package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;

public class OnGoingChildAddition<T> {
	private final View content;
	private T specifics;

	public OnGoingChildAddition(View content) {
		this.content = content;
	}

	public View getContent() {
		return content;
	}

	public T getSpecifics() {
		return specifics;
	}

	public void setSpecifics(T specifics) {
		this.specifics = specifics;
	}
}
