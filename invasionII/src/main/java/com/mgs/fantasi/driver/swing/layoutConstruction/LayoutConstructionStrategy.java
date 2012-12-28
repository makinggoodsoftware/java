package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;

import javax.swing.*;

public interface LayoutConstructionStrategy<T> {
	OnGoingChildAddition<T> queueForAddition(View child);

	void buildInto(JPanel container, SwingUINativeRenderer renderer, UIProfile uiProfile);
}
