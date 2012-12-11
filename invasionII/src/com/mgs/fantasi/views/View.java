package com.mgs.fantasi.views;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.wireframe.Margin;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public interface View {
	public Wireframe render();

	public View newCopy();

	boolean constraintsAreSatisfied();

	PolygonPointsIterator getShape();

	Margin getMargin();

	String getName();

	Structure buildLayoutAndChilds();
}
