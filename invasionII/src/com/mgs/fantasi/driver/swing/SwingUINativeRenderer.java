package com.mgs.fantasi.driver.swing;

import com.mgs.fantasi.driver.UINativeRenderer;
import com.mgs.fantasi.properties.BorderDefinition;
import com.mgs.fantasi.properties.UIProperties;
import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.measurements.Measurements;
import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.rendering.Margin;
import com.mgs.fantasi.rendering.Renderable;
import com.mgs.fantasi.rendering.structure.DelegateStructure;
import com.mgs.fantasi.rendering.structure.SimpleStructure;
import com.mgs.fantasi.rendering.structure.Structure;
import com.mgs.fantasi.rendering.structure.grid.CellContent;
import com.mgs.fantasi.rendering.structure.grid.CellIterator;
import com.mgs.fantasi.rendering.structure.grid.GridStructure;
import com.mgs.fantasi.rendering.structure.layer.LayerIterator;
import com.mgs.fantasi.rendering.structure.layer.LayeredStructure;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public final class SwingUINativeRenderer implements UINativeRenderer<JPanel> {
	@Override
	public JPanel render(Renderable renderable) {
		JPanel uiNativeElement = createUINativeElementSkeleton(renderable.getUIProperties());
		processStructure(renderable.getContent(), uiNativeElement);

		JPanel outmostPointer = uiNativeElement;
		Margin margin = renderable.getMargin();
		if (! margin.isEmpty()){
			outmostPointer = decorateWithMargin (uiNativeElement, margin);
		}
		return outmostPointer;
	}

	private JPanel createUINativeElementSkeleton(UIProperties uiProperties) {
		PolygonPointsIterator shape = uiProperties.getShape();
		return shape.isRectangular() ?
			newRectangularNativeElementSkeletonWithStyles(uiProperties):
			newNonRectangularNativeElementSkeletonWithStyles(uiProperties);
	}

	private void processStructure(Structure<Renderable> content, final JPanel nativeContainer) {
		switch (content.getType()){
			case GRID:
				final GridLayoutConstruction<OnGoingCellLayoutConstruction, GridBagConstraints> onGoingLayoutConstruction = new OnGoingLayoutBuildingStrategyFactory().grid(nativeContainer);
				((GridStructure<Renderable>) content).itereateCellsWith(new CellIterator<Renderable>() {
					@Override
					public void on(int x, int y, CellContent<Renderable> cell) {
						Renderable child = cell.getContent();
						JPanel childAsNativeComponent = render(child);
						onGoingLayoutConstruction.add(childAsNativeComponent).into(coordinates(x, y, cell.getWidthSizeRatio(), cell.getHeightSizeRatio()));
					}
				});
				break;
			case LAYERS:
				processLayerChilds(nativeContainer, (LayeredStructure<Renderable>) content);
				break;
			case SIMPLE:
				Renderable renderable = ((SimpleStructure<Renderable>) content).getContent();
				if (renderable!=null) processSimpleStructure(nativeContainer, renderable);
				break;
			case EMPTY:
				processEmptyStructure(nativeContainer);
				break;
			case DELEGATE:
				Structure<Renderable> delegate = ((DelegateStructure<Renderable>) content).getContent();
				processStructure(delegate, nativeContainer);
				break;
			default:
				throw new RuntimeException("Can't process the structure: " + content);
		}
	}

	protected final void processEmptyStructure(JPanel nativeContainer){

	}

	protected final void processSimpleStructure(JPanel nativeElement, Renderable content){
		nativeElement.setLayout(new GridBagLayout());
		nativeElement.add(render(content), coordinates(0, 0, Fractions.all(), Fractions.all()));
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
		marginContainer.add(nativeElement, coordinates(0, 0, Fractions.all(), Fractions.all()));
		return marginContainer;
	}

	private int resolveMeasurement(Measurement measurement) {
		if (measurement instanceof Measurements.SimpleMeasurement){
			return ((Measurements.SimpleMeasurement) measurement).getIntValue();
		}
		return 0;
	}

	protected final void processLayerChilds(final JPanel parentNativeElement, LayeredStructure<Renderable> content){
		parentNativeElement.setLayout(new OverlayLayout(parentNativeElement));
		content.iterateInCrescendo(new LayerIterator<Renderable>() {
			@Override
			public void on(int zIndex, Renderable layer) {
				JPanel childLayerAsNativeElement = render(layer);
				parentNativeElement.add(childLayerAsNativeElement, zIndex);
			}
		});
	}


	private JPanel newRectangularNativeElementSkeletonWithStyles(UIProperties uiProperties) {
		JPanel jPanel = newRectangularNativeElement();
		applyUIProperties (jPanel, uiProperties);
		return jPanel;
	}

	private void applyUIProperties(JPanel jPanel, UIProperties uiProperties) {
		jPanel.setBackground(uiProperties.getBackgroundColor());
		BorderDefinition border = uiProperties.getBorder();
		if (border!=null){
			Border lineBorder = BorderFactory.createLineBorder(border.getColor(), border.getWidth());
			jPanel.setBorder(lineBorder);
		}else{
			jPanel.setBorder(null);
		}

	}

	protected final JPanel newRectangularNativeElement(){
		JPanel jPanel = new JPanel();
		jPanel.setOpaque(false);
		return jPanel;
	}

	protected final JPanel newNonRectangularNativeElementSkeletonWithStyles(UIProperties uiProperties){
		return new JPanelWithDifferentShape(uiProperties.getShape(), uiProperties);
	}

	private GridBagConstraints coordinates(int x, int y, Fraction widthSizeRatio, Fraction heightSizeRatio) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = widthSizeRatio.toDouble();
		gbc.weighty = heightSizeRatio.toDouble();
		gbc.fill = GridBagConstraints.BOTH;
		return gbc;
	}

	private static interface SwingLayoutStrategy {
		GridLayoutConstruction grid(JPanel jpanel);
	}

	private static class OnGoingLayoutBuildingStrategyFactory implements SwingLayoutStrategy {
		@Override
		public OnGoingGridLayoutConstruction grid(JPanel jpanel){
			return new OnGoingGridLayoutConstruction(jpanel);
		}
	}

	private static interface GridLayoutConstruction <T extends OnGoingChildContentConstruction<Z>, Z>{
		T add(JPanel childAsNativeComponent);
	}

	private static class OnGoingGridLayoutConstruction implements GridLayoutConstruction{
		private final JPanel container;

		public OnGoingGridLayoutConstruction(JPanel container) {
			this.container = container;
			container.setLayout(new GridBagLayout());
		}

		@Override
		public OnGoingCellLayoutConstruction add(JPanel childAsNativeComponent) {
			return new OnGoingCellLayoutConstruction(this, childAsNativeComponent);
		}

		public void doAdd(JPanel cellContent, GridBagConstraints gridBagConstraints) {
			container.add(cellContent, gridBagConstraints);
		}
	}

	private static interface OnGoingChildContentConstruction<T> {
		void into(T gridBagConstraints);
	}

	private static class OnGoingCellLayoutConstruction implements OnGoingChildContentConstruction<GridBagConstraints>{
		private final OnGoingGridLayoutConstruction onGoingGridLayoutConstruction;
		private final JPanel cellContent;

		public OnGoingCellLayoutConstruction(OnGoingGridLayoutConstruction onGoingGridLayoutConstruction, JPanel cellContent) {
			this.onGoingGridLayoutConstruction = onGoingGridLayoutConstruction;
			this.cellContent = cellContent;
		}

		@Override
		public void into(GridBagConstraints gridBagConstraints) {
			onGoingGridLayoutConstruction.doAdd(cellContent, gridBagConstraints);
		}
	}
}
