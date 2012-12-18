package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.profile.UIStyle;
import com.mgs.fantasi.ui.wireframe.*;

import javax.swing.*;
import java.util.Set;

public abstract class BaseUINativeElementCreatorStrategy implements UINativeElementCreatorStrategy<JPanel> {
	@Override
	public JPanel create(Renderable renderable, UIProfile uiProfile) {
		JPanel uiNativeElement = createUINativeElementSkeleton(renderable, uiProfile);
		processStructure(renderable.getContent(), uiProfile, uiNativeElement);

		JPanel outmostPointer = uiNativeElement;
		Margin margin = renderable.getMargin();
		if (! margin.isEmpty()){
			outmostPointer = decorateWithMargin (uiNativeElement, margin);
		}
		return outmostPointer;
	}

	private JPanel createUINativeElementSkeleton(Renderable renderable, UIProfile uiProfile) {
		PolygonPointsIterator shape = renderable.getShape();
		Set<UIStyle> uiStyles = uiProfile.findStylesFor(renderable);
		return shape.isRectangular() ?
			newRectangularNativeElementSkeletonWithStyles(uiStyles):
			newNonRectangularNativeElementSkeletonWithStyles(shape, uiStyles);
	}

	private void processStructure(Structure<Renderable> content, UIProfile uiProfile, JPanel nativeContainer) {
		switch (content.getType()){
			case GRID:
				processGridChilds(nativeContainer, (Grid<Renderable>) content, uiProfile);
				break;
			case LAYERS:
				processLayerChilds(nativeContainer, (Layers<Renderable>) content, uiProfile);
				break;
			case SIMPLE:
				Renderable renderable = ((SimpleStructure<Renderable>) content).getContent();
				if (renderable!=null) processSimpleStructure(nativeContainer, renderable, uiProfile);
				break;
			case EMPTY:
				processEmptyStructure(nativeContainer);
				break;
			case DELEGATE:
				Structure<Renderable> delegate = ((DelegateStructure<Renderable>) content).getContent();
				processStructure(delegate, uiProfile, nativeContainer);
				break;
			default:
				throw new RuntimeException("Can't process the structure: " + content);
		}
	}

	protected abstract void processEmptyStructure(JPanel nativeContainer);

	protected abstract void processSimpleStructure(JPanel nativeElement, Renderable content, UIProfile uiProfile);

	protected abstract JPanel decorateWithMargin(JPanel nativeElement, Margin margin);

	protected abstract void processLayerChilds(JPanel parentNativeElement, Layers<Renderable> content, UIProfile uiProfile);

	protected abstract void processGridChilds(JPanel parentNativeElement, Grid<Renderable> childContent, UIProfile uiProfile);


	private JPanel newRectangularNativeElementSkeletonWithStyles(Set<UIStyle> uiStyles) {
		JPanel jPanel = newRectangularNativeElement();
		for (UIStyle uiStyle : uiStyles) {
			applyStyle(uiStyle, jPanel);
		}
		return jPanel;
	}

	protected abstract JPanel newRectangularNativeElement();

	protected abstract JPanel newNonRectangularNativeElementSkeletonWithStyles(PolygonPointsIterator shape, Set<UIStyle> uiStyles);

	public abstract void applyStyle(UIStyle uiStyle, JPanel nativeElement);
}
