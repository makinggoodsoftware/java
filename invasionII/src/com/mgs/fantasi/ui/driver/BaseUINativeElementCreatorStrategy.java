package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.*;
import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.profile.UIStyle;

import java.util.Set;

public abstract class BaseUINativeElementCreatorStrategy<T> implements UINativeElementCreatorStrategy<T> {
	@Override
	public T create(Wireframe wireframe, UIProfile uiProfile) {
		Set<UIStyle> uiStyles = uiProfile.findStylesFor(wireframe);
		PolygonPointsIterator shape = wireframe.getShape();
		T nativeElement = shape.isRectangular() ?
			newRectangularNativeElementSkeletonWithStyles(uiStyles):
			newNonRectangularNativeElementSkeletonWithStyles(shape, uiStyles);
		Margin margin = wireframe.getMargin();
		if (! margin.isEmpty()){
			nativeElement = decorateWithMargin (nativeElement, margin);
		}
		final Structure content = wireframe.getContent();
		if (content instanceof Grid) {
			processGridChilds(nativeElement, (Grid<Wireframe>) content, uiProfile);
		} else if (content instanceof Layers){
			processLayerChilds(nativeElement, (Layers<Wireframe>) content, uiProfile);
		}else{
			throw new RuntimeException("Can't process the wireframe: " + wireframe);
		}
		return nativeElement;

	}

	protected abstract T decorateWithMargin(T nativeElement, Margin margin);

	protected abstract void processLayerChilds(T parentNativeElement, Layers<Wireframe> content, UIProfile uiProfile);

	protected abstract void processGridChilds(T parentNativeElement, Grid<Wireframe> childContent, UIProfile uiProfile);


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
