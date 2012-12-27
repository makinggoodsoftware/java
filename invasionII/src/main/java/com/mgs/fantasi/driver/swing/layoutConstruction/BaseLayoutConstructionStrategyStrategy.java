package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.rendering.Renderable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseLayoutConstructionStrategyStrategy<T> implements LayoutConstructionStrategy<T> {
	protected final LayoutProvider layoutProvider;
	private List<OnGoingChildAddition<T>> toBeAdded = new ArrayList<OnGoingChildAddition<T>>();

	public BaseLayoutConstructionStrategyStrategy(LayoutProvider layoutProvider) {
		this.layoutProvider = layoutProvider;
	}

	@Override
	public OnGoingChildAddition<T> queueForAddition(Renderable child) {
		return new OnGoingChildAddition<T>(this, child);
	}

	@Override
	public void buildInto(JPanel container, SwingUINativeRenderer renderer){
		container.setLayout(layoutProvider.getLayoutManager(container));
		for (OnGoingChildAddition onGoingChildAddition : toBeAdded) {
			Renderable content = onGoingChildAddition.getCellContent();
			JPanel uiNativeElement = renderer.render(content);
			container.add(uiNativeElement, onGoingChildAddition.getSpecifics());
		}
	}

	public void doAdd(OnGoingChildAddition<T> onGoingCellLayoutConstruction) {
		toBeAdded.add(onGoingCellLayoutConstruction);
	}
}
