package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface LayoutConstructionStrategy<T, Z extends Wireframe> {
	void queueForAddition(View child, T specifics);

	LayoutConstructionStrategy<T, Z> fillWith(Z content);

	List<OnGoingChildAddition<T>> getToBeAdded();

	LayoutManager getLayoutManager(JPanel container);

	boolean isEmpty();
}
