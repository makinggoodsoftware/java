package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.profile.UIStyle;

import java.util.Set;

public abstract class BaseUINativeElementCreatorStrategy<T> implements UINativeElementCreatorStrategy<T> {
	@Override
	public T create(Wireframe wireframe, Set<UIStyle> uiStyles) {
		PolygonPointsIterator shape = wireframe.getShape();
		if (shape.isRectangular()) {
			T jPanel = newNativeRectangularElement();
			for (UIStyle uiStyle : uiStyles) {
				applyStyle(uiStyle, jPanel);
			}
			return jPanel;
		}
		return newNonRectangularNativeElement (shape, uiStyles);

	}

	protected abstract T newNativeRectangularElement();

	protected abstract T newNonRectangularNativeElement(PolygonPointsIterator shape, Set<UIStyle> uiStyles);

	public abstract void applyStyle(UIStyle uiStyle, T nativeElement);
}
