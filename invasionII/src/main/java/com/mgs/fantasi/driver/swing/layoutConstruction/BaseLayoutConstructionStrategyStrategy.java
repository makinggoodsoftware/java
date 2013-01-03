package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseLayoutConstructionStrategyStrategy<T, Z extends Wireframe> implements LayoutConstructionStrategy<T, Z> {
	protected final LayoutProvider layoutProvider;
	private List<OnGoingChildAddition<T>> toBeAdded = new ArrayList<OnGoingChildAddition<T>>();

	public BaseLayoutConstructionStrategyStrategy(LayoutProvider layoutProvider) {
		this.layoutProvider = layoutProvider;
	}

	@Override
	public void queueForAddition(View child, T specifics) {
		OnGoingChildAddition<T> onGoingCellLayoutConstruction = new OnGoingChildAddition<T>(child);
		onGoingCellLayoutConstruction.setSpecifics(specifics);
		doAdd(onGoingCellLayoutConstruction);
	}

	public void doAdd(OnGoingChildAddition<T> onGoingCellLayoutConstruction) {
		getToBeAdded().add(onGoingCellLayoutConstruction);
	}

	@Override
	public LayoutManager getLayoutManager(JPanel container) {
		return layoutProvider.getLayoutManager(container);
	}

	@Override
	public List<OnGoingChildAddition<T>> getToBeAdded() {
		return toBeAdded;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
