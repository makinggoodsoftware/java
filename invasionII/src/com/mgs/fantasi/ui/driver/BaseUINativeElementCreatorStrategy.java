package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.wireframe.Grid;
import com.mgs.fantasi.ui.wireframe.Layers;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;
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

	protected abstract void processLayerChilds(T nativeElement, Layers<Wireframe> content, UIProfile uiProfile);

	protected abstract void processGridChilds(T nativeElement, Grid<Wireframe> content, UIProfile uiProfile);


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
