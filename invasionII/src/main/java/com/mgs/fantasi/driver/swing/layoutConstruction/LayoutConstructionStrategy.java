package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.rendering.Renderable;

import javax.swing.*;

public interface LayoutConstructionStrategy<T> {
	OnGoingChildAddition<T> queueForAddition(Renderable child);

	void buildInto(JPanel container, SwingUINativeRenderer renderer);
}
