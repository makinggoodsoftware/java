package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.rendering.Renderable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class OnGoingLayoutConstructionBaseImpl<T> implements OnGoingLayoutConstruction<T> {
	protected final LayoutProvider layoutProvider;
	private List<OnGoingChildContentConstruction<T>> toBeAdded = new ArrayList<OnGoingChildContentConstruction<T>>();

	public OnGoingLayoutConstructionBaseImpl(LayoutProvider layoutProvider) {
		this.layoutProvider = layoutProvider;
	}

	@Override
	public OnGoingChildContentConstruction<T> add(Renderable child) {
		return new OnGoingChildContentConstruction<T>(this, child);
	}

	@Override
	public void buildInto(JPanel container, SwingUINativeRenderer renderer){
		container.setLayout(layoutProvider.getLayoutManager(container));
		for (OnGoingChildContentConstruction onGoingChildContentConstruction : toBeAdded) {
			Renderable content = onGoingChildContentConstruction.getCellContent();
			JPanel uiNativeElement = renderer.render(content);
			container.add(uiNativeElement, onGoingChildContentConstruction.getSpecifics());
		}
	}

	public void doAdd(OnGoingChildContentConstruction<T> onGoingCellLayoutConstruction) {
		toBeAdded.add(onGoingCellLayoutConstruction);
	}
}
