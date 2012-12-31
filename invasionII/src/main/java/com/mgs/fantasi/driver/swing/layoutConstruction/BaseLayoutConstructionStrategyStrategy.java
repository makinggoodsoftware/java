package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.rendering.wireframe.Wireframe;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseLayoutConstructionStrategyStrategy<T, Z extends Wireframe> implements LayoutConstructionStrategy<T, Z> {
	protected final LayoutProvider layoutProvider;
	private List<OnGoingChildAddition<T, Z>> toBeAdded = new ArrayList<OnGoingChildAddition<T, Z>>();

	public BaseLayoutConstructionStrategyStrategy(LayoutProvider layoutProvider) {
		this.layoutProvider = layoutProvider;
	}

	@Override
	public OnGoingChildAddition<T, Z> queueForAddition(View child) {
		return new OnGoingChildAddition<T, Z>(this, child);
	}

	@Override
	public void buildInto(JPanel container, SwingUINativeRenderer renderer, UIProfile uiProfile){
		container.setLayout(layoutProvider.getLayoutManager(container));
		for (OnGoingChildAddition onGoingChildAddition : getToBeAdded()) {
			View content = onGoingChildAddition.getContent();
			JPanel uiNativeElement = renderer.render(content, uiProfile);
			container.add(uiNativeElement, onGoingChildAddition.getSpecifics());
		}
	}

	public void doAdd(OnGoingChildAddition<T, Z> onGoingCellLayoutConstruction) {
		getToBeAdded().add(onGoingCellLayoutConstruction);
	}


    @Override
    public List<OnGoingChildAddition<T, Z>> getToBeAdded() {
        return toBeAdded;
    }
}
