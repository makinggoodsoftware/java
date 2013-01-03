package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface LayoutConstructionStrategy<T, Z extends Wireframe> {
	OnGoingChildAddition<T, Z> queueForAddition(View child);

	LayoutConstructionStrategy<T, Z> fillWith(Z content);

	List<OnGoingChildAddition<T, Z>> getToBeAdded();

	LayoutManager getLayoutManager(JPanel container);

	boolean isEmpty();
}
