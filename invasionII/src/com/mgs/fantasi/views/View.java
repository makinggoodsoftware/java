package com.mgs.fantasi.views;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.wireframe.*;

public interface View extends Structurable{
	public View newCopy();

	PolygonPointsIterator getShape();

	Margin getMargin();

	String getName();

	Wireframe<View> toWireframe();
}
