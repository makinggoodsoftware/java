package com.mgs.fantasi.driver.swing.layoutConstruction;

import com.mgs.fantasi.views.View;
import com.mgs.fantasi.wireframe.Wireframe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface LayoutConstructionStrategy<T, Z extends Wireframe<View>> {
	void queueForAddition(View child, T specifics);

	LayoutConstructionStrategy<T, Z> fillWith(Z content);

	List<ToBeAddedWithSpecifics<T>> getToBeAddedWithSpecifics();

	LayoutManager getLayoutManager(JPanel container);

	boolean isEmpty();
}
