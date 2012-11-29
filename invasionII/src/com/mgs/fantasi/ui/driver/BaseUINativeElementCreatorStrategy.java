package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.profile.UIStyle;

import java.util.Set;

public abstract class BaseUINativeElementCreatorStrategy<T> implements UINativeElementCreatorStrategy<T> {
	@Override
	public T createSkeleton(Wireframe wireframe, Set<UIStyle> uiStyles) {
		PolygonPointsIterator shape = wireframe.getShape();
		T nativeElementSkeletonWithStyles = shape.isRectangular() ?
			newRectangularNativeElementSkeletonWithStyles(uiStyles):
			newNonRectangularNativeElementSkeletonWithStyles(shape, uiStyles);
		createLayoutSkeleton (nativeElementSkeletonWithStyles);
		return nativeElementSkeletonWithStyles;

	}

	protected abstract void createLayoutSkeleton(T nativeElementSkeletonWithStyles);

	private T newRectangularNativeElementSkeletonWithStyles(Set<UIStyle> uiStyles) {
		T jPanel = newRectangularNativeElement();
		for (UIStyle uiStyle : uiStyles) {
			applyStyle(uiStyle, jPanel);
		}
		return jPanel;
	}

	protected abstract T newRectangularNativeElement();

	protected abstract T newNonRectangularNativeElementSkeletonWithStyles(PolygonPointsIterator shape, Set<UIStyle> uiStyles);

	public abstract void applyStyle(UIStyle uiStyle, T nativeElement);
}
