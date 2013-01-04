package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;

public class ToBeAddedWithSpecifics<T> {
	private final View content;
	private final T specifics;

	public ToBeAddedWithSpecifics(View content, T specifics) {
		this.content = content;
		this.specifics = specifics;
	}

	public View getContent() {
		return content;
	}

	public T getSpecifics() {
		return specifics;
	}
}
