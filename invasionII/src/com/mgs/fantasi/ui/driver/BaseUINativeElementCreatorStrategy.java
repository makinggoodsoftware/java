package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.profile.UIStyle;
import com.mgs.fantasi.ui.wireframe.*;

import java.util.Set;

public abstract class BaseUINativeElementCreatorStrategy<T> implements UINativeElementCreatorStrategy<T> {
	@Override
	public T create(Renderable renderable, UIProfile uiProfile) {
		T uiNativeElement = createUINativeElementSkeleton(renderable, uiProfile);
		processStructure(renderable.getContent(), uiProfile, uiNativeElement);

		T outmostPointer = uiNativeElement;
		Margin margin = renderable.getMargin();
		if (! margin.isEmpty()){
			outmostPointer = decorateWithMargin (uiNativeElement, margin);
		}
		return outmostPointer;
	}

	private T createUINativeElementSkeleton(Renderable renderable, UIProfile uiProfile) {
		PolygonPointsIterator shape = renderable.getShape();
		Set<UIStyle> uiStyles = uiProfile.findStylesFor(renderable);
		return shape.isRectangular() ?
			newRectangularNativeElementSkeletonWithStyles(uiStyles):
			newNonRectangularNativeElementSkeletonWithStyles(shape, uiStyles);
	}

	private void processStructure(Structure<Renderable> content, UIProfile uiProfile, T nativeContainer) {
		if (content instanceof Grid) {
			processGridChilds(nativeContainer, (Grid<Renderable>) content, uiProfile);
		} else if (content instanceof Layers){
			processLayerChilds(nativeContainer, (Layers<Renderable>) content, uiProfile);
		} else if (content instanceof SimpleStructure){
			Renderable renderable = ((SimpleStructure<Renderable>) content).getContent();
			if (renderable!=null) processSimpleStructure(nativeContainer, renderable, uiProfile);
		} else if (content instanceof EmptyStructure){
			processEmptyStructure(nativeContainer);
		} else if (content instanceof DelegateStructure){
			Structure<Renderable> delegate = ((DelegateStructure<Renderable>) content).getContent();
			processStructure(delegate, uiProfile, nativeContainer);
		}else{
			throw new RuntimeException("Can't process the structure: " + content);
		}
	}

	protected abstract void processEmptyStructure(T nativeContainer);

	protected abstract void processSimpleStructure(T nativeElement, Renderable content, UIProfile uiProfile);

	protected abstract T decorateWithMargin(T nativeElement, Margin margin);

	protected abstract void processLayerChilds(T parentNativeElement, Layers<Renderable> content, UIProfile uiProfile);

	protected abstract void processGridChilds(T parentNativeElement, Grid<Renderable> childContent, UIProfile uiProfile);


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
