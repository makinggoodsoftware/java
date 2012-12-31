package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.driver.swing.SwingUINativeRenderer;
import com.mgs.fantasi.rendering.wireframe.Wireframe;
import com.mgs.fantasi.styles.UIProfile;
import com.mgs.fantasi.views.View;

import javax.swing.*;
import java.util.List;

public interface LayoutConstructionStrategy<T, Z extends Wireframe> {
	OnGoingChildAddition<T, Z> queueForAddition(View child);

	void buildInto(JPanel container, SwingUINativeRenderer renderer, UIProfile uiProfile);

    LayoutConstructionStrategy<T, Z> fillWith(Z content);

    List<OnGoingChildAddition<T, Z>> getToBeAdded();
}
