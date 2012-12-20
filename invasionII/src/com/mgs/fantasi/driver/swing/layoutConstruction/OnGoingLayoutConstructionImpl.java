package com.mgs.fantasi.driver.swing.layoutConstruction;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OnGoingLayoutConstructionImpl<T> implements OnGoingLayoutConstruction<T> {
	private final LayoutProvider layoutProvider;
	private List<OnGoingChildContentConstruction<T>> toBeAdded = new ArrayList<OnGoingChildContentConstruction<T>>();

	public OnGoingLayoutConstructionImpl(LayoutProvider layoutProvider) {
		this.layoutProvider = layoutProvider;
	}

	@Override
	public OnGoingChildContentConstruction<T> add(JPanel childAsNativeComponent) {
		return new OnGoingChildContentConstruction<T>(this, childAsNativeComponent);
	}

	@Override
	public void buildInto(JPanel container){
		container.setLayout(layoutProvider.getLayoutManager(container));
		for (OnGoingChildContentConstruction onGoingCellLayoutConstruction : toBeAdded) {
			container.add(onGoingCellLayoutConstruction.getCellContent(), onGoingCellLayoutConstruction.getSpecifics());
		}
	}

	public void doAdd(OnGoingChildContentConstruction<T> onGoingCellLayoutConstruction) {
		toBeAdded.add(onGoingCellLayoutConstruction);
	}
}
