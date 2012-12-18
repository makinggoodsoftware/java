package com.mgs.fantasi.ui.driver;

import com.mgs.fantasi.measurements.Fraction;
import com.mgs.fantasi.measurements.Fractions;
import com.mgs.fantasi.measurements.Measurement;
import com.mgs.fantasi.measurements.Measurements;
import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.driver.swing.JPanelWithDifferentShape;
import com.mgs.fantasi.ui.profile.BorderDefinition;
import com.mgs.fantasi.ui.profile.UIProfile;
import com.mgs.fantasi.ui.profile.UIStyle;
import com.mgs.fantasi.ui.wireframe.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
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

	protected final void processEmptyStructure(JPanel nativeContainer){

	}

	protected final void processSimpleStructure(JPanel nativeElement, Renderable content, UIProfile uiProfile){
		nativeElement.setLayout(new GridBagLayout());
		nativeElement.add(create(content, uiProfile), intoCoordinates(0, 0, Fractions.all(), Fractions.all()));
	}

	protected final JPanel decorateWithMargin(JPanel nativeElement, Margin margin){
		JPanel marginContainer = new JPanel();
		marginContainer.setOpaque(false);
		marginContainer.setLayout(new GridBagLayout());
		int top = resolveMeasurement (margin.getTop());
		int right = resolveMeasurement (margin.getRight());
		int bottom = resolveMeasurement (margin.getBottom());
		int left = resolveMeasurement (margin.getLeft());
		marginContainer.setBorder(BorderFactory.createEmptyBorder(top, right, bottom, left));
		marginContainer.add(nativeElement, intoCoordinates(0,0, Fractions.all(), Fractions.all()));
		return marginContainer;
	}

	private int resolveMeasurement(Measurement measurement) {
		if (measurement instanceof Measurements.SimpleMeasurement){
			return ((Measurements.SimpleMeasurement) measurement).getIntValue();
		}
		return 0;
	}

	protected final void processLayerChilds(final JPanel parentNativeElement, Layers<Renderable> content, final UIProfile uiProfile){
		parentNativeElement.setLayout(new OverlayLayout(parentNativeElement));
		content.iterateInCrescendo(new LayerIterator<Renderable>() {
			@Override
			public void on(int zIndex, Renderable layer) {
				JPanel childLayerAsNativeElement = create(layer, uiProfile);
				parentNativeElement.add(childLayerAsNativeElement, zIndex);
			}
		});
	}

	protected final void processGridChilds(final JPanel parentNativeElement, Grid<Renderable> childContent, final UIProfile uiProfile){
		parentNativeElement.setLayout(new GridBagLayout());
		childContent.itereateCellsWith(new CellIterator<Renderable>() {
			@Override
			public void on(int x, int y, CellContent<Renderable> cell) {
				if (cell == null) {
					throw new RuntimeException
							("Error building the UI native element when inspecting the content of the original" +
									" wireframe. This should not happen ever! There must have been an error on the" +
									" createRenderable call previous to the transformation into a native UI element must be badly constructed");
				}
				Renderable child = cell.getContent();
				JPanel childAsNativeComponent = create(child, uiProfile);
				parentNativeElement.add(childAsNativeComponent, intoCoordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio()));
			}
		});
	}


	private JPanel newRectangularNativeElementSkeletonWithStyles(Set<UIStyle> uiStyles) {
		JPanel jPanel = newRectangularNativeElement();
		for (UIStyle uiStyle : uiStyles) {
			applyStyle(uiStyle, jPanel);
		}
		return jPanel;
	}

	protected final JPanel newRectangularNativeElement(){
		JPanel jPanel = new JPanel();
		jPanel.setOpaque(false);
		return jPanel;
	}

	protected final JPanel newNonRectangularNativeElementSkeletonWithStyles(PolygonPointsIterator shape, Set<UIStyle> uiStyles){
		return new JPanelWithDifferentShape(shape, uiStyles);
	}

	public final void applyStyle(UIStyle uiStyle, JPanel nativeElement){
		BorderDefinition border = uiStyle.getBorder();
		if (border != null){
			Border nativeBorder = BorderFactory.createLineBorder(border.getColor(), border.getWidth());
			nativeElement.setBorder(nativeBorder);
		}
		Color backgroundColor = uiStyle.getBackgroundColor();
		if (backgroundColor!=null){
			nativeElement.setBackground(backgroundColor);
			nativeElement.setOpaque(true);
		}
	}

	private GridBagConstraints intoCoordinates(int x, int y, Fraction widthSizeRatio, Fraction heightSizeRatio) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = widthSizeRatio.toDouble();
		gbc.weighty = heightSizeRatio.toDouble();
		gbc.fill = GridBagConstraints.BOTH;
		return gbc;
	}
}
