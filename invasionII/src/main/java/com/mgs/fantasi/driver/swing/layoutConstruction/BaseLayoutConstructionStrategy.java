package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseLayoutConstructionStrategy<T, Z extends Wireframe<View>> implements LayoutConstructionStrategy<T, Z> {
	protected final LayoutProvider layoutProvider;
	private List<ToBeAddedWithSpecifics<T>> toBeAddedWithSpecifics = new ArrayList<ToBeAddedWithSpecifics<T>>();

	public BaseLayoutConstructionStrategy(LayoutProvider layoutProvider) {
		this.layoutProvider = layoutProvider;
	}

	@Override
	public void queueForAddition(View child, T specifics) {
		getToBeAddedWithSpecifics().add(new ToBeAddedWithSpecifics<T>(child, specifics));
	}

	@Override
	public LayoutManager getLayoutManager(JPanel container) {
		return layoutProvider.getLayoutManager(container);
	}

	@Override
	public List<ToBeAddedWithSpecifics<T>> getToBeAddedWithSpecifics() {
		return toBeAddedWithSpecifics;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
