package com.mgs.fantasi.driver.swing.layoutConstruction;

import javax.swing.*;

public class OnGoingChildContentConstruction<T>{
	private final OnGoingLayoutConstructionImpl onGoingLayoutConstruction;
	private final JPanel cellContent;
	private T specifics;

	public OnGoingChildContentConstruction(OnGoingLayoutConstructionImpl onGoingLayoutConstruction, JPanel cellContent) {
		this.onGoingLayoutConstruction = onGoingLayoutConstruction;
		this.cellContent = cellContent;
	}

	public void into(T specifics) {
		this.specifics = specifics;
		onGoingLayoutConstruction.doAdd(this);
	}

	public JPanel getCellContent() {
		return cellContent;
	}

	public T getSpecifics() {
		return specifics;
	}
}
